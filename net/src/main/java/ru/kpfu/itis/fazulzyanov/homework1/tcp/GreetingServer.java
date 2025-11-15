package ru.kpfu.itis.fazulzyanov.homework1.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
//            serverSocket.setSoTimeout();
            clientSocket = serverSocket.accept();

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;

            while ((message = in.readLine()) != null) {
                if ("hello".equalsIgnoreCase(message.trim())) {
                    out.println("Hello from server");
                } else if ("bye".equalsIgnoreCase(message)) {
                    out.println("Bye");
                    stop();
                } else {
                    out.println("?");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GreetingServer server = new GreetingServer();
        server.start(5555);
    }
}
