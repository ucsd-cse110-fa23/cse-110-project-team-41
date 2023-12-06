package test.MS2Tests;
import org.junit.jupiter.api.Test;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import main.java.client.detailedRecipeScreen;
import main.java.client.Model;
import test.MockDatabase;

public class ScenarioTest5 {

    private Model model;
    private Button shareButton;
    boolean shared = false; //link has been shared and copied to clipboard
    boolean clickShare = true; //the share button has been pressed

    @Test
    public void MS2ScenarioTest5()   { 
        String link = model.shareRequest("recipeName");
            if(clickShare == true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Recipe Link Generated and Copied to Clipboard: \n" + link);
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(link);
                clipboard.setContent(content);
                alert.showAndWait();
                shared = true;
            }
        assert(shared);
    }
}

