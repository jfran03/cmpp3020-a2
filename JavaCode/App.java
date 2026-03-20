package JavaCode;

import java.lang.reflect.Array;
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
        // sorry to whoever has to edit this!
        System.out.printf("\nHello, welcome to the Registration Program\n%s\nPlease select an option:\n%s\n%s\n%s\n%s\n%s\n", 
                        hr(), 
                        "1. View data related to Students", 
                        "2. View instution's programs", 
                        "3. View instituions courses", 
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
                    System.out.printf("\nStudent Menu\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                        hr(),
                        "1. Enroll a New Student",
                        "2. Edit Student",
                        "3. View Enrolled Students",
                        "4. Remove Student",
                        "5. Return to Main Menu",
                        hr()
                    );

                    String option = validateInputBasedOnCondition("Select your option: ", input -> {
                        return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5");
                    });
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
                            studentMenu = false;
                        }
                    }
                }
            }
            case "2" -> {  
                boolean programMenu = true;
                while(programMenu) {
                    System.out.printf("\nProgram Menu\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                        hr(),
                        "1. View Programs",
                        "2. Create Program",
                        "3. Edit Program",
                        "4. Remove Program",
                        "5. Return to Main Menu",
                        hr()
                    );

                    String option = validateInputBasedOnCondition("Select your option: ", input -> {
                        return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5");
                    });
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
                        programMenu = false;
                    }
                }
                }
            }
            case "3" -> {  
                boolean courseMenu = true;
                while(courseMenu) {
                    System.out.printf("\nCourse Menu\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                        hr(),
                        "1. View Courses",
                        "2. Create Course",
                        "3. Remove Course",
                        "4. Return to Main Menu",
                        hr()
                    );

                    String option = validateInputBasedOnCondition("Select your option: ", input -> {
                        return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4");
                    });
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

    //this method queries the class instances and is only invoked through the validation interface, if entity exists it will return the queried entity 
    //this acts as a helper method to handle database queries and functions
    //Uses call by refernece  
    //Used source to help with Type generics: https://docs.oracle.com/javase/tutorial/java/generics/types.html 

    private static <T> T queryDatabase (ArrayList<T> list,  ValidationCondition condition) {
        for (T item : list) {
            String name = "";

            if (item instanceof Student s) {
                name = s.firstName + " " + s.lastName;
            } else if (item instanceof Program p) {
                name = p.programName;
            } else if (item instanceof Course c) {
                name = c.courseName;
            }

            if (condition.validate(name)) {
                return item;
            }
        }
        return null;
    }

    //this method gets the program's dummy dataset and populates each class object's arrayset to reference each other to simulate a working database 
    private static void populateDatabase() {
        // ideally we'll never do this in the same program as the App.java, FYI!
        studentData.add(new Student("Jefff", "Bezsos", "13/13/2020", Gender.MALE, 2.0f, "softwareDevelopment", 1, 0,new ArrayList<String>()));
        studentData.add(new Student("Mark", "Grayson", "10/10/2020", Gender.MALE, 3.5f, "CyberSecurity", 2, 0,new ArrayList<String>()));
        studentData.add(new Student("Robert", "Bob", "09/09/2020", Gender.OTHER, 3.0f, "Business", 3, 0,new ArrayList<String>()));


        programData.add(new Program("softwareDevelopment", 2.5f, new ArrayList<String>(), new ArrayList<String>()));
        programData.add(new Program("CyberSecurity", 2.5f,new ArrayList<String>(), new ArrayList<String>()));
        programData.add(new Program("Business", 2.5f, new ArrayList<String>(), new ArrayList<String>()));
        
        courseData.add(new Course("Intro to Programming", "softwareDevelopment",new ArrayList<String>()));
        courseData.add(new Course("Data Structures", "softwareDevelopment", new ArrayList<String>()));
        courseData.add(new Course("Finance", "Business", new ArrayList<String>()));
        courseData.add(new Course("Marketing", "Business", new ArrayList<String>()));
        courseData.add(new Course("Intro to Cybersecurity", "CyberSecurity",new ArrayList<String>()));
        courseData.add(new Course("Networking", "CyberSecurity",new ArrayList<String>()));
        courseData.add(new Course("Cloud Security","CyberSecurity",new ArrayList<String>()));
        
        //populates empty array fields for the class instances 
        for (Student s : studentData) {
            for (Program p : programData) {
                if (p.programName.equalsIgnoreCase(s.program)) {
                    p.enrolledStudents.add(s.firstName + " " + s.lastName);
                }
            }
        }
        //

        for (Course c : courseData) {
        for (Program p : programData) {
            if (p.programName.equalsIgnoreCase(c.programName)) {
                p.requiredCourses.add(c.courseName);
            }
        }

        
    }

    }

    //this method updates the data whenever a change has been created in the database,
    private static void updateDatabase() {
        //all students must belong to a program, if they dont put them as unregistered 
    }

    //this method cleans up any data that exists in the database but shouldn't, 
    //Only used for testing purposes for the dummy data 
    private static void cleanUpDatabase() {
        //checks each class instance's array to see if the all of its contents are properly referencing each other or not
        //Database rules:
        //all programs should have a course. If not delete it
        //all courses should belong to a program.If not delete it
        //all students must belong to a program, if they dont put them as unregistered 
        //removes class instances that shouldn't exist through validation interface 
    } 
    // we could overload "enrollstudent" method but i don't think it'll be necessary
 
    private static void EditStudent() {
        Student student = findStudentOnName();

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

        validateInputBasedOnCondition("Student GPA (4.0 Scale): ", input -> {
            // hacky fix
            boolean valid = input.length() == 0 || (Float.parseFloat(input) >= 0.0f && Float.parseFloat(input) <= 4.0f);

            if (valid) {
                if (input.length() != 0) {
                    student.gpa = Float.parseFloat(input);
                }
                return true;
            } else {
                return false;
            }
        });

        validateInputBasedOnCondition("Student Program: ", input -> {
            boolean valid = input.matches("[a-zA-Z]+") || input.length() == 0;

            if (valid) {
                if (input.length() != 0) {
                    student.program = input;
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

        //took courses enrolled out as it will be 0 by default 
        /*
        validateInputBasedOnCondition("Courses Enrolled: ", input -> {
            boolean valid = input.matches("\\d+") || input.length() == 0;

            if (valid) {
                if (input.length() != 0) {
                    student.numberOfCourses = Integer.parseInt(input);
                }
                return true;
            } else {
                return false;
            }
        });
        */
        System.out.printf("%s\n%s %s has been updated.\n%s", hr(),student.firstName, student.lastName,hr());
    }


    // finds and removes student form ArrayList, pretty straightforward
    private static void RemoveStudent() {
        Student student = findStudentOnName();

        System.out.printf("%s %s has been removed from the student database.\n%s", student.firstName, student.lastName,hr());
        studentData.remove(student);
    }
    
    // adds a new student to the "database"
    private static void EnrollStudent() {

        
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
        // removed input for updated validation flow; Since the student needs to be a certain gpa to enroll in a program 
        // It would be better to have the student be created first then enroll after 

        //String programInput = validateInputBasedOnCondition("Student Program: ", input -> input.matches("[a-zA-Z ]+"));

        // only allows unsigned ints
        String currentSemesterInput = validateInputBasedOnCondition("Current Semester: ", input -> input.matches("\\d+"));

        //took "numberOfCoursesInput" out as it will be 0 by default 
        //String numberOfCoursesInput = validateInputBasedOnCondition("Courses Enrolled: ", input -> input.matches("\\d+"));

        // adds new user to database
        studentData.add(new Student(firstNameInput, lastNameInput, dobInput, Gender.valueOf(genderInput.toUpperCase()), Float.parseFloat(gpaInput), "unregistered", Integer.parseInt(currentSemesterInput), 0, new ArrayList<String>()));
        // updateDatabase()
        System.out.printf("%s %s has been added to the student database.", firstNameInput, lastNameInput);
    }

    // this method iterates through studentData list and prints them
    private static void ViewStudents() {
        System.out.println("The Current Student List:\n");
        for (Student student : studentData) {
            // sorry to the reader of this
            System.out.printf(" %s\nFirst Name: %s\nLast Name: %s\nDate of Birth: %s\nGender: %s\nGPA: %.2f\nProgram: %s\nCurrent Semester: %d\nCourses Enrolled: %d\n", hr(), student.firstName, student.lastName, student.dateOfBirth,
                    student.gender, student.gpa, student.program, student.currentSemester, student.numberOfCourses
            );
        }
    }

    private static void EnrollToNewProgram() {
    
        // updateDatabase()

    }

    
    //program functions 
    private static void createProgram() {
        String programNameInput = validateInputBasedOnCondition("Student First Name: ", input -> input.matches("[a-zA-Z]+"));
        
        String gpaInput = validateInputBasedOnCondition("Student GPA (4.0 Scale): ", input -> {
            try {
                float gpa = Float.parseFloat(input);
                return gpa >= 0.0f && gpa <= 4.0f;
            } catch (NumberFormatException e) {
                return false;
            }
        
        Array coursesInput = validateInputBasedOnCondition(input, null)
        });    
        // updateDatabase()
    }
    private static void removeProgram() {
        //When a program is removed iterate courses array
        //for each course in array 
        //removeCourse()

    }
    
    private static void editProgram() {
        // updateDatabase()

    }
    private static void addStudentToPorgram() {
        // updateDatabase()
    }

    private static void editEnrolledStudentsInProgram() {
        // updateDatabase()
    }

    //course functions 
    private static void createCourse() {
        // updateDatabase()
    }
    
    private static void removeCourse() {
        //When a course is removed iterate students array
        //for each students in array 
        //remove all students from course 
        //then remove course 
    }
    private static void editEnrolledStudentsInCourse() {
        // updateDatabase()
    }
    private static void addStudenToCourse() {
        // updateDatabase()
    }

    private static void dropStudentfromCourse() {
        // updateDatabase()
    }
    
    private static void viewEnrolledStudentsForCourse() {
        
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
