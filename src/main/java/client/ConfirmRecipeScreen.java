package main.java.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class ConfirmRecipeScreen {
    private Scene scene;
    private Label title;
    private Label recNameMsg;
    private TextArea recLabel;
    private Button saveButton;
    private Button deleteButton; 
    private ScrollPane detailedRecipe;
    private Stage primaryStage;
    private Model model;
    private String name;
    private String details;

    public ConfirmRecipeScreen(Stage primaryStage, String name, String details) {
        StackPane root = new StackPane();
        title = new Label("PantyPal");
        recNameMsg = new Label(name);
        saveButton = new Button("Save"); 
        deleteButton = new Button("Delete"); 
        this.primaryStage = primaryStage;
        this.model = new Model();
        this.name = name;
        this.details = details;

        HBox r_buttons = new HBox(saveButton, deleteButton); 
        HBox heading = new HBox(title, r_buttons); 
        heading.setAlignment(Pos.CENTER);
        heading.setSpacing(80);

        HBox subHeading = new HBox(recNameMsg);
        subHeading.setAlignment(Pos.TOP_LEFT);
        VBox text = new VBox(heading, subHeading);
        text.setAlignment(Pos.CENTER);
        this.recLabel = new TextArea(details);
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
        deleteButton.setOnAction(e -> { 
            System.out.println("Deleting: " + name);
            String response = model.performRequest("DELETE", null, null, null, name, null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Recipe deleted!");
            alert.showAndWait();
            System.out.println(response);
            //Go Back to recipesScreen
            recipesScreen rs = new recipesScreen(primaryStage);
            primaryStage.setScene(rs.getScene());
        });  

        saveButton.setOnAction(e -> {
            Alert saved = new Alert(Alert.AlertType.INFORMATION, "Recipe saved!");
            saved.showAndWait();
            recipesScreen rs = new recipesScreen(primaryStage);
            primaryStage.setScene(rs.getScene());
        });
    }
}
