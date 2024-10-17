package app;

import java.util.ArrayList;
import java.util.List;



class Itemz {
    String name;
    double price;
    int qty;
    String category;
    double envFee = 0;

    Itemz(String n, double p, int q) {
        name = n;
        price = p;
        qty = q;
        category = "general";
    } 

    boolean checkItem(){
        if (price < 0 || qty < 0){
            System.err.println("Invalid input in the item "+ name +": Price and quantity must be non-negative.");
            return false;
        }
        return true;
    }

    double getTotal() {
        return price * qty;
    }

    String getCategory(){
        return category;
    }
}

class ShoppingCart {
    List<Itemz> items;
    double taxRate = 0.08;
    double memberDiscount = 0.05;
    double bigSpenderDiscount = 10;
    double couponDiscount = 0.15;
    String currency = "USD";

    ShoppingCart() {
        items = new ArrayList<>();
    }

    void addItem(Itemz item) {
        items.add(item);
    }

    int calculateElectronicItemsCount(){
        int count = 0;
        for (Itemz item: items){
            if (item.getCategory().equals("electronics"))
                count++;
        }
        return count;
    }

    double calculateSubtotal() {
        double subtotal = 0;
        for (Itemz item : items) {
            subtotal += item.getTotal();
        }
        return subtotal;
    }

    double applyDiscounts(double subtotal, boolean isMember) {
        if (isMember) {
            subtotal = subtotal - (subtotal * memberDiscount);
        }

        if (subtotal > 100) {
            subtotal = subtotal - bigSpenderDiscount;
        }

        return subtotal;
    }

    boolean validateItems(){
        for (Itemz item: items){
            if (item.checkItem())
                return false;
        }
        return true;
    }

    double calculateTotal(boolean isMember, boolean hasCoupon, int electronicsItemsCount) {
        if (!validateItems())
            return -1;

        double subtotal = calculateSubtotal();
        subtotal = applyDiscounts(subtotal, isMember);
        double total = subtotal + (subtotal * taxRate);

        if (hasCoupon) {
            total = total - (total * couponDiscount);
        }
        return total + 5 * electronicsItemsCount;
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Itemz item1 = new Itemz("Apple", 1.5, 10);
        Itemz item2 = new Itemz("Banana", 0.5, 5);
        Itemz item3 = new Itemz("Laptop", 1000, 1);

        item3.category = "electronics";
        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);

        boolean isMember = true;
        boolean hasCoupon = true;
        int electronicsItemsCount = cart.calculateElectronicItemsCount();

        double total = cart.calculateTotal(isMember, hasCoupon, electronicsItemsCount);

        if (total < 0) {
            System.out.println("Error in calculation!");
        } else {
            System.out.println("The total price is: $" + (int)total);
        }
    }
}
