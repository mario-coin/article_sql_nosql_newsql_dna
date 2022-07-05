package br.unisinos.edu.genome.database.mongodb;

import br.unisinos.edu.genome.database.NonRelationalDatabaseClient;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongodbClient extends NonRelationalDatabaseClient {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongodbClient(String databaseName) {
        Open("root", "root", "localhost", "27017", databaseName);
    }

    public boolean InsertSequence(Integer sequenceNumber, String nucleotides) {
        MongoCollection<Document> projects = this.database.getCollection("sequence");
        Document project = new Document("projectName", this.projectName)
            .append("geneticCodeName", this.geneticCodeName)
            .append("regionNumber", this.regionNumber)
            .append("sequenceNumber", sequenceNumber)
            .append("nucleotides", nucleotides);

        projects.insertOne(project);

        return true;
    }

    public void Open(String user, String password, String domain, String port, String databaseName) {
        String connectionString = String.format("mongodb://%s:%s@%s:%s", user, password, domain, port);

        this.mongoClient = MongoClients.create(connectionString);
        this.database = mongoClient.getDatabase(databaseName);
    }

    public void Close() {
        this.mongoClient.close();
    }
}
