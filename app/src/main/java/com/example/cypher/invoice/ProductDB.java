package com.example.cypher.invoice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cypher on 12/23/2016.
 */

public class ProductDB extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ProductDB";

    // Contacts table name
    private static final String TABLE_PRODUCTS = "products";

    // Contacts Table Columns names
    private static final String PRODUCT_ID = "product_id";
    private static final String PRDUCT_NAME = "product_name";
    private static final String PRODUCT_PRICE = "product_price";

    public ProductDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                +" _id"+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PRODUCT_ID + " VARCHAR(16) ," + PRDUCT_NAME + " TEXT,"
                + PRODUCT_PRICE + " REAL" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        // Create tables again
        onCreate(db);
    }
    public void addProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_ID, product.getProduct_id());
        values.put(PRDUCT_NAME, product.getProduct_name());
        values.put(PRODUCT_PRICE, product.getProduct_price());

        db.insert(TABLE_PRODUCTS, null, values);
        Log.d("addProduct()","added");

        db.close(); // Closing database connection
    }
    public  void updateProduct(Product product){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_ID,product.getProduct_id()); //These Fields should be your String values of actual column names
        cv.put(PRDUCT_NAME,product.getProduct_name());
        cv.put(PRODUCT_PRICE,product.getProduct_price());
        String selectQuery = "SELECT  _id FROM " + TABLE_PRODUCTS+ " WHERE "+
                PRODUCT_ID+" = "+"\""+product.getProduct_id()+"\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        //System.out.println(product.toString());
        int id= cursor.getInt(0);
        Log.d("updateProduct()","before");
        db.update(TABLE_PRODUCTS, cv, "_id="+id, null);
        Log.d("updateProduct()","updated");
    }

    public Product fetchProductById(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS+ " WHERE "+
                PRODUCT_ID+" = "+"\""+id+"\"";
        Cursor cursor = db.rawQuery(selectQuery, null);
      //  cursor.moveToFirst();
//Log.d(cursor.getString(1),"first column");
        Log.d("id to fetch 0 ", selectQuery+" ");

        Product product;
        if(cursor.getCount()==0){
            Log.d("id to fetch 0 ", id+" ");
            product= null;
        }else{

            cursor.moveToFirst();
            product=new Product();
            product.setProduct_id(cursor.getString(1));
            product.setProduct_name(cursor.getString(2));
            product.setProduct_price(cursor.getFloat(3));

        }
        db.close();
        return product;
    }


    public
    //List<Product>
    Cursor
    getAllProducts() {
        List<Product> productList = new ArrayList<Product>();


        SQLiteDatabase db = this.getWritableDatabase();


        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;


        Cursor cursor = db.rawQuery(selectQuery, null);

        //db.close();
        return cursor;

//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Product product = new Product();
//                product.setProduct_id(cursor.getString(0));
//                product.setProduct_name(cursor.getString(1));
//                product.setProduct_price(cursor.getFloat(2));
//                // Adding contact to list
//                productList.add(product);
//            } while (cursor.moveToNext());
//        }
//
//        // return contact list
//        return productList;
    }
}