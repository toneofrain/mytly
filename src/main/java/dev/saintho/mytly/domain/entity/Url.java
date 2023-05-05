package dev.saintho.mytly.domain.entity;

import static dev.saintho.mytly.domain.entity.UrlStatus.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import dev.saintho.mytly.exception.ExceptionType;
import dev.saintho.mytly.exception.MytlyException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, updatable = false, unique = true)
	private String shortened;
	@Column(nullable = false, updatable = false)
	private String original;
	@Column(name = "url_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private UrlStatus status = AVAILABLE;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	@Column(name = "expirable_yn", nullable = false)
	private boolean isExpirable = false;
	@Column
	private LocalDateTime expireAt;

	@Builder
	public Url(String shortened, String original, boolean isExpirable, LocalDateTime expireAt) {
		this.shortened = shortened;
		this.original = original;
		this.isExpirable = isExpirable;
		this.expireAt = expireAt;
	}
	public void updateExpirationOption(Boolean isExpirable, LocalDateTime expireAt) {
		this.isExpirable = isExpirable;
		this.expireAt = expireAt;
	}

	public void setNotExpirable() {
		this.isExpirable = false;
		this.expireAt = null;
	}

	public void softDelete() {
		this.status = DELETED;
	}

	public boolean isAvailableAtTheTime(LocalDateTime dateTime) {
		return !isExpiredAtTheTime(dateTime) && !isDeleted();
	}

	public boolean isExpiredAtTheTime(LocalDateTime dateTime) {
		return this.status == EXPIRED || dateTime.isAfter(this.expireAt);
	}

	public boolean isDeleted() {
		return this.status == DELETED;
	}

	public void checkAvailalbleAtTheTime(LocalDateTime dateTime) {
		if (!isAvailableAtTheTime(dateTime)) {
			throw new MytlyException(ExceptionType.URL_NOT_AVAILABLE);
		}
	}
}
