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
        switch (pizzaType) {
            case "Deluxe":
                pizza = new Deluxe(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Supreme":
                pizza = new Supreme(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Meatzza":
                pizza = new Meatzza(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Pepperoni":
                pizza = new Pepperoni(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Seafood":
                pizza = new Seafood(Size.SMALL, Sauce.ALFREDO,
                        false, false, 0);
                break;
            case "Breakfast":
                pizza = new Breakfast(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Hawaiian":
                pizza = new Hawaiian(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Philly":
                pizza = new Philly(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Veggie":
                pizza = new Veggie(Size.SMALL, Sauce.TOMATO,
                        false, false, 0);
                break;
            case "Alfredo":
                pizza = new Alfredo(Size.SMALL, Sauce.ALFREDO,
                        false, false, 0);
                break;
            case "BuildYourOwn":
                pizza = new BuildYourOwn(new ArrayList<Topping>(), Size.SMALL, Sauce.TOMATO
                        , false, false, 0);
                break;
            default:
                throw new IllegalStateException("Unexpected pizza type: '" + pizzaType + "'");
        }
        return pizza;
    }
}