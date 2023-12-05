package pizza;

import com.example.cs213_project_five.R;

public class PizzaImages {
    public static int getImage(String pizzaType) {
        switch (pizzaType) {
            case "Deluxe":
                return R.drawable.deluxe;
            case "Supreme":
                return R.drawable.supreme;
            case "Meatzza":
                return R.drawable.meatzza;
            case "Pepperoni":
                return R.drawable.pepperoni;
            case "Seafood":
                return R.drawable.seafood;
            case "BuildYourOwn":
                return R.drawable.deluxe;
            default:
                throw new IllegalStateException("Unexpected pizza type: '" + pizzaType + "'");
        }
    }
}
