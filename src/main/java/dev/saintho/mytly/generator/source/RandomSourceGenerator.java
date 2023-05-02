package dev.saintho.mytly.generator.source;

import java.util.Random;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomSourceGenerator implements SourceGenerator {
	private final Random random;
	private final int lowerLength;
	private final int upperLength;
	@Override
	public long generate(int base, String original) {
		long min = (long) Math.pow(base, lowerLength);
		long max = (long) Math.pow(base, upperLength + 1) - 1;

		return min +
			random
			.longs(min, max + 1)
			.limit(1)
			.findFirst()
			.getAsLong();
	}
}
