package JavaCode;

import java.util.ArrayList;

public class Course {

  public String courseName;
  public String programName;
  public ArrayList<String> enrolledStudents; 

    public Course(String courseName, String programName, ArrayList<String> enrolledStudents) {
 
        this.courseName = courseName;
        this.programName = programName;
        this.enrolledStudents = new ArrayList<>(enrolledStudents);
    }}
