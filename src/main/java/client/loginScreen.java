package main.java.client;

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

public class loginScreen { 
    private Button signupButton; 
    private Button loginButton; 
    private Label loginMsg; 
    private TextField user; 
    private TextField pass; 
    private Label title; 
    private Scene scene;

    public loginScreen(Stage primaryStage) { 
        user = new TextField(); 
        user.setPromptText("Username: "); 
        pass = new TextField(); 
        pass.setPromptText("Password: "); 
        primaryStage.setTitle("PantryPal"); 
        loginButton = new Button("Log In"); 
        signupButton = new Button("Sign Up"); 
        loginButton.setOnAction(e -> {
            // TODO: check credentials 
            homeScreen ls = new homeScreen(primaryStage); 
            primaryStage.setScene(ls.getScene()); 
        }); 
        signupButton.setOnAction(e -> {
            // TODO: 
        }); 
        HBox buttons = new HBox(loginButton,signupButton); 
        buttons.setAlignment(Pos.CENTER); 
        buttons.setSpacing(100); 
        loginMsg = new Label("Login Information: "); 
        title = new Label("PantyPal"); 
        VBox vbT = new VBox(title); 
        vbT.setAlignment(Pos.CENTER); 
        VBox vb = new VBox(loginMsg, user, pass, buttons);  
        vb.setAlignment(Pos.CENTER); 
        BorderPane logscreen = new BorderPane(); 
        logscreen.setTop(vbT); 
        logscreen.setCenter(vb); 
        StackPane root = new StackPane(); 
        root.getChildren().addAll(logscreen);
        this.scene = new Scene(root, 400, 300); 
    }

    public Scene getScene() {
        return this.scene;
    }
}
