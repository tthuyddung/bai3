package com.example.bai3_java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.*;
import java.net.*;

public class CalculatorController {
    @FXML
    private TextField thunhat;

    @FXML
    private TextField thuhai;

    @FXML
    private Label ketqua;

    @FXML
    public void congClicked() {
        connectAndSend("+");
    }

    @FXML
    public void truClicked() {
        connectAndSend("-");
    }

    @FXML
    public void nhanClicked() {
        connectAndSend("*");
    }

    @FXML
    public void chiaClicked() {
        connectAndSend("/");
    }

    private void connectAndSend(String operator) {
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
}
