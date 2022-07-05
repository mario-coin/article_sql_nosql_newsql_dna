package br.unisinos.edu.genome.reader;

import java.util.ArrayList;

public class GenomeAssemblies {
    private ArrayList<GenomeJsonFile> annotations;
    private ArrayList<GenomeJsonFile> genomeData;

    private AssemblyDataReport assemblyDataReport;

    public GenomeAssemblies(ArrayList<GenomeJsonFile> annotations, ArrayList<GenomeJsonFile> genomeData) {
        this.annotations = annotations;
        this.genomeData = genomeData;
    }

    public ArrayList<GenomeJsonFile> getAnnotations() {
        return annotations;
    }

    public ArrayList<GenomeJsonFile> getGenomeData() {
        return genomeData;
    }

    public AssemblyDataReport getAssemblyDataReport() {
        return assemblyDataReport;
    }

    public String getProjectName() {
        return assemblyDataReport.getProjectName();
    }

    public String getGeneticCodeName() {
        return assemblyDataReport.getGeneticCodeName();
    }

    public void setAssemblyDataReport(AssemblyDataReport assemblyDataReport) {
        this.assemblyDataReport = assemblyDataReport;
    }

    public void Print() {
        System.out.println("---> Annotations");
        for (GenomeJsonFile annotation:
             annotations) {
            annotation.Print();
        }
        System.out.println("---> GenomeData");
        for (GenomeJsonFile annotation:
                genomeData) {
            annotation.Print();
        }
    }
}
