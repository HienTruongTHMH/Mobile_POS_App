package com.midterm.pos_app;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CoffeeAdapter coffeeAdapter;
    private List<CoffeeItem> coffeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        loadCoffeeData();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setupRecyclerView() {
        coffeeList = new ArrayList<>();
        coffeeAdapter = new CoffeeAdapter(this, coffeeList, new CoffeeAdapter.OnCoffeeClickListener() {
            @Override
            public void onAddClick(CoffeeItem coffeeItem) {
                Toast.makeText(MainActivity.this, "Added " + coffeeItem.getName() + " to cart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHeartClick(CoffeeItem coffeeItem) {
                coffeeItem.setFavorite(!coffeeItem.isFavorite());
                coffeeAdapter.notifyDataSetChanged();
                String message = coffeeItem.isFavorite() ? "Added to favorites" : "Removed from favorites";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSizeClick(CoffeeItem coffeeItem, String size) {
                coffeeItem.setSelectedSize(size);
                coffeeAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Selected size: " + size, Toast.LENGTH_SHORT).show();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(coffeeAdapter);
    }

    private void loadCoffeeData() {
        coffeeList.clear();
        // Add sample coffee items with image file paths
        // IMPORTANT: Replace these with actual file paths to your images.
        // If images are in res/drawable, you can use a helper to get the URI or
        // store the resource name and then get the resource ID dynamically in the adapter.
        // For simplicity, let's assume they are directly accessible as paths.
        // For example, if you put 'cappuccino.png' in your app's internal storage
        // or a shared directory.
        // If your images are in res/drawable, you might do something like:
        // String uri = "android.resource://" + getPackageName() + "/" + R.drawable.coffee_cappuccino;
        // Or if you want to use the names directly and then find the resource:
        // "coffee_cappuccino" and then in adapter: getResources().getIdentifier(imageName, "drawable", getPackageName());

        // For demonstration, let's assume images are accessible via their names in drawable,
        // and we'll resolve them in the adapter.
        // OR, if you have actual file paths (e.g., from external storage or assets),
        // you would provide those paths directly.

        // Example if images are in res/drawable:
        // You'll still use the R.drawable IDs here, but the adapter will convert them to String paths.
        // A better approach if you want to avoid R.drawable IDs in CoffeeItem:
        // Store the *name* of the drawable (e.g., "coffee_cappuccino") and then resolve it
        // to a resource ID in the Adapter using context.getResources().getIdentifier()

        // Let's modify CoffeeItem to take resource name (String) instead of ID (int) for image
        // and then resolve it in the adapter. This makes CoffeeItem more flexible.

        coffeeList.add(new CoffeeItem(
                "ca_phe_sua",
                "3$",
                "../res/drawable/ca_phe_sua.png", // Storing drawable name
                false,
                "S",
                new String[]{"S", "M", "L"}
        ));

        coffeeList.add(new CoffeeItem(
                "cà phê đen",
                "2$",
                "../res/drawable/ca_phe_den.jpg", // Storing drawable name
                false,
                "S",
                new String[]{"S", "M", "L"}
        ));

        coffeeList.add(new CoffeeItem(
                "Matcha",
                "2.5$",
                "../res/drawable/matcha_latte.jpg", // Storing drawable name
                false,
                "S",
                new String[]{"S", "L"}
        ));

        coffeeList.add(new CoffeeItem(
                "Latte",
                "4$",
                "../res/drawable/ic_coffee_default.png", // Storing drawable name
                false,
                "S",
                new String[]{"S", "M", "L"}
        ));

        coffeeAdapter.notifyDataSetChanged();
    }
}