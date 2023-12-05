package pizza;

import android.content.Intent;
import android.os.Bundle;

import com.example.cs213_project_five.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.widget.Button;

import java.util.ArrayList;

public class SpecialtyPizzaScreen extends AppCompatActivity {
    private ArrayList<SpecialityItem> items = new ArrayList<>();

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_pizza_screen);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        SpecialtyItemsAdapter adapter = new SpecialtyItemsAdapter(this, items); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));

        //home button
        Button home = findViewById(R.id.btn_home);
        home.setOnClickListener(view -> {
                Intent intent = new Intent(this, MainActivity.class);
                finish();
                startActivity(intent);
        });
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < itemNames.length; i++) {
            Pizza pizza = PizzaMaker.createPizza(itemNames[i]);
            items.add(new SpecialityItem(
                    itemNames[i],
                    PizzaImages.getImage(itemNames[i]),
                    "$" + pizza.price() + "\n" + TextUtils.join("\n", pizza.toppings)
            ));
        }
    }
}