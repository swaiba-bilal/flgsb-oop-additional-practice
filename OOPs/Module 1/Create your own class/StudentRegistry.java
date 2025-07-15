// StudentRegistry class to test the Student class
public class StudentRegistry {
    public static void main(String[] args) {
        // Step 1: Create two instances of the Student class
        Student st1= new Student();
        Student st2= new Student();
        
        // Step 2: Use setter methods to set values for all attributes of first student
        // Example values: ID "S001", name "John Doe", grade 85.5, active true
        st1.setStudentId("S001");
        st1.setName("John  Doe");
        st1.setGrade(85.5);
        st1.setIsActive(true);
        // Step 3: Set values for second student
        // Example values: ID "S002", name "Jane Smith", grade 92.0, active true
        st2.setStudentId("S002");
        st2.setName("Jane Smith");
        st2.setGrade(92.0);
        st2.setIsActive(true);
        // Step 4: Display details of both students
        System.out.println("Details of students");
        System.out.println(st1.displayStudentDetails());
        System.out.println(st2.displayStudentDetails());
        
        // Step 5: Compare the grades of the two students and print who has the higher grade
        // Hint: Create a separate method for this comparison
        String result= comparingGrades(st1,st2);
        System.out.println("\nComparing grades "+result);
        
        // Step 6: Test the letter grade method for both students
        System.out.println("The letter grade of "+st1.getName()+": "+st1.getLetterGrade());
        System.out.println("The letter grade of "+st2.getName()+": "+st2.getLetterGrade());
        
        // Step 7: Test the passing status method for both students
        System.out.println("\nPassing status");
        System.out.println(st1.getName()+" is"+(st1.isPassing() ? " Passed":" not passed"));
        System.out.println(st2.getName()+" is"+(st2.isPassing() ? " Passed":" not passed"));
        
        // Step 8: Change one student to inactive and display the updated information
        st1.setIsActive(false);
        System.out.println("\nChanging " + st1.getName() + "'s status to inactive");
        System.out.println(st1);
    }
    
    // Step 9: Create a static method to compare two students' grades and return the student with the higher grade
    // Hint: Take two Student objects as parameters
    public static String comparingGrades(Student st11,Student st22){
        if(st11.getGrade()> st22.getGrade()){
            return st11.getName()+" has a higher grade";
        } else if (st22.getGrade()> st11.getGrade()) {
            return st22.getName()+" has a higher grade";
        }
        else
            return "Both students have same grade";
    }
}
