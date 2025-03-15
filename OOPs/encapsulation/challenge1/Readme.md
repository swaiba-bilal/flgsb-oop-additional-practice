### Testing encapsulation
Create two classes. Employee Class with attributes conforming to encapsulation principle and `TestEncapsulation` to test the `Employee` class.

#### Employee Class:

1. Declare private variables for name, age, and salary.

2. Implement public getter methods for each variable.

3. Implement public setter methods for each variable with validation logic:

    For age: Ensure it is greater than 0.

    For salary: Ensure it is non-negative.

4. Implement a method to stringify employee details.

#### TestEncapsulation Class:

1. Create an instance of the Employee class.

2. Use setter methods to set valid values for name, age, and salary.

3. Call the method to get employee details and print

4. Try setting invalid values for age and salary to test the validation logic.

5. Call the method to get employee details and print again.
