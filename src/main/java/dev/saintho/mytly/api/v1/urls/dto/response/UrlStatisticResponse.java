package dev.saintho.mytly.api.v1.urls.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import dev.saintho.mytly.domain.entity.RefererEngagement;
import dev.saintho.mytly.domain.entity.Url;
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

	@Getter
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class UrlRefererStats {
		private String shortened;
		private String original;
		private Boolean isExpirable;
		private LocalDateTime expireAt;
		private int total;
		private int direct;
		private int google;
		private int other;

		public static UrlRefererStats from(RefererEngagement refererEngagement) {
			UrlRefererStats stats = new UrlRefererStats();
			Url url = refererEngagement.getUrl();

			stats.shortened = url.getShortened();
			stats.original = url.getOriginal();
			stats.isExpirable = url.isExpirable();
			stats.expireAt = url.getExpireAt();
			stats.direct = refererEngagement.getDirect();
			stats.google = refererEngagement.getGoogle();
			stats.other = refererEngagement.getOther();
			stats.total = stats.direct + stats.google + stats.other;

			return stats;
		}
	}

	@Getter
	@NoArgsConstructor
	public static class DailyStats {
		private LocalDate date;
		private int count;
	}
}
