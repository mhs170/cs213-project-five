package pizza;

/**
 * Topping enum type for 13 different types of toppings
 * @author Mohammed Salama, Dakshal Panicker
 */
public enum Topping {
    //sausage, pepperoni, green pepper, onion, mushroom, ham, black olive, beef,  shrimp, squid, crab meats
    SAUSAGE ("Sausage"),
    PEPPERONI("Pepperoni"),
    GREEN_PEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    HAM("Ham"),
    BLACK_OLIVE("Black Olive"),
    BEEF("Beef"),
    SHRIMP("Shrimp"),
    SQUID("Squid"),
    CRAB_MEATS("Crab Meats"),
    //2 extra toppings to reach 13 possible toppings for the build your own pizza
    CHICKEN("Chicken"),
    PINEAPPLE("Pineapple");

    private final String topping;

    /**
     * Constructor method to initialize topping object
     * @param topping
     */
    Topping(String topping){
        this.topping = topping;
    }

    /**
     * Getter method to return the topping
     * @return topping
     */
    public String getTopping(){
        return topping;
    }

    /**
     * To string method
     * @return topping as a string
     */
    @Override
    public String toString() {
        return topping;
    }
}
