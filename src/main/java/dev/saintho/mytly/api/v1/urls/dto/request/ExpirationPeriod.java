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

	/**
	 *
	 * Json 역직렬화하여 ExpirationPeriod에 바인딩할 때 발생하는 HttpMessageNotReadalbeException을
	 * MethodArgumentNotValidException 스펙으로 다루기 위한 메소드
	 * null을 리턴할 수 있어 사용해서는 안 되고 추후 개선
	 */

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
