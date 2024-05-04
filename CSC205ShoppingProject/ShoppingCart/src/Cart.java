import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Cart {
    public List<Products> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(Products product) {
        items.add(product);
    }

    public void removeItem(Products product) {
        items.remove(product);
    }
    // Update quantity of item in the cart
    public void updateQuantity(Products product, int newQuantity) {
        if (items.contains(product)) {
            // Update the quantity of the specified product
            product.setQuantity(newQuantity);
        }
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Products product : items) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public List<Products> getItems() {
        return items;
    }
    public void displayCart() {
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Your cart is empty.", "Cart", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder cartContent = new StringBuilder();
            cartContent.append("Items in Your Cart:\n\n");
            for (Products product : items) {
                cartContent.append(product.getItemName()).append(" - $").append(String.format("%.2f", product.getPrice())).append("\n");
            }
            cartContent.append("\nTotal Price: $").append(String.format("%.2f", getTotalPrice()));
            JOptionPane.showMessageDialog(null, cartContent.toString(), "Cart", JOptionPane.INFORMATION_MESSAGE);
        }
    
    }
    }
