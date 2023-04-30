package dev.saintho.mytly.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.dto.command.UrlCreateCommand;
import dev.saintho.mytly.entity.Url;
import dev.saintho.mytly.generator.url.UrlGenerator;
import dev.saintho.mytly.repository.UrlRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlService {
	private final UrlRepository urlRepository;
	private final UrlGenerator urlGenerator;

	public Url createUrl(UrlCreateCommand command) {
		String shortened = generateUniqueUrl(command.getOriginal());

		Url.UrlBuilder builder = Url.builder()
			.shortened(shortened)
			.original(command.getOriginal())
			.isExpirable(command.getIsExpirable());

		Optional.ofNullable(command.getExpirationPeriod())
			.ifPresent(expirationPeriod ->
				builder.expireAt(expirationPeriod.getExpireAt()));

		Url url = builder.build();

		return urlRepository.save(url);
	}

	private String generateUniqueUrl(String original) {
		String generated = urlGenerator.generate(original);

		if (urlRepository.existsByShortened(generated)) {
			return urlGenerator.generate(original);
		}

		return generated;
	}
}
