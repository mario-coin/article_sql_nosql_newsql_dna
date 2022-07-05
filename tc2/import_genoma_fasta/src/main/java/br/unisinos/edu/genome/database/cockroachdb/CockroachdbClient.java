package br.unisinos.edu.genome.database.cockroachdb;

import br.unisinos.edu.genome.database.DriverManagerClient;

import java.sql.SQLException;

public class CockroachdbClient extends DriverManagerClient {
    public CockroachdbClient(String databaseName) throws SQLException {
        super("postgresql", "localhost", "26257", databaseName, "root", null);
    }
}