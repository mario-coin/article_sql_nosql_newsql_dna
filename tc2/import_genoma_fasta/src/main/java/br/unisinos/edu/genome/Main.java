package br.unisinos.edu.genome;

import br.unisinos.edu.genome.reader.*;
import java.io.FileNotFoundException;

public class Main {

    private static final String genomeDatasetPath = "resources/ncbi_dataset/data";
    private static final String genomeDatasetCatalogFileName = "dataset_catalog.json";
    private static final String databaseName = "seq_gen_fasta";
    private static final Integer sequenceLenghCount = 800;

    private static final boolean skipProject = false;
    private static final boolean skipGeneticCode = false;

    public static void main(String[] args) throws FileNotFoundException {
        GenomeAssemblies genomeAssemblies = GenomeJsonParser.ParseCatalog(genomeDatasetPath, genomeDatasetCatalogFileName);
        genomeAssemblies.setAssemblyDataReport(GenomeJsonParser.ParseAssemblyDataReport(genomeDatasetPath, genomeAssemblies.getAnnotations()));

//        genomeAssemblies.Print();

        new Importer(genomeDatasetPath, databaseName, sequenceLenghCount, skipProject, skipGeneticCode).Run(genomeAssemblies);
    }
}
