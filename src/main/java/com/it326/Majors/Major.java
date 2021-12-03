package com.it326.Majors;
import java.util.List;
import com.it326.*;

public interface Major{

    final int creditsNeededToGradute = 120;


	public List<Course> getRequiredCourse();
	public int getTotalMajorCredits();

}