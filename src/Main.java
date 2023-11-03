
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private Button savedRecipesButton; 
    private Button newRecipeButton; 
    private TextField title; 
    private TextField welcomeMsg; 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PantryPal"); 

        StackPane root = new StackPane(); 
        savedRecipesButton = new Button("Saved Recipes"); 
        newRecipeButton = new Button("New Recipes"); 
        HBox navButtons = new HBox(savedRecipesButton, newRecipeButton); 
        navButtons.setSpacing(20); 
        navButtons.setAlignment(Pos.CENTER); 
        title = new TextField("PantyPal"); 
        welcomeMsg = new TextField("Welcome to Pantry Pal!"); 
        title.setAlignment(Pos.CENTER); 
        title.setEditable(false); 
        welcomeMsg.setAlignment(Pos.CENTER); 
        welcomeMsg.setEditable(false); 
        VBox text = new VBox(title, welcomeMsg); 
        BorderPane welcomeScreen = new BorderPane(); 
        welcomeScreen.setTop(text); 
        welcomeScreen.setCenter(navButtons); 
        root.getChildren().addAll(welcomeScreen); 
        Scene scene = new Scene(root, 400, 300); 

        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 

    public static void main(String[] args) {
        launch(args); 
    }
}
