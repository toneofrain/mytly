package dev.saintho.mytly.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticResponse;
import dev.saintho.mytly.repository.jpa.UrlStatisticRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UrlStatisticService {
	private UrlStatisticRepository urlStatisticRepository;

	public UrlStatisticResponse getUrlStatisticForAWeekByShortened(String shortened, LocalDate lastDay) {
		UrlStatisticResponse.UrlRefererStats urlRefererStats =
			urlStatisticRepository.findUrlRefererStatsByShortened(shortened);
		List<UrlStatisticResponse.DailyStats> dailyStatsForAWeek =
			urlStatisticRepository.findDailyStatsForAWeekByShortened(shortened, lastDay);

		return UrlStatisticResponse.of(urlRefererStats, dailyStatsForAWeek);
	}
}
