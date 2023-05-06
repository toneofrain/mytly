package dev.saintho.mytly.api.v1.urls.dto.request;

import javax.validation.constraints.NotNull;

import dev.saintho.mytly.validation.annotation.ValidOriginalUrl;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UrlPostRequest {
	@Schema(example = "https://thoughtful-arch-8c2.notion.site/_URL-c7f57136e2744ff8872eaf973069bdfe")
	@ValidOriginalUrl
	private String original;
	@Schema(example = "true")
	@NotNull(message = "IsExpirable must not be null.")
	private Boolean isExpirable;
	@Schema(example = "ONE_MONTH")
	@NotNull(message = "ExpirationPeriods must be one of [NO_EXPIRATION, ONE_MONTH, THREE_MONTHS, SIX_MONTHS, TWELVE_MONTHS]")
	private ExpirationPeriod expirationPeriod;
}
