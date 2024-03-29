CREATE TABLE IF NOT EXISTS geneticCode (
	geneticCodeId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name varchar(100) NOT NULL,
	projectId INT UNSIGNED NOT NULL,
	FOREIGN KEY (projectId) REFERENCES project(projectId)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;