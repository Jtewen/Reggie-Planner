import java.util.*;

public class Schedule {
    
    private boolean complete;
    private int credits;
    private String major;
    private List<Semester> semesters;

    

    public Schedule(String major){
        complete = false;
        this.major = major;
    }

    public Schedule(String major, Semester semester){
        this.major = major;
        this.semesters.add(semester);
    }

    public Schedule(String major, List<Semester> semesters){
        this.major = major;
        this.semesters.addAll(semesters);
    }

    public boolean isComplete() {
        return this.complete;
    }

    public String getMajor() {
        return this.major;
    }

    public List<Semester> getSemesters() {
        return this.semesters;
    }

    public int getCredits(){
        return this.credits;
    }
}
