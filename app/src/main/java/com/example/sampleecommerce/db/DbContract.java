package com.example.sampleecommerce.db;

import android.provider.BaseColumns;

public class DbContract {

    public static String TABLE_NAME = "Cart";

    public static final class CartColumns implements BaseColumns{
        public static String ID = "id";
        public static String PRICE = "price";
        public static String PCS = "pcs";
        public static String NAME = "name";
        public static String IMAGE = "image";

    }

}
