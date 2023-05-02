package dev.saintho.mytly.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.domain.entity.RefererEngagement;
import dev.saintho.mytly.domain.entity.Url;
import dev.saintho.mytly.repository.RefererEngagementRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RefererEngagementService {
	private final RefererEngagementRepository refererEngagementRepository;

	public void createRefererEngagement(Url url) {
		RefererEngagement refererEngagement = RefererEngagement.from(url);
		refererEngagementRepository.save(refererEngagement);
	}
}
