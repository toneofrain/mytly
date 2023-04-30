package dev.saintho.mytly.controller;

import static org.springframework.http.HttpStatus.*;

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

import dev.saintho.mytly.dto.command.UrlDeleteCommand;
import dev.saintho.mytly.dto.command.UrlShortCommand;
import dev.saintho.mytly.dto.request.UrlPostRequest;
import dev.saintho.mytly.dto.response.UrlPostResponse;
import dev.saintho.mytly.entity.Url;
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

	@GetMapping("/{shortened}")
	public ResponseEntity<String> getOriginalUrl
		(@PathVariable String shortened, @RequestHeader("Referer") String referer) {
		Url url = urlService.findVerifiedOneByShortened(shortened);

		return ResponseEntity
			.status(MOVED_PERMANENTLY)
			.body(url.getOriginal());
	}

	@DeleteMapping("/{shortened}")
	public ResponseEntity<Void> deleteUrl(@PathVariable String shortened) {
		urlService.deleteUrl(
			UrlDeleteCommand.from(shortened));

		return ResponseEntity
			.noContent()
			.build();
	}
}
