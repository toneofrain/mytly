package dev.saintho.mytly.service;


import static dev.saintho.mytly.exception.ExceptionType.*;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.dto.command.UrlDeleteCommand;
import dev.saintho.mytly.dto.command.UrlShortCommand;
import dev.saintho.mytly.entity.Url;
import dev.saintho.mytly.event.dto.UrlCreateEvent;
import dev.saintho.mytly.exception.MytlyException;
import dev.saintho.mytly.generator.url.UrlGenerator;
import dev.saintho.mytly.repository.UrlRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {
	private final UrlRepository urlRepository;
	private final UrlGenerator urlGenerator;
	private final ApplicationEventPublisher eventPublisher;

	public Url shortUrl(UrlShortCommand command) {
		Optional<Url> urlOptional = urlRepository.findByOriginal(command.getOriginal());

		return urlOptional
			.map(url -> reRequestUrl(url, command))
			.orElseGet(() -> createUrl(command));

	}
	public void deleteUrl(UrlDeleteCommand command) {
		Url url = findVerifiedOneByShortened(command.getShortened());
		urlRepository.delete(url);
	}

	@Transactional(readOnly = true)
	public Url findVerifiedOneByShortened(String shortenend) {
		Optional<Url> urlOptional = urlRepository.findByShortened(shortenend);

		return urlOptional
			.orElseThrow(
				() -> new MytlyException(URL_NOT_FOUND, "URL_ENTITY_CORRESPONDING_SUCH_SHORTENED_URL_NOT_FOUND"));
	}

	private Url reRequestUrl(Url url, UrlShortCommand command) {
		if (command.getIsExpirable()) {
			url.updateExpirationOption(true, command.getExpirationPeriod());
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
				.expireAt(command.getExpirationPeriod().getExpireAt())
				.build();
		}

		return Url.builder()
			.shortened(shortened)
			.original(command.getOriginal())
			.isExpirable(false)
			.build();
	}
}
