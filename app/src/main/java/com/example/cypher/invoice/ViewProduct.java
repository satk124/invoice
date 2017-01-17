package com.example.cypher.invoice;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProduct extends AppCompatActivity {

    ListView listView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_product_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.list_view_product);
        viewAllProducts();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(this, "Implementation in progress", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void viewAllProducts() {

        ProductDB db = new ProductDB(this);
//          SQLiteDatabase db = handler.getWritableDatabase();
//        String s = "Product Id  | \tProduct Name | \tProduct Price\n";
//        for (Product p : db.getAllProducts()) {
//            s += p.getProduct_id() + "|   \t" + p.getProduct_name() + "|   \t" + p.getProduct_price() + "\n";//
//        }
//        textView.setText(s);
//        Log.d("all:", db.getAllProducts().toString());
//        SQLiteDatabase db1 = db.getWritableDatabase();
//        String selectQuery = "SELECT  * FROM " + "products";
//        Cursor cursor = db1.rawQuery(selectQuery, null);
//        Log.d("abc0","abc");
        try {
            ShowProductAdapter showProductAdapter = new ShowProductAdapter(getBaseContext(), db.getAllProducts());
            listView.setAdapter(showProductAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //details of item clicked
                    final TextView clicked_product_id = (TextView) view.findViewById(R.id.product_id);
                    TextView clicked_product_name = (TextView) view.findViewById(R.id.product_name);
                    TextView clicked_product_price = (TextView) view.findViewById(R.id.product_price);

                    AlertDialog.Builder edit_product_details = new AlertDialog.Builder(ViewProduct.this);
                    View add_product_view = LayoutInflater.from(ViewProduct.this).inflate(R.layout.activity_update_product_information, null);

                    TextView barcode = (TextView) add_product_view.findViewById(R.id.update_barcode);
                    final EditText product_name = (EditText) add_product_view.findViewById(R.id.update_product_name);
                    final EditText product_price = (EditText) add_product_view.findViewById(R.id.update_product_price);

                    final TextInputLayout productNameLayout = (TextInputLayout) add_product_view.findViewById(R.id.update_product_product_name_layout);
                    final TextInputLayout productPriceLayout = (TextInputLayout) add_product_view.findViewById(R.id.update_product_product_price_layout);

                    product_name.addTextChangedListener(new ProductAddWatcher(product_name, productNameLayout));
                    product_price.addTextChangedListener(new ProductAddWatcher(product_price, productPriceLayout));

                    barcode.setText("Barcode: " + clicked_product_id.getText().toString());
                    product_name.setText(clicked_product_name.getText().toString());
                    product_price.setText(clicked_product_price.getText().toString());
                    edit_product_details.setTitle("Edit Product Details")
                            .setView(add_product_view)
                            .setCancelable(false)
                            .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    ProductDB db = new ProductDB(ViewProduct.this);//check this

                                    ProductAddWatcher productUpdateWatcher = new ProductAddWatcher(product_name, product_price, productNameLayout, productPriceLayout);
                                    if (productUpdateWatcher.isUpdateFormValid()) {
                                        Product product = new Product(clicked_product_id.getText().toString(), product_name.getText().toString(), Float.parseFloat(product_price.getText().toString()));
                                        db.updateProduct(product);
                                        Toast.makeText(ViewProduct.this, "Product Information Updated", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(ViewProduct.this, "Product Not updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .create()
                            .show();


                }

            });


        } catch (Exception e) {
            Log.d("satendr" + e, "error");
        }

    }
}
