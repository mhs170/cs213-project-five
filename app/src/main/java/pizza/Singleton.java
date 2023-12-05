package pizza;

public final class Singleton {
    private static Singleton global_data = null;

    public StoreOrders storeOrders;
    public Order currentOrder;

    private Singleton() {
        storeOrders = new StoreOrders();
        currentOrder = new Order();
    }

    public static synchronized Singleton getInstance() {
        if(global_data == null) {
            global_data = new Singleton();
        }
        return global_data;
    }
}
