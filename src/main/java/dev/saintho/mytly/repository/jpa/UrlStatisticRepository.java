package dev.saintho.mytly.repository.jpa;

import java.time.LocalDate;
import java.util.List;

import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticResponse;

public interface UrlStatisticRepository {
	UrlStatisticResponse.UrlRefererStats findUrlRefererStatsByShortened(String shortened);

	List<UrlStatisticResponse.DailyStats> findDailyStatsForAWeekByShortened(String shortened, LocalDate lastDay);
}
