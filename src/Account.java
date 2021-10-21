public class Account {

    private String firstName;
    private String lastName;
    private Schedule schedule;
   
    public Account(String fname, String lname){
        firstName = fname;
        lastName = lname;
    }











    
    public String getName(){
        return firstName + " " + lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Schedule getSchedule(){
        return schedule;
    }

}
