package main.java;

import static com.mongodb.client.model.Filters.eq;


import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class QuickStart {
   public static void main( String[] args ) {


       // Replace the placeholder with your MongoDB deployment's connection string
       String uri = "mongodb://pantryPal:team41@ac-n5lr4ey-shard-00-00.2gh1r0b.mongodb.net:27017,ac-n5lr4ey-shard-00-01.2gh1r0b.mongodb.net:27017,ac-n5lr4ey-shard-00-02.2gh1r0b.mongodb.net:27017/?ssl=true&replicaSet=atlas-6m4qti-shard-0&authSource=admin&retryWrites=true&w=majority";


       try (MongoClient mongoClient = MongoClients.create(uri)) {
           MongoDatabase database = mongoClient.getDatabase("sample_mflix");
           MongoCollection<Document> collection = database.getCollection("movies");


           Document doc = collection.find(eq("title", "Back to the Future")).first();
           if (doc != null) {
               System.out.println(doc.toJson());
           } else {
               System.out.println("No matching documents found.");
           }
       }
   }
}

