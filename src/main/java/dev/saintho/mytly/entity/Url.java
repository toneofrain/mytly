package dev.saintho.mytly.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
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
	@Column(nullable = false, updatable = false, unique = true)
	private String original;
	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	@Column(name = "expirable_yn", nullable = false)
	private boolean isExpirable = false;
	@Column
	private LocalDateTime expireAt;
}
