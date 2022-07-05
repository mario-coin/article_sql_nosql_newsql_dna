package br.unisinos.edu.genome.database.postgresql;

import br.unisinos.edu.genome.database.DriverManagerClient;

import java.sql.*;

public class PostgresqlClient extends DriverManagerClient {
    public PostgresqlClient(String databaseName) throws SQLException {
        super("postgresql", "localhost", "5432", databaseName, "postgres", "root");
    }
}