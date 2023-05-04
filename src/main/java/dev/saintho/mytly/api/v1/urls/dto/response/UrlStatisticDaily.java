package dev.saintho.mytly.api.v1.urls.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UrlStatisticDaily {
	private LocalDate date;
	private int count;
}
