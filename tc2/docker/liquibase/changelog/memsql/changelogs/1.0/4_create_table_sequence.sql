CREATE TABLE sequence (
	sequenceId BIGINT UNSIGNED AUTO_INCREMENT,
	sequenceNumber varchar(255) NOT NULL,
	nucleotides varchar(800) NOT NULL,
	regionId BIGINT UNSIGNED NOT NULL,
	KEY (sequenceId, regionId)
);