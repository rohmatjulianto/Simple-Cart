package com.example.sampleecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sampleecommerce.RecyclerVIew.ProductAdapter;
import com.example.sampleecommerce.db.CartHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CartHelper cartHelper;
    RecyclerView recyclerView;
    private ArrayList<ProductModel> listProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvShow);
        listProduct.addAll(DummyData.getListProduct());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ProductAdapter productAdapter = new ProductAdapter(this);
        productAdapter.setListProduct(listProduct);
        recyclerView.setAdapter(productAdapter);

        cartHelper = CartHelper.getInstance(getApplicationContext());
        cartHelper.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart){
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
