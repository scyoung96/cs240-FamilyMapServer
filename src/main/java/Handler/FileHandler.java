package Handler;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                String urlPath = exchange.getRequestURI().toString();
                if (urlPath == null || urlPath.equals("/")) {
                    urlPath = "/index.html";
                }

                String relativePath = "web" + urlPath;

                // Create a response body that we will send as a response
                OutputStream respBody = exchange.getResponseBody();

                // Check if the requested file exists
                File reqFile = new File(relativePath);
                // If the file does not exist, return 404
                if (!reqFile.exists()) {
                    reqFile = new File("web/HTML/404.html");
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                }
                // If the file does exist, return its contents in the response
                else {
                    // Start sending the HTTP response to the client, starting with
                    // the status code and any defined headers.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                Files.copy(reqFile.toPath(), respBody);

                respBody.close();

                success = true;
            }

            if (!success) {
                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                // Since the client request was invalid, they will not receive the
                // list of games, so we close the response body output stream,
                // indicating that the response is complete.
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            // Since the server is unable to complete the request, the client will
            // not receive the list of games, so we close the response body output stream,
            // indicating that the response is complete.
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }

    /*
        The writeString method shows how to write a String to an OutputStream.
    */
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}