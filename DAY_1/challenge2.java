package DAY_1;

import java.util.LinkedList;
import java.util.ListIterator;

public class challenge2 {
    public static void main(String[] args) {
        
        LinkedList<String> placesToVisit = new LinkedList<>();

        placesToVisit.add("zurich");
        placesToVisit.add(0, "belgrade");

        System.out.println(placesToVisit);

        addMoreElements(placesToVisit);
        System.out.println(placesToVisit);

        removeElements(placesToVisit);
        System.out.println(placesToVisit);

        System.out.println("-".repeat(30));

        gettingElements(placesToVisit);
        System.out.println(placesToVisit);

        System.out.println("-".repeat(30));

        printItinerary(placesToVisit);
    }
    private static void addMoreElements(LinkedList<String> list) {

        list.addFirst("berlin");
        list.addLast("barcelona");
        
        // queue methods
        list.offer("Munichen");
        list.offerFirst("real madrid");
        list.offerLast("Chelsea");

        // Stack methods
        list.push("Paris");
        
    }
    private static void removeElements(LinkedList<String> list) {

        list.remove("belgrade");
        list.remove(2);

        //Removes first element.
        String s1 = list.remove();
        System.out.println(s1 + " was removed");

        //Removes first element.
        String s2 = list.removeFirst();
        System.out.println(s2 + " was removed");

        //Removes last element
        String s3 = list.removeLast();
        System.out.println(s3 + " was removed");

        //Queue, dequeue, poll methods.
        String p1 = list.poll();
        System.out.println(p1 + " was removed");

        String p2 = list.pollFirst();
        System.out.println(p2 + " was removed");

        String p3 = list.pollLast();
        System.out.println(p3 + " was removed");

        list.push("manchester");
        list.push("barcelona");
        list.push("real madrid");
        list.push("paris saint germain");
    }

    private static void gettingElements(LinkedList<String> list) {

        System.out.println("Retrieved element: " + list.get(2));

        System.out.println("First Element = " + list.getFirst());
        System.out.println("Last Element = " + list.getLast());

        System.out.println("Barcelona is a t postion " + list.indexOf("barcelona"));
        System.out.println("manchester is a t postion " + list.lastIndexOf("manchester"));

        //Queue retrieval method.
        System.out.println("Element from element = " + list.element());
        
        // Stack retrieval method.
        System.out.println("Element from peek() = " + list.peek());
        System.out.println("Element from peekFirst() = " + list.peekFirst());
        System.out.println("Element from peek() = " + list.peekLast());
    }

    public static void printItinerary(LinkedList<String> list) {

        System.out.println("John started his carrer at: " + list.getFirst());

        for(int i = 1; i < list.size(); i++) {

            System.out.println("-> From: " + list.get(i - 1) + " to " + list.get(i));
        }

        System.out.println("John finished his carrer at: " + list.getLast());
    }

    public static void printItinerary2(LinkedList<String> list) {

        System.out.println("Trip starts at " + list.getFirst());
        String previousTown = list.getFirst();

        ListIterator<String> iterator = list.listIterator(1);

        while(iterator.hasNext()){
            var town = iterator.next();
            System.out.println("---> From: " + previousTown + " to " + town);
            previousTown = town;
        }
        System.out.println("Trip ends at " + list.getLast());
    }

}
