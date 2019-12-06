package com.example.sampleecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sampleecommerce.db.CartHelper;

import static com.example.sampleecommerce.db.DbContract.CartColumns.ID;
import static com.example.sampleecommerce.db.DbContract.CartColumns.IMAGE;
import static com.example.sampleecommerce.db.DbContract.CartColumns.NAME;
import static com.example.sampleecommerce.db.DbContract.CartColumns.PCS;
import static com.example.sampleecommerce.db.DbContract.CartColumns.PRICE;

public class DetailActivity extends AppCompatActivity {
    public static String EXTRA_DATA = "extra data";
    ProductModel productModel;

    TextView tvName, tvPrice;
    ImageView imageView;

    Button btnAddToCart;

    CartHelper cartHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        productModel = getIntent().getParcelableExtra(EXTRA_DATA);
        cartHelper = CartHelper.getInstance(getApplicationContext());

        tvName = findViewById(R.id.name_detail);
        tvPrice = findViewById(R.id.price_detail);
        imageView = findViewById(R.id.image_detail);

        tvName.setText(productModel.getName());
        tvPrice.setText("Rp "+ productModel.getPrice());

        Glide.with(this)
                .load(productModel.getImage())
                .apply(new RequestOptions()
                        .centerCrop()
                        )
                .into(imageView);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(productModel.getName());
        }

        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
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

    private void showAddDialog(){
        View dialogView = getLayoutInflater().inflate(R.layout.add_to_cart, null);
        ImageView imageAdd = dialogView.findViewById(R.id.img_add);
        ImageButton btnAdd = dialogView.findViewById(R.id.btn_add);
        ImageButton btnRemove = dialogView.findViewById(R.id.btn_remove);
        final EditText edPcs = dialogView.findViewById(R.id.edit_pcs);
        final TextView totalPrice = dialogView.findViewById(R.id.total_price);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.ic_add_circle_black_24dp);
        alert.setTitle("Add :" + productModel.getName());
        alert.setView(dialogView);
        alert.setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertCart(Integer.parseInt(edPcs.getText().toString().trim()));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });




        if (edPcs.getText() != null){
            String pcs = edPcs.getText().toString().trim();
            Integer TotalPrice = Integer.parseInt(pcs) * Integer.parseInt(productModel.getPrice());
            totalPrice.setText("Rp "+ String.valueOf(TotalPrice));
        }else{
            totalPrice.setText("Rp "+ 0);
        }

        Glide.with(dialogView)
                .load(productModel.getImage())
                .apply(new RequestOptions().centerCrop())
                .into(imageAdd);
        edPcs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                Log.d("dD", "afterTextChanged: "+s.toString());
                if (!s.toString().isEmpty()){
                    String pcs = s.toString();
                    Integer TotalPrice = Integer.parseInt(pcs) * Integer.parseInt(productModel.getPrice());
                    totalPrice.setText("Rp "+ String.valueOf(TotalPrice));
                }else{
                    totalPrice.setText("Rp "+ 0);
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pcs = edPcs.getText().toString().trim();
                int increament = Integer.parseInt(pcs) + 1;
                edPcs.setText(String.valueOf(increament));

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pcs = edPcs.getText().toString().trim();
                if (Integer.parseInt(pcs) > 0){
                    int remove = Integer.parseInt(pcs) - 1;
                    edPcs.setText(String.valueOf(remove));
                }

            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }

    private void insertCart(int pcs){
        ContentValues values = new ContentValues();
        values.put(ID, Integer.parseInt(productModel.getId()));
        values.put(NAME, productModel.getName());
        values.put(IMAGE, productModel.getImage());
        values.put(PRICE, productModel.getPrice());
        values.put(PCS, pcs);

        long result = cartHelper.insert(values);
        if (result > 0){
            finish();
            Toast.makeText(this, productModel.getName()+" Succes Add, select shop toggle to view cart", Toast.LENGTH_LONG).show();
        }

    }
}
