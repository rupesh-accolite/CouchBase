package com.example.couchBase;



import org.springframework.stereotype.Service;


import com.couchbase.client.core.retry.FailFastRetryStrategy;
import com.couchbase.client.java.Bucket;

import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

@Service
public class CouchBasePersonService {
	public void start() {
		final CouchbaseEnvironment env = DefaultCouchbaseEnvironment
		           .builder().connectTimeout(50000)
		           .retryStrategy(FailFastRetryStrategy.INSTANCE)
		           .build();
		CouchbaseCluster cluster = CouchbaseCluster.create(env,"127.0.0.1");
		cluster.authenticate("rupesh","rupesh");
		Bucket bucket = cluster.openBucket("example");
		
		
	            
		JsonDocument doc = JsonDocument.create("501", JsonObject.create().put("name", "Rupesh")
				.put("age", "23")
				.put("company", "Accolite")
				.put("companyId", "501")
				);
		bucket.insert(doc);
		
		
	}
	
	
	public void addPerson(CouchBasePerson person) {
		final CouchbaseEnvironment env = DefaultCouchbaseEnvironment
		           .builder().connectTimeout(50000)
		           .retryStrategy(FailFastRetryStrategy.INSTANCE)
		           .build();
		CouchbaseCluster cluster = CouchbaseCluster.create(env,"127.0.0.1");
		cluster.authenticate("rupesh","rupesh");
		Bucket bucket = cluster.openBucket("example");
		
		
		JsonDocument doc = JsonDocument.create(person.getCompanyId(), JsonObject.create().put("name", person.getName())
				.put("age", person.getAge())
				.put("company", person.getCompany())
				.put("companyId", person.getCompanyId())
				);
		bucket.insert(doc);
	}
	
	public void updatePerson(CouchBasePerson person, String companyId) {
		final CouchbaseEnvironment env = DefaultCouchbaseEnvironment
		           .builder().connectTimeout(50000)
		           .retryStrategy(FailFastRetryStrategy.INSTANCE)
		           .build();
		CouchbaseCluster cluster = CouchbaseCluster.create(env,"127.0.0.1");
		cluster.authenticate("rupesh","rupesh");
		Bucket bucket = cluster.openBucket("example");
		
		
		
		bucket.upsert(JsonDocument.create(companyId, JsonObject.create().put("name", person.getName())
				.put("age", person.getAge())
				.put("company", person.getCompany())
				.put("companyId", person.getCompanyId())
				));
	}
	
	public void deletePerson(String id) {
		final CouchbaseEnvironment env = DefaultCouchbaseEnvironment
		           .builder().connectTimeout(50000)
		           .retryStrategy(FailFastRetryStrategy.INSTANCE)
		           .build();
		CouchbaseCluster cluster = CouchbaseCluster.create(env,"127.0.0.1");
		cluster.authenticate("rupesh","rupesh");
		Bucket bucket = cluster.openBucket("example");
		
		
		bucket.remove(id);
	}
	
}
