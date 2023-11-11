package main.java;
import java.io.*;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
public class recipeHandler { 
    private ArrayList<recipe> allRecipes = new ArrayList<recipe>(); 
    private int numRecipes = 0; 
    public recipeHandler(){ 
        FileReader file;
        // Changed URI for testing US3, use "/src/main/java/recipes.txt" path when not testing
        String uri = "/Users/josuemartinez/Documents/GitHub/cse-110-project-team-41/src/test/java/US3Mocks/recipes.txt";
        try {
            file = new FileReader(uri); 
            BufferedReader br = new BufferedReader(file); 
            String currLine = null; 
            while((currLine = br.readLine()) != null){
                String[] info = currLine.split(","); 
                allRecipes.add(new recipe(info[0], info[1])); 
                numRecipes += 1;
            } 
            br.close(); 
        } catch (Exception e) { 
            System.out.println("Exception Thrown"); 
        } 
    } 
    public VBox getRecipeElements(Stage primaryStage){ 
        // ArrayList<HBox> uiElements = new ArrayList<>(); 
        VBox uiElements = new VBox(); 
        for (recipe r : allRecipes){ 
            Button rec = new Button(r.getName() + ": " + r.getDetails()); 
            rec.setStyle("-fx-background-color: #00000000; "); 
            rec.setOnAction(e -> { 
                detailedRecipeScreen dsr = new detailedRecipeScreen(primaryStage, r); 
                primaryStage.setScene(dsr.getScene()); 
            }); 
            HBox hb = new HBox(rec); 
            hb.setAlignment(Pos.CENTER_LEFT); 
            uiElements.getChildren().add(hb); 
        } 
        return uiElements; 
    } 
    public ArrayList<recipe> getRecipes() {
        return allRecipes;
    }

    public int getNumRecipes() {
        return numRecipes;
    }


}
