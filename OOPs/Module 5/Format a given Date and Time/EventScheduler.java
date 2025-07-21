// EventScheduler.java
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class EventScheduler {
    private List<Event> events;
    private Scanner scanner;
    
    public EventScheduler() {
        // Initialize the events list and scanner
        this.events = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        boolean running = true;
        while (running) {
            // Display menu options
            System.out.println("\n=== Event Scheduler ===");
            System.out.println("1. Add Event");
            System.out.println("2. Display All Events");
            System.out.println("3. Show Time Until Event");
            System.out.println("4. Convert Event Time to Different Timezone");
            System.out.println("5. Find Upcoming Events");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = Integer.parseInt(scanner.nextLine());
            
          // Implement menu choices using switch-case
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
        }
    }
    //  Implement method to get event details from user and create a new Event
    private void addEvent() {
        try {
            System.out.println("Enter event name: ");
            String name = scanner.nextLine();
            System.out.println("Enter event's date in the format(yyyy-MM-dd): ");
            String dateStr = scanner.nextLine();
            LocalDate date=LocalDate.parse(dateStr);
            System.out.println("Enter the time of the event in the format(HH:mm: " );
            String timeStr=scanner.nextLine();
            LocalTime time=LocalTime.parse(timeStr);
            // Parse the date and time strings into LocalDateTime
            LocalDateTime dateTime=LocalDateTime.of(date,time);
            // Get a ZoneId from the timezone string
            System.out.println("Enter timezone (e.g., America/New_York, Europe/London): ");
            String timeZoneStr= scanner.nextLine();
            ZoneId zoneId=ZoneId.of(timeZoneStr);
            // Create a ZonedDateTime from LocalDateTime and ZoneId
            ZonedDateTime zonedDateTime=ZonedDateTime.of(dateTime,zoneId);
            // Create a Duration object from hours and minutes
            System.out.println("Enter duration in hours: ");
            int hours=Integer.parseInt(scanner.nextLine());
            System.out.println("Enter additional minutes: ");
            int minutes=Integer.parseInt(scanner.nextLine());
            Duration duration=Duration.ofHours(hours).plusMinutes(minutes);
            // Create a new Event object and add it to the events list
            Event event=new Event(name,zonedDateTime,duration);
            events.add(event);
            System.out.println("Event added successfully");
        }
        catch (DateTimeParseException e){
            System.out.println("Error: Invalid date time format.Try again");
        }
        catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }
    private void displayAllEvents() {
        if(events.isEmpty()){
            System.out.println("No events to display");
            return;
        }
        System.out.println("Enter the date format pattern e.g(dd-MM-yyyy hh:mm a , yyyy-MM-dd HH:mm z");
        String pattern= scanner.nextLine();
        System.out.println("\nAll Events");
        for(int i=0;i<events.size();i++){
            Event event=events.get(i);
            System.out.println(i+1+". "+event.getName()+" - "+
           event.formatDateTime(pattern)+"(Duration: "+
               event.getDuration().toHours()+"h "+
                    event.getDuration().toMinutesPart()+"m");
        }
    }
    
    private void showTimeUntilEvent() {
        // Hint: Show list of events with numbers
        // Get event selection from user
        // Calculate and display time until the selected event
        if(events.isEmpty()){
            System.out.println("NO events");
            return;
        }
        System.out.println("\n===All Events===");
        for(int i=0;i<events.size();i++){
            System.out.println(i+1+". "+events.get(i).getName());
        }
        try {
            System.out.println("Enter event number: ");
            int eventNumber= Integer.parseInt(scanner.nextLine())-1;
            if(eventNumber>=0 && eventNumber<events.size()){
                Event event= events.get(eventNumber);
                Duration timeUntil= event.TimeUntilEvent();
                if(timeUntil.equals(Duration.ZERO)){
                    System.out.println("Event already occurred");
                }
                else{
                    long days=timeUntil.toDays();
                    long hours=timeUntil.toHoursPart();
                    long minutes=timeUntil.toMinutesPart();
                    long seconds=timeUntil.toSecondsPart();
                    System.out.println("Time until " + event.getName() + ": " +
                            days + " days, " + hours + " hours, " +
                            minutes + " minutes, " + seconds + " seconds");
                }
            }
            else{
                System.out.println("Invalid event number.");
            }
        }
        catch (NumberFormatException e){
            System.out.println("Please enter a valid number");
        }
    }
    
    private void convertEventTime() {
        // Hint: Show list of events with numbers
        // Get event selection from user
        // Get target timezone from user
        // Convert and display event time in the target timezone
        if(events.isEmpty()){
            System.out.println("NO events");
            return;
        }
        System.out.println("\n===All Events===");
        for(int i=0;i<events.size();i++){
            System.out.println(i+1+". "+events.get(i).getName());
        }
        try {
            System.out.println("Enter event number: ");
            int eventNumber= Integer.parseInt(scanner.nextLine())-1;
            if(eventNumber>=0 && eventNumber<events.size()){
                Event event= events.get(eventNumber);
                System.out.println("Enter target Time zone (e.g ,America/New_York, Europe/London: ");
                String targetTimezone= scanner.nextLine();
                try{
                    ZonedDateTime convertedTime= event.convertToTimeZone(targetTimezone);
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
                    System.out.println("Event in "+targetTimezone+": "+convertedTime.format(formatter));
                }
                catch (Exception e){
                    System.out.println("invalid time zone. Please try again");
                }

    }
        else{
                System.out.println("Invalid event number.");
            }
        }
    catch (NumberFormatException e){
        System.out.println("Please enter a valid number");
    }
    }
    
    private void findUpcomingEvents() {
        // Hint: Get number of days from user
        // Loop through events and display those within the specified days
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }
        try {
            System.out.println("Enter the number of the days to look ahead");
            int days=Integer.parseInt(scanner.nextLine());
            ZonedDateTime now= ZonedDateTime.now();
            ZonedDateTime endDays= now.plusDays(days);
            System.out.println("\nEvents in the next "+days+"days:");
            boolean found=false;

            for(Event event:events){
                ZonedDateTime eventTime= event.getDateTime();
                if(eventTime.isAfter(now) && eventTime.isBefore(endDays)){
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
                    System.out.println("- " + event.getName() + " - " + eventTime.format(formatter));
                    found=true;
                }
            }
            if (!found) {
                System.out.println("No events found in the next " + days + " days.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    public static void main(String[] args) {
        // Step 15: Create an EventScheduler object and call its run method
        EventScheduler scheduler = new EventScheduler();
        scheduler.run();
    }
}
