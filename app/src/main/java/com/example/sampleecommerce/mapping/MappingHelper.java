package com.example.sampleecommerce.mapping;

import android.database.Cursor;

import com.example.sampleecommerce.ProductModel;
import com.example.sampleecommerce.db.DbContract;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<ProductModel> cursorToArray(Cursor cursor){
        ArrayList<ProductModel> productList = new ArrayList<>();

        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.CartColumns.ID));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.CartColumns.PRICE));
            String pcs = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.CartColumns.PCS));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.CartColumns.NAME));
            String image = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.CartColumns.IMAGE));

            productList.add(new ProductModel(id, price, pcs, name, image));
        }
        return productList;
    }
}
