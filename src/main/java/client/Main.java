package main.java.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) { 
        checkServer(); 
        if (checkRemember()) {
            homeScreen hs = new homeScreen(primaryStage);
            primaryStage.setScene(hs.getScene());
            primaryStage.show();
        }else{
            loginScreen ls = new loginScreen(primaryStage); 
            primaryStage.setScene(ls.getScene()); 
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
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
    private boolean checkRemember(){
        File file = new File("src/main/java/client/user.dat");
        if (file.exists()) {
            //Read file
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String username = br.readLine();
                String password = br.readLine();
                br.close();
                Model model = new Model();
                String response = model.performLoginRequest("GET", username, password);
                if (response.contains("Invalid")) {
                    Alert alert = new Alert(AlertType.ERROR, "Invalid Username or Password Saved", ButtonType.OK);
                    alert.show();
                    return false;
                }else{
                    return true;
                }
            }catch(Exception e){
                System.out.println("Error: " + e);
                Alert alert = new Alert(AlertType.ERROR, "Error Reading Saved Username/Password Data", ButtonType.OK);
                alert.show();
                return false;
            }
        }else{
            return false;
        }
    }
}
