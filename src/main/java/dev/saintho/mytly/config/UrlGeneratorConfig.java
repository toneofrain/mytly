package dev.saintho.mytly.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.saintho.mytly.generator.source.RandomIntSourceGenerator;
import dev.saintho.mytly.generator.source.SourceGenerator;

@Configuration
public class UrlGeneratorConfig {
	private final int URL_LENGTH_LOWER_LIMIT = 6;
	private final int URL_LENGTH_UPPER_LIMIT = 7;

	@Bean
	public SourceGenerator sourceGenerator() throws NoSuchAlgorithmException {
		return new RandomIntSourceGenerator(
			SecureRandom.getInstance("SHA1PRNG"),
			URL_LENGTH_LOWER_LIMIT,
			URL_LENGTH_UPPER_LIMIT);
	}
}
