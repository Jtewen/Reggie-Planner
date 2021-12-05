package com.it326;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class guiPassController {

    @FXML
    private TextField oldPass;
    @FXML
    private TextField newPass;
    @FXML
    private TextField confirmPass;
    @FXML
    private Label passError;

    public void changePassword() throws IOException{
        String password = DatabaseHandler.currentAccount.getPassword().trim();
        String newpassword = newPass.getText().trim();
        String confirmpassword = confirmPass.getText().trim();
        if(password.equals(newpassword)) {
            if (newpassword.equals(confirmpassword)) {
                DatabaseHandler.saveAccount(DatabaseHandler.currentAccount);
                DatabaseHandler.currentAccount.setPassword(newpassword);
                Stage stage = (Stage) passError.getScene().getWindow();
                stage.close();
            } else {
                passError.setText("Passwords do not match.");
            }
        }
        else {
            passError.setText("Incorrect password. Try again.");
        }
    }
}
