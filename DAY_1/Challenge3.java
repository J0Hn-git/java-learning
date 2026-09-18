package DAY_1;

import java.util.LinkedList;
import java.util.Scanner;

record Place(String name, int distance) {

    @Override 
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}

public class Challenge3 {
    public static void main(String[] args) {
        
        LinkedList<Place> placesToVisit = new LinkedList<>();

        addPlace(placesToVisit, new Place("Adelaide", 1374));
        addPlace(placesToVisit, new Place("adelaide", 1374));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Darwin", 3972));
        addPlace(placesToVisit, new Place("Melbourne", 877));

        placesToVisit.addFirst(new Place("Sydney", 0));

        Scanner sc = new Scanner(System.in);
        var iterator = placesToVisit.listIterator();

        printItems();

        boolean quitLoop = false;
        boolean forward = true;

        while(!quitLoop) {

            if(!iterator.hasPrevious()) {
                System.out.println("Origin: " + iterator.next());
                forward = true;
            }
            if(!iterator.hasNext()) {
                System.out.println("Final: " + iterator.previous());
                forward = false;
            }


            System.out.println("Enter the value: ");
            String value = sc.nextLine().toUpperCase().substring(0, 1);


            switch(value) {

                case "F":
                        if(!forward){
                            forward = true;
                            if(iterator.hasNext()){
                                iterator.next();
                            }
                        }
                        if(iterator.hasNext()){
                            System.out.println(iterator.next());
                        }

                case "L" : 
                         System.out.println(placesToVisit);
                         break;

                case "M" :
                         printItems();
                         break;

                default : 
                        quitLoop = true;
                        break;
            }
        }
        sc.close();
    }

    private static void addPlace(LinkedList<Place> list, Place place) {

        if(list.contains(place)) {
            System.out.println("Found Duplicate: " + place);
            return ;
        }

        for(Place p : list) {
            if(p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found Duplicate: " + place);
                return ;
            }
        }
        int matchedIndex = 0;

        for(var listPlace : list) {
            if(place.distance() < listPlace.distance()){
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }
        list.add(place);
    }

    private static void printItems() {

        String textBlock = """
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""";
        
        System.out.println(textBlock);
            
    }
}