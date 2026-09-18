package DAY_2;
import java.util.ArrayList;
import java.util.Scanner;

record CartItem(int qty, Product product) {}

public class Challenge2 {

    private static ArrayList<Product> inventory = new ArrayList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        inventory.add(new Electronics("Laptop", 899.99, "16GB RAM, 512GB SSD, 15-inch display"));
        inventory.add(new Electronics("Smartphone", 699.99, "128GB storage, OLED display, 5G"));
        inventory.add(new Electronics("Headphone", 149.99, "Wireless noise-cancelling headphones"));
        inventory.add(new Electronics("Smartwatch", 249.99, "Fitness tracking GPS, heart-rate monitor"));

        inventory.add(new Clothing("T-shirt", 24.99, "Cotton, regular fit"));
        inventory.add(new Clothing("Jeans", 59.99, "Baggy denim jeans"));
        inventory.add(new Clothing("Jacket", 119.99, "Waterproof winter jacket"));

        // Printing the inventory items to customers.
        System.out.println("Welcome to ABC ONLINE SHOPPING....");
        System.out.println("-".repeat(30));
        printInventory();
        System.out.println();

        ArrayList<CartItem> cart = new ArrayList<>();

        while (true) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Add Product");
            System.out.println("2. Show Cart");
            System.out.println("3. Get Bill");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice) {

                case 1: System.out.println("Enter Product #: ");
                int productChoice = sc.nextInt() - 1;

                if(productChoice < 0 || productChoice >= inventory.size()){
                    System.out.println("Invalid product number");
                    break;
                }
                Product product = inventory.get(productChoice);
                System.out.println("Enter the quantity: ");
                int qty = sc.nextInt();

                if(qty <= 0){
                    System.out.println("Quantity must be greater than 0");
                    break;
                }
                cart.add(new CartItem(qty, product));
                System.out.println("Product added to cart.");
                break;

                case 2: showCart(cart);
                break;

                case 3: showCart(cart);
                if(!cart.isEmpty()) {
                    System.out.println("\nDo you want to generate the bill? (Y/N): ");
                    String answer = sc.next();

                    if(answer.equalsIgnoreCase("Y")) {
                        generateBill(cart);
                        System.out.println("Thanks for shopping");

                        sc.close();
                        return;
                    }      
                }
                break;

                case 4: System.out.println("Thanks for shopping");
                sc.close();
                return ;

                default: System.out.println("Invalid choice!");
            }
    
        }

    }
    public static void printInventory(){

        for(int i = 0; i < inventory.size(); i++) {
            System.out.println("-".repeat(30));
            System.out.println("Product #" + (i + 1));
            inventory.get(i).showDetails();
        }
    }

    public static void showCart(ArrayList<CartItem> cart) {

        System.out.println("\n========== YOUR CART ==========");
        if(cart.isEmpty()) {
            System.out.println("your cart is empty");
            return;
        }

        double total = 0;
        for(var item : cart) {
            Product product = item.product();
            int qty = item.qty();

            double itemTotal = product.getTotalPrice(qty);
            System.out.printf("%d x %-15s $%6.2f%n", qty, product.name, itemTotal);

            total += itemTotal;
        }
        System.out.println("-------------------------------");
        System.out.printf("Cart Total: $%6.2f%n", total);
    }
    public static void generateBill(ArrayList<CartItem> cart) {

        System.out.println("\n");
        System.out.println("========================================");
        System.out.println("              ABC SHOP");
        System.out.println("             FINAL BILL");
        System.out.println("========================================");

        double grandTotal = 0;

        for(var item : cart) {

            Product product = item.product();
            int qty = item.qty();

            double itemTotal = product.getTotalPrice(qty);
            System.out.printf("%-15s x %-3d $%6.2f%n", product.name, qty, itemTotal);
            grandTotal += itemTotal;
        }
        System.out.println("----------------------------------------");

        System.out.printf(
                "TOTAL:                         $%.2f%n",
                grandTotal
        );

        System.out.println("========================================");

    }
}

abstract class Product {

    protected String name;
    protected double price;
    protected String description;

    public Product(String name, double price, String description){

        this.name = name;
        this.price = price;
        this.description = description;
    }

    public double getTotalPrice(int qty) {
        return price * qty;
    }

    public void printItem(int qty) {
        System.out.printf("%d x $%6.2f %n | %-15s | %-35s%n", qty, price, name, description);
    }

    public abstract void showDetails();
}

class Electronics extends Product {

    public Electronics(String name, double price, String description) {
        super(name, price, description);
    }

    public void showDetails() {

        System.out.println("Electronics: " + name);
        System.out.printf("Price: $%6.2f%n", price);
        System.out.println(description);
    }
}

class Clothing extends Product {

    public Clothing(String name, double price, String description) {
        super(name, price, description);
    }

    @Override 
    public void showDetails() {

        System.out.println("Clothing: " + name);
        System.out.printf("Price: $%6.2f%n", price);
        System.out.println(description);
    }
}
