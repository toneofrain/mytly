package dev.saintho.mytly.api.v1.urls.dto.response;

import java.time.LocalDateTime;

import dev.saintho.mytly.domain.entity.RefererEngagement;
import dev.saintho.mytly.domain.entity.Url;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlRefererStats {
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
