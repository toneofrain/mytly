package dev.saintho.mytly.repository.jpa.refererengagement;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saintho.mytly.domain.entity.RefererEngagement;
import dev.saintho.mytly.domain.entity.Url;

public interface RefererEngagementRepository extends JpaRepository<RefererEngagement, Long> {
	Optional<RefererEngagement> findByUrl(Url url);
}
