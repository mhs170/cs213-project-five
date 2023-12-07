package pizza;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs213_project_five.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CurrentOrderScreen extends AppCompatActivity {

    private Order currentOrder;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_screen);

        currentOrder = Singleton.getInstance().currentOrder;

        RecyclerView rcPizza = findViewById(R.id.list_current_pizzas);
        List<Pizza> pizzas = currentOrder.getPizzas();
        rcPizza.setAdapter(new PizzaItemAdapter(this, PizzaItemsMaker.getPizzaItems(pizzas), true));
        rcPizza.setLayoutManager(new LinearLayoutManager(this));

        TextView orderInfo = findViewById(R.id.tv_order_info);
        orderInfo.setText(String.format(
                "Order Number: %d \nOrder Subtotal: $%.2f \nSales Tax: $%.2f \nOrder Total: $%.2f",
                currentOrder.getOrderNumber(),
                currentOrder.getSubtotal(),
                currentOrder.getSalesTax(),
                currentOrder.getTotal()
        ));

        Button placeOrder = findViewById(R.id.btn_place_order);
        placeOrder.setOnClickListener(view -> {
            if(currentOrder.getPizzas().isEmpty()) {
                Toast.makeText(view.getContext(), "Order is empty.", Toast.LENGTH_SHORT).show();
            } else {
                Singleton globalData = Singleton.getInstance();
                globalData.storeOrders.addOrder(globalData.currentOrder);
                globalData.currentOrder = new Order();

                Toast.makeText(view.getContext(), "Placed order.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, StoreOrderScreen.class);
                finish();
                startActivity(intent);
            }
        });

        //home button
        Button home = findViewById(R.id.btn_home2);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void addToCurrentOrder(Pizza pizza) {
        currentOrder.addToOrder(pizza);
    }
}