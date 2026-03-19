package JavaCode;

import java.util.ArrayList;

public class Course {
  public long courseId;
  public String courseName;
  public long programId;
  public ArrayList<Long> enrolledStudents; 

    public Course(long courseId, String courseName, long programId, ArrayList<Long> enrolledStudents) {
 
        this.courseId = courseId;
        this.courseName = courseName;
        this.programId = programId;
        this.enrolledStudents = new ArrayList<>(enrolledStudents);
    }}
