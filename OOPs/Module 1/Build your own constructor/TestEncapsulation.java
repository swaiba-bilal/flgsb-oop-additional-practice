// TestEncapsulation class to test the Employee class
public class TestEncapsulation {
    public static void main(String[] args) {
        // Step 1: Create two instances of the Employee class
        // One using the parameterized constructor and one using default constructor with setters
        Employee emp1= new Employee();
        emp1.setName("Swaiba Bilal");
        emp1.setAge(20);
        emp1.setSalary(100000);
        Employee emp2= new Employee("Fatima Iqbal",20,150000);

        // Step 2: Print details of both employees
        System.out.println("Initial details of employees");
        emp1.display();
        emp2.display();
        // Step 3: Try setting invalid values (null name, age outside range, negative salary)
        // and see if your validation works
        System.out.println("Testing validations with invalid values");
        emp1.setName("");
        emp1.setAge(10);
        emp2.setSalary(-100);
        System.out.println("\nEmployee details after invalid values");
        emp1.display();
        emp2.display();
        // Step 4: Give both employees a 10% raise and display their details again
        emp1.giveRaise(10);
        emp2.giveRaise(10);
        System.out.println("\n Employee details after raise");
        emp1.display();
        emp2.display();
        // Step 5: Clone the first employee and display the cloned employee details
        // Hint: Use try-catch block to handle CloneNotSupportedException
        // Employee clonedEmployee = (Employee) employee1.clone();
        try {
            Employee clonedEmployee = (Employee) emp1.clone();
            // Step 6: Modify the original employee and verify that the clone remains unchanged
            // This demonstrates that cloning creates a separate object
            System.out.println("Modifying original employee's salary");
            emp1.setSalary(50000);
            System.out.println("printing details of original employee ");
            emp1.display();
            System.out.println("Printing details of cloned employees after modification in the original one");
            clonedEmployee.display();
        }
        catch(CloneNotSupportedException e){
            System.out.println("Cloning is not allowed "+e.getMessage());
        }
        // Step 7: Create a method that compares the salaries of two employees
        // and returns the name of the employee with the higher salary
        // If salaries are equal, return "Equal salaries"
        String result=compareSalaries(emp1,emp2);
        System.out.println("Salary comparison result "+result);
    }
    public static String compareSalaries(Employee emp11, Employee emp22){
        if(emp11.getSalary()== emp22.getSalary()){
         return "Salaries of both employees are equal or same";
        } else if (emp11.getSalary()>emp22.getSalary()) {
            return emp11.getName()+" has a higher salary ";
        }
        else{
         return   emp22.getSalary()+" has a higher salary";
        }
    }
}
