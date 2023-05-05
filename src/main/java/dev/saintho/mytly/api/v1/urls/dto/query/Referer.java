package dev.saintho.mytly.api.v1.urls.dto.query;

import java.net.URI;
import java.time.LocalDateTime;

import com.google.common.net.InternetDomainName;

import dev.saintho.mytly.domain.entity.RefererEngagement;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum Referer {
	DIRECT{
		@Override
		public void engageAtTheTime(RefererEngagement refererEngagement, LocalDateTime dateTime) {
			refererEngagement.getUrl().checkAvailalbleAtTheTime(dateTime);
			refererEngagement.engageByDirect();
		}
	},
	GOOGLE{
		@Override
		public void engageAtTheTime(RefererEngagement refererEngagement, LocalDateTime dateTime) {
			refererEngagement.getUrl().checkAvailalbleAtTheTime(dateTime);
			refererEngagement.engageFromGoogle();
		}
	},
	OTHER{
		@Override
		public void engageAtTheTime(RefererEngagement refererEngagement, LocalDateTime dateTime) {
			refererEngagement.getUrl().checkAvailalbleAtTheTime(dateTime);
			refererEngagement.engageFromOthers();
		}
	};

	public abstract void engageAtTheTime(RefererEngagement refererEngagement, LocalDateTime dateTime);

	public static Referer from(String refererHeader) {
		if (refererHeader == null) {
			return DIRECT;
		}

		String domain = parseDomainFromRefererHeader(refererHeader);

		if (domain.equals("google.com")) {
			return GOOGLE;
		}

		return OTHER;
	}

	private static String parseDomainFromRefererHeader(String refererHeader) {
		String host = URI
			.create(refererHeader)
			.getHost();

		return InternetDomainName
			.from(host)
			.topPrivateDomain()
			.toString();
	}
}
