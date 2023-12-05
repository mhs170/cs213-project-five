package pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaItemsMaker {
    public static ArrayList<PizzaItem> getPizzaItems(List<Pizza> pizzas) {
        ArrayList<PizzaItem> items = new ArrayList<>();
        for (Pizza pizza : pizzas) {
            items.add(new PizzaItem(pizza));
        }
        return items;
    }
}
