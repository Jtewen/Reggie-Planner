package com.it326.planner.Majors;
import java.util.List;
import com.it326.planner.*;

public interface Major{

    final int creditsNeededToGradute = 120;


	public List<Course> getRequiredCourse();
	public int getTotalMajorCredits();

}