package dev.saintho.mytly.service;

import static dev.saintho.mytly.exception.ExceptionType.*;

import java.net.URI;
import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.api.v1.urls.dto.command.UrlDeleteByShortenedCommand;
import dev.saintho.mytly.api.v1.urls.dto.command.UrlShortCommand;
import dev.saintho.mytly.api.v1.urls.dto.query.UrlRedirectQuery;
import dev.saintho.mytly.domain.entity.Url;
import dev.saintho.mytly.event.dto.UrlCreateEvent;
import dev.saintho.mytly.event.dto.UrlRedirectEvent;
import dev.saintho.mytly.exception.MytlyException;
import dev.saintho.mytly.generator.url.UrlGenerator;
import dev.saintho.mytly.repository.jpa.url.UrlRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {
	private final UrlRepository urlRepository;
	private final UrlGenerator urlGenerator;
	private final ApplicationEventPublisher eventPublisher;

	public URI getRedirectUrl(UrlRedirectQuery query) {
		Url url = findVerifiedOneByShortened(query.getShortened());

		eventPublisher.publishEvent(
			UrlRedirectEvent.of(url, query.getReferer(), query.getRedirectDate()));

		return URI.create(url.getOriginal());
	}

	public Url shortUrl(UrlShortCommand command) {
		Optional<Url> urlOptional = urlRepository.findLatestOneByOriginal(command.getOriginal());

		return urlOptional
			.map(url -> reRequestUrl(url, command))
			.orElseGet(() -> createUrl(command));

	}
	public void deleteUrlByShortened(UrlDeleteByShortenedCommand command) {
		Url url = findVerifiedOneByShortened(command.getShortened());
		url.softDelete();
	}

	@Transactional(readOnly = true)
	public Url findVerifiedOneByShortened(String shortenend) {
		Optional<Url> urlOptional = urlRepository.findByShortened(shortenend);

		return urlOptional
			.orElseThrow(
				() -> new MytlyException(URL_NOT_FOUND, "URL_ENTITY_CORRESPONDING_SUCH_SHORTENED_URL_NOT_FOUND"));
	}

	private Url reRequestUrl(Url url, UrlShortCommand command) {
		if (!url.isAvailableAtTheTime(command.getRequestedAt())) {
			return createUrl(command);
		}

		if (command.getIsExpirable()) {
			url.updateExpirationOption(true, command.getExpireAt());
			return url;
		}

		url.setIsNotExpirable();
		return url;
	}

	private Url createUrl(UrlShortCommand command) {
		String shortened = generateUniqueUrl(command.getOriginal());

		Url url = getUrlOf(shortened, command);
		eventPublisher.publishEvent(new UrlCreateEvent(url));

		return urlRepository.save(url);
	}

	private String generateUniqueUrl(String original) {
		String generated = urlGenerator.generate(original);

		if (urlRepository.existsByShortened(generated)) {
			return urlGenerator.generate(original);
		}

		return generated;
	}

	private Url getUrlOf(String shortened, UrlShortCommand command) {
		if (command.getIsExpirable()) {
			return Url.builder()
				.shortened(shortened)
				.original(command.getOriginal())
				.isExpirable(true)
				.expireAt(command.getExpireAt())
				.build();
		}

		return Url.builder()
			.shortened(shortened)
			.original(command.getOriginal())
			.isExpirable(false)
			.build();
	}
}
