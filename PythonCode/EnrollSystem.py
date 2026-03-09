


# main method
process = True

def main():
    while process:
        SelectOption()
    
# methods & helpers
def SelectOption():
    # displays options for user to select
    print("Welcome to the Student Enrollment System\n{}\nPlease select an option:\n{}\n{}\n{}\n{}".format(
            hr(),"1. Enroll a new student", "2. Edit student enrollment", "3. Exit the application", hr()))
    
    # accepts an input and validates it
    option = ValidateInputBasedOnCondition("Select your option: ", lambda x: x in ["1", "2", "3"])
    
    
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