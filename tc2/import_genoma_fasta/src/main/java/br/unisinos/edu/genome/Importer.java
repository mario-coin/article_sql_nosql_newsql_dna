package br.unisinos.edu.genome;

import br.unisinos.edu.genome.database.cockroachdb.CockroachdbClient;
import br.unisinos.edu.genome.database.hbase.HbaseClient;
import br.unisinos.edu.genome.database.memsql.MemsqlClient;
import br.unisinos.edu.genome.database.mongodb.MongodbClient;
import br.unisinos.edu.genome.database.mysql.MysqlClient;
import br.unisinos.edu.genome.database.postgresql.PostgresqlClient;
import br.unisinos.edu.genome.reader.GenomeAssemblies;
import br.unisinos.edu.genome.reader.GenomeJsonFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Importer {
    private MysqlClient mysqlClient;
    private PostgresqlClient postgresqlClient;
    private MongodbClient mongodbClient;
    private HbaseClient hbaseClient;
    private MemsqlClient memsqlClient;
    private CockroachdbClient cockroachdbClient;

    private final String genomeDatasetPath;
    private final Integer sequenceLenghCount;

    private final boolean skipProject;
    private final boolean skipGeneticCode;

    private final String[] skips = {
            "GCF_000001405.40/chr1.fna",
            "GCF_000001405.40/chr1.unlocalized.scaf.fna",
            "GCF_000001405.40/chr2.fna",
            "GCF_000001405.40/chr2.unlocalized.scaf.fna",
            "GCF_000001405.40/chr3.fna",
            "GCF_000001405.40/chr3.unlocalized.scaf.fna",
            "GCF_000001405.40/chr4.fna",
            "GCF_000001405.40/chr4.unlocalized.scaf.fna",
            "GCF_000001405.40/chr5.fna",
            "GCF_000001405.40/chr5.unlocalized.scaf.fna",
            "GCF_000001405.40/chr6.fna",
            "GCF_000001405.40/chr7.fna",
            "GCF_000001405.40/chr8.fna",
            "GCF_000001405.40/chr9.fna",
            "GCF_000001405.40/chr9.unlocalized.scaf.fna",
            "GCF_000001405.40/chr10.fna",
            "GCF_000001405.40/chr11.fna",
            "GCF_000001405.40/chr12.fna"
    };
    private final String[] stops = { "GCF_000001405.40/chr6.fna", "GCF_000001405.40/chr12.fna" };

    public Importer(String genomeDatasetPath, String databaseName, Integer sequenceLenghCount, boolean skipProject, boolean skipGeneticCode) {
        try {
            this.mysqlClient = new MysqlClient(databaseName);
            this.postgresqlClient = new PostgresqlClient(databaseName);
            this.mongodbClient = new MongodbClient(databaseName);
            this.hbaseClient = new HbaseClient(databaseName);
            this.memsqlClient = new MemsqlClient(databaseName);
            this.cockroachdbClient = new CockroachdbClient(databaseName);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        this.genomeDatasetPath = genomeDatasetPath;
        this.sequenceLenghCount = sequenceLenghCount;

        this.skipProject = skipProject;
        this.skipGeneticCode = skipGeneticCode;
    }

    public void Run(GenomeAssemblies genomeAssemblies) {
        try {
            if(!skipProject){
                InsertProject(genomeAssemblies);
            }

            if(!skipGeneticCode){
                InsertGeneticCode(genomeAssemblies);
            }

            InsertRegionAndSequence(genomeAssemblies);

            CloseConnections();
        } catch (Exception e)
        {
            try {
                CloseConnections();
            } catch (Exception eClose){
                eClose.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    private void InsertProject(GenomeAssemblies genomeAssemblies) throws Exception{
        String projectName = genomeAssemblies.getProjectName();

        this.mysqlClient.InsertProject(projectName);
        this.postgresqlClient.InsertProject(projectName);
        this.mongodbClient.SetProjectName(projectName);
        this.hbaseClient.SetProjectName(projectName);
        this.memsqlClient.InsertProject(projectName);
        this.cockroachdbClient.InsertProject(projectName);
    }

    private void InsertGeneticCode(GenomeAssemblies genomeAssemblies) throws Exception{
        String geneticCodeName = genomeAssemblies.getGeneticCodeName();

        this.mysqlClient.InsertGeneticCode(geneticCodeName, this.mysqlClient.GetProjectId());
        this.postgresqlClient.InsertGeneticCode(geneticCodeName, this.postgresqlClient.GetProjectId());
        this.mongodbClient.SetGeneticCodeName(geneticCodeName);
        this.hbaseClient.SetGeneticCodeName(geneticCodeName);
        this.memsqlClient.InsertGeneticCode(geneticCodeName, this.memsqlClient.GetProjectId());
        this.cockroachdbClient.InsertGeneticCode(geneticCodeName, this.cockroachdbClient.GetProjectId());
    }

    private void InsertRegionAndSequence(GenomeAssemblies genomeAssemblies) throws Exception{

        Integer lineCount = 0;
        for (GenomeJsonFile genomeJsonFile: genomeAssemblies.getGenomeData()) {
            if(!genomeJsonFile.isFileTypeGenomicNucleotideFasta()){
                continue;
            }
            boolean skipFile = false;
            for (String skip: skips) {
                if(skip.equals(genomeJsonFile.getFilePath())){
                    System.out.println("Skipping file " + skip + "(" + LocalDateTime.now() + ")");
                    skipFile = true;
                }
            }
            if(skipFile) continue;


//            String filePath = String.format("%s/%s", genomeDatasetPath, genomeAssemblies.getGenomeData().get(0).getFilePath());
            String filePath = String.format("%s/%s", genomeDatasetPath, genomeJsonFile.getFilePath());
            System.out.println("Reading file " + filePath + "(" + LocalDateTime.now() + ")");

            try (FileReader fileReader = new FileReader(filePath))
            {
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String nucleotides = "", line = bufferedReader.readLine(), regionNumber = "";
                Integer sequenceCount = 0;
                while (line != null){
                    //new region
                    if(line.charAt(0) == '>'){
                        //nucleotides left before new region
                        if(nucleotides.length() > 0){
                            InsertSequence(regionNumber, ++sequenceCount, nucleotides);
                            nucleotides = "";
                        }

                        regionNumber = line.substring(line.indexOf('>') + 1, line.indexOf(' '));
                        InsertRegion(regionNumber);
                    }
                    else{
                        nucleotides += line;

                        //reach sequence limit
                        Integer sumLength = nucleotides.length();
                        if(sumLength >= this.sequenceLenghCount){

                            String extra = "";
                            if(sumLength > this.sequenceLenghCount){
                                extra = nucleotides.substring(this.sequenceLenghCount, sumLength);
                                nucleotides = nucleotides.substring(0, this.sequenceLenghCount);
                            }

                            InsertSequence(regionNumber, ++sequenceCount, nucleotides);
                            nucleotides = extra;
                        }
                    }

                    line = bufferedReader.readLine();
                }
            }

            System.out.println("Finished file " + filePath);

            for (String stop: stops) {
                if(stop.equals(genomeJsonFile.getFilePath())){
                    System.out.println("Finishing... Line " + lineCount + " (" + LocalDateTime.now() + ")");
                    throw new Exception("Stopped at specified filePath.");
                }
            }
        }
    }

    private void InsertRegion(String regionNumber) throws Exception{
        this.mysqlClient.InsertRegion(regionNumber, this.mysqlClient.GetGeneticCodeId());
        this.postgresqlClient.InsertRegion(regionNumber, this.postgresqlClient.GetGeneticCodeId());
        this.mongodbClient.SetRegionNumber(regionNumber);
        this.hbaseClient.SetRegionNumber(regionNumber);
        this.memsqlClient.InsertRegion(regionNumber, this.memsqlClient.GetGeneticCodeId());
        this.cockroachdbClient.InsertRegion(regionNumber, this.cockroachdbClient.GetGeneticCodeId());
    }

    private void InsertSequence(String regionNumber, Integer sequenceCount, String nucleotides) throws Exception {
        String sequenceNumber = String.format("%s-pt%s", regionNumber, sequenceCount);

        this.mysqlClient.InsertSequence(sequenceNumber, nucleotides, this.mysqlClient.GetRegionId());
        this.postgresqlClient.InsertSequence(sequenceNumber, nucleotides, this.postgresqlClient.GetRegionId());
        this.mongodbClient.InsertSequence(sequenceCount, nucleotides);
        this.hbaseClient.InsertSequence(sequenceCount, nucleotides);
        this.memsqlClient.InsertSequence(sequenceNumber, nucleotides, this.memsqlClient.GetRegionId());
        this.cockroachdbClient.InsertSequence(sequenceNumber, nucleotides, this.cockroachdbClient.GetRegionId());
    }

    private  void CloseConnections() throws Exception {
        this.mysqlClient.Close();
        this.postgresqlClient.Close();
        this.mongodbClient.Close();
        this.hbaseClient.Close();
        this.memsqlClient.Close();
        this.cockroachdbClient.Close();
    }
}
