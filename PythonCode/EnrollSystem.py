import json
# main method
process = True

def main():
    SelectOption()

#This source was used to make this function: 
#https://www.geeksforgeeks.org/python/append-to-json-file-using-python/
def EnrollNewStudent(new_student, filename = 'students.json'):
    #This functions adds in new students to the data file.
    with open(filename, 'r+') as file:
        students_data = json.load(file)
        students_data["students"].append(new_student)
        file.seek(0)
        json.dump(students_data, file, indent=4)

# methods & helpers
def SelectOption():
    # displays options for user to select
    print("Welcome to the Student Enrollment System\n{}\nPlease select an option:\n{}\n{}\n{}\n{}".format(
            hr(),"1. Enroll a New Student", "2. Edit Enrolled Student", "3. Exit the Application", hr()))
    
    # accepts an input and validates it
    option = ValidateInputBasedOnCondition("Select your option: ", lambda x: x in ["1", "2", "3"])
    if option == "1":
        #This makes the user input the information for the student that will be added to the data file.
        print("Enter new student information:")
        n_name = input("Name: ")
        n_dob = input ("Date of birth: ")
        n_gen = input ("Gender: ")
        n_gpa = input ("GPA: ")
        n_sem = input ("Semester: ")
        n_pro = input ("Program: ")
        n_course = input ("Number of courses: ")
        #This source was used to create create the dictionary to add it to the database:
        #https://www.w3schools.com/python/python_dictionaries.asp
        #This takes all of the inputs and puts them into a dictionary to be added to the data file.
        newstudent = dict(Name = n_name,Date_of_birth = n_dob, Gender = n_gen, GPA = n_gpa, Semester = n_sem, Program = n_pro, Number_of_courses = n_course)
        EnrollNewStudent(newstudent)
        print("Student has been added to the system.")
    elif option == "2":
        print("Edit")
    else:
        print("Thank you for using the program.")
    


# this method takes in a condition and validate it BASED on custom conditions
# this should make it such that the method can be used for various use cases in the CLI
def ValidateInputBasedOnCondition(inputString, func):
    option = input(inputString)

    valid = func(option)

    if valid:
        return option
    else:
        print("Invalid input, please try again.")
        return ValidateInputBasedOnCondition(inputString, func)

# create a horizontal line for better UI
# if a length arg is not provided, assume 50 characters
def hr(length=50):
    return "-" * length

if __name__ == "__main__":
    main()


