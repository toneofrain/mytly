package dev.saintho.mytly.repository.jpa.url;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saintho.mytly.domain.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long>, QueryDslUrlRepository {
	Optional<Url> findByShortened(String shortenend);

	boolean existsByShortened(String shortened);
}
