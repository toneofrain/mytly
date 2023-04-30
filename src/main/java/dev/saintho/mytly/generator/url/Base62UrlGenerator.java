package dev.saintho.mytly.generator.url;

import static dev.saintho.mytly.generator.url.EncodingScheme.*;

import java.math.BigInteger;

import dev.saintho.mytly.generator.source.SourceGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Base62UrlGenerator implements UrlGenerator {
	private final SourceGenerator sourceGenerator;
	private final EncodingScheme scheme = BASE62;
	@Override
	public String generate() {
		long source = sourceGenerator.generate(scheme.getBase());

		return encodeToBase62(source);
	}

	private String encodeToBase62(long source) {
		final int base = scheme.getBase();
		final char[] chars = scheme.getCharacters().toCharArray();

		StringBuffer sb = new StringBuffer();

		do {
			int digit = (int) source % base;
			sb.append(chars[digit]);
			source /= base;
		} while (source > 0);

		return sb.toString();
	}
}
