package com.example.sampleecommerce.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "db_cart";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_TABLE = String.format(
            "CREATE TABLE %s" +
                    " (%s INTEGER,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL)",
            DbContract.TABLE_NAME,
            DbContract.CartColumns.ID,
            DbContract.CartColumns.NAME,
            DbContract.CartColumns.IMAGE,
            DbContract.CartColumns.PRICE,
            DbContract.CartColumns.PCS
    );

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.TABLE_NAME);
        onCreate(db);
    }
}
