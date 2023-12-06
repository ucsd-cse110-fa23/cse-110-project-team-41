package main.java.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class homeScreen {
    private Button savedRecipesButton;
    private Button newRecipeButton;
    private Label title;
    private Label welcomeMsg;
    private Scene scene;
    private String username;

    public homeScreen(String username, Stage primaryStage) {
        primaryStage.setTitle("PantryPal");
        StackPane root = new StackPane();
        savedRecipesButton = new Button("Saved Recipes");
        newRecipeButton = new Button("New Recipes");
        this.username = username;
        savedRecipesButton.setOnAction(e -> {
            recipesScreen rs = new recipesScreen(username, primaryStage);
            primaryStage.setScene(rs.getScene());
        }); 
        HBox navButtons = new HBox(savedRecipesButton, newRecipeButton);
        navButtons.setSpacing(20);
        navButtons.setAlignment(Pos.CENTER);
        title = new Label("PantryPal"); 
        welcomeMsg = new Label("Welcome to Pantry Pal!");
        VBox text = new VBox(title, welcomeMsg);
        text.setAlignment(Pos.CENTER);
        BorderPane welcomeScreen = new BorderPane();
        welcomeScreen.setTop(text);
        welcomeScreen.setCenter(navButtons);
        root.getChildren().addAll(welcomeScreen);
        this.scene = new Scene(root, 400, 300);
        newRecipeButton.setOnAction(e -> {
            AddRecipe newRecipe = new AddRecipe(username, this.scene, primaryStage);
            primaryStage.setScene(newRecipe.getScene());
        });
    }

    public Scene getScene() {
        return this.scene;
    }
}
