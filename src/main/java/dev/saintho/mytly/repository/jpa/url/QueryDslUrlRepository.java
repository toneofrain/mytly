package dev.saintho.mytly.repository.jpa.url;

import java.util.Optional;

import dev.saintho.mytly.domain.entity.Url;

public interface QueryDslUrlRepository {
	/***
	 *
	 * @param original 원본 URL
	 * @return Optional을 리턴하여 값은 원본 URL을 갖는 URL의 레코드가 여러개 있을 경우 가장 최신의 것이 되며
	 * 			원본 URL을 갖는 레코드가 없을 경우엔 null이 된다.
	 */
	Optional<Url> findLatestOneByOriginal(String original);
}
