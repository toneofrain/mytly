CREATE SCHEMA IF NOT EXISTS mytly DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE mytly;

DROP TABLE IF EXISTS referer_engagement;

DROP TABLE IF EXISTS daily_engagement;

DROP TABLE IF EXISTS url;

CREATE TABLE url (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    shortened VARCHAR(255) NOT NULL,
    original VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    expirable_yn TINYINT(1) NOT NULL DEFAULT 0,
    expire_at TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE INDEX ux_shortened (shortened),
    UNIQUE INDEX ux_original (original)
);

CREATE TABLE referer_engagement (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    direct MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
    other MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
    google MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE daily_engagement (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    url_id BIGINT UNSIGNED NOT NULL,
    engage_date DATE NOT NULL,
    engage_count MEDIUMINT UNSIGNED NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

ALTER TABLE referer_engagement
ADD CONSTRAINT fk_url_referer_engagement
FOREIGN KEY (id)
REFERENCES url (id)
ON DELETE CASCADE;

ALTER TABLE daily_engagement
ADD CONSTRAINT fk_url_daily_engagement
FOREIGN KEY (url_id)
REFERENCES url (id)
ON DELETE CASCADE;