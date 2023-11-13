package test; 
import main.java.server.*; 
import main.java.client.*; 
import org.junit.jupiter.api.Test;

import javafx.stage.Stage;


import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
 * Tests displaying all recipes functionality
 */
public class US3Tests {
    private recipeHandler rh;
    private Stage primaryStage; 
    
    @BeforeEach
    void setUp() {
    }
    
    @Test
    void testZeroRecipes() {
        rh = new recipeHandler();
        assertEquals(0, rh.getNumRecipes());
    }

    @Test
    void testAddRecipes() { 
        rh = new recipeHandler();
        // rh.getRecipeElements(primaryStage); 
        int currentCount = rh.getNumRecipes();
        // load in the same recipe 3 times into the database. 
        for(int i = 0; i < 3; i++) {
            rh.addToDB();
        }
        ArrayList<recipe> recipes = rh.getRecipes();
        // Assert database accounted for 3 new recipes and total count matches with database count.
        assertEquals(currentCount + 3, rh.getNumRecipes());
        assertEquals(currentCount + 3, recipes.size());
    }
    
}
