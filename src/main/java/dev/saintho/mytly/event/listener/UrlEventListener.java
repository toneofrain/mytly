package dev.saintho.mytly.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.saintho.mytly.event.dto.UrlCreateEvent;
import dev.saintho.mytly.event.dto.UrlRedirectEvent;
import dev.saintho.mytly.service.DailyEngagementService;
import dev.saintho.mytly.service.RefererEngagementService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UrlEventListener {
	private final RefererEngagementService refererEngagementService;
	private final DailyEngagementService dailyEngagementService;

	@EventListener
	public void createRefererEngagement(UrlCreateEvent event) {
		refererEngagementService.createRefererEngagement(event.getUrl());
	}

	@EventListener
	public void updateEngagement(UrlRedirectEvent event) {
		refererEngagementService.updateEngageCountByRedirecting(event);
		dailyEngagementService.updateEngageCountByRedirecting(event);
	}
}
