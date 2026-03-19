package JavaCode;
import java.util.ArrayList;

public class Program {
    public long programId;
    public String programName;
    public float prerequisiteGpa;    
    public String startDate;          
    public String endDate;          
    public ArrayList<Long> requiredCourses;   
    public ArrayList<Long> enrolledStudents; 

        public Program(long programId, String programName, float prerequisiteGpa, ArrayList<Long> requiredCourses, ArrayList<Long> enrolledStudents) {

        this.programId = programId;
        this.programName = programName;
        this.prerequisiteGpa = prerequisiteGpa;
        this.requiredCourses = new ArrayList<>(requiredCourses);
        this.enrolledStudents = new ArrayList<>(enrolledStudents);
    }
}
