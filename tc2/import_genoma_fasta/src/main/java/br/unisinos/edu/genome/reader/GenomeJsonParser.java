package br.unisinos.edu.genome.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class GenomeJsonParser {
    private static final String assembliesLabel =  "assemblies";
    private static final String filesLabel =  "files";
    private static final String filePathLabel =  "filePath";
    private static final String fileTypeLabel =  "fileType";

    private static final int annotations_position = 0;
    private static final int genome_data_position = 1;

    public static GenomeAssemblies ParseCatalog(String genomeDatasetPath, String genomeDatasetCatalogFileName) {
        String genomeDatasetCatalogPath = String.format("%s/%s", genomeDatasetPath, genomeDatasetCatalogFileName);

        try (FileReader reader = new FileReader(genomeDatasetCatalogPath)) {
            Object obj = JsonParser.parseReader(reader);
            JsonObject jsonObject = (JsonObject) obj;
            JsonArray assembliesObject = (JsonArray) jsonObject.get(assembliesLabel);

            JsonArray filesArray;
            JsonObject filesObject;

            filesObject = (JsonObject) assembliesObject.get(annotations_position);
            filesArray = (JsonArray) filesObject.get(filesLabel);
            ArrayList<GenomeJsonFile> annotations = ParseFiles(filesArray);

            filesObject = (JsonObject) assembliesObject.get(genome_data_position);
            filesArray = (JsonArray) filesObject.get(filesLabel);
            ArrayList<GenomeJsonFile> genomeData = ParseFiles(filesArray);

            GenomeAssemblies genomeAssemblies = new GenomeAssemblies(annotations, genomeData);
            return genomeAssemblies;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ArrayList<GenomeJsonFile> ParseFiles(JsonArray filesArray) {
        ArrayList<GenomeJsonFile> genomeData = new ArrayList<>();
        filesArray.forEach( f ->
            genomeData.add(ParseFiles((JsonObject) f ))
        );

        return genomeData;
    }

    private static GenomeJsonFile ParseFiles(JsonObject fileObject) {
        String filePath = fileObject.get(filePathLabel).getAsString();
        String fileType = fileObject.get(fileTypeLabel).getAsString();
        return new GenomeJsonFile(filePath, fileType);
    }

    private static final String assemblyInfoLabel =  "assemblyInfo";
    private static final String assemblyNameLabel =  "assemblyName";
    private static final String bioprojectLineageLabel =  "bioprojectLineage";
    private static final String bioprojectsLabel =  "bioprojects";
    private static final String accessionLabel =  "accession";

    public static AssemblyDataReport ParseAssemblyDataReport(String genomeDatasetPath, ArrayList<GenomeJsonFile> annotations){
        String assemblyDataReportFilePath = annotations.get(1).getFilePath();
        String assemblyDataReportPath = String.format("%s/%s", genomeDatasetPath, assemblyDataReportFilePath);

        try (FileReader reader = new FileReader(assemblyDataReportPath)) {
            Object obj = JsonParser.parseReader(reader);
            JsonObject jsonObject = (JsonObject) obj;
            jsonObject = (JsonObject) jsonObject.get(assemblyInfoLabel);

            String geneticCodeName = jsonObject.get(assemblyNameLabel).getAsString();

            JsonArray jsonArray = (JsonArray) jsonObject.get(bioprojectLineageLabel);
            jsonObject = (JsonObject) jsonArray.get(0);
            jsonArray = (JsonArray) jsonObject.get(bioprojectsLabel);
            jsonObject = (JsonObject) jsonArray.get(0);

            String projectName = jsonObject.get(accessionLabel).getAsString();

            AssemblyDataReport assemblyDataReport = new AssemblyDataReport(projectName, geneticCodeName);
            return assemblyDataReport;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
