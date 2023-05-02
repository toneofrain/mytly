package dev.saintho.mytly.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saintho.mytly.domain.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
	Optional<Url> findByShortened(String shortenend);

	Optional<Url> findByOriginal(String original);

	boolean existsByShortened(String shortened);

	boolean existsByOriginal(String original);
}