package dev.saintho.mytly.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyEngagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "engage_date", nullable = false)
	private LocalDate date;
	@Column(name = "engage_count", nullable = false)
	private int count = 1;

	@ManyToOne
	@JoinColumn(name = "url_id")
	private Url url;

	public static DailyEngagement of(Url url, LocalDate engageDate) {
		DailyEngagement dailyEngagement = new DailyEngagement();

		dailyEngagement.url = url;
		dailyEngagement.date = engageDate;

		return dailyEngagement;

	}

	public void engage() {
		this.count++;
	}
}
