package dev.saintho.mytly.generator.source;

import java.math.BigInteger;
import java.util.Random;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomIntSourceGenerator implements SourceGenerator {
	private final Random random;
	private final int lowerLength;
	private final int upperLength;
	@Override
	public BigInteger generate(int base) {
		int min = (int) Math.pow(base, lowerLength);
		int max = (int) Math.pow(base, upperLength + 1) - 1;

		int generated = min + random.nextInt(max - min + 1);

		return BigInteger.valueOf(generated);
	}
}
