package dev.saintho.mytly.api.v1.urls.dto.response;

import java.time.LocalDateTime;

import dev.saintho.mytly.domain.entity.UrlStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UrlStatisticUrlInfo {
	private String shortened;
	private String original;
	private UrlStatus urlStatus;
	private LocalDateTime createdAt;
	private Boolean isExpirable;
	private LocalDateTime expireAt;
}
