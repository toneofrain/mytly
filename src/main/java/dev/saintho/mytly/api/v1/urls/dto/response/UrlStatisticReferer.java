package dev.saintho.mytly.api.v1.urls.dto.response;

import dev.saintho.mytly.domain.entity.RefererEngagement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlStatisticReferer {
	private int total;
	private int direct;
	private int google;
	private int other;

	public static UrlStatisticReferer from(RefererEngagement refererEngagement) {
		UrlStatisticReferer stats = new UrlStatisticReferer();

		stats.direct = refererEngagement.getDirect();
		stats.google = refererEngagement.getGoogle();
		stats.other = refererEngagement.getOther();
		stats.total = stats.direct + stats.google + stats.other;

		return stats;
	}
}
