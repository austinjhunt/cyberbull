package edu.vanderbilt.cs.cyberbull.core.couchbase;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.*;
import com.couchbase.client.java.query.QueryResult;

public class CouchbaseClient {
    private final Cluster cluster;
    private final Bucket bucket;
    public CouchbaseClient(){
//        cluster = Cluster.connect(
//                System.getenv("DB_CONNECTION_STRING"),
//                System.getenv("DB_USERNAME"),
//                System.getenv("DB_PASSWORD"));
        System.out.println("Connecting....");
        cluster = Cluster.connect("127.0.0.1","***", "***");
//        bucket = cluster.bucket(
//                System.getenv("DB_BUCKET_NAME"));
        bucket = cluster.bucket("travel-sample");
    }
    public Bucket getBucket(){
        return bucket;
    }


    public static void main(String[] args){
        CouchbaseClient cli = new CouchbaseClient();
        Bucket bucket = cli.getBucket();
        Scope scope = bucket.scope("inventory");
        QueryResult result = scope.query("SELECT * FROM airline");
        result.rowsAsObject().forEach(System.out::println);
    }
}
