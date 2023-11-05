import java.io.File;

import javax.sound.sampled.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Creates a new recipe from user input and adds it to database.
 * Includes the UI for the user to input the recipe information using voice recordings
 */
public class AddRecipe{
    private Scene currScene;
    private Stage parent;
    private Button backButton;
    private Button submitButton;
    private Button recordButton;
    private Button stopButton;
    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;
    private Label recordingLabel;

    String defaultButtonStyle = "-fx-border-color: #000000; -fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px;";
    String defaultLabelStyle = "-fx-font: 13 arial; -fx-pref-width: 175px; -fx-pref-height: 50px; -fx-text-fill: red; visibility: hidden";

    public AddRecipe(Stage parent) {
        this.parent = parent;
        GridPane root = new GridPane();
        backButton = new Button("Back");
        submitButton = new Button("Submit");
        recordButton = new Button("Record");
        recordingLabel = new Label("Recording...");
        


        



        parent.setTitle("Add Recipe");
        this.currScene= new Scene(root, 400, 300);
    }

    /*
     * Return scene to be displayed
     * @return scene object to be shown in main stage
     */
    public Scene getScene(){
        return currScene;
    }

    public void addListeners(){
        recordButton.setOnAction(e -> {
            recordingLabel.setText("Recording...");
            recordButton.setDisable(true);
            stopButton.setDisable(false);
            captureAudio();
        });
        stopButton.setOnAction(e -> {
            recordingLabel.setText("Record");
            recordButton.setDisable(false);
            stopButton.setDisable(true);
            stopCapture();
        });
    }

    /**
     * Returns the audio format information
     * @return the audio format used for the recording
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    private void captureAudio(){
         try {
            // the format of the TargetDataLine
            DataLine.Info dataLineInfo = new DataLine.Info(
                    TargetDataLine.class,
                    audioFormat);
            // the TargetDataLine used to capture audio data from the microphone
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            recordingLabel.setVisible(true);

            // the AudioInputStream that will be used to write the audio data to a file
            AudioInputStream audioInputStream = new AudioInputStream(
                    targetDataLine);

            // the file that will contain the audio data
            File audioFile = new File("recording.wav");
            AudioSystem.write(
                    audioInputStream,
                    AudioFileFormat.Type.WAVE,
                    audioFile);
            recordingLabel.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void stopCapture(){
        targetDataLine.stop();
        targetDataLine.close();
    }
}
