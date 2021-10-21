import java.util.*;
import majors.*;
//This class is 

public class Schedule {
    
    private boolean complete;
    private int credits;
    private Major major;
    private List<Semester> semesters;

    

    public Schedule(Major major){
        complete = false;
    }

    public Schedule(Major major, Semester semester){
        this.major = major;
        this.semesters.add(semester);
    }

    public Schedule(Major major, List<Semester> semesters){
        this.major = major;
        this.semesters.addAll(semesters);
    }






















    
    public boolean isComplete() {
        return this.complete;
    }

    public Major getMajor() {
        return this.major;
    }

    public void setMajor(Major major){
        this.major = major;
    }

    public List<Semester> getSemesters() {
        return this.semesters;
    }

    public int getCredits(){
        return this.credits;
    }
}
