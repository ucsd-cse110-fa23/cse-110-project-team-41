package main.java;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class database { 
    private String uri; 
   public database() { 

       // Replace the placeholder with your MongoDB deployment's connection string
       this.uri = "mongodb+srv://pantryPal:team41@pantrypal.2gh1r0b.mongodb.net/?retryWrites=true&w=majority";
            /* 
            Document doc = collection.find(eq("title", "Back to the Future")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
            */
   } 
   public void addFileToDb(){ 
    try (MongoClient mongoClient = MongoClients.create(uri)) {
           MongoDatabase database = mongoClient.getDatabase("Recipes");
           MongoCollection<Document> collection = database.getCollection("savedRecipes");
           addRecipeFile(collection, "src/recipe.txt" ); 
       } catch (Exception e){ 
        System.out.println(e); 
       } 
   }
   public Iterator<Document> getAll(){ 
      String uri = "mongodb+srv://pantryPal:team41@pantrypal.2gh1r0b.mongodb.net/?retryWrites=true&w=majority";

       try (MongoClient mongoClient = MongoClients.create(uri)) {
           MongoDatabase database = mongoClient.getDatabase("Recipes");
           MongoCollection<Document> collection = database.getCollection("savedRecipes");
           return collection.find().iterator(); 
       } catch (Exception e){ 
        System.out.println(e); 
       } 
       return null; 
   } 

    private void addRecipeFile(MongoCollection<Document> collection, String fp){
        try (BufferedReader br = new BufferedReader(new FileReader(fp))){
            //String title = br.readLine();
            StringBuilder title = new StringBuilder();
            StringBuilder ingredients = new StringBuilder();
            StringBuilder instructions = new StringBuilder();
            String line;

            boolean isIngredients = false;
            boolean isInstructions = false; 
            while((line = br.readLine()) != null){
                if(line.contains("Ingredients:")){
                    isIngredients = true;
                    isInstructions = false; 

                } else if(line.equals("Instructions:")){
                    isIngredients = false;
                    isInstructions = true;
                } else if(isIngredients){
                    ingredients.append(line).append("\n");
                } else if(isInstructions){
                    instructions.append(line).append("\n");
                }else {
                    title.append(line).append("\n"); 
                }
            } 
            Document doc = new Document();
            doc.append("title", title.toString().trim());
            doc.append("ingredients", ingredients.toString().trim());
            doc.append("instructions", instructions.toString().trim());
            
            collection.insertOne(doc);
            System.out.println("data added to mongoDB"); 

        }catch (IOException e){ 
            System.out.println(e); 
             e.printStackTrace();
        }
    }  
        public void clearDB(){ 
        try (MongoClient mongoClient = MongoClients.create(uri)) {
           MongoDatabase database = mongoClient.getDatabase("Recipes");
           MongoCollection<Document> collection = database.getCollection("savedRecipes");
           collection.deleteMany(new Document()); 
           System.out.println("Cleared Database"); 
       } catch (Exception e){ 
        System.out.println(e); 
       } 
    }
}


