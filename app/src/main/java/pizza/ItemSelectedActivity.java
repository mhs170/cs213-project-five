package pizza;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cs213_project_five.R;
/**
 * For demonstration purpose, this class is the Activity to be started when an item on the
 * RecyclerView was clicked
 * Get the name of the item from an intent extra. The text of the button is set to the item name.
 * @author Lily Chang
 */
public class ItemSelectedActivity extends AppCompatActivity {

    private String pizzaType;
    private TextView title;
    private TextView tv_toppings_list;

    private Button confirmButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);

        Intent intent = getIntent();
        pizzaType = intent.getStringExtra("ITEM");

        title = findViewById(R.id.tv_title);
        title.setText(String.format("Add a %s Pizza to your order?", pizzaType));

        tv_toppings_list = findViewById(R.id.tv_toppings_list);
        tv_toppings_list.setText(PizzaMaker.createPizza(pizzaType.trim()).toppings.toString());

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