package Handler;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.*;

import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.LoadResult;
import Service.LoadService;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;

public class LoadHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();

                // Read JSON string from the input stream
                String reqDataString = readString(reqBody);

                // Display/log the request JSON data
//                System.out.println(reqDataString);

// BASICALLY, GET ALL THE REQUEST DATA OUT OF THE JSON, CREATE A Request, CALL Service,
// SAVE THE RESULT IN A Result, AND THEN RE-JSONIFY IT TO SEND BACK IN THE RESPONSE
                Map<String, List<Map<String, String>>> reqData = new Gson().fromJson(reqDataString, Map.class);
                List<Map<String, String>> userList = reqData.get("users");
                List<Map<String, String>> personList = reqData.get("persons");
                List<Map<String, String>> eventList = reqData.get("events");

                User[] userArr = new User[userList.size()];
                Person[] personArr = new Person[personList.size()];
                Event[] eventArr = new Event[eventList.size()];

                int arrCount = 0;

                for (Map<String, String> userObj : userList) {
                    String userObjStr = userObj.toString().replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
                    String userObjStr2 = userObjStr.replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
                    User user = new Gson().fromJson(userObjStr2, User.class);

                    userArr[arrCount] = user;

                    arrCount++;
                }

                arrCount = 0;

                for (Map<String, String> personObj : personList) {
                    String personObjStr = personObj.toString().replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
                    String personObjStr2 = personObjStr.replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
                    Person person = new Gson().fromJson(personObjStr2, Person.class);

                    personArr[arrCount] = person;

                    arrCount++;
                }

                arrCount = 0;

                for (Map<String, String> eventObj : eventList) {
                    // regex manipulation: replaces all spaces in every property for the events with underscores (_)
                    String eventObjStr = eventObj.toString().replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
                    String eventObjStr2 = eventObjStr.replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
                    Event event = new Gson().fromJson(eventObjStr2, Event.class);

                    // we don't want underscores in country, city, or eventType, so change them back to spaces
                    event.setCountry(event.getCountry().replaceAll("([a-zA-Z]+?)_([a-zA-Z]+?)", "$1 $2"));
                    event.setCountry(event.getCountry().replaceAll("([a-zA-Z]+?)_([a-zA-Z]+?)", "$1 $2"));
                    event.setCity(event.getCity().replaceAll("([a-zA-Z]+?)_([a-zA-Z]+?)", "$1 $2"));
                    event.setCity(event.getCity().replaceAll("([a-zA-Z]+?)_([a-zA-Z]+?)", "$1 $2"));
                    event.setEventType(event.getEventType().replaceAll("([a-zA-Z]+?)_([a-zA-Z]+?)", "$1 $2"));
                    event.setEventType(event.getEventType().replaceAll("([a-zA-Z]+?)_([a-zA-Z]+?)", "$1 $2"));

                    eventArr[arrCount] = event;

                    arrCount++;
                }

                LoadRequest request = new LoadRequest(userArr,
                        personArr,
                        eventArr);

                LoadService service = new LoadService();
                LoadResult result = service.LoadService(request);

                String response = new Gson().toJson(result);

                if (result.isSuccess()) {
                    // Start sending the HTTP response to the client, starting with
                    // the status code and any defined headers.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    // Start sending the HTTP response to the client, starting with
                    // the status code and any defined headers.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                // Get the response body output stream.
                OutputStream respBody = exchange.getResponseBody();

                // Write the JSON string to the output stream.
                writeString(response, respBody);

                // Close the output stream.  This is how Java knows we are done
                // sending data and the response is complete.
                respBody.close();

                success = true;
            }

            if (!success) {
                System.out.println("400");
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
		The readString method shows how to read a String from an InputStream.
	*/
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
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