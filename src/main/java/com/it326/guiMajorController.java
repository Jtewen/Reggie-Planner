package com.it326;

import java.util.*;

import com.it326.Majors.Major;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class guiMajorController {
    
    @FXML
    private ListView<String> majorList;

    public void initialize(){
        ArrayList<String> majors = DatabaseHandler.majorNames;
        majorList.getItems().setAll(majors);
    }

    public void setMajor(){
        String major = majorList.getSelectionModel().getSelectedItem();
        Account acc = DatabaseHandler.currentAccount;
        acc.getManager().getSchedule().clearSchedule();
        acc.getManager().getSchedule().setMajor(new Major(major));
        Stage stage = (Stage) majorList.getScene().getWindow();
        stage.close();
    }
}
