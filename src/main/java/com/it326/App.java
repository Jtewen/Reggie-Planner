package com.it326;

import java.io.IOException;
import java.util.*;

import com.it326.Majors.IT;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.*;


/**
 * JavaFX App
 */
public class App extends Application {

    public static ArrayList<Account> accountList;
    public static String notes;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        primaryStage.setTitle("Reggie Planner");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

    }

    public static void main(String[] args) {
        accountList = new ArrayList<Account>();
        accountList.add(new Account("Jacob", "Ewen", "jewen", "pwd"));
        ScheduleManager manager = accountList.get(0).getManager();
        manager.addSchedule("First Sched");
        manager.getSchedules().get(0).setMajor(new IT());
        manager.getSchedules().get(0).addSemester();
        manager.calculateSchedule(manager.getSchedules().get(0));

        
        launch(args);
    }

}