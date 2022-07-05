package br.unisinos.edu.genome.database.hbase;

import br.unisinos.edu.genome.database.NonRelationalDatabaseClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseClient extends NonRelationalDatabaseClient {
    private TableName tableName;
    private Configuration configuration;
    private Connection connection;

    public HbaseClient(String tableName) throws Exception {
        this.tableName = TableName.valueOf(tableName);
        this.configuration = HBaseConfiguration.create();

        Open();
    }

    public boolean InsertSequence(Integer sequenceNumber, String nucleotides) throws Exception {
        Table table = connection.getTable(this.tableName);

        Put put = new Put(Bytes.toBytes(sequenceNumber));
        put.add(Bytes.toBytes("project"), Bytes.toBytes("name"), Bytes.toBytes(this.projectName));
        put.add(Bytes.toBytes("geneticCode"), Bytes.toBytes("projectId"), Bytes.toBytes(this.geneticCodeName));
        put.add(Bytes.toBytes("region"), Bytes.toBytes("regionNumber"), Bytes.toBytes(this.regionNumber));
        put.add(Bytes.toBytes("sequence"), Bytes.toBytes("nucleotides"), Bytes.toBytes(nucleotides));

        table.put(put);
        table.close();
        return true;
    }

    public void Open() throws Exception {
        this.connection = ConnectionFactory.createConnection(this.configuration);

        Table table = this.connection.getTable(this.tableName);
        Admin admin = this.connection.getAdmin();

        if(!admin.tableExists(table.getName())){
            HTableDescriptor tableDescriptor = new HTableDescriptor(this.tableName);
            tableDescriptor.addFamily(new HColumnDescriptor("project"));
            tableDescriptor.addFamily(new HColumnDescriptor("geneticCode"));
            tableDescriptor.addFamily(new HColumnDescriptor("region"));
            tableDescriptor.addFamily(new HColumnDescriptor("sequence"));
            admin.createTable(tableDescriptor);

            System.out.println("HBase ===> Table " + this.tableName.toString() + " created");
        }

        table.close();
        admin.close();
    }

    public void Close() throws Exception {
        connection.close();
    }
}
