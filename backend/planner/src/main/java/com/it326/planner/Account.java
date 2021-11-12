package com.it326.planner;

public class Account {

    ScheduleManager manager;
    String lastName;
    String firstName;
    String username;
    String password;

// Constructor
    public void Account(ScheduleManager manager, String fname, String lname, String username String password)
    {
        this.manager = manager;
        firstName = fname;
        lastName = lname;
        this.username = username;
        this.password = password;
    }











    // Getters and Setters
	public ScheduleManager getManager() {
		return this.manager;
	}

	public void setManager(ScheduleManager manager) {
		this.manager = manager;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}