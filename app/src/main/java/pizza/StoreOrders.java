package pizza;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Store orders class that holds order objects
 * @author Mohammed Salama, Dakshal Panicker
 */
public class StoreOrders {
    private static int nextOrderNumber; //automatically initialized to 0
    private List<Order> orders;

    /**
     * Get next order number based on static counter
     * @return the next order number
     */
    public static int getNextOrderNumber() {
        nextOrderNumber ++;
        return nextOrderNumber;
    }

    /**
     * Constructor for store order, intializing with empty list
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Add an order
     * @param order order to add
     */
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    /**
     * Remove an order
     * @param order order to remove
     */
    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    /**
     * Get numbers of all orders in a list of ints
     * @return int list of order numbers
     */
    public List<Integer> getOrderNumbers() {
        List<Integer> orderNumbers = new ArrayList<>();
        for(Order order : orders) {
            orderNumbers.add(order.getOrderNumber());
        }
        return orderNumbers;
    }

    /**
     * Get order from orders based on order number
     * @param orderNumber order number inputted
     * @return order object outputted
     */
    public Order getOrder(int orderNumber) {
        for(Order order: orders) {
            if(order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    /**
     * Method to save the store orders to an external text file
     */
    public void export() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save store orders");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(new Stage());
        if(file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Order order : orders) {
                    writer.write(order.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
