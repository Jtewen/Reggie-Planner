package com.it326;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class guiSemController {
    

    @FXML
    private ChoiceBox<String> seasonChoice;
    @FXML
    private ChoiceBox<Integer> yearChoice;

    private Account acct;

    public void initialize(){
        ArrayList<String> seasons = new ArrayList<String>();
        seasons.add("Fall"); seasons.add("Spring"); seasons.add("Summer");
        seasonChoice.getItems().clear();
        seasonChoice.getItems().addAll(seasons);
        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        ArrayList<Integer> years = new ArrayList<Integer>();
        for(int i = 0; i<10; i++){
            years.add(cal.get(cal.YEAR)+i);
        }
        yearChoice.getItems().clear();
        yearChoice.getItems().addAll(years);
        acct = DatabaseHandler.currentAccount;
    }

    public void addSemester() {
        String season = seasonChoice.getValue();
        int year = yearChoice.getValue();
        acct.getManager().getSchedule().addSemester(new Semester(season, year));
        Stage stage = (Stage) seasonChoice.getScene().getWindow();
        stage.close();
    }
}
