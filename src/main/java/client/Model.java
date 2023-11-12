package main.java.client;

import java.io.File;
import java.nio.file.Files;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
/**
 * Handles requests from the client and sends them to the server.
 * 
 */
public class Model {
    public String performRequest(String method, File mealTime, File ingredients, String postType, String recipeName, String details){
        String response = ""; 
        try{ 
            String urlString = "http://localhost:8100/";
            if(recipeName != null){ 
                urlString += "/?=" + recipeName;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            if(method.equals("POST")){
                
                //Send mealtime.wav to server
                String boundary = Long.toHexString(System.currentTimeMillis());
                String CRLF = "\r\n";

                //Set up request headers
                connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                OutputStream output = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true);
                writer.append("--" + boundary).append(CRLF);
                if(postType.equals("mealTime")){
                    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + mealTime.getName() + "\"").append(CRLF);
                    writer.append("Content-Type: " + connection.guessContentTypeFromName(mealTime.getName())).append(CRLF);
                    writer.append(CRLF).flush();
                    Files.copy(mealTime.toPath(), output);
                    output.flush();
                    writer.append(CRLF).flush();
                }else if(postType.equals("ingredients")){
                    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + ingredients.getName() + "\"").append(CRLF);
                    writer.append("Content-Type: " + connection.guessContentTypeFromName(ingredients.getName())).append(CRLF);
                    writer.append(CRLF).flush();
                    Files.copy(ingredients.toPath(), output);
                    output.flush();
                    writer.append(CRLF).flush();
                }
                writer.append("--" + boundary + "--").append(CRLF).flush();
                writer.close();
                output.close();

                //Get response from server
                response = processResponse(connection);
            }else if (method.equals("GET")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while((line = reader.readLine()) != null){
                    response += line + "\n";
                }
                reader.close();
            }else if(method.equals("PUT")){
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(recipeName + "\n" + details);
                writer.close();
            }else if(method.equals("DELETE")){
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(recipeName);
                writer.close();
            }else{
                throw new Exception("Not Valid Request Method");
            }
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return response;
    }

    private String processResponse(HttpURLConnection connection) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String response = "";
        while((line = reader.readLine()) != null){
            response += line + "\n";
        }
        reader.close();
        return response;
    }
}
