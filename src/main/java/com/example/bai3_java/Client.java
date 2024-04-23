package com.example.bai3_java;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.*;
import java.net.*;

import static javafx.application.Application.launch;


public class Client  extends  Application{
    @FXML
    public TextField thunhat;
    public TextField thuhai;
    public Label ketqua;

    public void connectAndSend(String operator) {
        try {
            Socket clientSocket = new Socket("localhost", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            double a = Double.parseDouble(thunhat.getText());
            double b = Double.parseDouble(thuhai.getText());

            outToServer.writeBytes(a + "," + b + "," + operator + '\n');

            String result = inFromServer.readLine();
            ketqua.setText("Kết quả: " + result);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void congClicked() {
        connectAndSend("+");
    }

    public void truClicked() {
        connectAndSend("-");
    }

    public void nhanClicked() {
        connectAndSend("*");
    }

    public void chiaClicked() {
        connectAndSend("/");
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 467, 306));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
