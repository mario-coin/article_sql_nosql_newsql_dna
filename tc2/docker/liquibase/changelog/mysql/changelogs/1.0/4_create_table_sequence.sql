CREATE TABLE IF NOT EXISTS sequence (
	sequenceId BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	sequenceNumber varchar(255) NOT NULL,
	nucleotides varchar(800) NOT NULL,
	regionId BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY (regionId) REFERENCES region(regionId)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;