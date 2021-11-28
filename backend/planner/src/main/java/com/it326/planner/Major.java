package com.it326.planner;
import java.util.List;
import com.it326.planner.*;

public interface Major{

    List<Course> requiredCourse;
    final int creditsNeededToGradute = 120;

    public void major();

	public List<Course> getRequiredCourse();
	public int getCreditsNeededToGradute();
    public Course calcBestCourse();



}