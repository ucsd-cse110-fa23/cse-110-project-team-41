
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class homeScreen{
    private Button savedRecipesButton; 
    private Button newRecipeButton; 
    private Label title; 
    private Label welcomeMsg; 
    private Scene scene; 
    public homeScreen(Stage primaryStage){ 
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
        title = new Label("PantyPal"); 
        welcomeMsg = new Label("Welcome to Pantry Pal!"); 
        VBox text = new VBox(title, welcomeMsg); 
        text.setAlignment(Pos.CENTER); 
        BorderPane welcomeScreen = new BorderPane(); 
        welcomeScreen.setTop(text); 
        welcomeScreen.setCenter(navButtons); 
        root.getChildren().addAll(welcomeScreen); 
        this.scene = new Scene(root, 400, 300); 
    } 
    public Scene getScene(){
        return this.scene; 
    }
} 

