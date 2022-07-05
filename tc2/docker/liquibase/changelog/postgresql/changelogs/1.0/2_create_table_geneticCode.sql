CREATE TABLE IF NOT EXISTS geneticCode (
	geneticCodeId SERIAL PRIMARY KEY,
	name varchar(100) NOT NULL,
	projectId INT NOT NULL,
	FOREIGN KEY (projectId) REFERENCES project(projectId)
);