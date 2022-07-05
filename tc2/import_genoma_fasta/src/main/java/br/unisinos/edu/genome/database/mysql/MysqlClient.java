package br.unisinos.edu.genome.database.mysql;

import br.unisinos.edu.genome.database.DriverManagerClient;

import java.sql.*;

public class MysqlClient extends DriverManagerClient {

    public MysqlClient(String databaseName) throws SQLException {
        super("mysql", "localhost", "3306", databaseName, "root", "root");
    }
}
