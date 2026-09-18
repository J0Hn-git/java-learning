package DAY_2;

import java.util.ArrayList;

public class Exercise1 {
    
    public static void main(String[] args) {
        
        Dog dog = new Dog("Wolf", "big", 100);

        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(dog);
        animals.add(new Dog("German Shepard", "big", 150));
        animals.add(new Fish("Goldfish", "small", 1));
        animals.add(new Fish("Fighter fish", "small", 1));
        animals.add(new Dog("pug", "small", 20));

        animals.add(new Horse("Clyesdale", "large", 200));

        for(Animal animal : animals) {
            doAnimalStuff(animal);

            if(animal instanceof Mammal currweMammal) {
                currweMammal.shedHair();
            }
        }

    }
    private static void doAnimalStuff(Animal animal) {

        animal.makeNoise();
        animal.move("slow");
    }
}

abstract class Mammal extends Animal {

    public Mammal(String type, String size, double weight) {
        super(type, size, weight);
    }

     @Override 
    public void move(String speed) {

        System.out.println(getExplicitType() + " ");
        System.out.println(speed.equals("slow") ? "walks" : "runs");
    }
    public abstract void shedHair();
}

class Horse extends Mammal {

    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override 
    public void shedHair(){
        System.out.println(getExplicitType() + " sheds in the spring");
    }
    @Override 
    public void makeNoise() {

    }
}

abstract class Animal {

    protected String type;
    private String size;
    private double weight;

    public Animal(String type, String size, double weight) {

        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public abstract void move(String speed);
    public abstract void makeNoise();

    public final String getExplicitType() {
        return getClass().getSimpleName() + " (" + type + ")";
    }
}
class Dog extends Animal {

    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override 
    public void move(String speed) {

        if(speed.equals("slow")){
            System.out.println(getExplicitType() + " walking");
        }
        else {
            System.out.println(getExplicitType() + " running");
        }
    }
    @Override 
    public void makeNoise() {
        
        if(type == "wolf"){
            System.out.println("Howling!");
        }
        else {
            System.out.println("Woof!");
        }
    }
}
class Fish extends Animal {

    public Fish(String type, String size, double weight) {
        super(type, size, weight);

    }

    @Override 
    public void move(String speed) {
        if(speed.equals("slow")) {
            System.out.println(getExplicitType() + " lazily swimming");
        }
        else {
            System.out.println(getExplicitType() + " darting frantically.");
        }
    }

    @Override 
    public void makeNoise() {
        
        if(type == "Goldfish") {
            System.out.println("swish");
        }
        else {
            System.out.println("splash");
        }
    }
}
