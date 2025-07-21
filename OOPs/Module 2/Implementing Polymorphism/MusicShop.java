// Step 1: Create an abstract class Instrument
abstract class Instrument{
    // - private String name
    private String name;
    // - protected int year (year of manufacture)
    protected int year;
    // - constructor that initializes both fields
   public Instrument(String name, int year){
       this.name=name;
       this.year=year;
   }
   public String getName(){
       return name;
   }
    // - abstract method play() that returns a String
   public abstract String play();
    // - concrete method getInstrumentDetails() that returns information about the instrument
   public String getInstrumentDetails(){
       return"Name of the instrument: "+this.name+
     ",Year of manufacture: "+this.year;
   }

}

// Step 2: Create an interface Tunable
interface Tunable{
    // - abstract method tune() that returns a String
    String tune();
    // - abstract method adjustPitch(boolean up) that returns a String (up means increase pitch)
     String adjustPitch(boolean up);
}

// Step 3: Create an interface Maintainable
interface Maintainable {
// - abstract method clean() that returns a String
String clean();
// - abstract method inspect() that returns a String
    String inspect();
}
// Step 4: Create a concrete class StringedInstrument that extends Instrument
class StringedInstrument extends Instrument {
    private int numberOfStrings;
// - constructor that initializes name, year, and numberOfStrings
    public StringedInstrument(String name,int year,int numberOfStrings){
        super(name, year);
        this.numberOfStrings=numberOfStrings;
    }
// - implementation of the abstract play() method

    @Override
    public String play() {
        return "Playing the stringed instrument";
    }
    // - override getInstrumentDetails() to include number of strings
    @Override
    public String getInstrumentDetails() {
      return   super.getInstrumentDetails()+", Strings: "+numberOfStrings;
    }
public int getNumberOfStrings(){
        return numberOfStrings;
}}
// Step 5: Create a concrete class Guitar that extends StringedInstrument 
// and implements Tunable and Maintainable
    class Guitar extends StringedInstrument implements Tunable,Maintainable{
// - private String guitarType (acoustic, electric, etc.)
    private String guitarType;
// - constructor that initializes all fields
    Guitar(String name,int year,int numberOfStrings,String guitarType){
        super(name,year,numberOfStrings);
        this.guitarType=guitarType;
    }
// Override the parent's class

    @Override
    public String play() {
        return "Playing the "+this.guitarType+" guitar with "+getNumberOfStrings()+" strings";
    }

    @Override
    public String getInstrumentDetails() {
        return super.getInstrumentDetails()+",Type: "+this.guitarType;
    }
    // Implement methods from Tunable interface

    @Override
    public String tune() {
        return "Tuning the "+guitarType+" guitar";
    }
    public String adjustPitch(boolean up){
        return up ? "Increasing pitch of the guitar":"Decreasing pitch of the guitar";
    }
    // Implement methods from Maintainable interface
    @Override
    public String clean(){
        return "Cleaning the "+guitarType+" guitar";
    }
    @Override
    public String inspect(){
        return "Inspecting the "+guitarType+" guitar from"+year;
    }}

    // Step 6: Create a concrete class Piano that extends Instrument
// and implements Tunable and Maintainable

    class Piano extends Instrument implements Tunable, Maintainable{
 private boolean isGrand;
// - constructor that initializes all fields
        public Piano(String name,int year, boolean isGrand){
            super(name,year);
            this.isGrand=isGrand;
        }
        public boolean isGrand(){
            return isGrand;
        }
// - implementation of the abstract play() method
        public String play(){
            return "Playing the "+(isGrand ?"grand":"upright")+" piano";
        }

        @Override
        public String getInstrumentDetails() {
            return super.getInstrumentDetails()+",Type "+(isGrand ?"Grand":"Upright");
        }
        @Override
        public String tune(){
            return "Tuning the piano";
        }

        @Override
        public String adjustPitch(boolean up) {
            return (up?"Increasing the pitch of piano":"Decreasing the pitch of piano");
        }
        @Override
        public String clean(){
            return "Cleaning the piano";
        }
        @Override
        public String inspect(){
            return "Inspecting the "+(isGrand?"Grand":"Upright")+" piano from "+year;
        }}

        // Step 7: Create a public class MusicShop to test the classes:
        public class MusicShop{
            public static void main(String[] args) {
//   1. Creates an array of Instrument objects including Guitar and Piano instances
                Instrument[] instruments=new Instrument[3];
                instruments[0] = new Guitar("Fender Stratocaster", 2020, 6, "electric");
                instruments[1] = new Piano("Steinway", 2015, true);
                instruments[2] = new Guitar("Martin", 2018, 12, "acoustic");
               // 2. Iterates through the array calling play() for each instrument
                System.out.println("======Playing instruments=====");
                for(Instrument instrument:instruments){
                    System.out.println(instrument.play());
                    System.out.println(instrument.getInstrumentDetails());
                }
//   3. Demonstrates polymorphism by testing if each instrument is Tunable or Maintainable
                System.out.println("\n====Maintaining instruments");
                for(Instrument instrument:instruments){
                    System.out.println("Working with "+instrument.getName());
                    if(instrument instanceof Tunable){
                        Tunable tunableInstrument=(Tunable) instrument;
                        System.out.println(tunableInstrument.tune());
                        System.out.println(tunableInstrument.adjustPitch(true));
                    }
                    if(instrument instanceof Maintainable){
                        Maintainable maintainableInstrument=(Maintainable) instrument;
                        System.out.println(maintainableInstrument.clean());
                        System.out.println(maintainableInstrument.inspect());
                    }
                }
                System.out.println("----------");


}}