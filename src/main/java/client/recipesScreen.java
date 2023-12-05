package main.java.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.java.server.imageGenerator;

import java.io.File;


public class recipesScreen {
    private Stage primaryStage;
    private Scene scene;
    private Label title;
    private Label welcomeMsg;
    private Button editButton;
    private MenuButton filterButton;
    private Button backButton;
    private ScrollPane recipes;
    private Model model;

    public recipesScreen(Stage primaryStage) {
        StackPane root = new StackPane();
        title = new Label("PantryPal");
        welcomeMsg = new Label("Saved Recipes");
        backButton = new Button("Back");
        editButton = new Button("Edit");
        this.model = new Model();
        this.primaryStage = primaryStage;

        filterButton = new MenuButton("Filter by Meal");
        MenuItem all = new MenuItem("All");
        MenuItem breakfast = new MenuItem("Breakfast");
        MenuItem lunch = new MenuItem("Lunch");
        MenuItem dinner = new MenuItem("Dinner");
        filterButton.getItems().addAll(all, breakfast, lunch, dinner);

        backButton.setOnAction(e -> {
            homeScreen hs = new homeScreen(primaryStage);
            primaryStage.setScene(hs.getScene());
        });

        HBox heading = new HBox(backButton, title, editButton, filterButton);
        heading.setAlignment(Pos.CENTER);
        heading.setSpacing(80);
        VBox text = new VBox(heading, welcomeMsg);
        text.setAlignment(Pos.CENTER);

        VBox uiElement = new VBox();
        setList(uiElement, "all");
        recipes = new ScrollPane(uiElement);

        BorderPane recipeListScreen = new BorderPane();
        recipeListScreen.setTop(text);
        recipeListScreen.setCenter(recipes);
        root.getChildren().addAll(recipeListScreen);
        this.scene = new Scene(root, 400, 300);


        all.setOnAction(e -> {
            VBox uiE = new VBox();
            setList(uiE, "all");
            recipes = new ScrollPane(uiE);
            recipeListScreen.setCenter(recipes);
            primaryStage.setScene(new Scene(root, 400, 300));
        });
        breakfast.setOnAction(e -> {
            VBox uiE = new VBox();
            setList(uiE, "Breakfast");
            recipes = new ScrollPane(uiE);
            recipeListScreen.setCenter(recipes);
            primaryStage.setScene(new Scene(root, 400, 300));
        });
        lunch.setOnAction(e -> {
            VBox uiE = new VBox();
            setList(uiE, "Lunch");
            recipes = new ScrollPane(uiE);
            recipeListScreen.setCenter(recipes);
            primaryStage.setScene(new Scene(root, 400, 300));
        });
        dinner.setOnAction(e -> {
            VBox uiE = new VBox();
            setList(uiE, "Dinner");
            recipes = new ScrollPane(uiE);
            recipeListScreen.setCenter(recipes);
            primaryStage.setScene(new Scene(root, 400, 300));
        });
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setList(VBox list, String mealFilter) {
        String response = model.performRequest("GET", null, null, null, "ALL", null);
        String[] recipes = response.split("\\|");
        System.out.println(recipes.length);
        for (int i = 0; i < recipes.length - 1; i++) {
            System.out.println(i);
            Button rec = new Button(recipes[i]);
            String recResponse = model.performRequest("GET", null, null, null, recipes[i], null);
            System.out.println("recresponse: ");
            System.out.println(recResponse);
            System.out.println("done");
            String[] mealDetails = recResponse.split("\\$");
            String mealType = mealDetails[0];
            String details = mealDetails[1];
            String imageURL;

            //String imageURL = mealDetails[2];
            //need to get imageURL from mongoDB JSON object
            //String imageURL = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-Sd9bwBmEf5IDns4KIh3k3fXp/user-mlF2UiRIjgQpU5OH9QHus5gd/img-Mxf2GRV2JwmSkO7yzxs2xQz1.png?st=2023-12-05T10%3A32%3A23Z&se=2023-12-05T12%3A32%3A23Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-04T16%3A34%3A12Z&ske=2023-12-05T16%3A34%3A12Z&sks=b&skv=2021-08-06&sig=ZrHOLUPfVtALS316YtqbVBwngjn6zAtLgjuJDqGBLAg%3D";
           
           
           //regenerates image each time if it does not exist already locally
           //not saving in mongoDb since imageURL expires after around 2hrs
           
            String localImage = recipes[i]+"image.png";
            File imageFile = new File(localImage);
            if(!imageFile.exists()){
                imageGenerator recipeImage = new imageGenerator(recipes[i]);
                try{
                    recipeImage.main();
                } catch (Exception e1){
                    e1.printStackTrace();
                }
                imageURL = recipeImage.getImageURL();
            }else{
                imageURL = "file://"+imageFile.getAbsolutePath();
            }


           
            System.out.println(recipes[i]);
            if (mealType.equals(mealFilter) || mealFilter.equals("all")) {
                Label mealLabel = new Label(mealType);
                switch (mealType) {
                    case "Breakfast":
                        mealLabel.setTextFill(Color.color(0, 0, 1));
                        break;
                    case "Lunch":
                        mealLabel.setTextFill(Color.color(1, 0, 0));
                        break;
                    case "Dinner":
                        mealLabel.setTextFill(Color.color(0, 1, 0));
                        break;
                    default:
                        mealLabel.setTextFill(Color.color(0, 1, 1));
                        break;
                }

                ImageView imageView = new ImageView(new Image(imageURL));
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                String name = recipes[i];
                rec.setStyle("-fx-background-color: #00000000; ");
                rec.setMaxHeight(10);
                rec.setMaxWidth(list.getMaxWidth());
                rec.setOnAction(e -> {
                    detailedRecipeScreen dsr = new detailedRecipeScreen(primaryStage, name, details, imageURL);
                    primaryStage.setScene(dsr.getScene());
                });
                HBox hb = new HBox(imageView, rec, mealLabel);
                hb.setAlignment(Pos.CENTER_LEFT);
                list.getChildren().add(hb);
            }
        }
    }
}
