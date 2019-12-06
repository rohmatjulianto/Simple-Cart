package com.example.sampleecommerce.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.example.sampleecommerce.db.DbContract.CartColumns.ID;
import static com.example.sampleecommerce.db.DbContract.TABLE_NAME;

public class CartHelper {

    private static final String DB_TABLE = TABLE_NAME;
    private static DbHelper dbHelper;
    private static CartHelper INSTANCE;
    private static SQLiteDatabase database;

    public CartHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public static CartHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = new CartHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
        if (database.isOpen()){
            database.close();
        }
    }

    public Cursor queryAll(){
        return database.query(DB_TABLE, null,null,null,null,null,ID + " ASC");
    }

    public long insert(ContentValues values) {
        return database.insert(DB_TABLE, null, values);
    }

    public int update(String id, ContentValues values) {
        return database.update(DB_TABLE, values, ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DB_TABLE, ID + " = ?", new String[]{id});
    }
}
