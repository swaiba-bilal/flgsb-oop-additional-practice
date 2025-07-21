import java.util.*;

public class LibraryManagementSystem {
    // Book class to represent book information
    static class Book {
        // Step 1: Declare variables for title, author, genre, and publication year
        private String title;
        private String author;
        private String genre;
        private int publicationYear;
        // Step 2: Create a constructor for the Book class
        Book(String title,String author,String genre,int publicationYear){
            this.title=title;
            this.author=author;
            this.genre=genre;
            this.publicationYear=publicationYear;
        }
        // Step 3: Create getter methods for each attribute
        // Hint: Use the format: public dataType getAttribute()
        public String getTitle(){
            return title;
        }
        public String getAuthor(){
            return author;
        }
        public String getGenre(){
            return genre;
        }

        public int getPublicationYear() {
            return publicationYear;
        }
        // Step 4: Create a method to display book details
      public   String toString(){
            return "Title: "+title+", Author: "+author+", Genre: "+",Publication Year: "+publicationYear;
        }
    }
    
    // Method to validate if the title and author have valid formats
    private static boolean isValidText(String text) {
       return text!=null && !text.trim().isEmpty();
    }
    // Method to validate publication year
    private static boolean isValidYear(int year) {
       int currentYear= Calendar.getInstance().get(Calendar.YEAR);
        return year>=1000 && year<= currentYear;
    }
    public static void addBook(Scanner sc,HashMap<String ,Book> library){
        boolean hasAdded= true;
        while(hasAdded){
            System.out.println("Enter ISBN");
                String isbn= sc.nextLine();
                // Check if isbn already exists
                if(library.containsKey(isbn)){
                    System.out.println("A book with this isbn already exists");
                    System.out.println("Do you want to replace it?(y/n)");
                    String replace=sc.nextLine();
                    if(!replace.equalsIgnoreCase("y")){
                        continue;
                    }
                }
                System.out.println("Enter the title of the book");
                String title= sc.nextLine();
                if(!isValidText(title)){
                    System.out.println("Invalid title.Title cannot be empty");
                    continue;
                }
                System.out.println("Enter the author of the book");
                String author= sc.nextLine();
                if(!isValidText(author)){
                    System.out.println("Invalid author.Author cannot be empty");
                    continue;
                }
                System.out.println("Enter the genre");
                String genre=sc.nextLine();
                if(!isValidText(genre)){
                    System.out.println("Invalid genre.Genre cannot be empty");
                    continue;
                }
                int year;
                try{
                System.out.println("Enter the publication year");
              year=Integer.parseInt(sc.nextLine());
                if(!isValidYear(year)){
                    System.out.println("Invalid year");
                    continue;
                }}
                catch (NumberFormatException e){
                    System.out.println("Invalid Number. Enter a valid number");
                    continue;
                }
                Book newBook = new Book(title,author,genre,year);
                library.put(isbn,newBook);
            System.out.println("Book added successfully");
            hasAdded=false;

            }
        }
        public static void viewAllBooks(HashMap<String,Book> library){
        if(library.isEmpty()){
            System.out.println("Library is empty");
        }
        for(Map.Entry<String,Book> entry:library.entrySet()){
            System.out.println("ISBN: "+entry.getKey()+" ,"+entry.getValue());
        }
        }
        public static void searchAllBooks(Scanner sc, HashMap<String, Book>library){
            if(library.isEmpty()){
                System.out.println("Library is empty");
            }
            System.out.println("Press 1 to search by ISBN"+"\n Press 2 to search by title"+
                    "\n Press3 to search by author");
            int choice= Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    System.out.println("Enter ISBN");
                    String isbn=sc.nextLine();
                    if(library.containsKey(isbn)){
                        System.out.println("Book found: ");
                        System.out.println("ISBN: "+isbn+","+library.get(isbn));
                    }
                    else{
                        System.out.println("No book found matching your results");
                    }
                    break;
                case 2:
                    System.out.println("Enter title");
                    String title=sc.nextLine().toLowerCase();
                    boolean titleFound = false;
                  for(Map.Entry<String, Book> entry: library.entrySet()){
                      if(entry.getValue().getTitle().toLowerCase().contains(title)){
                          if (!titleFound) {
                              System.out.println("\nBooks found:");
                              titleFound = true;
                          }
                          System.out.println("ISBN: "+entry.getKey()+", "+entry.getValue());
                      }
                  }
                    if (!titleFound) {
                    System.out.println("Nothing found matching your results");}
                    break;
                case 3:
                    System.out.println("Enter author");
                    String author=sc.nextLine().toLowerCase();
                    boolean authorFound = false;
                    for(Map.Entry<String, Book> entry: library.entrySet()){
                        if(entry.getValue().getTitle().toLowerCase().contains(author)){
                            if(!authorFound){
                            System.out.println("\n Book found:");
                            authorFound=true;}
                            System.out.println("ISBN: "+entry.getKey()+", "+entry.getValue());
                        }
                    }
                    if (!authorFound) {
                        System.out.println("Nothing found matching your results");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        public  static void removeBook(Scanner sc,HashMap<String,Book> library){
            System.out.println("Enter the isbn of the book to remove ");
                String isbn=sc.nextLine();
                if(library.containsKey(isbn)){
                    Book removedBook=library.remove(isbn);
                    System.out.println("Book removed successfully: ");
                    System.out.println("ISBN: "+isbn+", "+library.get(isbn));
                }
                else{
                    System.out.println("No book found with isbn: "+isbn);
                }

        }
public static void sortBooks(Scanner sc, HashMap<String,Book> library){
        if(library.isEmpty()){
            System.out.println("library is empty");
        }
        else {
            System.out.println("\n===== Sort Books =====");
            System.out.println("1. Sort by Title");
            System.out.println("2. Sort by Author");
            System.out.print("Enter your choice: ");
            try{
            int sortChoice = Integer.parseInt(sc.nextLine());
            switch (sortChoice){
                case 1:
                    TreeMap<String,Book> sortedByTitle= new TreeMap<>(
                            Comparator.comparing(isbn ->library.get(isbn).getTitle())
                    );
                    sortedByTitle.putAll(library);

                    System.out.println("\n===== Books Sorted by Title =====");
                    for (Map.Entry<String, Book> entry : sortedByTitle.entrySet()) {
                        System.out.println("ISBN: " + entry.getKey() + ", " + entry.getValue());
                    }
                    break;
                case 2:
                    TreeMap<String,Book> sortedByAuthor= new TreeMap<>(
                            Comparator.comparing(isbn ->library.get(isbn).getAuthor())
                    );
                    sortedByAuthor.putAll(library);
                    System.out.println("\n===== Books Sorted by Author =====");
                    for(Map.Entry<String,Book> entry: sortedByAuthor.entrySet()){
                        System.out.println("ISBN: "+entry.getKey()+", "+entry.getValue());
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }}
            catch (NumberFormatException e){
                System.out.println("Invalid ");
            }
        }

}

    public static void main(String[] args) {
        // Step 7: Create a Scanner for user input
        Scanner sc= new Scanner (System.in);
        // Step 8: Create a HashMap to store books (with ISBN as the key)
        HashMap<String,Book> library= new HashMap<>();
        // Step 9: Implement the main loop with menu options
        // Hint: Options should include adding a book, viewing all books, 
        // searching for books, removing a book, viewing sorted books, and exiting
        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add a book");
            System.out.println("2. View all books");
            System.out.println("3. Search for a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View sorted books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    addBook(sc,library);
                    break;
                case "2":
                    viewAllBooks(library);
                    break;
                case "3":
                    searchAllBooks(sc,library);
                    break;
                case "4":
                    removeBook(sc,library);
                    break;
                case "5":
                    sortBooks(sc,library);
                    break;
                case "6":
                    System.out.println("Thanks for using library management system. GoodBye!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}