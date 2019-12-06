package com.example.sampleecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sampleecommerce.RecyclerVIew.CartAdapter;
import com.example.sampleecommerce.db.CartHelper;
import com.example.sampleecommerce.mapping.MappingHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements LoadCartCallback {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    CartHelper cartHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Your Cart");
        }

        recyclerView = findViewById(R.id.rvCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this);
        recyclerView.setAdapter(cartAdapter);

        cartHelper = CartHelper.getInstance(getApplicationContext());

        if (savedInstanceState == null) {
            new LoadCartAsync(cartHelper, this).execute();
        }
    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postexecute(ArrayList<ProductModel> productModel) {
        if (productModel.size() > 0) {
            cartAdapter.setListCart(productModel);
        }
    }

    private static class LoadCartAsync extends AsyncTask<Void, Void, ArrayList<ProductModel>>{
        private final WeakReference<CartHelper> weakCartHelper;
        private final WeakReference<LoadCartCallback> weakCallback;

        private LoadCartAsync(CartHelper cartHelper, LoadCartCallback cartCallback) {
            weakCartHelper = new WeakReference<>(cartHelper);
            weakCallback = new WeakReference<>(cartCallback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<ProductModel> doInBackground(Void... voids) {
            Cursor dataCursor = weakCartHelper.get().queryAll();
            return MappingHelper.cursorToArray(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<ProductModel> productModels) {
            super.onPostExecute(productModels);
            weakCallback.get().postexecute(productModels);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

//    private String TotalPrice(){
//
//    }
}
