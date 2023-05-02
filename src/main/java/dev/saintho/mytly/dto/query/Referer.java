package dev.saintho.mytly.dto.query;

import java.net.URI;

import com.google.common.net.InternetDomainName;

public enum Referer {
	DIRECT,
	OTHER,
	GOOGLE;

	public static Referer fromRefererHeader(String refererHeader) {
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
