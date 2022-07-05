CREATE TABLE IF NOT EXISTS region (
	regionId BIGSERIAL PRIMARY KEY,
	regionNumber varchar(255) NOT NULL,
	geneticCodeId INT NOT NULL,
	FOREIGN KEY (geneticCodeId) REFERENCES geneticCode(geneticCodeId)
);