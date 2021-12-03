package com.it326;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML private ListView<Schedule> scheduleList;
    @FXML private ListView<Semester> semesterList;
    @FXML private ListView<Course> courseList;


    private ScheduleManager manager;


    //login button clicked check if it matches anything in account list
    public void attemptLogin(){
        String usr = usrField.getText();
        String pwd = pwdField.getText();
        boolean success = false;
        for(Account a : App.accountList){
            System.out.println("Checking");
            if(usr.equals(a.getUsername()) && pwd.equals(a.getPassword())){
                loginInit(a);
                success = true;
                break;
            }
        }
        if(!success){
            detailsPane.setText("Invalid username/password");
            pwdField.setText("");
        }
    }

    private void loginInit(Account a){
        detailsPane.setText("Login successful. Welcome " + a.getFirstName() + ".");
        menuContainer.getChildren().remove(loginBar);
        noteField.setDisable(false);
        noteField.setPromptText("Type to take notes");
        manager = a.getManager();
        loadSchedules();
    }

    //These update the graphical grids based on what is selected
    public void loadSchedules(){
        ObservableList<Schedule> listContent = FXCollections.observableList(manager.getSchedules());
        scheduleList.setItems(listContent);
    }

    public void loadSemesters(){
        //repopulate list
        Schedule s = scheduleList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Selected " + s);
        ObservableList<Semester> listContent = FXCollections.observableList(s.getSemesters());
        System.out.println(listContent);
        semesterList.setItems(listContent);

    }

    public void loadCourses(){
        Semester s = semesterList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Selected " + s);
        ObservableList<Course> listContent = FXCollections.observableList(s.getCourses());
        System.out.println(listContent);
        courseList.setItems(listContent);
    }

    public void loadCourseInfo(){
        Course c = courseList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(5);
        detailsPane.setText("Course: " + c + "\n\n" + "Description: \n" + c.getDescription());
    }

    //handles new user registration
    public void register(){
        System.out.println("user attempted to register");
    }

    //Onclick schedule tab
    public void scheduleTabController(){

    }

    //onclick semester tab
    public void semesterTabController(){
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Listing all semesters in selected schedule");
    }

    //onclick course tab
    public void courseTabController(){
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Listing all courses in selected semester");
    }

    //onclick notes tab
    public void notesTabController(){
        detailsPane.setText("");
        if(manager != null)
            noteField.setText(manager.getNotes());
        else{
            detailsPane.setVisible(true);
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login to use notes.");
        }

    }

    //onclick save note button
    public void saveNotes(){
        if(manager != null)
            manager.saveNotes(noteField.getText());
        else{
            detailsPane.setVisible(true);
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login to use notes.");
        }
    }
}
