interface ShoppingPaymentMethod {
    boolean processPayment(double amount);
}

class ShoppingCreditCard implements ShoppingPaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Payment initiated via Credit Card.");
        return true;
    }
}

class ShoppingPayPal implements ShoppingPaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Payment initiated via PayPal.");
        return false;
    }
}

class ShoppingBankTransfer implements ShoppingPaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Payment initiated via Bank Transfer.");
        return true;
    }
}

class ShoppingProduct {
    private String name;
    private double price;

    public ShoppingProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class ShoppingCustomer {
    private String name;

    public ShoppingCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class ShoppingOrder {
    private ShoppingCustomer customer;
    private double total;
    private boolean paid;

    public ShoppingOrder(ShoppingCustomer customer) {
        this.customer = customer;
        System.out.println("Order created for " + customer.getName() + ".");
    }

    public void addProduct(ShoppingProduct product, int quantity) {
        if (quantity > 0)
            total += product.getPrice() * quantity;
    }

    public void pay(ShoppingPaymentMethod method) {
        if (total == 0) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        if (method.processPayment(total)) {
            paid = true;
            System.out.println("Payment successful.");
        } else {
            System.out.println("Payment failed.");
        }

        System.out.println("Order status: " +
                (paid ? "Paid" : "Pending"));
    }
}

public class ShoppingPaymentSystem {
}