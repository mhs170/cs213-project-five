package pizza;

/**
 * Sauce enum class for two sauce types
 * @author Mohammed Salama, Dakshal Panicker
 */
public enum Sauce {
    TOMATO ("Tomato"),
    ALFREDO ("Alfredo");
    private final String sauce;

    /**
     * Constructor method that initializes the Sauce object
     * @param sauce type of sauce
     */
    Sauce(String sauce){
        this.sauce = sauce;
    }

    /**
     * Getter method to return the type of sauce
     * @return sauce
     */
    public String getSauce(){
        return sauce;
    }

    /**
     * To string method
     * @return sauce as string
     */
    @Override
    public String toString() {
        return sauce;
    }
}
