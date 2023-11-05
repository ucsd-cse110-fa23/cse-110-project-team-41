import java.io.FileWriter;
import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class ChatGPT{

    private static final String MODEL = "text-davinci-003"; 
    private static final String API_KEY = "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"; 
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";

    public static void main(String[] args) throws Exception {
        String mealType = "dinner"; //temp holder 
        String ingredients = "Chicken, rice, eggs"; //temp holder

        String recipe = generateRecipe(mealType, ingredients);

        saveRecipe("recipe.txt", recipe);
    }


    //generates recipe from chatGPT
    private static String generateRecipe(String mealType, String ingredients) throws Exception {
        String prompt = "Give me a concise recipe for a " +
                mealType +
                " meal that ONLY uses the following ingredients: " +
                ingredients;
        int maxTokens = 250;

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
        .newBuilder()
        .uri(new URI(API_ENDPOINT))
        .header("Content-Type", "application/json")
        .header("Authorization", String.format("Bearer %s", API_KEY))
        .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
        .build();

        HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );

        String responseBody = response.body();

        JSONObject responseJSON = new JSONObject(responseBody);
        JSONArray choices = responseJSON.getJSONArray("choices");
        String recipeGPT = choices.getJSONObject(0).getString("text");
        
        return recipeGPT;
    }

    //saves recipe to a file 
    private static void saveRecipe(String fp, String recipe) {
        try {
            FileWriter fw = new FileWriter(fp);
            fw.write(recipe);
            fw.close();
            System.out.println("Recipe saved to " + fp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
