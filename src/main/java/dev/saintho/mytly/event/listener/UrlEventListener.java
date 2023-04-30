package dev.saintho.mytly.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.saintho.mytly.event.dto.UrlCreateEvent;
import dev.saintho.mytly.service.RefererEngagementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
public class UrlEventListener {
	private final RefererEngagementService refererEngagementService;

	@EventListener
	public void createRefererEngagement(UrlCreateEvent event) {
		refererEngagementService.createRefererEngagement(event.getUrl());
	}
}
