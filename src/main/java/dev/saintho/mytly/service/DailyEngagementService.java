package dev.saintho.mytly.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.saintho.mytly.domain.entity.DailyEngagement;
import dev.saintho.mytly.domain.entity.Url;
import dev.saintho.mytly.event.dto.UrlRedirectEvent;
import dev.saintho.mytly.repository.jpa.dailyengagement.DailyEngagementRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyEngagementService {
	private final DailyEngagementRepository dailyEngagementRepository;

	public void updateEngageCountByRedirecting(UrlRedirectEvent event) {
		Url url = event.getUrl();
		LocalDate redirectDate = event.getRedirectDateTime().toLocalDate();

		Optional<DailyEngagement> dailyEngagementOptional =
			dailyEngagementRepository.findByUrlAndDate(url, redirectDate);

		dailyEngagementOptional
			.ifPresentOrElse(
				DailyEngagement::engage,
				() -> createDailyEngagement(url, redirectDate));
	}

	public void createDailyEngagement(Url url, LocalDate engageDate) {
		DailyEngagement dailyEngagement = DailyEngagement.of(url, engageDate);

		dailyEngagementRepository.save(dailyEngagement);
	}
}
