package com.it326;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class guiController {

    @FXML private TextField usrField;
    @FXML private TextField pwdField;
    @FXML private TextArea detailsPane;
    @FXML private HBox loginBar;
    @FXML private VBox menuContainer;
    @FXML private GridPane semesterGrid;
    @FXML private GridPane courseGrid;
    @FXML private TextArea noteField;

    private Account currentAccount;


    //login button clicked read username and password fields
    public void attemptLogin(){
        String usr = usrField.getText();
        String pwd = pwdField.getText();
        boolean success = false;
        for(Account a : App.accountList){
            System.out.println("Checking");
            if(usr.equals(a.getUsername()) && pwd.equals(a.getPassword())){
                detailsPane.setText("Login successful. Welcome " + a.getFirstName() + ".");
                menuContainer.getChildren().remove(loginBar);
                noteField.setDisable(false);
                noteField.setPromptText("Type to take notes");
                currentAccount = a;
                success = true;
                break;
            }
        }
        if(!success){
            detailsPane.setText("Invalid username/password");
            pwdField.setText("");
        }
    }

    public void register(){
        System.out.println("user attempted to register");
    }

    public void scheduleTabController(){
        detailsPane.setText("Listing all schedules");
    }

    public void semesterTabController(){
        detailsPane.setText("Listing all semesters in selected schedule");
    }

    public void courseTabController(){
        detailsPane.setText("Listing all courses in selected semester");
    }

    public void notesTabController(){
        detailsPane.setText("");
        if(currentAccount != null)
            noteField.setText(currentAccount.getNotes());
        else{
            detailsPane.setVisible(true);
            detailsPane.setText("Login to use notes.");
        }

    }

    public void saveNotes(){
        if(currentAccount != null)
            currentAccount.saveNotes(noteField.getText());
        else{
            detailsPane.setVisible(true);
            detailsPane.setText("Login to use notes.");
        }
    }
}
