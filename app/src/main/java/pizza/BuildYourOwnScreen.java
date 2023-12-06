package pizza;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs213_project_five.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildYourOwnScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private ArrayList<SpecialityItem> items = new ArrayList<>();

    private int [] itemImages = {
            R.drawable.deluxe, R.drawable.meatzza,
            R.drawable.pepperoni, R.drawable.seafood, R.drawable.supreme
    };

    private List<String> availableToppings;
    private List<String> selectedToppings;

    private ArrayAdapter<String> availableToppingsAdapter;
    private ArrayAdapter<String> selectedToppingsAdapter;
    private Topping[] toppings = {Topping.BLACK_OLIVE,
            Topping.GREEN_PEPPER,
            Topping.MUSHROOM,
            Topping.ONION,
            Topping.PINEAPPLE,
            Topping.PEPPERONI,
            Topping.CHICKEN,
            Topping.HAM,
            Topping.BEEF,
            Topping.SAUSAGE,
            Topping.SQUID,
            Topping.CRAB_MEATS,
            Topping.SHRIMP};


    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own_screen);

        Spinner sizeSpinner = findViewById(R.id.size_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
        sizeSpinner.setOnItemSelectedListener(this);

        Spinner sauceSpinner = findViewById(R.id.sauce_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.sauces, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sauceSpinner.setAdapter(adapter1);
        sauceSpinner.setOnItemSelectedListener(this);

        Button home = findViewById(R.id.btn_home);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        });

        CheckBox extraSauceCheckBox = findViewById(R.id.extraSauceCheckBox);
        extraSauceCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Extra Sauce Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Extra Sauce Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CheckBox extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
        extraCheeseCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(), "Extra Cheese Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Extra Cheese Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        availableToppings = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.available_toppings)));
        selectedToppings = new ArrayList<>();

        availableToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, availableToppings);
        selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, selectedToppings);

        ListView availableToppingsListView = findViewById(R.id.availableToppingList);
        availableToppingsListView.setAdapter(availableToppingsAdapter);
        availableToppingsListView.setOnItemClickListener(this);

        ListView selectedToppingsListView = findViewById(R.id.selectedToppingList);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);
        selectedToppingsListView.setOnItemClickListener(this);

        availableToppingsListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTopping = availableToppings.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwnScreen.this);
            builder.setTitle("Add Topping");
            builder.setMessage("Do you want to add " + selectedTopping + "?");

            builder.setPositiveButton("Add", (dialog, which) -> {
                // User clicked Add button
                availableToppings.remove(position);
                selectedToppings.add(selectedTopping);
                refreshAdapters();
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> {
                // User clicked Cancel button
                dialog.dismiss(); // Dismiss the dialog
            });
            builder.show();
        });

        selectedToppingsListView.setOnItemClickListener((parent, view, position, id) -> {
            String removedTopping = selectedToppings.get(position);

            // Build an AlertDialog to confirm the removal of the topping
            AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwnScreen.this);
            builder.setTitle("Remove Topping");
            builder.setMessage("Do you want to remove " + removedTopping + "?");

            // Add positive button to confirm removal
            builder.setPositiveButton("Remove", (dialog, which) -> {
                // User clicked Remove button
                selectedToppings.remove(position);
                availableToppings.add(removedTopping);
                refreshAdapters();
            });

            // Add negative button to cancel removal
            builder.setNegativeButton("Cancel", (dialog, which) -> {
                // User clicked Cancel button
                dialog.dismiss(); // Dismiss the dialog
            });

            // Show the AlertDialog
            builder.show();
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
            items.add(new SpecialityItem(itemNames[i], itemImages[i], "$1.39"));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private void refreshAdapters() {
        availableToppingsAdapter.notifyDataSetChanged();
        selectedToppingsAdapter.notifyDataSetChanged();
    }
}