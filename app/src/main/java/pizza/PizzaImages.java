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
            case "Alfredo":
                return R.drawable.alfredo;
            case "Breakfast":
                return R.drawable.breakfast;
            case "Hawaiian":
                return R.drawable.hawaiian;
            case "Philly":
                return R.drawable.philly;
            case "Veggie":
                return R.drawable.veggie;
            default:
                throw new IllegalStateException("Unexpected pizza type: '" + pizzaType + "'");
        }
    }
}
