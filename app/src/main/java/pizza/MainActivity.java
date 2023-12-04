package pizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cs213_project_five.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSpecialtyPizzaScreen(View view) {
        Intent intent = new Intent(this, SpecialtyPizzaScreen.class);
        startActivity(intent);
    }
    public void showBuildYourOwnScreen(View view) {
        Intent intent = new Intent(this, BuildYourOwnScreen.class);
        startActivity(intent);
    }
    public void showCurrentOrderScreen(View view) {
        Intent intent = new Intent(this, CurrentOrderScreen.class);
        startActivity(intent);
    }
    public void showStoreOrderScreen(View view) {
        Intent intent = new Intent(this, StoreOrderScreen.class);
        startActivity(intent);
    }
}