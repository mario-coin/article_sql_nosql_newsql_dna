CREATE TABLE region (
	regionId BIGINT UNSIGNED AUTO_INCREMENT,
	regionNumber varchar(255) NOT NULL,
	geneticCodeId INT UNSIGNED NOT NULL,
	KEY (regionId, geneticCodeId)
);