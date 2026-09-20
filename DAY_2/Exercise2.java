package DAY_2;

import java.util.LinkedList;
import java.util.*;

//Enum

enum FlightStages implements Trackable {
    
    GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;

    @Override 
    public void track() {

        if(this != GROUNDED){
            System.out.println("Monitoring " + this);
        }
    }
    public FlightStages getNextStage() {
        FlightStages[] allStages = values();
        return allStages[(ordinal() + 1) % allStages.length]; 
    }
}

// Record

record DragonFly(String name, String type) implements FlightEnabled {

    @Override 
    public void takeOff() {

    }
    @Override 
    public void land() {

    }
    @Override 
    public void fly() {

    }
}

// An Interface extending from another interface 

interface OrbitEarth extends FlightEnabled {

    void achieveOrbit();

    // Use of Public static methods in the interface.

    static void  log(String description) {

        var today = new java.util.Date();
        System.out.println(today + ": " + description);
    }
}

// interface

interface FlightEnabled {

    final double MILES_TO_KM = 1.60934;
    final double KM_TO_MILES = 0.621371;

    void takeOff();
    void land();
    void fly();


    // A default method has a body, becuase of default, this methos already has implementation.
    // Therefore a class implementing the inteface FlightEnabled doesnot need to ovveride transition().
    // Eg : imagine you have a interface and many classes implements the interface, now suppose you 
    // decided to add a new abstract method, then every class has to implement it.
    // but if you make them default, they get default behaviour and only needs to ovveride whne required.


    default FlightStages transition(FlightStages stage) {
       // System.out.println("transition not implemented on " + getClass().getName());
       // return null;

       FlightStages nexStages = stage.getNextStage();
       System.out.println("Transitioning from " + stage + " to " + nexStages);
       return nexStages;
    }
}

// interface

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

        LinkedList<FlightEnabled> fliers = new LinkedList<>();
        fliers.add(bird);

        List<FlightEnabled> betterFliers = new LinkedList<>();
        betterFliers.add(bird);

        triggerFliers(fliers);
        flyFliers(fliers);
        landFliers(fliers);

        triggerFliers(betterFliers);
        flyFliers(betterFliers);
        landFliers(betterFliers);
    
    }
    private static void inFlight(FlightEnabled flier) {
        flier.takeOff();
        flier.fly();

        if(flier instanceof Trackable tracker) {
            tracker.track();
        }
        flier.land();
    }
    private static void triggerFliers(List<FlightEnabled> fliers) {

        for(var flier : fliers) {
            flier.takeOff();
        }
    }
    private static void flyFliers(List<FlightEnabled> fliers) {

        for(var flier : fliers) {
            flier.fly();
        }
    }
    private static void landFliers(List<FlightEnabled> fliers) {

        for(var flier : fliers) {
            flier.land();
        }
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

    /*
    * transition() is a default method in the FlightEnabled interface,
    * so Jet does not have to override it.
    *
    * Here, Jet overrides transition() but still uses the default
    * implementation from FlightEnabled using:
    *
    *     FlightEnabled.super.transition(stage);
    *
    * This means:
    * Jet.transition()
    *      -> FlightEnabled's default transition()
    *
   * This override is not necessary if no Jet-specific behavior is needed.
   * It is useful when we want to add Jet-specific behavior while still
   * reusing the interface's default implementation.
   */

    @Override 
    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getSimpleName() + " transitioning");
        return FlightEnabled.super.transition(stage);
    }

}

class Truck implements Trackable {

    public void track() {
        System.out.println(getClass().getSimpleName() + " coordinates recorded");
    }
}

class Satellite implements OrbitEarth {

    public void achieveOrbit() {
        System.out.println("Orbit achieved");
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
}
