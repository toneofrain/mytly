package dev.saintho.mytly.service;

import static dev.saintho.mytly.domain.entity.UrlStatus.*;
import static dev.saintho.mytly.exception.ExceptionType.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticDaily;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticReferer;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticResponse;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticUrlInfo;
import dev.saintho.mytly.exception.MytlyException;
import dev.saintho.mytly.repository.jpa.urlstatistic.UrlStatisticRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlStatisticService {
	private final UrlStatisticRepository urlStatisticRepository;

	public UrlStatisticResponse getUrlStatisticForAWeekByShortened(String shortened, LocalDate lastDay) {
		UrlStatisticUrlInfo urlInfo = findVerifiedUrlInfoByShortened(shortened);

		checkUrlStatisticAvailable(urlInfo);

		UrlStatisticReferer urlStatisticReferer =
			urlStatisticRepository.findUrlRefererStatsByShortened(shortened);

		List<UrlStatisticDaily> urlStatisticDailyForAWeek =
			urlStatisticRepository.findDailyStatsForAWeekByShortened(shortened, lastDay);

		return UrlStatisticResponse.of(urlInfo, urlStatisticReferer, urlStatisticDailyForAWeek);
	}

	private UrlStatisticUrlInfo findVerifiedUrlInfoByShortened(String shortened) {
		Optional<UrlStatisticUrlInfo> urlInfoOptional = urlStatisticRepository.findUrlInfoByShortenend(shortened);

		return urlInfoOptional
			.orElseThrow(() -> new MytlyException(URL_NOT_FOUND));
	}

	private void checkUrlStatisticAvailable(UrlStatisticUrlInfo urlInfo) {
		if (urlInfo.getUrlStatus() == DELETED) {
			throw new MytlyException(URL_STATISTIC_NOT_AVAILABLE,
				"Url Statistic is not availalbe becuase Url is deleted");
		}
	}
}
