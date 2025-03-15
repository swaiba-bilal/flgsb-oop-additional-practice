### Testing encapsulation
Create two classes. Student Class with attributes conforming to encapsulation principle and `Student` to test the `StudentRegistry` class.

#### Student Class:

1. Declare private variables for studentId, name, grade, and isActive
2. Create getter methods for each variable
3. Create setter methods for each variable
  - Add simple validation:
    - For grade: Ensure it is between 0 and 100
    - For studentId: No special validation needed
    - For name: No special validation needed
    - For isActive: No special validation needed
    
4. Create a method to strigify student details
     - Details should include ID, name, grade, and status (Active/Inactive)
        
5. Create a method that returns a letter grade based on the numeric grade
  >Hint: A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: below 60
    
6. Create a method to check if the student is passing (grade >= 60)

#### StudentRegistry Class:

1. Create two instances of the Student class
2. Use setter methods to set values for all attributes of first student
3. Set values for second student
4. Display details of both students
5. Compare the grades of the two students and print who has the higher grade
  // Hint: Create a separate method for this comparison
6. Test the letter grade method for both students
7. Test the passing status method for both students
8. Change one student to inactive and display the updated information
9. Create a static method to compare two students' grades and return the student with the higher grade
    // Hint: Take two Student objects as parameters
