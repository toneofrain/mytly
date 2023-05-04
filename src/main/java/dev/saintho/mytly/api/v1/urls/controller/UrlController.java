package dev.saintho.mytly.api.v1.urls.controller;

import static org.springframework.http.HttpStatus.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import dev.saintho.mytly.api.v1.urls.dto.response.UrlStatisticResponse;
import dev.saintho.mytly.api.v1.urls.dto.result.UrlShortResult;
import dev.saintho.mytly.service.UrlService;
import dev.saintho.mytly.service.UrlStatisticService;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {
	private final UrlService urlService;
	private final UrlStatisticService urlStatisticService;
	private final String prefix;

	public UrlController(
		UrlService urlService,
		UrlStatisticService urlStatisticService,
		@Value("${host.name}") String hostname,
		@Value("${host.path}") String path) {

		this.urlService = urlService;
		this.urlStatisticService = urlStatisticService;
		this.prefix = hostname + path;
	}

	@PostMapping
	public ResponseEntity<UrlPostResponse> shortUrl(@Validated @RequestBody UrlPostRequest request) {
		UrlShortResult result = urlService.shortUrl(
			UrlShortCommand.of(request, LocalDateTime.now()));

		UrlPostResponse response = UrlPostResponse.of(result, prefix);

		return ResponseEntity
			.status(CREATED)
			.body(response);
	}

	@GetMapping("/{shortened:[A-Za-z0-9]+}")
	public ResponseEntity<Void> getRedirectUrl
		(@PathVariable String shortened, @RequestHeader(value = "Referer", required = false) String refererHeader) {
		Referer referer = Referer.from(refererHeader);

		URI redirectUrl = urlService.getRedirectUrl(
			UrlRedirectQuery.of(shortened, referer, LocalDate.now()));

		HttpHeaders headers = setRedirectUrlToLocatinHeader(redirectUrl);

		return ResponseEntity
			.status(MOVED_PERMANENTLY)
			.headers(headers)
			.build();
	}

	@GetMapping("/{shortenedFollowedByPlusSign:[A-Za-z0-9]+[+]$}")
	public ResponseEntity<UrlStatisticResponse> getUrlStatistics (@PathVariable String shortenedFollowedByPlusSign) {
		String shortened = shortenedFollowedByPlusSign.substring(0, shortenedFollowedByPlusSign.length() - 1);

		UrlStatisticResponse response =
			urlStatisticService.getUrlStatisticForAWeekByShortened(shortened, LocalDate.now());

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{shortened:[A-Za-z0-9]+}")
	public ResponseEntity<Void> deleteUrl(@PathVariable String shortened) {
		urlService.deleteUrlByShortened(
			UrlDeleteByShortenedCommand.from(shortened));

		return ResponseEntity
			.noContent()
			.build();
	}

	private HttpHeaders setRedirectUrlToLocatinHeader(URI redirectUrl) {
		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(redirectUrl);

		return headers;
	}
}
