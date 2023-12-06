package main.java.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    public homeScreen(Stage primaryStage) { 
        checkServer(); 
        primaryStage.setTitle("PantryPal");
        StackPane root = new StackPane();
        savedRecipesButton = new Button("Saved Recipes");
        newRecipeButton = new Button("New Recipes");
        savedRecipesButton.setOnAction(e -> {
            recipesScreen rs = new recipesScreen(primaryStage);
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
            AddRecipe newRecipe = new AddRecipe(this.scene, primaryStage);
            primaryStage.setScene(newRecipe.getScene());
        });
    }

    public Scene getScene() {
        return this.scene;
    } 
    private void checkServer(){ 
        Model model = new Model(); 
        String response = model.performRequest("GET", null, null, null, null, null); 
        if(response.contains("java.net.ConnectException")){ 
            serverError(); 
            response = model.performRequest("GET", null, null, null, null, null); 
        } 
    } 
    private void serverError() { 
        //Stop program is server isn't running
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Missing Server"); 
        alert.setHeaderText("Server Not Active!"); 
        alert.setContentText("Please Load Up Server."); 
        alert.showAndWait(); 
        System.exit(0);
    } 
}
