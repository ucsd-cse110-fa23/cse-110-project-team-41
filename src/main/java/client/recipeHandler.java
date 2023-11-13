package main.java.client;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.print.Doc;

import org.bson.Document;
import org.json.JSONObject;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.server.database;
import main.java.server.recipe;

public class recipeHandler {
    private ArrayList<recipe> allRecipes;
    private database dbOBJ = new database();

    public recipeHandler() {
        allRecipes = new ArrayList<recipe>();
    }

    public void addToDB() {
        // this.dbOBJ.clearDB();
        this.dbOBJ.addFileToDb();
    }

    private void getRecipesFromDB() {
        Iterator<Document> it = this.dbOBJ.getAll();
        while (it.hasNext()) {
            Document doc = it.next();
            recipe newRec = new recipe(doc.getString("title"), "Ingredients: \n" + doc.getString("ingredients")
                    + "\nInstructions: \n" + doc.getString("instructions"));
            allRecipes.add(newRec);
            System.out.println(newRec);
        }
    }

    // public VBox getRecipeElements(Stage primaryStage) {
    //     this.getRecipesFromDB();
    //     // ArrayList<HBox> uiElements = new ArrayList<>();
    //     VBox uiElements = new VBox();
    //     for (recipe r : allRecipes) {
    //         // Button rec = new Button(r.getName() + ": " + r.getDetails());
    //         Button rec = new Button(r.getName());
    //         rec.setStyle("-fx-background-color: #00000000; ");
    //         rec.setMaxHeight(10);
    //         // rec.setMinHeight(10);
    //         rec.setMaxWidth(uiElements.getMaxWidth());
    //         rec.setOnAction(e -> {
    //             detailedRecipeScreen dsr = new detailedRecipeScreen(primaryStage, r);
    //             primaryStage.setScene(dsr.getScene());
    //         });
    //         HBox hb = new HBox(rec);
    //         hb.setAlignment(Pos.CENTER_LEFT);
    //         uiElements.getChildren().add(hb);
    //     }
    //     return uiElements;
    // }

}
