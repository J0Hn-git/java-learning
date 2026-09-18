package DAY_2;

import java.util.ArrayList;

record OrderItem(int qty, ProductForSale product) {

}

public class Challenge1 {

    private static ArrayList<ProductForSale> storeProducts = new ArrayList<>();
    public static void main(String[] args) {
        
        storeProducts.add(new ArtObject("Oil Painting", 1350, "Impressive work by ABF painted in 2010"));
        storeProducts.add(new ArtObject("Sculpture", 2000, "Bronze work by JKF, produced in 1950"));

        storeProducts.add(new Furniture("desk", 500, "Mahagony Desk"));
        storeProducts.add(new Furniture("Lamp", 200, "Tiffany Lamp with Hummingbirds"));

        listProducts();

        System.out.println("\n Order 1");
        var order1 = new ArrayList<OrderItem>();

        addItemToOrder(order1, 1, 2);
        addItemToOrder(order1, 0, 1);
        printOrder(order1);

        System.out.println("\n Order 2");
        var order2 = new ArrayList<OrderItem>();

        addItemToOrder(order2, 3, 5);
        printOrder(order1);
    }


    public static void listProducts() {

        for(var item : storeProducts) {
            System.out.println("-".repeat(30));
            item.showDetails();
        }
    }

    public static void addItemToOrder(ArrayList<OrderItem> order, int orderIndex, int qty) {

        order.add(new OrderItem(qty, storeProducts.get(orderIndex)));
    }

    public static void printOrder(ArrayList<OrderItem> order) {

        double salesTotal = 0;
        for(var item : order) {

            item.product().printPricedItem(item.qty());
            salesTotal += item.product().getSalesPrice(item.qty());
        }
        System.out.printf("Sales Total = $%6.2f %n", salesTotal);
    }
}

abstract class ProductForSale {

    protected String type;
    protected double price;
    protected String description;

    public ProductForSale(String type, double price, String description) {

        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getSalesPrice(int qty) {
        return price * qty;
    }

    public void printPricedItem(int qty) {

        System.out.printf("%2d qty at $%8.2f each, %-15s %-35s %n", qty, price, type, description);
    }

    public abstract void showDetails();
}

class ArtObject extends ProductForSale {

    public ArtObject(String type, double price, String description) {

        super(type, price, description);
    }

    public void showDetails() {

        System.out.println("This " + type + " is a beautiful reproduction");
        System.out.printf("The price of this piece is $%6.2f %n", price);
        System.out.println(description);
    }
}
class Furniture extends ProductForSale {

    public Furniture(String type, double price, String description) {
        super(type, price, description);
    }

    @Override 
    public void showDetails() {

        System.out.println("This " + type + " was manufactured in Texas");
        System.out.printf("The price of the piece is $%6.2f %n", price);
        System.out.println(description);
    }
}
