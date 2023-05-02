package dev.saintho.mytly.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefererEngagement {
	@Id
	@Column(name = "url_id")
	private Long id;
	@Column(nullable = false)
	private int direct = 0;
	@Column(nullable = false)
	private int google = 0;
	@Column(nullable = false)
	private int other = 0;

	@MapsId
	@OneToOne
	@JoinColumn(name = "url_id")
	private Url url;

	public static RefererEngagement from(Url url) {
		RefererEngagement refererEngagement = new RefererEngagement();

		refererEngagement.url = url;

		return refererEngagement;
	}

	public void engageByDirect() {
		this.direct++;
	}

	public void engageFromGoogle() {
		this.google++;
	}

	public void engageFromOthers() {
		this.other++;
	}
}
