package main.java.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.server.database;
import main.java.server.recipe;

public class detailedRecipeScreen {
    private Scene scene;
    private Label title;
    private Label recNameMsg;
    private TextArea recLabel;
    private Button editButton;
    private Button backButton; 
    private Button deleteButton; 
    private ScrollPane detailedRecipe;
    private Stage primaryStage;
    private boolean editing;
    private recipe rec;

    public detailedRecipeScreen(Stage primaryStage, recipe rec) {
        StackPane root = new StackPane();
        title = new Label("PantyPal");
        recNameMsg = new Label(rec.getName());
        backButton = new Button("Back");
        editButton = new Button("Edit"); 
        deleteButton = new Button("Delete"); 
        this.primaryStage = primaryStage;
        editing = false;
        this.rec = rec;

        HBox r_buttons = new HBox(editButton, deleteButton); 
        HBox heading = new HBox(backButton, title, r_buttons); 
        heading.setAlignment(Pos.CENTER);
        heading.setSpacing(80);

        HBox subHeading = new HBox(recNameMsg);
        subHeading.setAlignment(Pos.TOP_LEFT);
        VBox text = new VBox(heading, subHeading);
        text.setAlignment(Pos.CENTER);
        this.recLabel = new TextArea(rec.getDetails());
        this.recLabel.setEditable(false);
        // recLabel.setWrapText(true);
        detailedRecipe = new ScrollPane(recLabel);
        detailedRecipe.setFitToWidth(true); 
        detailedRecipe.setFitToHeight(true); 

        BorderPane detailedScreen = new BorderPane(); 
        detailedScreen.setTop(text); 
        detailedScreen.setCenter(detailedRecipe); 
        root.getChildren().addAll(detailedScreen); 
        this.scene = new Scene(root, 400, 300); 
        addListeners();
    }

    public Scene getScene() {
        return this.scene;
    }

    private void addListeners() {
        backButton.setOnAction(e -> {
            if (editing) {
                editing = false;
                editButton.setText("Edit");
                backButton.setText("Back");
                // Disable editing for detailedRecipe and reset text
                recLabel.setText(rec.getDetails());
                recLabel.setEditable(false);
            } else {
                recipesScreen rs = new recipesScreen(primaryStage);
                primaryStage.setScene(rs.getScene());
            }
        }); 
        deleteButton.setOnAction(e -> { 

        });  

        editButton.setOnAction(e -> {
            if (!editing) {
                editing = true; 
                editButton.setText("Save");
                backButton.setText("Cancel");
                // Enable editing for detailedRecipe
                recLabel.setEditable(true);
            } else {
                editing = false;
                editButton.setText("Edit");
                backButton.setText("Back");
                // Disable editing for detailedRecipe and reset text
                recLabel.setEditable(false);
                // TODO: Save changes to recipe in DB

                database dbOBJ = new database();
                dbOBJ.editRecipe(rec.getName(), recLabel.getText());
            }
        });
    }
}
