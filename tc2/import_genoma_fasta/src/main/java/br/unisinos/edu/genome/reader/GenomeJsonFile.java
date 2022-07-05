package br.unisinos.edu.genome.reader;

public class GenomeJsonFile {
    private String filePath;
    private String fileType;

    public GenomeJsonFile(String filePath, String fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isFileTypeGenomicNucleotideFasta() {
        return fileType.equals(GenomeJsonFileTypeConstants.GENOMIC_NUCLEOTIDE_FASTA);
    }

    public void Print() {
        System.out.println("------> Path = " + filePath);
        System.out.println("------> Type = " + fileType);
    }
}
