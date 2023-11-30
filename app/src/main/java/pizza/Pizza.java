package pizza;

import java.util.ArrayList;

/** Abstract pizza class inherited by all of the pizza types
 * @author Mohammed Salama, Dakshal Panicker
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings; //Topping is a enum class

    protected Size size; //Size is a enum class
    protected Sauce sauce; //Sauce is a enum class
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price(); //polymorphism
    protected static final double EXTRA_FOR_MEDIUM = 2.00;
    protected static final double EXTRA_FOR_LARGE = 4.00;

    /**
     * Constructor method to create new deluxe pizza object
     * @param toppings initial toppings for the pizza (only called by subclasses)
     * @param size pizza size
     * @param sauce pizza sauce
     * @param extraSauce if there's extra sauce
     * @param extraCheese if there's extra cheese
     * @param price final price
     */
    public Pizza(ArrayList<Topping> toppings, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese, double price) {
        this.toppings = toppings;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        this.price();
    }

    /**
     * Set toppings of the pizza
     * @param toppings toppings list
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Set extra cheese boolean
     * @param extraCheese true if extra cheese
     */
    public void setExtraCheese(boolean extraCheese){
        this.extraCheese = extraCheese;
    }

    /**
     * Set extra sauce boolean
     * @param extraSauce true if extra sauce
     */
    public void setExtraSauce(boolean extraSauce){
        this.extraSauce = extraSauce;
    }

    /**
     * Set size
     * @param size the Size
     */
    public void setSize(Size size){
        this.size = size;
    }

    /**
     * Set sauce
     * @param sauce the Sauce
     */
    public void setSauce(Sauce sauce){
        this.sauce = sauce;
    }

    /**
     * To string method for pizza
     * @return pizza in a string format
     */
    @Override
    public String toString() {
        return  String.format("[%s] [%s] %s %s %s %.2f",
                    this.getClass().getSimpleName(),
                    size,
                    toppings,
                    extraCheese ? "Extra Cheese, " : "",
                    extraSauce ? "Extra Sauce" : "",
                    price()
                );
    }
}