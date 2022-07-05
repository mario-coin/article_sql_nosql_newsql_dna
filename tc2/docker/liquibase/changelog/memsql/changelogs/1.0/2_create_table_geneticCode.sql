CREATE TABLE geneticCode (
	geneticCodeId INT UNSIGNED AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	projectId INT UNSIGNED NOT NULL,
	KEY (geneticCodeId, projectId)
);