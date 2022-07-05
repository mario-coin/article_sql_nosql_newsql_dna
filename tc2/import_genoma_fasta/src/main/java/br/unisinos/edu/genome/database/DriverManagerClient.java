package br.unisinos.edu.genome.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverManagerClient extends RelationalDatabaseClient {

    public DriverManagerClient(String driver, String domain, String port, String databaseName, String user, String password) throws SQLException {
        Open(driver, domain, port, databaseName, user, password);
    }

    public boolean InsertProject(String projectName) throws SQLException {
        String query = String.format("insert into project (name) values ('%s')", projectName);

        Statement statement = this.connection.createStatement();

        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        this.projectId = -1L;
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            this.projectId = resultSet.getLong(1);
        }
        resultSet.close();

        return true;
    }

    public boolean InsertGeneticCode(String geneticCodeName, Long projectId) throws SQLException {
        String query = String.format("insert into geneticCode (name, projectId) values ('%s', '%s')", geneticCodeName, projectId);

        Statement statement = this.connection.createStatement();

        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        this.geneticCodeId = -1L;
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            this.geneticCodeId = resultSet.getLong(1);
        }
        resultSet.close();

        return true;
    }

    public boolean InsertRegion(String regionNumber, Long geneticCodeId) throws SQLException {
        String query = String.format("insert into region (regionNumber, geneticCodeId) values ('%s', '%s')", regionNumber, geneticCodeId);

        Statement statement = this.connection.createStatement();

        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        this.regionId = -1L;
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            this.regionId = resultSet.getLong(1);
        }
        resultSet.close();

        return true;
    }

    public boolean InsertSequence(String sequenceNumber, String nucleotides, Long regionId) throws SQLException {
        String query = String.format("insert into sequence (sequenceNumber, nucleotides, regionId) values ('%s', '%s', '%s')", sequenceNumber, nucleotides, regionId);

        Statement statement = this.connection.createStatement();

        statement.execute(query);

        return true;
    }

    public void Open(String driver, String domain, String port, String databaseName, String user, String password) throws SQLException {
        String url = String.format("jdbc:%s://%s:%s/%s", driver, domain, port, databaseName);

        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void Close() throws SQLException {
        this.connection.close();
    }
}
