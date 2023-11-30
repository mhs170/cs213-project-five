package pizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Seafood pizza type
 * @author Mohammed Salama, Dakshal Panicker
 */
public class Seafood extends Pizza {
    private static final Sauce SAUCE = Sauce.ALFREDO;
    private static final double SMALL_PRICE = 17.99;

    /**
     * Constructor to create new seafood pizza object
     * @param size pizza size
     * @param sauce pizza sauce
     * @param extraSauce if there's extra sauce
     * @param extraCheese if there's extra cheese
     * @param price final price
     */
    public Seafood(Size size, Sauce sauce,
                   boolean extraSauce, boolean extraCheese, double price) {
        super(new ArrayList<>(
                Arrays.asList(
                        Topping.SHRIMP,
                        Topping.SQUID,
                        Topping.CRAB_MEATS
                )
        ), size, sauce, extraSauce, extraCheese, price);
    }

    /**
     * method to calculate final price
     * @return final price
     */
    @Override
    public double price() {
        double price = SMALL_PRICE;
        if (size.equals(Size.MEDIUM)){
            price += EXTRA_FOR_MEDIUM;
        }
        if (size.equals(Size.LARGE)){
            price += EXTRA_FOR_LARGE;
        }
        if (extraCheese){
            price += 1;
        }
        if (extraSauce){
            price += 1;
        }
        return price;
    }
}
