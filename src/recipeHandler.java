import java.io.*;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox; 
public class recipeHandler { 
    private ArrayList<recipe> allRecipes = new ArrayList<recipe>(); 
    public recipeHandler(){ 
        FileReader file;
        try {
            file = new FileReader("src/recipes.txt"); 
            BufferedReader br = new BufferedReader(file); 
            String currLine = null; 
            while((currLine = br.readLine()) != null){
                String[] info = currLine.split(","); 
                allRecipes.add(new recipe(info[0], info[1])); 
            } 
            br.close(); 
        } catch (Exception e) { 
            System.out.println("Exception Thrown"); 
        } 
    } 
    public VBox getRecipeElements(){ 
        // ArrayList<HBox> uiElements = new ArrayList<>(); 
        VBox uiElements = new VBox(); 
        for (recipe r : allRecipes){ 
            Button rec = new Button(r.getName() + ": " + r.getDetails()); 
            rec.setStyle("-fx-background-color: #00000000; "); 
            HBox hb = new HBox(rec); 
            hb.setAlignment(Pos.CENTER_LEFT); 
            uiElements.getChildren().add(hb); 
        } 
        return uiElements; 
    } 


}