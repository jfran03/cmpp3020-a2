package JavaCode;

import java.util.ArrayList;
import java.util.List;
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

        // ideally we'll never do this in the same program as the App.java, FYI!
        studentData.add(new Student(1,"Jefff", "Bezsos", "13/13/2020", Gender.MALE, 2.0f, "Programmer", 1, 3));
        studentData.add(new Student(2,"Mark", "Grayson", "10/10/2020", Gender.MALE, 3.5f, "CyberSecurity", 2, 4));
        studentData.add(new Student(3,"Robert", "Bob", "09/09/2020", Gender.OTHER, 3.0f, "Business", 3, 5));


        programData.add(new Program(1, "Programmer", 2.5f, new ArrayList<Long>(List.of(101L, 102L)), new ArrayList<Long>(List.of(1L,2L ))));
        programData.add(new Program(2, "CyberSecurity", 2.5f, new ArrayList<Long>(List.of(101L, 102L)), new ArrayList<Long>(List.of(1L,2L ))));
        programData.add(new Program(3, "Business", 2.5f,  new ArrayList<Long>(List.of(101L, 102L)), new ArrayList<Long>(List.of(1L,2L ))));
        
        courseData.add(new Course(1, "Intro to Programming", 1,new ArrayList<Long>(List.of(1L))));
        courseData.add(new Course(2, "Data Structures", 1, new ArrayList<Long>(List.of(1L))));
        courseData.add(new Course(3, "Finance", 3, new ArrayList<Long>(List.of(1L))));
        courseData.add(new Course(4, "Marketing", 3, new ArrayList<Long>(List.of(1L))));
        courseData.add(new Course(5, "Intro to Cybersecurity", 2,new ArrayList<Long>(List.of(1L))));
        courseData.add(new Course(6, "Networking", 2, new ArrayList<Long>(List.of(1L))));
        courseData.add(new Course(7, "Cloud Security", 2,new ArrayList<Long>(List.of(1L))));




    while (process) {
                // sorry to whoever has to edit this!
                System.out.printf("\nWelcome to the Student Enrollment System\n%s\nPlease select an option:\n%s\n%s\n%s\n%s\n%s\n%s\n", hr(), "1. Enroll a New Student", "2. Edit Enrolled Student", "3. View Enrolled Students", "4. Remove Enrolled Student", "5. Exit the Application", hr());

                // implementation here is kind of naive, but fits all test cases!
                String option = validateInputBasedOnCondition("Select your option: ", input -> {
                    return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5");
                });

                // my ide was hinting a "rule switch" case, but i like this implementation compared to tradtional switch case
                switch (option) {
                    case "1" -> {
                        EnrollStudent();
                    }
                    case "2" -> {
                        EditStudent();
                    }
                    case "3" -> {
                        ViewStudents();
                    }
                    case "4" -> {
                        RemoveStudent();
                    }
                    case "5" -> {
                        System.out.println("Exiting the system. Goodbye!");
                        process = false;
                    }
                }
            }
        }
    
    //
   
    // 🐱 generates id based on length of arraylist and handles all the class objects

    private static <T> long generateId(ArrayList<T> list) {
        return list.size() + 1;
    }

    // 🐱 reorders ids when class object is removed from the database 
    private static <T> void resequenceIds(ArrayList<T> list) {
        long id = 1;
        for (T obj : list) {
            if (obj instanceof Student s) {
                s.studentId = id++;
            } else if (obj instanceof Program p) {
                p.programId = id++;
            } else if (obj instanceof Course c) {
                c.courseId = id++;
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

    // we could overload "enrollstudent" method but i don't think it'll be necessary
    private static void EditStudent() {
        Student student = findStudentOnName();

        // the core function here should be, if the user wants to change something they can add an input
        // if they dont, just press Enter, the information does not change, we'll need to take this into account
    }

    // finds and removes student form ArrayList, pretty straightforward
    private static void RemoveStudent() {
        Student student = findStudentOnName();

        System.out.printf("%s %s has been removed from the student database.\n%s", student.firstName, student.lastName,hr());
        studentData.remove(student);
        resequenceIds(studentData);
    }
    


    // adds a new student to the "database"
    private static void EnrollStudent() {
        // 🐱 all of the class objects will have an id that iterates by one, another method will be created that sorts the id upon every delected and added entry.
        long studentId = generateId(studentData); 
        
        // input validation through regex, ensures that data is good for when we add it to the class
        String firstNameInput = validateInputBasedOnCondition("Student First Name: ", input -> input.matches("[a-zA-Z]+"));
        String lastNameInput = validateInputBasedOnCondition("Student Last Name: ", input -> input.matches("[a-zA-Z]+"));
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

        // string validation, allow space's for multiple words like "Software Development"
        String programInput = validateInputBasedOnCondition("Student Program: ", input -> input.matches("[a-zA-Z ]+"));

        // only allows unsigned ints
        String currentSemesterInput = validateInputBasedOnCondition("Current Semester: ", input -> input.matches("\\d+"));
        String coursesEnrolledInput = validateInputBasedOnCondition("Courses Enrolled: ", input -> input.matches("\\d+"));

        // adds new user to  database
        studentData.add(new Student(studentId,firstNameInput, lastNameInput, dobInput, Gender.valueOf(genderInput.toUpperCase()), Float.parseFloat(gpaInput), programInput, Integer.parseInt(currentSemesterInput), Integer.parseInt(coursesEnrolledInput)));

        System.out.printf("%s %s has been added to the student database.", firstNameInput, lastNameInput);
    }

    // this method iterates through studentData list and prints them
    private static void ViewStudents() {
        System.out.println("The Current Student List:\n");
        for (Student student : studentData) {
            // sorry to the reader of this
            System.out.printf("%s\nStudentId: %s\nFirst Name: %s\nLast Name: %s\nDate of Birth: %s\nGender: %s\nGPA: %.2f\nProgram: %s\nCurrent Semester: %d\nCourses Enrolled: %d\n", hr(), student.studentId, student.firstName, student.lastName, student.dateOfBirth,
                    student.gender, student.gpa, student.program, student.currentSemester, student.coursesEnrolled
            );
        }
    }

    // helper method that asks for a studet based on first and last name, no case sensitivty
    // will keep prompting until user inputs correct name
    private static Student findStudentOnName() {
        Student result = null;

        // we shouldn't need to validate, but it wont hurt considering all entries are compliant with the format
        String firstNameInput = validateInputBasedOnCondition("Student First Name: ", input -> input.matches("[a-zA-Z]+"));
        String lastNameInput = validateInputBasedOnCondition("Student Last Name: ", input -> input.matches("[a-zA-Z]+"));

        for (Student student : studentData) {
            if (student.firstName.equalsIgnoreCase(firstNameInput) && student.lastName.equalsIgnoreCase(lastNameInput)) {
                result = student;
            }
        }

        if (result == null) {
            System.out.printf("\n%s %s was not found, try again.\n", firstNameInput, lastNameInput);
            findStudentOnName();
        }

        return result;
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
