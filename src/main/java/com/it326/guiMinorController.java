package com.it326;

import java.util.*;

import com.it326.Majors.Major;
import com.it326.Majors.Minor;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class guiMinorController {
    
    @FXML
    private ListView<String> minorList;

    public void initialize(){
        ArrayList<String> minors = DatabaseHandler.minorNames;
        minorList.getItems().setAll(minors);
    }

    public void setMinor(){
        String minor = minorList.getSelectionModel().getSelectedItem();
        Account acc = DatabaseHandler.currentAccount;
        acc.getManager().getSchedule().setMinor(new Minor(minor));
        Stage stage = (Stage) minorList.getScene().getWindow();
        stage.close();
    }
}
