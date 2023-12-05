package pizza;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cs213_project_five.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CurrentOrderScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_screen);

        ListView pizza_list = findViewById(R.id.list_current_pizzas);

        List<Pizza> pizzas = Singleton.getInstance().currentOrder.getPizzas();
        List<String> pizzaStrings = pizzas.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        ArrayAdapter<String> toppings = new ArrayAdapter<>(
                this,
                R.layout.specialty_item_selected_toppings_item,
                R.id.tv_listview_item,
                pizzaStrings
        );
        pizza_list.setAdapter(toppings);

        //home button
        Button home = findViewById(R.id.btn_home2);
        home.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}