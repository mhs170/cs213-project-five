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
        Pizza pizza;
        if (pizzaType.equals("Deluxe")){
            pizza = new Deluxe(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
        }
        if (pizzaType.equals("Supreme")){
            pizza = new Supreme(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
        }
        if (pizzaType.equals("Meatzza")){
            pizza = new Meatzza(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
        }
        if (pizzaType.equals("Pepperoni")){
            pizza = new Pepperoni(Size.SMALL, Sauce.TOMATO,
                    false, false, 0);
        }
        if (pizzaType.equals("Seafood")){
            pizza = new Seafood(Size.SMALL, Sauce.ALFREDO,
                    false, false, 0);
        }
        if (pizzaType.equals("BuildYourOwn")){
            pizza = new BuildYourOwn(new ArrayList<Topping>(), Size.SMALL, Sauce.TOMATO
                    , false, false, 0);
        }
        else{
            throw new IllegalStateException("Unexpected value: " + pizzaType);
        }
        return pizza;
    }
}