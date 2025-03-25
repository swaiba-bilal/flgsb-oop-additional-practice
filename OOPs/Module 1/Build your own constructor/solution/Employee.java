// A simple class to demonstrate encapsulation
public class Employee {
    // Private variables - these are encapsulated
    private String name;
    private int age;
    private double salary;


    // Public methods to access the private variables (getters and setters)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) { // Validation to ensure age is positive
            this.age = age;
        } else {
            System.out.println("Invalid age! Age must be greater than 0.");
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) { // Validation to ensure salary is non-negative
            this.salary = salary;
        } else {
            System.out.println("Invalid salary! Salary must be non-negative.");
        }
    }

    // Method to get string version
    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nSalary: " + salary;
    }
}
