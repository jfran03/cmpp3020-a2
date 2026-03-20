package JavaCode;

import java.util.ArrayList;

public class Student {

    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY,
        OTHER
    }
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public Gender gender;
    public float gpa;
    public String program;
    public int currentSemester; // this and coursesEnrolled should really use byte, but bytes don't have a literal declartion...
    public int numberOfCourses;
    public ArrayList<String> enrolledcourses; 

    public Student(String firstName, String lastName, String dateOfBirth, Gender gender, float gpa, String program, int currentSemester, int numberOfCourses, ArrayList<String> enrolledcourses ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.gender = gender;
        this.program = program;
        this.currentSemester = currentSemester;
        this.numberOfCourses = numberOfCourses;
        this.enrolledcourses = enrolledcourses; 
    }

}