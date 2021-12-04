package com.it326;

import java.io.IOException;
import java.util.*;

import com.it326.Majors.Major;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class guiController {

    @FXML
    private TextField usrField;
    @FXML
    private TextField pwdField;
    @FXML
    private TextArea detailsPane;
    @FXML
    private HBox loginBar;
    @FXML
    private VBox menuContainer;
    @FXML
    private GridPane scheduleGrid;
    @FXML
    private TextArea noteField;
    @FXML
    private ListView<Semester> semesterList;
    @FXML
    private ListView<Course> currentCourseList;
    @FXML
    private ListView<Course> unassignedCourseList;
    @FXML
    private Button semAddButton;
    @FXML
    private Button courseAddButton;
    @FXML
    private Button removeCourse;
    @FXML
    private TextField search;
    @FXML
    private ChoiceBox<String> seasonChoice;
    @FXML
    private ChoiceBox<Integer> yearChoice;
    @FXML
    private ContextMenu semContext;

    private Account acc;


    // login button clicked check if it matches anything in account list
    public void attemptLogin() {
        String usr = usrField.getText();
        String pwd = pwdField.getText();
        acc = DatabaseHandler.verifyAccount(usr, pwd);
        if(acc != null)
            loginInit(acc);
        else{
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login failed. Invalid Username/Password.");
        }

    }

    // initialization code on login
    private void loginInit(Account a) {
        DatabaseHandler.currentAccount = a;
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Login successful. Welcome " + a.getUsername() + ".");
        menuContainer.getChildren().remove(loginBar);
        noteField.setDisable(false);
        noteField.setPromptText("Type to take notes");
        courseAddButton.setDisable(false);
        removeCourse.setDisable(false);
        updateLists();

    }

    // populate lists
    public void updateLists() {
        // repopulate list
        ObservableList<Semester> listContent = FXCollections
                .observableList(acc.getManager().getSchedule().getSemesters());
        semesterList.setItems(listContent);
        loadCurrentCourses();
        loadUnassignedCourses();
    }

    public void loadCurrentCourses() {
        Semester s = semesterList.getSelectionModel().getSelectedItem();
        ObservableList<Course> listContent = FXCollections.observableList(s.getCourses());
        currentCourseList.setItems(listContent);
        currentCourseList.setCellFactory(cell -> {
            return new ListCell<Course>() {
                @Override
                protected void updateItem(Course item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.toString());
                        if(item.getCmpleted())
                            setTextFill(Color.GREEN);
                        else
                            setTextFill(Color.BLACK);
                    }
                }
            };
        });
    }

    public void loadUnassignedCourses() {

        ArrayList<Course> newlist = new ArrayList<Course>();
        for (Course c : acc.getManager().getSchedule().getUnassignedCourses()) {
            newlist.add(c);
        }
        String searchbox = search.getText();
        for (Course c : acc.getManager().getSchedule().getUnassignedCourses()) {
            if (!(c.toString().toLowerCase().contains(searchbox.toLowerCase())
                    || c.getDescription().toLowerCase().contains(searchbox.toLowerCase())))
                newlist.remove(c);
        }
        ObservableList<Course> listContent = FXCollections.observableList(newlist);
        unassignedCourseList.setItems(listContent);
    }

    public void loadCCourseInfo() {
        Course c = currentCourseList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(5);
        detailsPane.setText("Course: " + c + "\n\n" + "Description: \n" + c.getDescription());
    }

    public void loadUCourseInfo() {
        Course c = unassignedCourseList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(5);
        detailsPane.setText("Course: " + c + "\n\n" + "Description: \n" + c.getDescription());
    }

    // handles new user registration
    public void register() throws IOException {
        acc = DatabaseHandler.registerAccount(usrField.getText(), pwdField.getText());
        if(acc!=null)
            loginInit(acc);
        else{
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login failed. Username is taken or a required field is empty.");
        }
    }

    // onclick semester tab
    public void semesterTabController() {
    }

    // onclick course tab
    public void courseTabController() {
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Listing all courses in selected semester");
    }

    // onclick notes tab
    public void notesTabController() {
        detailsPane.setText("");
        if (acc.getManager() != null)
            noteField.setText(acc.getManager().getNotes());
        else {
            detailsPane.setVisible(true);
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login to use notes.");
        }

    }

    public void scheduleTabController() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Semester s : acc.getManager().getSchedule().getSemesters()) {
            list.add(s.getYear());
        }
        ArrayList<Integer> newlist = new ArrayList<Integer>();
        for (int year : list) {
            if (!newlist.contains(year))
                newlist.add(year);
        }

        for (int i = 0; i < newlist.size(); i++) {
            scheduleGrid.addColumn(i, new Label(newlist.get(i).toString()));
        }
    }

    // onclick save note button
    public void saveNotes() {
        if (acc.getManager() != null)
            acc.getManager().saveNotes(noteField.getText());
        else {
            detailsPane.setVisible(true);
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login to use notes.");
        }
    }

    public void openSemWindow() throws IOException{
        Stage inputStage = new Stage();
        Parent newScene = FXMLLoader.load(getClass().getResource("New_Semester_Scene.fxml"));
        inputStage.setTitle("Add Semester");
        inputStage.setScene(new Scene(newScene));
        inputStage.initStyle(StageStyle.UTILITY);
        //on "add semester" window close
        inputStage.setOnHidden(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try{updateLists();
                }finally{
                    inputStage.close();
                }
            }
        });  
        inputStage.show();
    }


    public void removeCourse() {
        Course c = currentCourseList.getSelectionModel().getSelectedItem();
        Semester s = semesterList.getSelectionModel().getSelectedItem();
        acc.getManager().getSchedule().removeCourse(s, c);
        updateLists();
    }

    public void addCourse() {
        Course c = unassignedCourseList.getSelectionModel().getSelectedItem();
        Semester s = semesterList.getSelectionModel().getSelectedItem();
        acc.getManager().getSchedule().addCourseExplicit(s, c);
        updateLists();
    }

    public void logout() {
        DatabaseHandler.currentAccount = null;
        acc = null;
        menuContainer.getChildren().add(loginBar);
        courseAddButton.setDisable(true);
        removeCourse.setDisable(true);
        semesterList.getItems().clear();
        currentCourseList.getItems().clear();
        unassignedCourseList.getItems().clear();
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Logged out.");
        usrField.setText("");
        pwdField.setText("");
    }

    public void saveAccount() throws IOException {
        DatabaseHandler.saveAccount(acc);
    }

    public void calcAll() {
        if(acc.getManager().getSchedule().getMajor() == null){
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Select a Major first.");
            return;
        }
        acc.getManager().calculateAllSchedule(acc.getManager().getSchedule());
        updateLists();
    }

    public void calcCurrent() {
        if(acc.getManager().getSchedule().getMajor() == null){
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Select a Major first.");
            return;
        }
        acc.getManager().calculateCurrentSchedule(acc.getManager().getSchedule());
        updateLists();
    }

    public void setMajor() throws IOException{
        Stage inputStage = new Stage();
        Parent newScene = FXMLLoader.load(getClass().getResource("Major_List_Scene.fxml"));
        inputStage.setTitle("Select Major");
        inputStage.setScene(new Scene(newScene));
        inputStage.initStyle(StageStyle.UTILITY);
        //on "add semester" window close
        inputStage.setOnHidden(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try{updateLists();
                }finally{
                    inputStage.close();
                }
            }
        });  
        inputStage.show();
    }

    public void setMinor(){

    }

    public void checkForSem(){
        Semester selection = semesterList.getSelectionModel().getSelectedItem();
        if(selection!=null){

        }
    }


}
