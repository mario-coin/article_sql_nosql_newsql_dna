package br.unisinos.edu.genome.reader;

public class AssemblyDataReport {
    private String ProjectName;
    private String GeneticCodeName;

    public AssemblyDataReport(String projectName, String geneticCodeName) {
        ProjectName = projectName;
        GeneticCodeName = geneticCodeName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public String getGeneticCodeName() {
        return GeneticCodeName;
    }
}
