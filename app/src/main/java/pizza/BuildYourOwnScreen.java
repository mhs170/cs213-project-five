package pizza;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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


    private final Topping[] toppings = {Topping.BLACK_OLIVE,
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

    private final ArrayList<Topping> availableToppings = new ArrayList<>(Arrays.asList(toppings));
    private final ArrayList<Topping> selectedToppings = new ArrayList<>();

    private ArrayAdapter<Topping> availableToppingsAdapter;
    private ArrayAdapter<Topping> selectedToppingsAdapter;

    private Spinner sizeSpinner;
    private Spinner sauceSpinner;
    private CheckBox extraSauceCheckBox;
    private CheckBox extraCheeseCheckBox;
    private TextView priceBox;
    private final int MIN_TOPPINGS = 3;
    private final int MAX_TOPPINGS = 7;

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     *
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
        extraSauceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getApplicationContext(), R.string.extra_sauce_added, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.extra_sauce_removed, Toast.LENGTH_SHORT).show();
            }
            updatePrice();
        });

        extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
        extraCheeseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getApplicationContext(), R.string.extra_cheese_added, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), R.string.extra_cheese_removed, Toast.LENGTH_SHORT).show();
            }
            updatePrice();
        });

        ListView availableToppingsListView = findViewById(R.id.availableToppingList);
        availableToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, availableToppings);
        availableToppingsListView.setAdapter(availableToppingsAdapter);

        availableToppingsListView.setOnItemClickListener(this);

        ListView selectedToppingsListView = findViewById(R.id.selectedToppingList);
        selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedToppings);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);
        selectedToppingsListView.setOnItemClickListener(this);

        priceBox = findViewById(R.id.priceBox);
        updatePrice();


        Button addToOrderButton = findViewById(R.id.addToOrderButton);
        addToOrderButton.setOnClickListener(v -> {
            if (selectedToppings.size() < MIN_TOPPINGS) {
                new AlertDialog.Builder(BuildYourOwnScreen.this)
                        .setTitle(R.string.not_enough_toppings)
                        .setMessage(R.string.at_least_three_toppings)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            } else if (selectedToppings.size() > MAX_TOPPINGS) {
                new AlertDialog.Builder(BuildYourOwnScreen.this)
                        .setTitle(R.string.too_many_toppings)
                        .setMessage(R.string.at_most_seven_toppings)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            } else {
                addPizzaToCurrentOrder(getPizza());
                new AlertDialog.Builder(BuildYourOwnScreen.this)
                        .setTitle(R.string.successfully_added)
                        .setMessage(R.string.successfully_added_to_order)
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            dialog.dismiss();

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        })
                        .show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        updatePrice();
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
            action = getString(R.string.add_action);
        } else if (adapterView.getId() == R.id.selectedToppingList) {
            selectedTopping = selectedToppings.get(i);
            sourceList = selectedToppings;
            targetList = availableToppings;
            sourceAdapter = selectedToppingsAdapter;
            targetAdapter = availableToppingsAdapter;
            action = getString(R.string.remove_action);
        } else {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwnScreen.this);
        builder.setTitle(R.string.topping_text);

        if (action.equals(getString(R.string.add_action))) {
            message = getString(R.string.add_message_prompt) + " " + selectedTopping + getString(R.string.question_mark);
        } else {
            message = getString(R.string.remove_message_prompt) + " " + selectedTopping + getString(R.string.question_mark);
        }
        builder.setMessage(message);

        builder.setPositiveButton(action.equals(getString(R.string.add_action)) ? getString(R.string.add_choice) : getString(R.string.remove_choice), (dialog, which) -> {
            sourceList.remove(i);
            targetList.add(selectedTopping);
            sourceAdapter.notifyDataSetChanged();
            targetAdapter.notifyDataSetChanged();
            updatePrice();
        });

        builder.setNegativeButton(R.string.cancel_choice, (dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }

    private void addPizzaToCurrentOrder(Pizza pizza) {
        Singleton.getInstance().currentOrder.addToOrder(pizza);
    }

    @SuppressLint("DefaultLocale")
    public void updatePrice() {
        priceBox.setText(String.format("%.2f", getPizza().price()));
    }


    public Pizza getPizza() {
        Pizza pizza = PizzaMaker.createPizza(getString(R.string.byo));
        Sauce selectedSauce = Sauce.valueOf(sauceSpinner.getSelectedItem().toString().toUpperCase());
        Size selectedSize = Size.valueOf(sizeSpinner.getSelectedItem().toString().toUpperCase());
        pizza.setSize(selectedSize);
        pizza.setSauce(selectedSauce);
        pizza.setToppings(selectedToppings);
        pizza.setExtraCheese(extraCheeseCheckBox.isChecked());
        pizza.setExtraSauce(extraSauceCheckBox.isChecked());
        return pizza;
    }
}