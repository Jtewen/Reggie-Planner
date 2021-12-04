package com.it326;

import java.io.Serializable;

public class Account implements Serializable {

    ScheduleManager manager;
    String username;
    String password;
	String notes;

// Constructor
	public Account(String uname, String pwd){
		manager = new ScheduleManager();
		username = uname.trim();
		password = pwd.trim();
	}

    // Getters and Setters
	public ScheduleManager getManager() {
		return this.manager;
	}

	public void setManager(ScheduleManager manager) {
		this.manager = manager;
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

	public String toString(){
		return username;
	}



}