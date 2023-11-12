package main.java.server;
import com.mongodb.internal.logging.LogMessage.Entry;
import com.sun.glass.ui.SystemClipboard;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.*;

import org.bson.Document;
public class RequestHandler implements HttpHandler{
    private final database db;
    public RequestHandler(database db){
        this.db = db;
    }

    public void handle(HttpExchange exchange) throws IOException{
        String response = "Request received";
        String method = exchange.getRequestMethod();
        try{
            if (method.equals("GET")) {
                response = handleGet(exchange);
            } else if (method.equals("POST")) {
                response = handlePost(exchange);
            }else if (method.equals("PUT")) {
                response = handlePut(exchange);
            }else if(method.equals("DELETE")){
                response = handleDelete(exchange);
            }else{
                throw new Exception("Not Valid Request Method");
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream out = exchange.getResponseBody();
        out.write(response.getBytes());
        out.close();
    }

    private String handleGet(HttpExchange exchange){
        String response = "Invalid GET Request";
        URI uri = exchange.getRequestURI();
        String query = uri.getRawQuery();
        if(query != null){
            String value = query.substring(query.indexOf("=") + 1);
            if(value.equals("ALL")){
                Iterator<Document> itr = db.getAll();
                while(itr.hasNext()){
                    response += itr.next().getString("title") + "\n";
                }
            }else{
                recipe out = db.getRecipe(value);
                if (out == null) {
                    response = "No recipe found with that name";
                }else{
                    response = out.getDetails();
                }
            }
        }
        return response;
    }

    private String handlePost(HttpExchange exchange) throws IOException{
        System.out.println("Handling post request");
        System.out.println(exchange.getRequestHeaders());
        String response = "Invalid POST Request";
        // InputStream is = exchange.getRequestBody();
        // BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // for(String line = reader.readLine(); line != null; line = reader.readLine()){
        //     System.out.println(line);
        // }
        File output = processMultipart(exchange);
        // Files.copy(is, output.toPath(), StandardCopyOption.REPLACE_EXISTING);
        // is.close();
        //Read multipart/form-data
        

        URI uri = exchange.getRequestURI();
        String query = uri.getRawQuery();
        return response;
    }

    private String handlePut(HttpExchange exchange){
        String response = "Invalid PUT Request";




        return response;
    }

    private String handleDelete(HttpExchange exchange){
        String response = "Invalid DELETE Request";

        
        


        return response;
    }

    private File processMultipart(HttpExchange exchange) throws IOException{
        InputStream in = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        boolean content = false;
        String boundary = reader.readLine();
        String fileName = "";
        String line;
        File output = null;
        FileOutputStream out = null;
        while ((line = reader.readLine()) != null) {
            if(line.contains(boundary)){
                break;
            }
            if (content) {
                output = new File("src/main/java/server/" + fileName);
                //Clear the existing file
                if(output.exists()){
                    output.delete();
                }
                output.createNewFile();
                out = new FileOutputStream(output);
                out.write(line.getBytes());
            }
            if(line.contains("filename")){
                fileName = line.substring(line.indexOf("filename") + 10, line.length() - 1);
                System.out.println(fileName);
            }
            if(line.contains("Content-Type")){
                content = true;
                line = reader.readLine();
            }
        }
        out.close();
        in.close();
        return output;
    }
}
