package com.it326;

import java.io.*;
import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static ArrayList<Account> accountList = new ArrayList<Account>();
    public static String notes;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        primaryStage.setTitle("Reggie Planner");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        if(new File("src/main/java/com/it326/Data/Accounts.dat").length() == 0)
            DatabaseHandler.saveAccount();
        DatabaseHandler.loadAccounts();
        launch(args);
    }

}