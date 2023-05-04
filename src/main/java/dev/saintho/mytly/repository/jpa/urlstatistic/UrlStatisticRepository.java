package dev.saintho.mytly.repository.jpa.urlstatistic;

import java.time.LocalDate;
import java.util.List;

import dev.saintho.mytly.api.v1.urls.dto.response.DailyStats;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlRefererStats;

public interface UrlStatisticRepository {
	UrlRefererStats findUrlRefererStatsByShortened(String shortened);

	List<DailyStats> findDailyStatsForAWeekByShortened(String shortened, LocalDate lastDay);
}
