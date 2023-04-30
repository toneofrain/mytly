package dev.saintho.mytly.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.entity.RefereEngagement;
import dev.saintho.mytly.entity.Url;
import dev.saintho.mytly.repository.RefererEngagementRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RefererEngagementService {
	private final RefererEngagementRepository refererEngagementRepository;

	public void createRefererEngagement(Url url) {
		RefereEngagement refereEngagement = RefereEngagement.from(url);
		refererEngagementRepository.save(refereEngagement);
	}
}
