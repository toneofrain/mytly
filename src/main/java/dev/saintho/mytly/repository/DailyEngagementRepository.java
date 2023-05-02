package dev.saintho.mytly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.saintho.mytly.entity.DailyEngagement;

public interface DailyEngagementRepository extends JpaRepository<DailyEngagement, Long> {
}
