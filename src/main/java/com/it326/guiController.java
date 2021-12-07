package com.it326;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import javax.security.auth.SubjectDomainCombiner;

import com.it326.Majors.Major;
import com.it326.Majors.Minor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
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
    @FXML
    private CheckBox summerBool;
    @FXML
    private Label majorProgress;
    @FXML
    private Label minorProgress;
    @FXML
    private Label majorCProgress;
    @FXML
    private Label minorCProgress;


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
        ObservableList<Semester> listContent = FXCollections.observableList(acc.getManager().getSchedule().getSemesters());
        semesterList.setItems(listContent);
        if(semesterList.getSelectionModel().getSelectedItem()!=null)
            loadCurrentCourses();
        if(acc.getManager().getSchedule().getUnassignedCourses()!=null)
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
        detailsPane.setPrefRowCount(8);
        if(c!=null)
            detailsPane.setText("Course: " + c + "\n\n" + "Prerequisites: " + c.getPreReqs() + "\n\n" + "Description: \n" + c.getDescription());
    }

    public void loadUCourseInfo() {
        Course c = unassignedCourseList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(8);
        if(c!=null)
            detailsPane.setText("Course: " + c + "\n\n" + "Prerequisites: " + c.getPreReqs() +"\n\n"+ "Description: \n" + c.getDescription());
    }

    // handles new user registration
    public void register() throws IOException, ClassNotFoundException {
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
        Minor minor = acc.getManager().getSchedule().getMinor();
        Major major = acc.getManager().getSchedule().getMajor();
        double majorprog = 0;
        double minorprog = 0;
        double majorcprog = 0;
        double minorcprog = 0;
        double majortot = 1;
        double minortot = 1;
        if(major!=null)
            majortot = major.getRequiredCourse().size();
        if(minor!=null)
            minortot = minor.getRequiredCourse().size();


        for(Semester s : acc.getManager().getSchedule().getSemesters()){
            for(Course c : s.getCourses()){
                if(major!=null&&major.getRequiredCourse().contains(c)){
                    majorprog+=1;
                    if(c.getCmpleted())
                        majorcprog+=1;
                }
                if(minor!=null&&minor.getRequiredCourse().contains(c)){
                    minorprog+=1;
                    if(c.getCmpleted())
                        minorcprog+=1;
                }
            }

            
        }
        DecimalFormat df2 = new DecimalFormat(" #,##0.00 '%'");
        majorProgress.setText("Assigned Courses: " + df2.format(majorprog/majortot*100));
        majorCProgress.setText("Completed Courses: " + df2.format(majorcprog/majortot*100));
        minorProgress.setText("Assigned Courses: " + df2.format(minorprog/minortot*100));
        minorCProgress.setText("Completed Courses: " + df2.format(minorcprog/minortot*100));

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
        boolean success = acc.getManager().getSchedule().addCourseExplicit(s, c);
        if(!success){
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Add course failed. Must add prerequisites first.");
        }
        updateLists();
    }

    public void logout() throws ClassNotFoundException, IOException {
        DatabaseHandler.logout();
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

    public void saveAccount() throws IOException, ClassNotFoundException {
        System.out.println("pressed save");
        DatabaseHandler.saveAccount(acc);
    }

    public void calcAll() {
        boolean summer = summerBool.isSelected();
        if(acc.getManager().getSchedule().getMajor() == null){
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Select a Major first.");
            return;
        }
        acc.getManager().calculateAllSchedule(acc.getManager().getSchedule(), summer);
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
        updateLists();
    }

    public void setMinor() throws IOException{
        Stage inputStage = new Stage();
        Parent newScene = FXMLLoader.load(getClass().getResource("Minor_List_Scene.fxml"));
        inputStage.setTitle("Select Minor");
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

    public void checkForSem(){
        Semester selection = semesterList.getSelectionModel().getSelectedItem();
        if(selection!=null){

        }
    }

    public void removeSemester(){
        Semester del = semesterList.getSelectionModel().getSelectedItem();
        acc.getManager().getSchedule().removeSemester(del);
        updateLists();
    }

    public void openPassChange() throws IOException{
        Stage inputStage = new Stage();
        Parent newScene = FXMLLoader.load(getClass().getResource("Pass_Change_Scene.fxml"));
        inputStage.setTitle("Change Password");
        inputStage.setScene(new Scene(newScene));
        inputStage.initStyle(StageStyle.UTILITY);
        //on "add semester" window close 
        inputStage.show();
    }

    public void lockCourse(){
        Course c = currentCourseList.getSelectionModel().getSelectedItem();
        if(c.getCmpleted())
            c.setCmpleted(false);
        else
            c.setCmpleted(true);
        System.out.println(c.getCmpleted());
        updateLists();
    }

}
