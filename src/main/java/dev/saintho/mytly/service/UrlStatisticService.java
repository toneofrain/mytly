package dev.saintho.mytly.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.api.v1.urls.dto.response.DailyStats;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlRefererStats;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticResponse;
import dev.saintho.mytly.repository.jpa.urlstatistic.UrlStatisticRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlStatisticService {
	private final UrlStatisticRepository urlStatisticRepository;

	public UrlStatisticResponse getUrlStatisticForAWeekByShortened(String shortened, LocalDate lastDay) {
		UrlRefererStats urlRefererStats =
			urlStatisticRepository.findUrlRefererStatsByShortened(shortened);
		List<DailyStats> dailyStatsForAWeek =
			urlStatisticRepository.findDailyStatsForAWeekByShortened(shortened, lastDay);

		return UrlStatisticResponse.of(urlRefererStats, dailyStatsForAWeek);
	}
}
