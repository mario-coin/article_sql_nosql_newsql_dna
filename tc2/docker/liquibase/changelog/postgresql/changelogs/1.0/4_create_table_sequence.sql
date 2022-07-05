CREATE TABLE IF NOT EXISTS sequence (
	sequenceId BIGSERIAL PRIMARY KEY,
	sequenceNumber varchar(255) NOT NULL,
	nucleotides varchar(800) NOT NULL,
	regionId BIGINT NOT NULL,
	FOREIGN KEY (regionId) REFERENCES region(regionId)
);