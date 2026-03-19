package JavaCode;

import java.util.ArrayList;

public class Course {
  public long courseId;
  public String courseName;
  public long programId;
  public int prerequisiteSemester;
  public String startDate;
  public String endDate;
  public ArrayList<Long> enrolledStudents; 

    public Course(long courseId, String courseName, long programId, int prerequisiteSemester, String startDate, String endDate, ArrayList<Long> enrolledStudents) {
 
        this.courseId = courseId;
        this.courseName = courseName;
        this.programId = programId;
        this.prerequisiteSemester = prerequisiteSemester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrolledStudents = new ArrayList<>(enrolledStudents);
    }}
