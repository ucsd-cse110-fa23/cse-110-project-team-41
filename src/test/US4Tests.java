package test; 
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

public class US4Tests {

    private recipeHandler rh;
    private database db;
    recipe rec;

    @BeforeEach 
    void setUp() {
        rh = new recipeHandler();
        db = new database();
        rec = new recipe("Recipe1", "Description 1");
    }

    /* 
     * Test for working functionality when database contains prefilled recipes
     */
    @Test
    void testEdit() {
        // Assumptions: (1) add numRecipes++ to getRecipesFromDB() within while loop in recipeHandler class (2) add private member numRecipes (3) add public getNumRecipes(){return numRecipes;}
        // rh.getRecipeElements(new Stage()); 
        int beforeCount = rh.getNumRecipes();

        String recName = rec.getName();
        String updated = "Updated Description for Recipe1";

        db.editRecipe(recName, updated);

        // Assert that no new recipes were created/added to the database, only modified. 
        assertEquals(beforeCount, rh.getNumRecipes());
    }
    /* 
     * Test for editing response when editing is enabled yet there are no recipes to edit
     */ 
    @Test
    void testEditEmpty() {
        // Assumptions: (1) add isEditable() to recipeHandler class that will return true when there is at least one recipe recorded in db
        db.clearDB();
        db.editRecipe("Recipe 0", "description 0");
        assertFalse(rh.isEditable()); 
    }

} 
