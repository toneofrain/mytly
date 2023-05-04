package dev.saintho.mytly.api.v1.urls.dto.request;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExpirationPeriod {
	ONE_MONTH(Period.ofMonths(1)),
	THREE_MONTHS(Period.ofMonths(3)),
	SIX_MONTHS(Period.ofMonths(6)),
	TWELVE_MONTHS(Period.ofMonths(12));

	private final Period months;

	public LocalDateTime getExpireAt(LocalDateTime startDateTime) {
		return startDateTime
			.plus(months);
	}

	@JsonCreator
	public static ExpirationPeriod createForDeserializing(String name) {
		if (name.isEmpty()) {
			return null;
		}

		return Stream.of(ExpirationPeriod.values())
			.filter(value -> value.name().equals(name))
			.findFirst()
			.orElse(null);
	}
}
