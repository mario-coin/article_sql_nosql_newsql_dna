package br.unisinos.edu.genome.database.memsql;

import br.unisinos.edu.genome.database.DriverManagerClient;

import java.sql.*;

public class MemsqlClient extends DriverManagerClient {
    public MemsqlClient(String databaseName) throws SQLException  {
        super("mysql", "localhost", "3307", databaseName, "root", "oR{KD*Z]YA**^c<K<~uv");
    }
}
