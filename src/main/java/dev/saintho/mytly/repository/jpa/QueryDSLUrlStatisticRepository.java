package dev.saintho.mytly.repository.jpa;

import static dev.saintho.mytly.domain.entity.QDailyEngagement.*;
import static dev.saintho.mytly.domain.entity.QRefererEngagement.*;
import static dev.saintho.mytly.domain.entity.QUrl.*;
import static dev.saintho.mytly.exception.ExceptionType.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import dev.saintho.mytly.api.v1.urls.dto.response.DailyStats;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlRefererStats;
import dev.saintho.mytly.domain.entity.RefererEngagement;
import dev.saintho.mytly.domain.entity.Url;
import dev.saintho.mytly.exception.MytlyException;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryDSLUrlStatisticRepository implements UrlStatisticRepository {
	private final EntityManager entityManager;
	private final JPAQueryFactory queryFactory;

	@Transactional
	@Override
	public UrlRefererStats findUrlRefererStatsByShortened(String shortened) {
		RefererEngagement found =
			findRefererEngagementByShortened(shortened)
			.orElseGet(() -> createRefererEngagementFromShortened(shortened));

		return UrlRefererStats.from(found);
	}

	@Override
	public List<DailyStats> findDailyStatsForAWeekByShortened(String shortened, LocalDate lastDay) {

		return queryFactory
			.select(Projections.fields(DailyStats.class,
				dailyEngagement.date,
				dailyEngagement.count
				))
			.from(dailyEngagement)
			.where(
				dailyEngagement.url.shortened.eq(shortened),
				dailyEngagement.date.between(lastDay.minusDays(6), lastDay))
			.fetch();
	}

	private Optional<RefererEngagement> findRefererEngagementByShortened(String shortened) {
		return Optional.ofNullable(queryFactory
			.select(refererEngagement)
			.from(refererEngagement)
			.where(refererEngagement.url.shortened.eq(shortened))
			.fetchOne());
	}

	private RefererEngagement createRefererEngagementFromShortened(String shortened) {
		Url foundUrl = findVerifiedUrlByShortenend(shortened);
		RefererEngagement created = RefererEngagement.from(foundUrl);

		entityManager.persist(created);

		return created;
	}

	private Url findVerifiedUrlByShortenend(String shortened) {
		Optional<Url> urlOptional =
			Optional.ofNullable(queryFactory
				.select(url)
				.from(url)
				.where(url.shortened.eq(shortened))
				.fetchOne());

		return urlOptional
			.orElseThrow(
				() -> new MytlyException(URL_NOT_FOUND, "URL_ENTITY_CORRESPONDING_SUCH_SHORTENED_URL_NOT_FOUND"));
	}
}
