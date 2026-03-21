package JavaCode;

import java.util.ArrayList;

public class Course {

  public String courseName;
  public String program;
  public ArrayList<String> enrolledStudents; 

    public Course(String courseName, String program, ArrayList<String> enrolledStudents) {
 
        this.courseName = courseName;
        this.program = program;
        this.enrolledStudents = new ArrayList<>(enrolledStudents);
    }}
