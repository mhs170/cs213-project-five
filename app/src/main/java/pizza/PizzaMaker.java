package pizza;

import java.util.ArrayList;

/**
 * Factory class for creating pizzas
 * @author Mohammed Salama, Dakshal Panicker
 */
public class PizzaMaker {
    /**
     * Create the appropriate pizza
     * @param pizzaType String pizza type
     * @return the appropriate type of pizza
     */
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType) {
            case "Deluxe" -> new Deluxe(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
            case "Supreme" -> new Supreme(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
            case "Meatzza" -> new Meatzza(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
            case "Pepperoni" -> new Pepperoni(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
            case "Seafood" -> new Seafood(Size.SMALL, Sauce.ALFREDO,
                    false, false, 0);
            case "BuildYourOwn" ->
                    new BuildYourOwn(new ArrayList<Topping>(), Size.SMALL, Sauce.TOMATO
                            , false, false, 0);
            default ->
                    throw new IllegalStateException("Unexpected value: " + pizzaType);
        };
    }
}