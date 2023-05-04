package dev.saintho.mytly.api.v1.urls.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlStatisticResponse {
	private UrlStatisticUrlInfo urlInfo;
	private UrlStatisticReferer urlStatisticReferer;
	private List<UrlStatisticDaily> urlStatisticDailyForTheLastWeek;

	public static UrlStatisticResponse of(
		UrlStatisticUrlInfo urlInfo,
		UrlStatisticReferer urlStatisticReferer,
		List<UrlStatisticDaily> urlStatisticDailies) {

		UrlStatisticResponse response = new UrlStatisticResponse();

		response.urlInfo = urlInfo;
		response.urlStatisticReferer = urlStatisticReferer;
		response.urlStatisticDailyForTheLastWeek = urlStatisticDailies;

		return response;
	}
}
