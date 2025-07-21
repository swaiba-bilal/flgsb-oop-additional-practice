// ContactReader.java
// This program reads contact information from a file and displays it in a formatted way
// Step 1: Import necessary packages for file I/O operations
// Hint: You'll need classes from java.io or java.nio.file packages
// You'll also need Scanner for user input
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class ContactReader {
    public static void main(String[] args) {
        // Step 2: Create a Scanner object to read user input
        Scanner sc= new Scanner(System.in);
        // Step 3: Prompt the user to enter the file name containing contacts
        // Example: "Enter the name of the contacts file:"
        System.out.println("Enter the name of the contact file");
        String filename=sc.nextLine();
        // Step 4: Read the file name entered by the user
        int contactCount=0;
        try {
            // Step 5: Create a FileReader or similar object to read the file
            // Hint: You can use FileReader, BufferedReader, or Files class
            BufferedReader reader= new BufferedReader(new FileReader(filename));

            // Step 6: Read the file line by line
            String line;
            System.out.println("\n=====Contacts List====");
            // Hint: Use a loop to process each line
            while((line=reader.readLine())!=null){{
                if(!line.trim().isEmpty()){
                    String [] parts= line.split(":");
                    if(parts.length==2){
                        String name= parts[0].trim();
                        String phoneNumber=parts[1].trim();
                        System.out.println("Contact: "+name+" | Phone: "+phoneNumber);
                        contactCount++;
                    }
                    else{
                        System.out.println("Warning: Skipping improperly formatted line: "+line);
                    }
                }
            }
            reader.close();
            }
            System.out.println("Total contact read "+contactCount);

                // Step 7: Parse each line to extract name and phone number
                // Hint: Use String methods like split() to separate by colon
                
                // Step 8: Display the contact information in a formatted way
                // Example: "Contact: John Doe | Phone: +1-555-123-4567"
            
            // Step 9: Close the file reader when done
            
        } catch (FileNotFoundException fe) {
            System.err.println("Error: File not found - "+filename);
            System.err.println("Please check the file name and path then try again");
            // Step 10: Handle exceptions appropriately
            // Display a user-friendly error message
            
        }
        catch (IOException e){
            System.out.println("Error reading from file "+ e.getMessage());
        }
        catch (Exception e){
            System.out.println("An unexpected error occurred: "+e.getMessage());
        }
        finally {
            sc.close();
        }
        
        // Optional Bonus:
        // Step 11: Add a feature to count and display the total number of contacts read
    }
}
