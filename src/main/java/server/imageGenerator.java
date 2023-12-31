package main.java.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class imageGenerator {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/images/generations"; 
    private static final String API_KEY = "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";
    private static final String MODEL = "dall-e-2";

    private String imagePrompt;
    private static String generatedImageURL;

    public imageGenerator(String imagePrompt){
        this.imagePrompt = imagePrompt;
    }

    public String getImageURL(){
        return generatedImageURL;
    }

    public String main() throws IOException, InterruptedException, URISyntaxException, JSONException {
        String imageURL = generateImage(imagePrompt);
        saveImage(imageURL,imagePrompt);
        return imageURL;
    }

    public static String generateImage(String prompt) throws IOException, InterruptedException, URISyntaxException, JSONException{

        int n = 1;

        //create request body which will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("n", n);
        requestBody.put("size", "256x256");

        // Create the HTTP client
        HttpClient client = HttpClient.newHttpClient();
    
    
        // Create the request object
        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(API_ENDPOINT))
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
        .build();
    
    
        // Send the request and receive the response
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        // Process the response
        String responseBody = response.body();
    
    
        JSONObject responseJson = new JSONObject(responseBody);
 
         
        JSONArray data = responseJson.getJSONArray("data");
        JSONObject image = data.getJSONObject(0);
        generatedImageURL = image.getString("url");
     
        System.out.println("DALL-E Response:");
        System.out.println(generatedImageURL);

        return generatedImageURL;
    }

    public static void saveImage(String generatedImageURL, String name) throws IOException , URISyntaxException  {

        // Download the Generated Image to Current Directory
        try(
            InputStream in = new URI(generatedImageURL).toURL().openStream()
        )
        {
            Files.copy(in, Paths.get(name +"image.png"));
        }
    }
        
}
