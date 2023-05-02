package dev.saintho.mytly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saintho.mytly.domain.entity.DailyEngagement;

public interface DailyEngagementRepository extends JpaRepository<DailyEngagement, Long> {
}
