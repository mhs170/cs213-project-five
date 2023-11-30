package pizza;

/**
 * Size enum class for 3 types of sizes
 * @author Mohammed Salama, Dakshal Panicker
 */
public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");
    private final String size;

    /**
     * Constructor method to initialize size object
     * @param size
     */
    Size(String size){
        this.size = size;
    }

    /**
     * Getter method to return size of pizza
     * @return pizza size
     */
    public String getSize(){
        return size;
    }

    /**
     * To string method
     * @return size as string
     */
    @Override
    public String toString() {
        return size;
    }
}
