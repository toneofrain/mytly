package dev.saintho.mytly.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.saintho.mytly.dto.request.UrlPostRequest;
import dev.saintho.mytly.dto.response.UrlPostResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlController {

	@PostMapping
	public ResponseEntity<UrlPostResponse> shortUrl(@RequestBody UrlPostRequest request) {

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(null);
	}
}
