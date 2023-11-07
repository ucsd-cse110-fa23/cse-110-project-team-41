package main.java;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class detailedRecipeScreen { 
    private Scene scene; 
    private Label title; 
    private Label recNameMsg; 
    private Button editButton; 
    private Button backButton; 
    private ScrollPane detailedRecipe; 
    public detailedRecipeScreen(Stage primaryStage, recipe rec){ 
        StackPane root = new StackPane(); 
        title = new Label("PantyPal"); 
        recNameMsg = new Label(rec.getName()); 
        backButton = new Button("Back"); 
        editButton = new Button("Edit"); 

        backButton.setOnAction(e -> { 
            recipesScreen rs = new recipesScreen(primaryStage); 
            primaryStage.setScene(rs.getScene()); 
        }); 

        HBox heading = new HBox(backButton, title, editButton); 
        heading.setAlignment(Pos.CENTER); 
        heading.setSpacing(80); 

        HBox subHeading = new HBox(recNameMsg); 
        subHeading.setAlignment(Pos.TOP_LEFT); 
        VBox text = new VBox(heading, subHeading); 
        text.setAlignment(Pos.CENTER); 
        Label recLabel = new Label(rec.getDetails()); 
        recLabel.setWrapText(true); 
        detailedRecipe = new ScrollPane(recLabel); 
        detailedRecipe.setFitToWidth(true); 
        detailedRecipe.setFitToHeight(false); 

        BorderPane detailedScreen = new BorderPane(); 
        detailedScreen.setTop(text); 
        detailedScreen.setCenter(detailedRecipe); 
        root.getChildren().addAll(detailedScreen); 
        this.scene = new Scene(root, 400, 300); 
    } 
    public Scene getScene(){
        return this.scene; 
    }
}

