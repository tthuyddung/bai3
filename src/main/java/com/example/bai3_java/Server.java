package com.example.bai3_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6789);
        System.out.println("Server is running...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            Thread clientThread = new Thread(new ClientHandler(socket));
            clientThread.start();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

                String clientSentence = inFromClient.readLine();
                System.out.println("Received from client: " + clientSentence);

                String[] tokens = clientSentence.split(",");
                double a = Double.parseDouble(tokens[0]);
                double b = Double.parseDouble(tokens[1]);
                String operator = tokens[2];

                double result = 0;

                switch (operator) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b != 0) {
                            result = a / b;
                        } else {
                            result = Double.NaN; // Not-a-Number
                        }
                        break;
                }

                outToClient.writeBytes(Double.toString(result) + '\n');
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

