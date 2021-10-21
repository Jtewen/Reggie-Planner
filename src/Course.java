import java.util.*;

public class Course{
    private String id;
    private String description;
    private int credit_hours;
    private List<Course> prereq;

    public Course(String id, String description, int credit_hours){
        this.id = id;
        this.description = description;
        this.credit_hours = credit_hours;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCredit_hours() {
        return this.credit_hours;
    }

    public List<Course> getPrereq() {
        return this.prereq;
    }

    public void addPrereq(Course course){
        prereq.add(course);
    }
    
    public void addPrereq(List<Course> course){
        prereq.addAll(course);
    }
}