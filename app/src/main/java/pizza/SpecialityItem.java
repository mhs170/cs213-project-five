package pizza;

/**
 * This class defines the data structure of an item to be displayed in the RecyclerView
 * @author Lily Chang
 */
public class SpecialityItem {
	private String itemName;
	private int image;
	private String description;
	/**
	 * Parameterized constructor.
	 * @param itemName
	 * @param image
	 * @param description
	 */
	public SpecialityItem(String itemName, int image, String description) {
		this.itemName = itemName;
		this.image = image;
		this.description = description;
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
}
