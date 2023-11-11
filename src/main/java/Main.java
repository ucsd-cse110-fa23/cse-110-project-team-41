package main.java;

import javafx.application.Application;
import javafx.concurrent.Task;
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
    @Override 
    public void start(Stage primaryStage) {
        homeScreen hs = new homeScreen(primaryStage); 
        primaryStage.setScene(hs.getScene()); 
        primaryStage.show(); 
    } 

    public static void main(String[] args) {
        launch(args); 
    }
}
