package edu.vanderbilt.cs.cyberbull.core.couchbase.stockdb;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.*;

public class CouchbaseClient {
    private final Cluster cluster;
    private final String connectionString;
    private final String username;
    private final String password;
    private final Bucket bucket;
    public CouchbaseClient(){
        connectionString = System.getenv("db-connectionString");
        username = System.getenv("db-username");
        password = System.getenv("db-password");
        cluster = Cluster.connect(connectionString, username, password);
        bucket = cluster.bucket("cyberbull");
    }
}
