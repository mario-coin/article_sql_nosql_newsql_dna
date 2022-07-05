package br.unisinos.edu.genome.database;

public abstract class NonRelationalDatabaseClient implements DatabaseClient {
    protected String projectName;
    protected String geneticCodeName;
    protected String regionNumber;

    public void SetProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void SetGeneticCodeName(String geneticCodeName) {
        this.geneticCodeName = geneticCodeName;
    }

    public void SetRegionNumber(String regionNumber) {
        this.regionNumber = regionNumber;
    }

    public abstract boolean InsertSequence(Integer sequenceNumber, String nucleotides) throws Exception;

    public abstract void Close() throws Exception;
}
