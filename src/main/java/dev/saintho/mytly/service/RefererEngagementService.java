package dev.saintho.mytly.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.api.v1.urls.dto.query.Referer;
import dev.saintho.mytly.domain.entity.RefererEngagement;
import dev.saintho.mytly.domain.entity.Url;
import dev.saintho.mytly.event.dto.UrlRedirectEvent;
import dev.saintho.mytly.repository.jpa.refererengagement.RefererEngagementRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RefererEngagementService {
	private final RefererEngagementRepository refererEngagementRepository;

	public RefererEngagement createRefererEngagement(Url url) {
		RefererEngagement refererEngagement = RefererEngagement.from(url);

		return refererEngagementRepository.save(refererEngagement);
	}

	public void updateEngageCountByRedirecting(UrlRedirectEvent event) {
		RefererEngagement refererEngagement = findVerifiedOneByUrl(event.getUrl());
		Referer referer = event.getReferer();

		referer.engageAtTheTime(refererEngagement, event.getRedirectDateTime());
	}

	@Transactional(readOnly = true)
	public RefererEngagement findVerifiedOneByUrl(Url url) {
		Optional<RefererEngagement> refererEngagementOptional = refererEngagementRepository.findByUrl(url);

		return refererEngagementOptional
			.orElseGet(() -> createRefererEngagement(url));
	}
}
