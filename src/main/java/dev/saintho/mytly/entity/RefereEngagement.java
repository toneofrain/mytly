package dev.saintho.mytly.entity;

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
public class RefereEngagement {
	@Id
	@Column(name = "url_id")
	private Long id;
	@Column(nullable = false)
	private int direct = 0;
	@Column(nullable = false)
	private int other = 0;
	@Column(nullable = false)
	private int google = 0;

	@MapsId
	@OneToOne
	@JoinColumn(name = "url_id")
	private Url url;

	public static RefereEngagement from(Url url) {
		RefereEngagement refereEngagement = new RefereEngagement();

		refereEngagement.url = url;

		return refereEngagement;
	}
}
