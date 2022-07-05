CREATE TABLE IF NOT EXISTS region (
	regionId BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	regionNumber varchar(255) NOT NULL,
	geneticCodeId INT UNSIGNED NOT NULL,
	FOREIGN KEY (geneticCodeId) REFERENCES geneticCode(geneticCodeId)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;