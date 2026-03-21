package JavaCode;
import java.util.ArrayList;

public class Program {
    public String programName;
    public float prerequisiteGpa;    
    public ArrayList<String> requiredCourses;   
    public ArrayList<String> enrolledStudents; 

        public Program(String programName, float prerequisiteGpa, ArrayList<String> requiredCourses, ArrayList<String> enrolledStudents) {

        this.programName = programName;
        this.prerequisiteGpa = prerequisiteGpa;
        this.requiredCourses = new ArrayList<>(requiredCourses);
        this.enrolledStudents = new ArrayList<>(enrolledStudents);
    }
}
