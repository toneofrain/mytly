package dev.saintho.mytly.repository.jpa.urlstatistic;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticDaily;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticReferer;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticUrlInfo;

public interface UrlStatisticRepository {
	/***
	 *
	 * @param shortenend 단축 URL
	 * @return URL 정보를 갖는 UrlStatisticUrlInfo의 Optional을 리턴하며
	 * 			입력받은 shortened를 갖는 URL 레코드가 존재하지 않을 경우 값은 null이 된다.
	 */
	Optional<UrlStatisticUrlInfo> findUrlInfoByShortenend(String shortenend);

	/***
	 *
	 * @param shortened 단축 URL
	 * @return 단축 URL의 리퍼러별 조회수 통계를 갖는 UrlStatisticRefer 클래스
	 */
	UrlStatisticReferer findUrlRefererStatsByShortened(String shortened);

	/***
	 *
	 * @param shortened 단축 URL
	 * @param lastDay 조회할 일주일간 일별 조회수의 마지막 날
	 * @return lastDay까지 일주일간의 단축 URL 일별 조회수를 갖는 UrlStatisticDaily의 리스트.
	 */
	List<UrlStatisticDaily> findDailyStatsForAWeekByShortened(String shortened, LocalDate lastDay);
}
