import java.io.*;
import java.net.*;

import Handler.*;
import com.sun.net.httpserver.*;

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    private void run(String portNumber) {
        System.out.println(String.format("Initializing HTTP Server on port %s", portNumber));

        try {
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // This line is necessary, but its function is unimportant for our purposes.
        server.setExecutor(null);

        System.out.println("Creating contexts");

        server.createContext("/", new FileHandler());

        server.createContext("/user/register", new UserRegisterHandler());
        server.createContext("/user/login", new UserLoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill/", new FillHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person/", new PersonHandler());
        server.createContext("/person", new PersonFamilyHandler());
        server.createContext("/event/", new EventHandler());
        server.createContext("/event", new EventFamilyHandler());

        System.out.println("Starting server");

        server.start();

        System.out.println("Server started");
    }

    // "main" method for the server program
    // "args" should contain one command-line argument, which is the port number
    // on which the server should accept incoming client connections.
    public static void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }
}