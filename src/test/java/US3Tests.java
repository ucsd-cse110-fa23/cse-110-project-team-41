
import org.junit.jupiter.api.Test;

import main.java.recipe;
import main.java.recipeHandler;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class US3Tests {
    private recipeHandler rh;
    private File f; 
    private FileWriter fw;
    private String uri; 
    private BufferedWriter bw;
    
    @BeforeEach
    void setUp() {
        uri = "/Users/josuemartinez/Documents/GitHub/cse-110-project-team-41/src/test/java/US3Mocks/recipes.txt";
        f = new File(uri);
        try {
            fw = new FileWriter(f.getAbsoluteFile());
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
        bw = new BufferedWriter(fw);  
    }
    
    @Test
    void testZeroRecipes() {
        rh = new recipeHandler();
        assertEquals(0, rh.getNumRecipes());
    }

    @Test
    void testAddRecipes() {
        String text = "";
        for(int i = 1; i <= 20; i++) {
            text = "Recipe " + i + ", description - ... for recipe " + i;
            try {
                // fw.write("Recipe " + i + ": description - ... for recipe " + i);
                bw.write(text + "\n");
                bw.flush();
            } catch (IOException e) {
                System.out.println("Cannot write recipes to file!");
            }
        }
        rh = new recipeHandler();
        assertEquals(20, rh.getNumRecipes());

        // Final test to prove tracked added recipes
        String expRecName = "Recipe 21";
        String expRecDesc = " description - ... for recipe 21";
        try {
            bw.write(expRecName + "," + expRecDesc);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rh = new recipeHandler();
        
        assertEquals(21, rh.getNumRecipes());

        ArrayList<recipe> recipes = rh.getRecipes();
        String actualRecName = recipes.get(20).getName();
        String actualRecDesc = recipes.get(20).getDetails();

        assertEquals(expRecDesc, actualRecDesc);
        assertEquals(expRecName, actualRecName);
    }
    
}
