package dev.saintho.mytly.repository.jpa.url;

import java.util.Optional;

import dev.saintho.mytly.domain.entity.Url;

public interface QueryDslUrlRepository {
	Optional<Url> findLatestOneByOriginal(String original);
}
