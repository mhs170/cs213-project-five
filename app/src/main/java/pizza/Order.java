package pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that stores a single order
 *
 * @author Mohammed Salama, Dakshal Panicker
 */
public class Order {
    private int orderNumber;
    private List<Pizza> pizzas;

    private static final double SALES_TAX_PERCENTAGE = 0.06625;

    /**
     * Constructor for order, gets next order number from static storeorders
     * class
     */
    public Order() {
        this.orderNumber = StoreOrders.getNextOrderNumber();
        this.pizzas = new ArrayList<Pizza>();
    }

    /**
     * Get the order number
     *
     * @return order number int
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Return pizzas in order
     *
     * @return pizzas list
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Add a pizza to the order
     *
     * @param pizza Pizza obj
     */
    public void addToOrder(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    /**
     * Remove a pizza from the order
     *
     * @param pizza Pizza obj
     */
    public void removeFromOrder(Pizza pizza) {
        this.pizzas.remove(pizza);
    }

    /**
     * Return subtotal of order
     *
     * @return double subtotal
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    /**
     * Get sales tax
     *
     * @return double sales tax
     */
    public double getSalesTax() {
        return SALES_TAX_PERCENTAGE * getSubtotal();
    }

    /**
     * Return subtotal + sales tax
     *
     * @return double total
     */
    public double getTotal() {
        return getSalesTax() + getSubtotal();
    }

    /**
     * Method to display order
     *
     * @return String with order details
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order Number: ").append(orderNumber).append(
                "\n");

        stringBuilder.append("Pizzas in Order:\n");
        for (Pizza pizza : pizzas) {
            stringBuilder.append("- ").append(pizza).append("\n");
        }

        stringBuilder.append("Subtotal: $").append(String.format("%.2f",
                getSubtotal())).append("\n");
        stringBuilder.append("Sales Tax: $").append(String.format("%.2f",
                getSalesTax())).append("\n");
        stringBuilder.append("Total: $").append(String.format("%.2f",
                getTotal())).append("\n");

        return stringBuilder.toString();
    }
}
