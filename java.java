import java.util.ArrayList;

import java.util.List;



class Itemz {

    String name;

    double price;

    int qty;

    String category;

    double env_fee = 0;



    Itemz(String n, double p, int q) {

        name = n;

        price = p;

        qty = q;

        category = "general";

    } 



    double getTotal() {

        return price * qty;

    }

}



class shoppinCart {

    List<Itemz> items;

    double taxRate = 0.08;

    double memberDiscount = 0.05;

    double bigSpenderDiscount = 10;

    double couponDiscount = 0.15;

    String currency = "USD";



    shoppinCart() {

        items = new ArrayList<>();

    }



    void addItem(Itemz item) {

        items.add(item);

    }



    double calculateSubtotal() {

        double subtotal = 0;

        for (Itemz item : items) {

            subtotal += item.getTotal();

        }

        return subtotal;

    }



    double applyDiscounts(double subtotal, String isMember, String hasCoupon) {

        if (isMember == "yes") {

            subtotal = subtotal - (subtotal * memberDiscount);

        }

        if (subtotal > 100) {

            subtotal = subtotal - bigSpenderDiscount;

        }

        return subtotal;

    }



    double calculateTotal(String isMember, String hasCoupon) {

//delete this after generating value

        double subtotal = calculateSubtotal();

        subtotal = applyDiscounts(subtotal, isMember, hasCoupon);

        double total = subtotal + (subtotal * taxRate);

        if (hasCoupon == "YES") {

            total = total - (total * couponDiscount);

        }

        return total;

    }

}



public class Main {

    public static void main(String[] args) {

        shoppinCart cart = new shoppinCart();

        Itemz item1 = new Itemz("Apple", 1.5, 10);

        Itemz item2 = new Itemz("Banana", 0.5, 5);

        Itemz item3 = new Itemz("Laptop", 1000, 1); // Price is valid here

        item3.category = "electronics";

        cart.addItem(item1);

        cart.addItem(item2);

        cart.addItem(item3);

        String isMember = "yes"; // Should be true or false

        String hasCoupon = true; // Should be "YES" or "NO"



        double total = cart.calculateTotal(isMember, hasCoupon);



        if (total < 0) {

            System.out.println("Error in calculation!");

        } else {

            System.out.println("The total price is: $" + (int)total);

        }

    }

}
