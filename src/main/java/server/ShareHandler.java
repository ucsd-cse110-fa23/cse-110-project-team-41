package main.java.server;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class ShareHandler implements HttpHandler{
    private database db;
    
    public ShareHandler(database db) {
        this.db = db;
    }

    public void handle(HttpExchange exchange) throws IOException{
        String response = "Request received";
        String method = exchange.getRequestMethod();
        try {
            if (method.equals("GET")) {
                response = handleGet(exchange);
            }else {
                response = "Invalid Request Method";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            response = "Error: " + e.getMessage();
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream out = exchange.getResponseBody();
        out.write(response.getBytes());
        out.close();
    }

    private String handleGet(HttpExchange exchange) {
        String response = "Invalid GET request";
        URI uri = exchange.getRequestURI();
        String query = uri.getRawQuery();
        String name = "";
        if (query != null) {
            name = query.substring(query.indexOf("=") + 1);
            name = name.replaceAll("%20", " ");
        }else{
            return "Invalid Request";
        }
        System.out.println("Name: " + name);
        recipe r = db.getRecipe(name);
        if (r == null) {
            return "Invalid Recipe Name";
        }
        StringBuilder htmlBuilder = new StringBuilder();
        //Build html response
        //TODO: Implement image support
        htmlBuilder.append("<html>")
            .append("<body>")
            .append("<h1>").append(r.getName()).append("</h1>")
            //Image Here
            .append("<h2>").append("Details").append("</h2>")
            .append("<p>").append(r.getDetails().replaceAll("\n","<br\\>")).append("</p>")
            .append("</body>")
            .append("</html>");

        response = htmlBuilder.toString();
        return response;
    }
}
