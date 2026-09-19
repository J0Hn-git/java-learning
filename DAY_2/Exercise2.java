package DAY_2;


interface FlightEnabled {

    final double MILES_TO_KM = 1.60934;
    final double KM_TO_MILES = 0.621371;

    void takeOff();
    void land();
    void fly();
}

interface Trackable {

    void track();
}

public class Exercise2 {
    public static void main(String[] args) {
        
        Bird bird = new Bird();
        Animal animal = bird;
        FlightEnabled flier = bird;
        Trackable tracked = bird;

        //animal.move();
        //flier.takeOff();
        //flier.fly();
        //tracked.track();
        //flier.land();

        inFlight(flier);

        Jet jet = new Jet();
        inFlight(jet);

        Truck truck = new Truck();
        truck.track();

        double kmsTraveled = 100;
        double milesTraveled = kmsTraveled * FlightEnabled.KM_TO_MILES;
        System.out.printf("the truck travelled %.2f km or %.2f miles%n", kmsTraveled, milesTraveled);
    
    }
    private static void inFlight(FlightEnabled flier) {
        flier.takeOff();
        flier.fly();

        if(flier instanceof Trackable tracker) {
            tracker.track();
        }
        flier.land();
    }
}

abstract class Animal {

    public abstract void move();
}

class Bird extends Animal implements FlightEnabled, Trackable {

    @Override 
    public void move() {
        System.out.println("Flap wings");
    }

    @Override 
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }
    @Override 
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }
    @Override 
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }
    @Override 
    public void track() {
        System.out.println(getClass().getSimpleName() + " coordinates recorded");
    }
}
class Jet implements FlightEnabled, Trackable {

    @Override 
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }
    @Override 
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }
    @Override 
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }
    @Override 
    public void track() {
        System.out.println(getClass().getSimpleName() + " coordinates recorded");
    }
}

class Truck implements Trackable {

    public void track() {
        System.out.println(getClass().getSimpleName() + " coordinates recorded");
    }
}
