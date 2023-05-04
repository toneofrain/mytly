package dev.saintho.mytly.repository.jpa.dailyengagement;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saintho.mytly.domain.entity.DailyEngagement;
import dev.saintho.mytly.domain.entity.Url;

public interface DailyEngagementRepository extends JpaRepository<DailyEngagement, Long> {
	Optional<DailyEngagement> findByUrlAndDate(Url url, LocalDate engageDate);
}
