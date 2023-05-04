package dev.saintho.mytly.repository.jpa.url;

import static dev.saintho.mytly.domain.entity.QUrl.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import dev.saintho.mytly.domain.entity.Url;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QueryDslUrlRepositoryImpl implements QueryDslUrlRepository {
	private final JPAQueryFactory queryFactory;
	@Override
	public Optional<Url> findLatestOneByOriginal(String original) {

		return Optional.ofNullable(
			queryFactory
				.select(url)
				.from(url)
				.where(url.original.eq(original))
				.orderBy(url.id.desc())
				.limit(1)
				.fetchOne());
	}
}
