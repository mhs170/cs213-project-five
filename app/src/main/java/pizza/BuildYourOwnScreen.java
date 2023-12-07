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
import android.widget.TextView;
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

    private ArrayList<Topping> availableToppings = new ArrayList<>(Arrays.asList(toppings));
    private ArrayList<Topping> selectedToppings = new ArrayList<>();

    private ArrayAdapter<Topping> availableToppingsAdapter;
    private ArrayAdapter<Topping> selectedToppingsAdapter;

    private Spinner sizeSpinner;
    private Spinner sauceSpinner;
    private CheckBox extraSauceCheckBox;
    private CheckBox extraCheeseCheckBox;
    private Button addToOrderButton;
    private TextView priceBox;

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own_screen);

        sizeSpinner = findViewById(R.id.size_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
        sizeSpinner.setOnItemSelectedListener(this);

        sauceSpinner = findViewById(R.id.sauce_spinner);
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

        extraSauceCheckBox = findViewById(R.id.extraSauceCheckBox);
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

        extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
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

        ListView availableToppingsListView = findViewById(R.id.availableToppingList);
        availableToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, availableToppings);
        availableToppingsListView.setAdapter(availableToppingsAdapter);

        availableToppingsListView.setOnItemClickListener(this);

        ListView selectedToppingsListView = findViewById(R.id.selectedToppingList);
        selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedToppings);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);
        selectedToppingsListView.setOnItemClickListener(this);

        addToOrderButton = findViewById(R.id.addToOrderButton);
        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedToppings.size() < 3){
                    new AlertDialog.Builder(BuildYourOwnScreen.this)
                            .setTitle("Not enough toppings")
                            .setMessage("You need at least 3 toppings.")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
                else if (selectedToppings.size() > 7){
                    new AlertDialog.Builder(BuildYourOwnScreen.this)
                            .setTitle("Too many toppings")
                            .setMessage("You can have at most 7 toppings.")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
                else{
                    addPizzaToCurrentOrder(getPizza());
                    new AlertDialog.Builder(BuildYourOwnScreen.this)
                            .setTitle("Successfully added")
                            .setMessage("Successfully added pizza to current order.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
            }
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
        Topping selectedTopping;
        List<Topping> sourceList;
        List<Topping> targetList;
        ArrayAdapter<Topping> sourceAdapter;
        ArrayAdapter<Topping> targetAdapter;
        String action;
        String message;

        if (adapterView.getId() == R.id.availableToppingList) {
            selectedTopping = availableToppings.get(i);
            sourceList = availableToppings;
            targetList = selectedToppings;
            sourceAdapter = availableToppingsAdapter;
            targetAdapter = selectedToppingsAdapter;
            action = "add";
        } else if (adapterView.getId() == R.id.selectedToppingList) {
            selectedTopping = selectedToppings.get(i);
            sourceList = selectedToppings;
            targetList = availableToppings;
            sourceAdapter = selectedToppingsAdapter;
            targetAdapter = availableToppingsAdapter;
            action = "remove";
        } else {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwnScreen.this);
        builder.setTitle("Topping");

        if (action.equals("add")) {
            message = "Do you want to add " + selectedTopping + "?";
        }
        else{
            message = "Do you want to remove " + selectedTopping + "?";
        }
        builder.setMessage(message);

        builder.setPositiveButton(action.equals("add") ? "Add" : "Remove", (dialog, which) -> {
            sourceList.remove(i);
            targetList.add(selectedTopping);
            sourceAdapter.notifyDataSetChanged();
            targetAdapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }

    private void addPizzaToCurrentOrder(Pizza pizza) {
        Singleton.getInstance().currentOrder.addToOrder(pizza);
    }

    public void updatePrice() {
        double price = getPizza().price();
        priceBox.setText(String.format("%.2f", price));
    }


    public Pizza getPizza() {
        Pizza pizza = PizzaMaker.createPizza("BuildYourOwn");
        Sauce selectedSauce = Sauce.valueOf(sauceSpinner.getSelectedItem().toString().toUpperCase());
        Size selectedSize = Size.valueOf(sizeSpinner.getSelectedItem().toString().toUpperCase());
        pizza.setSize(selectedSize);
        pizza.setSauce(selectedSauce);
        pizza.setToppings(selectedToppings);
        pizza.setExtraCheese(extraCheeseCheckBox.isSelected());
        pizza.setExtraSauce(extraSauceCheckBox.isSelected());
        return pizza;
    }
}