package com.it326;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class guiController {

    @FXML private TextField usrField;
    @FXML private TextField pwdField;


    //login button clicked read username and password fields
    public void attemptLogin(){
        System.out.println("User: " + usrField.getText() + " Password: " + pwdField.getText());
    }
}
