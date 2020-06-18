package com.github.gotetu.java_socket_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static com.github.gotetu.java_socket_sample.Server.SERVER_PORT;
import static com.github.gotetu.java_socket_sample.Server.SHUTDOWN_KEYWORD;

public class Client {
    private static final String LOCAL_HOST = "127.0.0.1";

    public static void main(String[] args) {

        Socket socket = null;

        try {
            try {
                socket = new Socket(LOCAL_HOST, SERVER_PORT);
                System.out.println("Server socket: " + socket.getRemoteSocketAddress());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
                String line;
                while (true) {
                    System.out.print("Enter something: ");
                    line = new BufferedReader(new InputStreamReader(System.in)).readLine() + "\n";
                    out.write(line);
                    out.flush();
                    if (line.equals(SHUTDOWN_KEYWORD)) {
                        break;
                    }

                    System.out.print("Received message: ");
                    line = in.readLine();
                    System.out.println(line);
                    if (line.equals(SHUTDOWN_KEYWORD)) {
                        break;
                    }
                }
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
