package test.MS2Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import java.util.Iterator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.server.recipe;
import java.util.ArrayList;

/*
 * UNIT TESTS
 * A class to test tasks related to US5 in PantryPal2.
 * Specifically aimed at testing mealTag filtration
 */
public class MS2US5Test {
    ArrayList<recipe> recipes;

    @BeforeEach 
    void setUp() {
        recipes = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            switch (i % 3) {
                case 1:
                    recipes.add(new recipe("Recipe " + i, "Breakfast", "imageURl", "Description " + i));
                    break;
                case 2:
                    recipes.add(new recipe("Recipe " + i, "Lunch", "imageURl", "Description " + i));
                    break;
                default:
                    recipes.add(new recipe("Recipe " + i, "Dinner", "imageURl", "Description " + i));
                    break;
            }
        }
    }

    /* 
     * Simulates the user only wanting Breakfast recipes to show, filtering out Lunch and Dinner
     */
    @Test
    void testFilterBreakfast() {
        String mealFilter = "Breakfast";
        Iterator<recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            recipe recipe = iterator.next();
            if (!recipe.getMealType().equals(mealFilter)) {
                iterator.remove();
            }
        }
        assertEquals(3, recipes.size());
        assertEquals(mealFilter, recipes.get(0).getMealType());
        assertEquals(mealFilter, recipes.get(1).getMealType());
        assertEquals(mealFilter, recipes.get(2).getMealType());
    }  

    @Test
    void testFilterNull() {
        String mealFilter = "";
        Iterator<recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            recipe recipe = iterator.next();
            if (!recipe.getMealType().equals(mealFilter)) {
                iterator.remove();
            }
        }
        assertEquals(9, recipes.size());
    }  
}