package pizza;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cs213_project_five.R;
import com.example.cs213_project_five.databinding.ActivityStoreOrderScreenBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StoreOrderScreen extends AppCompatActivity {

    Spinner orderSpinner;
    TextView tvOrderTotal;

    Button cancelOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_screen);

        orderSpinner = findViewById(R.id.spinner_order);
        tvOrderTotal = findViewById(R.id.tv_current_order_total);
        cancelOrderButton = findViewById(R.id.btn_cancel_order);


        //setup spinner
        List<Integer> orderNumbers = Singleton.getInstance().storeOrders.getOrderNumbers();
        if(orderNumbers.isEmpty()) {
            tvOrderTotal.setText(R.string.no_order);
            cancelOrderButton.setEnabled(false);



        } else {

            List<String> orderStrings = orderNumbers.stream()
                    .map(orderNumber ->  "Order " + " " + orderNumber)
                    .collect(Collectors.toList());

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    orderStrings
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            orderSpinner.setAdapter(adapter);

            //set selection to last added order
            orderSpinner.setSelection(orderStrings.size() - 1);

            showOrder();

            //on change
            orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    showOrder();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //do nothing
                }
            });

            cancelOrderButton.setEnabled(true);
            cancelOrderButton.setOnClickListener(view -> {
                Singleton.getInstance().storeOrders.removeOrder(getCurrentOrder());
                Intent intent = new Intent(this, StoreOrderScreen.class);
                finish();
                startActivity(intent);
            });
        }

        //home button
        Button home = findViewById(R.id.btn_home3);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }



    protected Order getCurrentOrder() {
        int selectedOrderNumber = Integer.parseInt(
                orderSpinner.getSelectedItem().toString().replaceAll("\\D", "")
        );
        return Singleton.getInstance().storeOrders.getOrder(selectedOrderNumber);
    }

    protected void updateOrderPizzaList() {
        RecyclerView rcPizza = findViewById(R.id.rc_current_order);
        List<Pizza> pizzas = getCurrentOrder().getPizzas();
        rcPizza.setAdapter(new PizzaItemAdapter(this, PizzaItemsMaker.getPizzaItems(pizzas), false));
        rcPizza.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("DefaultLocale")
    protected void updateOrderTotal() {
        tvOrderTotal.setText(String.format("Order Total: $%.2f", getCurrentOrder().getTotal()));
    }

    protected void showOrder() {
        updateOrderPizzaList();
        updateOrderTotal();
    }
}