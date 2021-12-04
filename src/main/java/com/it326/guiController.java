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
    @FXML private GridPane scheduleGrid;
    @FXML private TextArea noteField;
    @FXML private ListView<Semester> semesterList;
    @FXML private ListView<Course> currentCourseList;
    @FXML private ListView<Course> unassignedCourseList;
    @FXML private Button semAddButton;
    @FXML private Button courseAddButton;
    @FXML private Button removeCourse;
    @FXML private ChoiceBox<String> seasonMenu;
    @FXML private TextField yearField;
    @FXML private TextField search;

    private Account acc;

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
        detailsPane.setText("Login successful. Welcome " + a.getUsername() + ".");
        menuContainer.getChildren().remove(loginBar);
        noteField.setDisable(false);
        noteField.setPromptText("Type to take notes");
        acc = a;

        semAddButton.setDisable(false);
        yearField.setDisable(false);
        seasonMenu.setDisable(false);
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("Season"); arr.add("Fall"); arr.add("Spring"); arr.add("Summer");
        seasonMenu.getItems().addAll(arr);
        seasonMenu.setDisable(false);
        seasonMenu.setValue("Season");
        courseAddButton.setDisable(false);
        removeCourse.setDisable(false);
        updateLists();

    }

    //populate lists
    public void updateLists(){
        //repopulate list
        ObservableList<Semester> listContent = FXCollections.observableList(acc.getManager().getSchedule().getSemesters());
        semesterList.setItems(listContent);
        loadCurrentCourses();
        loadUnassignedCourses();
    }

    public void loadCurrentCourses(){
        Semester s = semesterList.getSelectionModel().getSelectedItem();
        ObservableList<Course> listContent = FXCollections.observableList(s.getCourses());
        currentCourseList.setItems(listContent);
    }

    public void loadUnassignedCourses(){

        ArrayList<Course> newlist = new ArrayList<Course>();
        for(Course c : acc.getManager().getSchedule().getUnassignedCourses()){
            newlist.add(c);
        }
        String searchbox = search.getText();
        for(Course c : acc.getManager().getSchedule().getUnassignedCourses()){
            if(!(c.toString().toLowerCase().contains(searchbox.toLowerCase()) || c.getDescription().toLowerCase().contains(searchbox.toLowerCase())))
                newlist.remove(c);
        }
        ObservableList<Course> listContent = FXCollections.observableList(newlist);
        unassignedCourseList.setItems(listContent);
    }

    public void loadCCourseInfo(){
        Course c = currentCourseList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(5);
        detailsPane.setText("Course: " + c + "\n\n" + "Description: \n" + c.getDescription());
    }

    public void loadUCourseInfo(){
        Course c = unassignedCourseList.getSelectionModel().getSelectedItem();
        detailsPane.setPrefRowCount(5);
        detailsPane.setText("Course: " + c + "\n\n" + "Description: \n" + c.getDescription());
    }

    //handles new user registration
    public void register(){
        System.out.println("user attempted to register");
    }

    //onclick semester tab
    public void semesterTabController(){
    }

    //onclick course tab
    public void courseTabController(){
        detailsPane.setPrefRowCount(1);
        detailsPane.setText("Listing all courses in selected semester");
    }

    //onclick notes tab
    public void notesTabController(){
        detailsPane.setText("");
        if(acc.getManager() != null)
            noteField.setText(acc.getManager().getNotes());
        else{
            detailsPane.setVisible(true);
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login to use notes.");
        }

    }

    public void scheduleTabController(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(Semester s : acc.getManager().getSchedule().getSemesters()){
            list.add(s.getYear());
        }
        ArrayList<Integer> newlist = new ArrayList<Integer>();
        for(int year : list){
            if(!newlist.contains(year))
                newlist.add(year);
        }

        for(int i = 0; i<newlist.size(); i++){
            scheduleGrid.addColumn(i, new Label(newlist.get(i).toString()));
        }
    }

    //onclick save note button
    public void saveNotes(){
        if(acc.getManager() != null)
            acc.getManager().saveNotes(noteField.getText());
        else{
            detailsPane.setVisible(true);
            detailsPane.setPrefRowCount(1);
            detailsPane.setText("Login to use notes.");
        }
    }

    public void addSemester(){
        String season = seasonMenu.getValue();
        int year = Integer.parseInt(yearField.getText());
        acc.getManager().getSchedule().addSemester(new Semester(season, year));
        updateLists();
    }

    public void removeCourse(){
        Course c = currentCourseList.getSelectionModel().getSelectedItem();
        Semester s = semesterList.getSelectionModel().getSelectedItem();
        acc.getManager().getSchedule().removeCourse(s, c);
        updateLists();
    }

    public void addCourse(){
        Course c = unassignedCourseList.getSelectionModel().getSelectedItem();
        Semester s = semesterList.getSelectionModel().getSelectedItem();
        acc.getManager().getSchedule().addCourse(s, c);
        updateLists();
    }

    public void searchUnassigned(){
        String searchTxt = search.getText();


    }

}
