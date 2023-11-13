package main.java.client;

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
    private Stage primaryStage;
    private Scene scene;
    private Label title;
    private Label welcomeMsg;
    private Button editButton;
    private Button backButton;
    private ScrollPane recipes;
    private Model model;

    public recipesScreen(Stage primaryStage) {
        StackPane root = new StackPane();
        title = new Label("PantyPal");
        welcomeMsg = new Label("Saved Recipes");
        backButton = new Button("Back");
        editButton = new Button("Edit");
        this.model = new Model();
        this.primaryStage = primaryStage;

        backButton.setOnAction(e -> {
            homeScreen hs = new homeScreen(primaryStage);
            primaryStage.setScene(hs.getScene());
        });

        HBox heading = new HBox(backButton, title, editButton);
        heading.setAlignment(Pos.CENTER);
        heading.setSpacing(80);
        VBox text = new VBox(heading, welcomeMsg);
        text.setAlignment(Pos.CENTER);

        VBox uiElement = new VBox();
        setList(uiElement);
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

    public void setList(VBox list) {
        String response = model.performRequest("GET", null, null, null, "ALL", null);
        String[] recipes = response.split("\\|");
        System.out.println(recipes.length);
        for (String recipe : recipes) {
            System.out.println(recipe);
            Button rec = new Button(recipe);
            String recResponse = model.performRequest("GET", null, null, null, recipe, null);
            String name = recipe;
            String details = recResponse;
            rec.setStyle("-fx-background-color: #00000000; ");
            rec.setMaxHeight(10);
            rec.setMaxWidth(list.getMaxWidth());
            rec.setOnAction(e -> {
                detailedRecipeScreen dsr = new detailedRecipeScreen(primaryStage, name, details);
                primaryStage.setScene(dsr.getScene());
            });
            HBox hb = new HBox(rec);
            hb.setAlignment(Pos.CENTER_LEFT);
            list.getChildren().add(hb);
        }
    }
}
