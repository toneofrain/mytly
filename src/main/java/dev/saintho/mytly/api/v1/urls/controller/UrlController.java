package dev.saintho.mytly.api.v1.urls.controller;

import static org.springframework.http.HttpStatus.*;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.saintho.mytly.api.v1.urls.dto.command.UrlDeleteByShortenedCommand;
import dev.saintho.mytly.api.v1.urls.dto.command.UrlShortCommand;
import dev.saintho.mytly.api.v1.urls.dto.query.Referer;
import dev.saintho.mytly.api.v1.urls.dto.query.UrlRedirectQuery;
import dev.saintho.mytly.api.v1.urls.dto.request.UrlPostRequest;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlPostResponse;
import dev.saintho.mytly.api.v1.urls.dto.response.UrlRedirectResponse;
import dev.saintho.mytly.domain.entity.Url;
import dev.saintho.mytly.service.UrlService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlController {
	private final UrlService urlService;

	@PostMapping
	public ResponseEntity<UrlPostResponse> shortUrl(@RequestBody UrlPostRequest request) {
		Url url = urlService.shortUrl(UrlShortCommand.from(request));

		UrlPostResponse response = UrlPostResponse.from(url);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(response);
	}

	@GetMapping("/{shortened:[A-Za-z0-9]+}")
	public ResponseEntity<UrlRedirectResponse> getRedirectUrl
		(@PathVariable String shortened, @RequestHeader(value = "Referer", required = false) String refererHeader) {
		Referer referer = Referer.from(refererHeader);

		URI redirectUrl = urlService.getRedirectUrl(
			UrlRedirectQuery.of(shortened, referer, LocalDate.now()));

		UrlRedirectResponse response = UrlRedirectResponse.from(redirectUrl);

		return ResponseEntity
			.status(MOVED_PERMANENTLY)
			.body(response);
	}

	@GetMapping("/{shortenedFollowedByPlusSign:[A-Za-z0-9]+[+]$}")
	public ResponseEntity<String> getUrlStatistics
		(@PathVariable String shortenedFollowedByPlusSign) {
		return ResponseEntity
			.ok("good");
	}

	@DeleteMapping("/{shortened}")
	public ResponseEntity<Void> deleteUrl(@PathVariable String shortened) {
		urlService.deleteUrlByShortened(
			UrlDeleteByShortenedCommand.from(shortened));

		return ResponseEntity
			.noContent()
			.build();
	}
}
