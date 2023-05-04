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
import org.springframework.web.bind.annotation.ResponseStatus;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


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

	@Tag(name = "URL")
	@Operation(summary = "기존 URL과 연결될 단축 URL을 생성한다.")
	@ApiResponse(
		description = "단축 URL 생성 성공",
		responseCode = "201",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = UrlPostResponse.class))
	)
	@ResponseStatus(CREATED)
	@PostMapping
	public ResponseEntity<UrlPostResponse> shortUrl(@Validated @RequestBody UrlPostRequest request) {
		UrlShortResult result = urlService.shortUrl(
			UrlShortCommand.of(request, LocalDateTime.now()));

		UrlPostResponse response = UrlPostResponse.of(result, prefix);

		return ResponseEntity
			.status(CREATED)
			.body(response);
	}

	@Tag(name = "URL")
	@Operation(summary = "단축 URL과 연결된 기존 URL로 리다이렉트한다.")
	@ApiResponses(value = {
		@ApiResponse(
			description = "리다이렉트 URL 조회 성공",
			responseCode = "301",
			headers = @Header(name = "Location", description = "Redirect Url")
		),
		@ApiResponse(description = "존재하지 않는 단축 URL", responseCode = "404", content = @Content(schema = @Schema()))
	})
	@ResponseStatus(MOVED_PERMANENTLY)
	@GetMapping("/{shortened:[A-Za-z0-9]+}")
	public ResponseEntity<Void> getRedirectUrl
		(@PathVariable String shortened, @RequestHeader(value = "Referer", required = false) String refererHeader) {
		Referer referer = Referer.from(refererHeader);

		URI redirectUrl = urlService.getRedirectUrl(
			UrlRedirectQuery.of(shortened, referer, LocalDateTime.now()));

		HttpHeaders headers = setRedirectUrlToLocatinHeader(redirectUrl);

		return ResponseEntity
			.status(MOVED_PERMANENTLY)
			.headers(headers)
			.build();
	}

	@Tag(name = "URL")
	@Operation(summary = "단축 URL 정보와 리퍼러별/일주일간 일일 조회수를 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			description = "단축 URL 통계 조회 성공",
			responseCode = "200",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = UrlStatisticResponse.class))
		),
		@ApiResponse(description = "존재하지 않는 단축 URL", responseCode = "404", content = @Content(schema = @Schema()))
	})
	@GetMapping("/{shortenedFollowedByPlusSign:[A-Za-z0-9]+[+]$}")
	public ResponseEntity<UrlStatisticResponse> getUrlStatistics (@PathVariable String shortenedFollowedByPlusSign) {
		String shortened = shortenedFollowedByPlusSign.substring(0, shortenedFollowedByPlusSign.length() - 1);

		UrlStatisticResponse response =
			urlStatisticService.getUrlStatisticForAWeekByShortened(shortened, LocalDate.now());

		return ResponseEntity.ok(response);
	}

	@Tag(name = "URL")
	@Operation(summary = "리다이렉트와 통계 조회가 가능하지 않도록 단축 URL을 삭제한다.")
	@ApiResponses(value = {
		@ApiResponse(description = "단축 URL 삭제 성공", responseCode = "204"),
		@ApiResponse(description = "존재하지 않는 단축 URL", responseCode = "404")
	})
	@ResponseStatus(NO_CONTENT)
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
