package dev.saintho.mytly.dto.request;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExpirationPeriod {
	ONE_MONTH(1),
	THREE_MONTHS(3),
	SIX_MONTHS(6),
	TWELVE_MONTHS(12);

	private final int months;

	public LocalDateTime getExpireAt() {
		return LocalDateTime
			.now()
			.plusMonths(months);
	}
}
