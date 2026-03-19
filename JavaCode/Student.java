package JavaCode;

public class Student {

    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY,
        OTHER
    }
    public long studentId; // 🐱 all classes will have a id to be used to reference each other in the program 
        // 🐱 Actually it easier to reference based on the class objects name but I already added some functions related to it 
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public Gender gender;
    public float gpa;
    public String program;
    public int currentSemester; // this and coursesEnrolled should really use byte, but bytes don't have a literal declartion...
    public int coursesEnrolled;

    public Student(long studentId, String firstName, String lastName, String dateOfBirth, Gender gender, float gpa, String program, int currentSemester, int coursesEnrolled) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.gender = gender;
        this.program = program;
        this.currentSemester = currentSemester;
        this.coursesEnrolled = coursesEnrolled;
    }

}