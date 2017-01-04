package com.example.cypher.invoice;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {
    EditText barcode;
    EditText productName;
    EditText productPrice;
    Button  saveProductButton;

    TextInputLayout barcodeLayout;
    TextInputLayout productNameLayout;
    TextInputLayout productPriceLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        barcode = (EditText) findViewById(R.id.add_product_barcode_etext);
        productName = (EditText) findViewById(R.id.add_product_adde);
        productPrice = (EditText) findViewById(R.id.add_product_price_etext);
        saveProductButton=(Button)findViewById(R.id.add_product_button);

        barcodeLayout=(TextInputLayout)findViewById(R.id.add_product_barcode_layout);
        productNameLayout=(TextInputLayout)findViewById(R.id.add_product_product_name_layout);
        productPriceLayout=(TextInputLayout)findViewById(R.id.add_product_product_price_layout);


        barcode.addTextChangedListener(new ProductAddWatcher(barcode, barcodeLayout));
        productName.addTextChangedListener(new ProductAddWatcher(productName, productNameLayout));
        productPrice.addTextChangedListener(new ProductAddWatcher(productPrice, productPriceLayout));

        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductAddWatcher productAddWatcher=new ProductAddWatcher(barcode,productName,productPrice, barcodeLayout, productNameLayout, productPriceLayout);
                if(productAddWatcher.isFormValid()) {
                    addProductDetails();
                }
            }
        });


    }


    public void resetText(View view) {
        barcode.setText("");
        productName.setText("");
        productPrice.setText("");
        barcodeLayout.setErrorEnabled(false);
        productNameLayout.setErrorEnabled(false);
        productPriceLayout.setErrorEnabled(false);
    }

    public void addProductDetails() {

        Product product = new Product(barcode.getText().toString(), productName.getText().toString(), Float.parseFloat(productPrice.getText().toString()));
        ProductDB db = new ProductDB(this);
        // Inserting Product
        // Log.d("Insert: ", "Inserting .."+product.toString());
        db.addProduct(product);
        Log.d("Insert: ", "Inserting .." + product.toString());
        Toast.makeText(this, "Product Information Inserted", Toast.LENGTH_SHORT).show();
        barcode.setText("");
        productName.setText("");
        productPrice.setText("");
    }

    public void scan_bar_code_to_add(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        String barcode_content;
        if (scanResult != null) {
            Log.d("add Item:", "result not empty");
            try {
                barcode_content = scanResult.getContents();

                if (barcode_content.length() <= 16) {

                    Log.d("before", "" + barcode_content.length());
                    barcode.setText("" + barcode_content);
                    Log.d("after", "" + barcode_content.length());

                } else {
                    Toast.makeText(this, "Barcode Length is greater than 16", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Barcode not valid", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }

    }

}








