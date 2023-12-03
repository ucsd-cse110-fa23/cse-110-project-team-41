package test.MS1Tests; 
import main.java.server.*; 
import main.java.client.*; 
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import javafx.stage.Stage; 
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class US5Tests {

    private recipeHandler rh;
    private database db;
    recipe rec;
    recipe rec2;
    recipe rec3;
    recipe rec4;

    @BeforeEach 
    void setUp() {
        rh = new recipeHandler();
        db = new database();
        rec = new recipe("Recipe1", "Description 1");
        rec2 = new recipe("Recipe2", "Description 2");
        rec3 = new recipe("Recipe3", "Description 3");
        rec4 = new recipe("Recipe4", "Description 4");
        

    }

    /* 
     * Test for deleting a recipe from db
     */
    @Test
    void testDeleteDB() {
        // 4 recipes already exist in the database, delete recipe2
        db.deleteRecipe("Recipe2");
        //if a recipe dne in db, attempting to find it will result in a null return value
        //we now attempt to find the recipe we deleted, but since it should not exist in the db anymore we should get null
        assertEquals(null,db.getRecipe("Recipe2"));
    }
    
}