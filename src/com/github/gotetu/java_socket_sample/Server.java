package com.github.gotetu.java_socket_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 50000;
    public static final String SHUTDOWN_KEYWORD = "QUIT";

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                System.out.println("ServerSocket is running... (port: " + serverSocket.getLocalPort() + ")");
                socket = serverSocket.accept();
                System.out.println("Receiver socket: " + socket.getRemoteSocketAddress());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Receive message: " + line);
                    out.println(line);
                    System.out.println("Send message: " + line);
                    if (line.equals(SHUTDOWN_KEYWORD)) {
                        break;
                    }
                }
            } finally {
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
