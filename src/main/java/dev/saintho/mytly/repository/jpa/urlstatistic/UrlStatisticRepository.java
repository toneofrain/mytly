package dev.saintho.mytly.repository.jpa.urlstatistic;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticDaily;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticReferer;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticUrlInfo;

public interface UrlStatisticRepository {
	Optional<UrlStatisticUrlInfo> findUrlInfoByShortenend(String shortenend);

	UrlStatisticReferer findUrlRefererStatsByShortened(String shortened);

	List<UrlStatisticDaily> findDailyStatsForAWeekByShortened(String shortened, LocalDate lastDay);
}
