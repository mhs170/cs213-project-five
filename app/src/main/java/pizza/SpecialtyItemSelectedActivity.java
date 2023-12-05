package pizza;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cs213_project_five.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * For demonstration purpose, this class is the Activity to be started when an item on the
 * RecyclerView was clicked
 * Get the name of the item from an intent extra. The text of the button is set to the item name.
 * @author Lily Chang
 */
public class SpecialtyItemSelectedActivity extends AppCompatActivity {

    private String pizzaType;
    private TextView title;
    private ListView toppings_list;

    private Button confirmButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality_item_selected);

        Intent intent = getIntent();
        pizzaType = intent.getStringExtra("ITEM");

        title = findViewById(R.id.tv_title);
        title.setText(String.format("Add a %s Pizza to your order?", pizzaType));

        ArrayList<Topping> toppingsList = PizzaMaker.createPizza(pizzaType).toppings;
        List<String> toppingStrings = toppingsList.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        toppings_list = findViewById(R.id.list_toppings);
        ArrayAdapter<String> toppings = new ArrayAdapter<>(
                this,
                R.layout.specialty_item_selected_toppings_item,
                R.id.tv_listview_item,
                toppingStrings
        );
        toppings_list.setAdapter(toppings);

        cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(view -> {
            finish();
        });

        confirmButton = findViewById(R.id.btn_confirm);
        confirmButton.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Add to order");
            alert.setMessage(pizzaType);
            //handle the "YES" click
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(view.getContext(),
                            pizzaType + " added.", Toast.LENGTH_LONG).show();
                    finish();
                }
                //handle the "NO" click
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(view.getContext(),
                            pizzaType + " not added.", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }
}