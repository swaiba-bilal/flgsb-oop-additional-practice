import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * TravelPlanner - A utility to help travelers plan their trips
 * This class provides functionality to:
 * 1. Calculate the duration of a trip
 * 2. Validate travel dates
 * 3. Calculate check-in and check-out dates
 * 4. Check if a trip overlaps with holidays
 */
public class TravelPlanner {
    
    // Step 1: Declare a DateTimeFormatter for the standard date format "dd/MM/yyyy"
    // Hint: Use DateTimeFormatter.ofPattern()
  private static final DateTimeFormatter DATE_FORMATTER= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * Calculates the duration of a trip in days
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @return The number of days between departure and return
     */
    public static long calculateTripDuration(LocalDate departureDate, LocalDate returnDate) {
        // Step 2: Calculate and return the number of days between departure and return dates
        // Hint: Use ChronoUnit.DAYS.between()
        return  ChronoUnit.DAYS.between(departureDate,returnDate);
    }
    
    /**
     * Validates that the provided dates make logical sense for a trip
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @return true if dates are valid, false otherwise
     */
    public static boolean validateTravelDates(LocalDate departureDate, LocalDate returnDate) {
        // Step 3: Implement validation logic:
        // - Departure date should not be in the past
        LocalDate today=LocalDate.now();
        if(departureDate.isBefore(today)){
            System.out.println("Departure date cannot be in past");
            return false;
        }
        // - Return date should be after departure date
        if(returnDate.isBefore(departureDate)){
            System.out.println("Error: Return date must be after departure date");
            return false;
        }
        // - Trip should not be longer than 90 days
        long tripDuration= calculateTripDuration(departureDate, returnDate);
        if(tripDuration>90){
            System.out.println("Error: Trip cannot be longer than 90 days");
            return false;
        }
        return true; // Replace with actual validation
    }
    
    /**
     * Calculates hotel check-in and check-out dates based on travel dates
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @return A string containing the check-in and check-out dates
     */
    public static String calculateHotelDates(LocalDate departureDate, LocalDate returnDate) {
        // Step 4: Calculate hotel dates:
        String checkInDate=departureDate.format(DATE_FORMATTER);
        String checkOutDate=returnDate.format(DATE_FORMATTER);
        return "Hotel Check-in:  "+checkInDate+"\nHotel Check-out: "+checkOutDate; // Replace with actual calculation
    }
    
    /**
     * Checks if a trip overlaps with a specific holiday
     * @param departureDate The date of departure
     * @param returnDate The date of return
     * @param holidayDate The date of the holiday
     * @return true if the trip overlaps with the holiday, false otherwise
     */
    public static boolean tripOverlapsHoliday(LocalDate departureDate, LocalDate returnDate, LocalDate holidayDate) {
        // Step 5: Check if the holiday date falls between departure and return dates
      return  ( holidayDate.isEqual(departureDate) || holidayDate.isEqual(returnDate) ||
              (holidayDate.isAfter(departureDate)&& holidayDate.isBefore(returnDate)));
    }
    /**
     * Parses a date string into a LocalDate object
     * @param dateStr The date string in format "dd/MM/yyyy"
     * @return The parsed LocalDate
     * @throws DateTimeParseException if the date string cannot be parsed
     */
    private static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }
    
    /**
     * Main method to run the TravelPlanner application
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running=true;
        while (running) {
            System.out.println("\n===Travel Planner Application ===");
            System.out.println("All dates should be formatted in dd/MM/yyyy");
            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate trip duration");
            System.out.println("2. Validate travel dates");
            System.out.println("3. Calculate hotel check-in and check-out");
            System.out.println("4. Check if trip overlaps with a holiday");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine();
            LocalDate departureDate = null;
            LocalDate returnDate = null;
            // Step 7: Use try-catch blocks to handle invalid date inputs
            try {
                switch (choice) {
                    case "1":
                        System.out.println("Enter the departure date (dd/MM/yyyy)");
                        departureDate = parseDate(scanner.nextLine());
                        System.out.println("Enter the return date (dd/MM/yyyy)");
                        returnDate = parseDate(scanner.nextLine());
                        long duration = calculateTripDuration(departureDate, returnDate);
                        System.out.println("Trip duration: " + duration + "days.");
                        break;
                    case "2":
                        System.out.println("Enter the departure date (dd/MM/yyyy)");
                        departureDate = parseDate(scanner.nextLine());
                        System.out.println("Enter the return date (dd/MM/yyyy)");
                        returnDate = parseDate(scanner.nextLine());
                        boolean isValid = validateTravelDates(departureDate, returnDate);
                        if (isValid) {
                            System.out.println("Travel dates are valid!");
                        } else {
                            System.out.println("Travel dates are not valid");
                        }
                        break;
                    case "3":
                        System.out.println("Enter the departure date (dd/MM/yyyy)");
                        departureDate = parseDate(scanner.nextLine());
                        System.out.println("Enter the return date (dd/MM/yyyy)");
                        returnDate = parseDate(scanner.nextLine());
                        if (validateTravelDates(departureDate, returnDate)) {
                            String hotelDays = calculateHotelDates(departureDate, returnDate);
                            System.out.println(hotelDays);
                        }
                        break;
                    case "4":
                        System.out.println("Enter the departure date (dd/MM/yyyy)");
                        departureDate = parseDate(scanner.nextLine());
                        System.out.println("Enter the return date (dd/MM/yyyy)");
                        returnDate = parseDate(scanner.nextLine());
                        System.out.print("Enter holiday date (dd/MM/yyyy): ");
                        LocalDate holidayDate = parseDate(scanner.nextLine());
                        if (validateTravelDates(departureDate, returnDate)) {
                            boolean overLaps = tripOverlapsHoliday(departureDate, returnDate, holidayDate);
                            if (overLaps) {
                                System.out.println("Your trip overlaps with the holiday!");
                            } else {
                                System.out.println("Your trip does not overlaps with the holiday!");
                            }
                        }
                        break;
                    case "5":
                        running = false;
                        System.out.println("Thank you for using Travel Planner!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format. Please use dd/MM/yyyy format.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
