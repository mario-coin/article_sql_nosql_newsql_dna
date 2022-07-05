package br.unisinos.edu.genome.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class RelationalDatabaseClient implements DatabaseClient{
    protected Connection connection;
    protected Long projectId;
    protected Long geneticCodeId;
    protected Long regionId;

    public Long GetProjectId(){
        return this.projectId;
    }

    public Long GetGeneticCodeId(){
        return this.geneticCodeId;
    }

    public Long GetRegionId(){
        return this.regionId;
    }

    public abstract boolean InsertProject(String projectName) throws Exception;
    public abstract boolean InsertGeneticCode(String geneticCodeName, Long projectId) throws Exception;
    public abstract boolean InsertRegion(String regionNumber, Long geneticCodeId) throws Exception;
    public abstract boolean InsertSequence(String sequenceNumber, String nucleotides, Long regionId) throws Exception;

    public abstract void Open(String driver, String domain, String port, String databaseName, String user, String password) throws Exception;

    public abstract void Close() throws Exception;
}
