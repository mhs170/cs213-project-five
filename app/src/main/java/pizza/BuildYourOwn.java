package pizza;

import java.util.ArrayList;

/**
 * Customizable pizza class
 * @author Mohammed Salama, Dakshal Panicker
 */
public class BuildYourOwn extends Pizza {
    private static final double SMALL_PRICE = 8.99;
    /**
     * Constructor method to create custom pizza
     * @param toppings list of toppings
     * @param size pizza size
     * @param sauce pizza sauce
     * @param extraSauce if there's extra sauce
     * @param extraCheese if there's extra cheese
     * @param price final price
     */
    public BuildYourOwn(ArrayList<Topping> toppings, Size size, Sauce sauce
            , boolean extraSauce, boolean extraCheese, double price) {
        super(toppings, size, sauce, extraSauce, extraCheese, price);
    }

    /**
     * Method to calculate final price
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
        if(toppings.size() > 3) {
            int additionalToppings = toppings.size() - 3;
            price += (1.49 * additionalToppings);
        }
        return price;
    }
}
