package JavaCode;

import java.util.ArrayList;
import java.util.Scanner;

import JavaCode.Student.Gender;

// normally a new file for abstract classes/interfaces is made
// but in this scenario it feels like unnecessary clutter, and i'm only using one interface
interface ValidationCondition {

    public boolean validate(String input);
}

// this class will contain the main process function
// and helper functions that make it look nice!
public class App {

    private static boolean process = true;
    private static final Scanner SCANNER = new Scanner(System.in); // ide was hinting final keyword, and i agree

    // java primitive lists dont resize dynamically, we either clone the array to resize or use ArrayList class
    private static ArrayList<Student> studentData = new ArrayList<>();
    private static ArrayList<Program> programData = new ArrayList<>();
    private static ArrayList<Course> courseData = new ArrayList<>();

    public static void main(String[] args) {

        populateDatabase();



    while (process) {
        //program uses lengthy while loop to run itself

        System.out.printf("\nHello, welcome to the Registration Program\n%s\nPlease select an option:\n%s\n%s\n%s\n%s\n%s\n", 
                        hr(), 
                        "1. View instution's student database", 
                        "2. View data related to instution's programs", 
                        "3. View data related to instution's courses", 
                        "4. Exit the Application", 
                        hr());
        // implementation here is kind of naive, but fits all test cases!
        String mainMenuOptions = validateInputBasedOnCondition("Select your option: ", input -> {
            return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5");
        });
        // my ide was hinting a "rule switch" case, but i like this implementation compared to tradtional switch case
        switch (mainMenuOptions) {
            case "1" -> { 
                boolean studentMenu = true;
                while(studentMenu) {
                    if (checkUnregisteredStudents()) {
                        System.out.printf("\n⚠ The following students are not registered to a program, input '4' to enroll tem now.:\n");
                        for (Student s : studentData) {
                            if (s.program.equalsIgnoreCase("unregistered")) {
                                System.out.printf("  - %s %s\n", s.firstName, s.lastName);
                            }
                        }
                    }
                    System.out.printf("\nStudent Menu\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                        hr(),
                        "1. Enroll Student",
                        "2. Register Student to program",
                        "3. Edit Student",
                        "4. View Enrolled Students",
                        "5. Remove Student",
                        "6. Return to Main Menu",
                        hr()
                    );
                    String option = validateInputBasedOnCondition("Select your option: ", input -> {
                        return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6");
                    });
                    switch (option) {
                            case "1" -> {
                                EnrollStudentFlow();
                            }
                            case "2" -> {
                                studentRegistrationFlow();
                            }
                            case "3" -> {
                                EditStudentFlow();
                            }
                            case "4" -> {
                                ViewStudentsFlow();
                            }
                            case "5" -> {
                                RemoveStudentFlow();
                            }
                            case "6" -> {
                                studentMenu = false;
                            }
                        }
                        }
                    }
            case "2" -> {  
                boolean programMenu = true;
                while(programMenu) {
                    System.out.printf("\nProgram Menu\n%s\n%s\n%s\n%s\n%s\n%s\n",
                        hr(),
                        "1. View Programs",
                        "2. Create Program",
                        "3. Remove Program",
                        "4. Return to Main Menu",
                        hr()
                    );
                    String option = validateInputBasedOnCondition("Select your option: ", input -> {
                        return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4");
                    });
                    switch (option) {
                        case "1" -> viewProgramsFlow();
                        case "2" -> createProgramFlow();
                        case "3" -> removeProgramFlow();
                        case "4" -> programMenu = false;
                    }
                }
            }
            case "3" -> {  
                boolean courseMenu = true;
                while(courseMenu) {
                    System.out.printf("\nCourse Menu\n%s\n%s\n%s\n%s\n",
                        hr(),
                        "1. View Courses",
                        "2. Return to Main Menu",
                        hr()
                    );
                    String option = validateInputBasedOnCondition("Select your option: ", input -> {
                        return input.equals("1") || input.equals("2");
                    });
                    switch (option) {
                    case "1" -> {
                       ViewCoursesFlow();
                    }
                    case "2" -> {
                       courseMenu = false;
                    }

                }
                }
            }
            case "4" -> {  
                System.out.println("Exiting the system. Goodbye!");
                process = false;
            }
        }
    }
    }
    // this method takes in a condition and validate it BASED on custom conditions
    // this should make it such that the method can be used for various use cases in the CLI
    // for java, we need to make an interface to pass a lambda function as a parameter
    private static String validateInputBasedOnCondition(String inputString, ValidationCondition condition) {
        System.out.print(inputString);
        String input = SCANNER.nextLine();

        if (condition.validate(input)) {
            return input;
        } else {
            System.out.println("Invalid input, please try again.");
            return validateInputBasedOnCondition(inputString, condition);
        }
    }
    //this method gets the program's dummy dataset and populates each class object's arrayset to reference each other to simulate a working database 
    private static void populateDatabase() {
        // ideally we'll never do this in the same program as the App.java, FYI!
        studentData.add(new Student("Jefff", "Bezsos", "13/13/2020", Gender.MALE, 2.0f, "softwareDevelopment", 1, 0,new ArrayList<>()));
        studentData.add(new Student("Mark", "Grayson", "10/10/2020", Gender.MALE, 3.5f, "CyberSecurity", 2, 0,new ArrayList<>()));
        studentData.add(new Student("Robert", "Bob", "09/09/2020", Gender.OTHER, 3.0f, "Business", 3, 0,new ArrayList<>()));


        programData.add(new Program("softwareDevelopment", 2.5f, new ArrayList<>(), new ArrayList<>()));
        programData.add(new Program("CyberSecurity", 2.5f,new ArrayList<>(), new ArrayList<>()));
        programData.add(new Program("Business", 2.5f, new ArrayList<>(), new ArrayList<>()));
        
        courseData.add(new Course("Intro to Programming", "softwareDevelopment",new ArrayList<>()));
        courseData.add(new Course("Data Structures", "softwareDevelopment", new ArrayList<>()));
        courseData.add(new Course("Finance", "Business", new ArrayList<>()));
        courseData.add(new Course("Marketing", "Business", new ArrayList<>()));
        courseData.add(new Course("Intro to Cybersecurity", "CyberSecurity",new ArrayList<>()));
        courseData.add(new Course("Networking", "CyberSecurity",new ArrayList<>()));
        courseData.add(new Course("Cloud Security","CyberSecurity",new ArrayList<>()));
        
        //populates empty array lists of class instances to simulate existing data
        for (Student s : studentData) {
            for (Program p : programData) {
                if (p.programName.equalsIgnoreCase(s.program)) {
                    p.enrolledStudents.add(s.firstName + " " + s.lastName);
                }
            }
        }

        for (Course c : courseData) {
            for (Program p : programData) {
                if (p.programName.equalsIgnoreCase(c.program)) {
                    p.requiredCourses.add(c.courseName);
                }
            }
            for (Student s : studentData) {
                if (c.program.equalsIgnoreCase(s.program)) {
                    c.enrolledStudents.add(s.firstName + " " + s.lastName);
                    s.enrolledCourses.add(c.courseName);
                    s.numberOfCourses = s.enrolledCourses.size();
                }
            }
        }
    }
    //helper method that cleans up database upon every update; whenever a class instance is changed 
    //acts as a impure function
    //it cleans up the database to make sure all class instances follow their constraints upon being changed
    private static void updateDatabase() {
        // if student's program no longer exists, set them to unregistered and clear their courses
        for (Student s : studentData) {
            Program p = queryDatabase(programData, name -> name.equalsIgnoreCase(s.program));
            if (p == null && !s.program.equalsIgnoreCase("unregistered")) {
                s.program = "unregistered";
                s.enrolledCourses.clear();
            }
        }
        // if a course's program no longer exists, remove the course
        for (Course c : new ArrayList<>(courseData)) {
            Program p = queryDatabase(programData, name -> name.equalsIgnoreCase(c.program));
            if (p == null) {
                removeCourse(c, null);
            }
        }
        // if a program's enrolled student no longer exists in the database, remove them from the program
        for (Program p : programData) {
            p.enrolledStudents.removeIf(enrolledName ->
                queryDatabase(studentData, name -> name.equalsIgnoreCase(enrolledName)) == null
            );
        }

        // if a course's enrolled student no longer exists  in the database, remove them from the course
        for (Course c : courseData) {
            c.enrolledStudents.removeIf(enrolledName ->
                queryDatabase(studentData, name -> name.equalsIgnoreCase(enrolledName)) == null
            );
        }

        // re-sync program to have class instances be referenced in the proper class array lists 
        for (Course c : courseData) {
            for (Program p : programData) {
                if (p.programName.equalsIgnoreCase(c.program) && !p.requiredCourses.contains(c.courseName)) {
                    p.requiredCourses.add(c.courseName);
                }
            }
            for (Student s : studentData) {
                if (c.program.equalsIgnoreCase(s.program)) {
                    if (!c.enrolledStudents.contains(s.firstName + " " + s.lastName)) {
                        c.enrolledStudents.add(s.firstName + " " + s.lastName);
                    }
                    if (!s.enrolledCourses.contains(c.courseName)) {
                        s.enrolledCourses.add(c.courseName);
                    }
                }
            }
        }
        // sync numberOfCourses to match actual enrolledCourses size
        for (Student s : studentData) {
            s.numberOfCourses = s.enrolledCourses.size();
        }
    }
    
