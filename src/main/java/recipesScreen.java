package main.java;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class recipesScreen {
    private Scene scene;
    private Label title;
    private Label welcomeMsg;
    private Button editButton;
    private Button backButton;
    private ScrollPane recipes;

    public recipesScreen(Stage primaryStage) {
        StackPane root = new StackPane();
        title = new Label("PantyPal");
        welcomeMsg = new Label("Saved Recipes");
        backButton = new Button("Back");
        editButton = new Button("Edit");

        backButton.setOnAction(e -> {
            homeScreen hs = new homeScreen(primaryStage);
            primaryStage.setScene(hs.getScene());
        });

        HBox heading = new HBox(backButton, title, editButton);
        heading.setAlignment(Pos.CENTER);
        heading.setSpacing(80);
        VBox text = new VBox(heading, welcomeMsg);
        text.setAlignment(Pos.CENTER);

        recipeHandler rh = new recipeHandler();
        VBox uiElement = rh.getRecipeElements(primaryStage);
        recipes = new ScrollPane(uiElement);

        BorderPane recipeListScreen = new BorderPane();
        recipeListScreen.setTop(text);
        recipeListScreen.setCenter(recipes);
        root.getChildren().addAll(recipeListScreen);
        this.scene = new Scene(root, 400, 300);
    }

    public Scene getScene() {
        return this.scene;
    }
}
