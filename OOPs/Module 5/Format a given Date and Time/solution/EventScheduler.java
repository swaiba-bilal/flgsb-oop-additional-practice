package solution;// EventScheduler.java
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventScheduler {
    private List<Event> events;
    private Scanner scanner;
    
    // Step 7: Initialize the events list and scanner
    public EventScheduler() {
        this.events = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        boolean running = true;
        while (running) {
            // Step 8: Display menu options
            System.out.println("\n=== Event Scheduler ===");
            System.out.println("1. Add Event");
            System.out.println("2. Display All Events");
            System.out.println("3. Show Time Until Event");
            System.out.println("4. Convert Event Time to Different Timezone");
            System.out.println("5. Find Upcoming Events");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                // Step 9: Implement menu choices using switch-case
                switch (choice) {
                    case 1:
                        addEvent();
                        break;
                    case 2:
                        displayAllEvents();
                        break;
                    case 3:
                        showTimeUntilEvent();
                        break;
                    case 4:
                        convertEventTime();
                        break;
                    case 5:
                        findUpcomingEvents();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting the Event Scheduler. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    // Step 10: Implement method to get event details from user and create a new Event
    private void addEvent() {
        try {
            // Get event details from user
            System.out.print("Enter event name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter date (yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateStr);
            
            System.out.print("Enter time (HH:mm): ");
            String timeStr = scanner.nextLine();
            LocalTime time = LocalTime.parse(timeStr);
            
            // Combine date and time
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            
            System.out.print("Enter timezone (e.g., America/New_York, Europe/London): ");
            String timezone = scanner.nextLine();
            ZoneId zoneId = ZoneId.of(timezone);
            
            // Create ZonedDateTime
            ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, zoneId);
            
            System.out.print("Enter duration in hours: ");
            int hours = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter additional minutes: ");
            int minutes = Integer.parseInt(scanner.nextLine());
            
            // Create Duration
            Duration duration = Duration.ofHours(hours).plusMinutes(minutes);
            
            // Create and add the event
            Event event = new Event(name, zonedDateTime, duration);
            events.add(event);
            
            System.out.println("Event added successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date or time format. Please try again.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Step 11: Implement method to display all events
    private void displayAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events to display.");
            return;
        }
        
        System.out.print("Enter date format pattern (e.g., yyyy-MM-dd HH:mm z, dd-MMM-yyyy hh:mm a): ");
        String pattern = scanner.nextLine();
        
        System.out.println("\nAll Events:");
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            System.out.println((i + 1) + ". " + event.getName() + " - " + 
                              event.formatDateTime(pattern) + " (Duration: " +
                              event.getDuration().toHours() + "h " + 
                              event.getDuration().toMinutesPart() + "m)");
        }
    }
    
    // Step 12: Implement method to calculate and display time until a specific event
    private void showTimeUntilEvent() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }
        
        // Display list of events
        System.out.println("\nSelect an event:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).getName());
        }
        
        try {
            System.out.print("Enter event number: ");
            int eventNumber = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (eventNumber >= 0 && eventNumber < events.size()) {
                Event event = events.get(eventNumber);
                Duration timeUntil = event.timeUntilEvent();
                
                if (timeUntil.equals(Duration.ZERO)) {
                    System.out.println("This event has already occurred.");
                } else {
                    long days = timeUntil.toDays();
                    long hours = timeUntil.toHoursPart();
                    long minutes = timeUntil.toMinutesPart();
                    long seconds = timeUntil.toSecondsPart();
                    
                    System.out.println("Time until " + event.getName() + ": " +
                                      days + " days, " + hours + " hours, " +
                                      minutes + " minutes, " + seconds + " seconds");
                }
            } else {
                System.out.println("Invalid event number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    // Step 13: Implement method to convert event time to a different timezone
    private void convertEventTime() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }
        
        // Display list of events
        System.out.println("\nSelect an event:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).getName());
        }
        
        try {
            System.out.print("Enter event number: ");
            int eventNumber = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (eventNumber >= 0 && eventNumber < events.size()) {
                Event event = events.get(eventNumber);
                
                System.out.print("Enter target timezone (e.g., America/New_York, Europe/London): ");
                String targetTimezone = scanner.nextLine();
                
                try {
                    ZonedDateTime convertedTime = event.convertToTimezone(targetTimezone);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
                    System.out.println("Event in " + targetTimezone + ": " + convertedTime.format(formatter));
                } catch (Exception e) {
                    System.out.println("Invalid timezone. Please try again.");
                }
            } else {
                System.out.println("Invalid event number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    // Step 14: Implement method to find events within a specific number of days
    private void findUpcomingEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }
        
        try {
            System.out.print("Enter number of days to look ahead: ");
            int days = Integer.parseInt(scanner.nextLine());
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime endDate = now.plusDays(days);
            
            System.out.println("\nEvents in the next " + days + " days:");
            boolean found = false;
            
            for (Event event : events) {
                ZonedDateTime eventTime = event.getDateTime();
                if (eventTime.isAfter(now) && eventTime.isBefore(endDate)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
                    System.out.println("- " + event.getName() + " - " + eventTime.format(formatter));
                    found = true;
                }
            }
            
            if (!found) {
                System.out.println("No events found in the next " + days + " days.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    // Step 15: Create an EventScheduler object and call its run method
    public static void main(String[] args) {
        EventScheduler scheduler = new EventScheduler();
        scheduler.run();
    }
}

