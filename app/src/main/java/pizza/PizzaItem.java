package pizza;

import android.annotation.SuppressLint;
import android.text.TextUtils;

/**
 * This class defines the data structure of an item to be displayed in the RecyclerView
 * @author Lily Chang
 */
public class PizzaItem {
	private String itemName;
	private int image;
	private String description;

	private Pizza pizza;
	/**
	 * Parameterized constructor.
	 * @param pizza
	 */
	@SuppressLint("DefaultLocale")
	public PizzaItem(Pizza pizza) {
		this.pizza = pizza;
		this.itemName = pizza.getClass().getSimpleName();
		this.image = PizzaImages.getImage(this.itemName);
		this.description = String.format("%s ($%.2f) %s \n[Toppings]:\n%s",
				pizza.size,
				pizza.price(),
				pizza.extraCheese ?
						pizza.extraSauce ? "\nExtra Cheese, Extra Sauce" : "\nExtra Cheese" :
						pizza.extraSauce ? "\nExtra Sauce" : "",
				TextUtils.join("\n", pizza.toppings));
	}

	/**
	 * Getter method that returns the item name of an item.
	 * @return the item name of an item.
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Getter method that returns the image of an item.
	 * @return the image of an item.
	 */
	public int getImage() {
		return image;
	}

	/**
	 * Getter method that returns the unit price.
	 * @return the unit price of the item.
	 */
	public String getDescription() {
		return description;
	}

	public Pizza getPizza() { return pizza; }
}
