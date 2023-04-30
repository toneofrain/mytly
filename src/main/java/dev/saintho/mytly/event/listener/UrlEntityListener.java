package dev.saintho.mytly.event.listener;

import javax.persistence.PostPersist;


import dev.saintho.mytly.entity.Url;
import dev.saintho.mytly.service.RefererEngagementService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UrlEntityListener {
	private RefererEngagementService refererEngagementService;

	@PostPersist
	public void postPersist(Url url) {
		refererEngagementService.createRefererEngagement(url);
	}
}
