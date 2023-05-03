package dev.saintho.mytly.api.v1.urls.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlStatisticResponse {
	private UrlRefererStats urlRefererStats;
	private List<DailyStats> dailyStatsForTheLastWeek;

	public static UrlStatisticResponse of(UrlRefererStats urlRefererStats, List<DailyStats> dailyStatses) {
		UrlStatisticResponse response = new UrlStatisticResponse();

		response.urlRefererStats = urlRefererStats;
		response.dailyStatsForTheLastWeek = dailyStatses;

		return response;
	}
}
