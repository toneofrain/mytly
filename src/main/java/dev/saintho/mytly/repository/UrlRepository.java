package dev.saintho.mytly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saintho.mytly.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
	boolean existsByShortened(String shortened);

	boolean existsByOriginal(String original);
}
