package pizza;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs213_project_five.R;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Lily Chang
 */
class PizzaItemAdapter extends RecyclerView.Adapter<PizzaItemAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<PizzaItem> items; //need the data binding to each row of RecyclerView

    private boolean deleteOnClick;

    public PizzaItemAdapter(Context context, ArrayList<PizzaItem> items, boolean deleteOnClick) {
        this.context = context;
        this.items = items;
        this.deleteOnClick = deleteOnClick;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row_view, parent, false);

        return new ItemsHolder(view, deleteOnClick);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.tv_name.setText(items.get(position).getItemName());
        holder.tv_price.setText(items.get(position).getDescription());
        holder.im_item.setImageResource(items.get(position).getImage());
        holder.pizza = items.get(position).getPizza();
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout

        private Pizza pizza;

        public ItemsHolder(@NonNull View itemView, boolean deleteOnClick) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            parentLayout = itemView.findViewById(R.id.rowLayout);

            /* set onClickListener for the row layout,
            clicking on a row will trigger this
             */
            if(deleteOnClick) {
                parentLayout.setOnClickListener(view -> {

                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                    alert.setTitle("Delete pizza");
                    alert.setMessage("Do you want to delete this " + pizza.getClass().getSimpleName() + " pizza?");

                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Singleton.getInstance().currentOrder.removeFromOrder(pizza);
                            Toast.makeText(view.getContext(), "Deleted pizza", Toast.LENGTH_SHORT).show();
                            parentLayout.setVisibility(View.INVISIBLE);
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                });
            }
        }
    }
}