    //helper method that only exists to notify system that there are unregistered students in the system. 
    //This method prints a message in the student menu 
    private static boolean checkUnregisteredStudents() {
    for (Student s : studentData) {
        if (s.program.equalsIgnoreCase("unregistered")) {
            return true;
        }
    }
        return false;
    }
    //this method queries the class instances and is only invoked through the validation interface, if entity exists it will return the queried entity 
    //this acts as a helper method to handle database queries and functions
    //Used source to help with Type generics: https://docs.oracle.com/javase/tutorial/java/generics/types.html 
    private static <T> T queryDatabase (ArrayList<T> list,  ValidationCondition condition) {
        for (T item : list) {
            //input string stored here 
            String inputString = "";
            if (item instanceof Student s) {
                inputString = s.firstName + " " + s.lastName;
            } else if (item instanceof Program p) {
                inputString = p.programName;
            } else if (item instanceof Course c) {
                inputString = c.courseName;
            }
            //if found returns instance of object 
            if (condition.validate(inputString)) {
                return item;
            }
        }
        return null;
    }
    // adds a new student to the "database"
     private static void EnrollStudentFlow() {
        // input validation through regex, ensures that data is good for when we add it to the class
        String firstNameInput = validateInputBasedOnCondition("Student First Name: ", input -> input.matches("[a-zA-Z]+"));
        String lastNameInput = validateInputBasedOnCondition("Student Last Name: ", input -> input.matches("[a-zA-Z]+"));

        if (queryDatabase(studentData, name -> name.equalsIgnoreCase(firstNameInput + " " + lastNameInput)) != null) {
            System.out.println("\nStudent already exists.");
            return;
        }

        String dobInput = validateInputBasedOnCondition("Student Date of Birth (MM/DD/YYYY): ", input -> input.matches("\\d{2}/\\d{2}/\\d{4}"));
        // checks gender enums list (can accept lowercase/mix of both) to see if input matches
        String genderInput = validateInputBasedOnCondition("Student Gender (male, female, non_binary, other): ", input -> {
            for (Gender gender : Gender.values()) {
                if (gender.name().equalsIgnoreCase(input)) {
                    return true;
                }
            }
            return false;
        });

        // ensures value is b/n 0.0-4.0
        // im not even gonna try regex to enforce the 4.0 scale
        String gpaInput = validateInputBasedOnCondition("Student GPA (4.0 Scale): ", input -> {
            try {
                float gpa = Float.parseFloat(input);
                return gpa >= 0.0f && gpa <= 4.0f;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        // only allows unsigned ints
        String currentSemesterInput = validateInputBasedOnCondition("Current Semester: ", input -> input.matches("\\d+"));


        // adds new user to database
        Student newStudentObj = new Student(firstNameInput, lastNameInput, dobInput, Gender.valueOf(genderInput.toUpperCase()), Float.parseFloat(gpaInput), "unregistered", Integer.parseInt(currentSemesterInput), 0, new ArrayList<>());
        studentData.add(newStudentObj);

        System.out.printf("%s %s has been enrolled at SAIT.", firstNameInput, lastNameInput);

        //added while loop for the student to register upon being created 
        while (true) {
            System.out.println("\nRegister student to program?");
            String choice = validateInputBasedOnCondition("1 = Yes, 2 = No: ",input -> input.equals("1") || input.equals("2"));
            switch (choice) {
                
                case "1" -> {
                    if (!programData.isEmpty()) {
                        System.out.println("\nType the name of the program you want to enroll the student in ");
                        String programNameInput = validateInputBasedOnCondition("Program Name: ", input -> input.matches("[a-zA-Z]+"));
                        Program program = queryDatabase(programData, name -> name.equalsIgnoreCase(programNameInput));
                        addStudentToProgram(newStudentObj, program);
                        return; 
                    } else {
                        //if no programs exist, createProgramFlow() is invoked to register the student into a class
                        System.out.println("\n No programs currently avaliable, please create a program before registering");
                        createProgramFlow();
                        // Loops back to registration option once a program is created 
                    }
                }
                case "2" -> {
                    System.out.println("\nSkipping program registration. You can register later through the student menu.");
                    return; 
                    //added a option to register later. If the student's GPA is to low to be registered into the program, the loop would become infinite 
                }
            }
        }
    }

    // finds and removes student form ArrayList, pretty straightforward
    private static void RemoveStudentFlow() {
        String firstNameInput = validateInputBasedOnCondition("Student First Name: ", input -> input.matches("[a-zA-Z]+"));
        String lastNameInput = validateInputBasedOnCondition("Student Last Name: ", input -> input.matches("[a-zA-Z]+"));

        Student student = queryDatabase(studentData, name -> name.equalsIgnoreCase(firstNameInput + " " + lastNameInput));
        if (student == null) {
            System.out.println("\nStudent not found.");
            return;
        }
        System.out.printf("%s %s has withdrew from Sait.\n%s", student.firstName, student.lastName,hr());
        studentData.remove(student);
    
        updateDatabase();
    }

    private static void EditStudentFlow() {
        String firstNameInput = validateInputBasedOnCondition("Student First Name: ", input -> input.matches("[a-zA-Z]+"));
        String lastNameInput = validateInputBasedOnCondition("Student Last Name: ", input -> input.matches("[a-zA-Z]+"));

        Student student = queryDatabase(studentData, name -> name.equalsIgnoreCase(firstNameInput + " " + lastNameInput));

        // the core function here should be, if the user wants to change something they can add an input
        // if they dont, just press Enter, the information does not change, we'll need to take this into account
        // sorry for anyone reading this!
        
        validateInputBasedOnCondition("Student First Name: ", input -> {
            boolean valid = input.matches("[a-zA-Z]+") || input.length() == 0;

            if (valid) {
                if (input.length() != 0) {
                    student.firstName = input;
                }
                return true;
            } else {
                return false;
            }
        });

        validateInputBasedOnCondition("Student Last Name: ", input -> {
            boolean valid = input.matches("[a-zA-Z]+") || input.length() == 0;

            if (valid) {
                if (input.length() != 0) {
                    student.lastName = input;
                }
                return true;
            } else {
                return false;
            }
        });

        validateInputBasedOnCondition("Student Date of Birth (MM/DD/YYYY): ", input -> {
            boolean valid = input.matches("\\d{2}/\\d{2}/\\d{4}") || input.length() == 0;

            if (valid) {
                if (input.length() != 0) {
                    student.dateOfBirth = input;
                }
                return true;
            } else {
                return false;
            }
        });

        validateInputBasedOnCondition("Student Gender (male, female, non_binary, other): ", input -> {
            boolean valid = input.length() == 0;

            for (Gender gender : Gender.values()) {
                if (gender.name().equalsIgnoreCase(input)) {
                    valid = true;
                }
            }

            if (valid) {
                if (input.length() != 0) {
                    student.gender = Gender.valueOf(input.toUpperCase());
                }
                return true;
            } else {
                return false;
            }
        });

        validateInputBasedOnCondition("Current Semester: ", input -> {
            boolean valid = input.matches("\\d+") || input.length() == 0;

            if (valid) {
                if (input.length() != 0) {
                    student.currentSemester = Integer.parseInt(input);
                }
                return true;
            } else {
                return false;
            }
        });
        System.out.printf("%s\n%s %s has been updated.\n%s", hr(),student.firstName, student.lastName,hr());
    }

    // this method iterates through studentData list and prints them
    private static void ViewStudentsFlow() {
        System.out.println("The Current Student List:\n");
        for (Student student : studentData) {
            // sorry to the reader of this
            System.out.printf(" %s\nFirst Name: %s\nLast Name: %s\nDate of Birth: %s\nGender: %s\nGPA: %.2f\nProgram: %s\nCurrent Semester: %d\nCourses Enrolled: %d\n", hr(), student.firstName, student.lastName, student.dateOfBirth,
                    student.gender, student.gpa, student.program, student.currentSemester, student.numberOfCourses
            );
        }
    }

    //method acts as a seperate registration flow to assign existing student instances to a program.
    private static void studentRegistrationFlow(){
        String firstNameInput = validateInputBasedOnCondition("Student First Name: ", input -> input.matches("[a-zA-Z]+"));
        String lastNameInput = validateInputBasedOnCondition("Student Last Name: ", input -> input.matches("[a-zA-Z]+"));
        Student student = queryDatabase(studentData, name -> name.equalsIgnoreCase(firstNameInput + " " + lastNameInput));
        
        if (student == null) {
        System.out.println("\nStudent not found.");
        return;
        }
        
        String programNameInput = validateInputBasedOnCondition("Program Name: ", input -> input.matches("[a-zA-Z ]+"));
        
        Program program = queryDatabase(programData, name -> name.equalsIgnoreCase(programNameInput));

        if (program == null) {
            System.out.println("\nProgram not found.");
            return;
        }
        addStudentToProgram(student, program);
        updateDatabase();
        System.out.println("\nStudent successfully registered to their desired program.");
    }

    // helper function that enrolls student to a program
    // if student was in a program before hand they will be removed from their registered courses (and the courses will remove the student)
    // then the student changes their program to the new registered program and is enrolled into their courses 
    private static void addStudentToProgram(Student student, Program program){
        
        if (student.gpa < program.prerequisiteGpa) {
            System.out.printf("\n%s %s does not meet the minimum GPA requirement of %.1f for %s.\n", student.firstName, student.lastName, program.prerequisiteGpa, program.programName);
        return;}
        
        //if student instance is currently enrolled at another program, they are removed from that program and its courses accordingly 
        if (!student.program.equalsIgnoreCase("unregistered")) {
            for (Course c : courseData) {
                if (c.program.equalsIgnoreCase(student.program)) {
                    c.enrolledStudents.remove(student.firstName + " " + student.lastName);
                }
            }
            // remove student from old program's enrolled list
            Program oldProgram = queryDatabase(programData, name -> name.equalsIgnoreCase(student.program));
            if (oldProgram != null) {
                oldProgram.enrolledStudents.remove(student.firstName + " " + student.lastName);
            }
            // clear student's enrolled courses
            student.enrolledCourses.clear();
        }
        // assign student to new program
        student.program = program.programName;
        // enroll student into new program's courses
        for (Course c : courseData) {
            if (c.program.equalsIgnoreCase(student.program)) {
                addStudentToCourse(student, c);
            }
        }
        program.enrolledStudents.add(student.firstName + " " + student.lastName);
        updateDatabase();
    }


    //program functions 
    //createProgramFlow() creates a new program & course instance 
    //because a course needs to exist in a program instance array for its required courses 
    private static void createProgramFlow() {
        String programNameInput = validateInputBasedOnCondition("Program Name: ", input -> input.matches("[a-zA-Z]+"));
        
        if (queryDatabase(programData, name -> name.equalsIgnoreCase(programNameInput)) != null) {
            System.out.println("\n Program already exists.");
            return;
        }

        String gpaInput = validateInputBasedOnCondition("Minimum GPA requirements for the program", input -> {
            try {
                float gpa = Float.parseFloat(input);
                return gpa >= 0.0f && gpa <= 4.0f;
            } catch (NumberFormatException e) {
                return false;
            }
        });
        
        // new program instance is created
        Program newProgramObj = new Program(programNameInput, Float.parseFloat(gpaInput), new ArrayList<>(), new ArrayList<>());
        
        // added to the program's "database"
        programData.add(newProgramObj);
        //program.programName is used for the creation of the course instance
        
        System.out.println("\nAdd courses for the newly created program.");
        
        //added a loop for the user to add enough courses to the database
        //added a limit as 5 courses should be good enough 
        int count = 0;
        boolean continueAdding = true;

        while (continueAdding && count < 5) {

            createCourseFlow(newProgramObj);
            count++;

            if (count == 5) {
                System.out.println("Program has reached the required amount of courses.");
                break;
            }
            // ask user if they want to continue
            String option = validateInputBasedOnCondition(
                "Add another course? (1 = yes, 2 = no): ",
                input -> input.equals("1") || input.equals("2")
            );
            if (option.equals("2")) {
                continueAdding = false;
            }
        }
        
        System.out.printf("%s\nProgram: %s\nMinimum GPA: %.2f\nRequired Courses: %s\nEnrolled Students: %s\n",
            hr(), newProgramObj.programName, newProgramObj.prerequisiteGpa, newProgramObj.requiredCourses, newProgramObj.enrolledStudents
        );

    }


    //removes the program from "database"
    private static void removeProgramFlow() {
        String programName = validateInputBasedOnCondition("Enter program name to remove: ",input -> input.matches("[a-zA-Z ]+"));
        Program program = queryDatabase(programData, name -> name.equalsIgnoreCase(programName));
        if (program == null) {
            System.out.println("\nProgram not found.");
            return;
        }
        programData.remove(program);
        updateDatabase();
    }

    private static void viewProgramsFlow() {
        System.out.println("The Current Program List:\n");

        for (Program program : programData) {
            System.out.printf( "%s\nProgram: %s\nRequired GPA: %.2f\nCourses: %s\nEnrolled Students: %s\n", hr(), program.programName, program.prerequisiteGpa, program.requiredCourses, program.enrolledStudents);
        }
    }
    
    //course functions 
    //depedent on program instance; course's can only exists if program exists 
    private static void createCourseFlow(Program program) {
        String courseNameInput = validateInputBasedOnCondition("Course Name: ",input -> input.matches("[a-zA-Z ]+"));
        
        if (queryDatabase(courseData, name -> name.equalsIgnoreCase(courseNameInput)) != null) {
            System.out.println("\n Course already exists.");
            return;
        }
        //course instance created for the program 
        Course newCourseObj = new Course(courseNameInput, program.programName, new ArrayList<String>());
        
        // add new CourseObj to program array to be referenced
        courseData.add(newCourseObj); 
        program.requiredCourses.add(newCourseObj.courseName);
        System.out.printf("%s\nCourse: %s\nProgram: %s\nEnrolled Students: %s\n",
            hr(), newCourseObj.courseName, newCourseObj.program, newCourseObj.enrolledStudents
        );
    }
    
    // this method iterates through courseData list and prints them
    private static void ViewCoursesFlow() {
        System.out.println("The Current Course List:\n");
        for (Course course : courseData) { System.out.printf("%s\nCourse: %s\nProgram: %s\nEnrolled Students: %s\n", hr(),course.courseName,course.program,course.enrolledStudents);}
    }
    
    //helper method only invoked in removeProgramFlow(),
    //because course is dependent on the existence of the program instance, it will also be deleted from the database
    //Students remove the course from their enrolledCourses<> Array
    private static void removeCourse(Course course, Program program) {
        if (program != null) {
            program.requiredCourses.remove(course.courseName);
        }
        // drop all enrolled students from the course
        for (Student s : studentData) {
            if (s.program.equalsIgnoreCase(course.program)) {
                dropStudentfromCourse(s, course);
            }
        }
        courseData.remove(course);
    }

    //helper method that enrolls student to a course
    //only invoked in EnrollStudentFlow()
    private static void addStudentToCourse(Student student, Course course) {
        course.enrolledStudents.add(student.firstName + " " + student.lastName);
        student.enrolledCourses.add(course.courseName);
    }

    //helper method that student drops from course
    //only invoked in removeProgramFlow()
    private static void dropStudentfromCourse(Student student, Course course) {
        course.enrolledStudents.remove(student.firstName + " " + student.lastName);
        student.enrolledCourses.remove(course.courseName);
        student.numberOfCourses = student.enrolledCourses.size();
    }
    
    // create a horizontal line for better UI
    // if a length param is not provided, assume 50 characters
    // java doesn't really support optional params, overloading is common alternative
    private static String hr() {
        return "-".repeat(50);
    }

    private static String hr(int length) {
        return "-".repeat(length);
    }
}
