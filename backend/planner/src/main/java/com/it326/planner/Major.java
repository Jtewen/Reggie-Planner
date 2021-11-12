package com.it326.planner;

public Interface Major{

    List<Course> requiredCourse;
    Final int creditsNeededToGradute = 120;

	public List<Course> getRequiredCourse();
	public int getCreditsNeededToGradute();



}