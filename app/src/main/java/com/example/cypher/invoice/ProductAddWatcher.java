package com.example.cypher.invoice;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by cypher on 12/30/2016.
 */
public class ProductAddWatcher implements TextWatcher {
    View inputView;
    TextInputLayout textInputLayout;
    View barcode, productName, productPrice;

    TextInputLayout barcodeLayout;
    TextInputLayout productNameLayout;
    TextInputLayout productPriceLayout;


    public ProductAddWatcher(View view) {
        inputView = view;
    }

    public ProductAddWatcher(View view, TextInputLayout inputLayout) {
        inputView = view;
        textInputLayout = inputLayout;
    }

    public ProductAddWatcher( View productName, View productPrice, TextInputLayout productNameLayout, TextInputLayout productPriceLayout) {
        this.productName = productName;
        this.productPrice = productPrice;

        this.productNameLayout = productNameLayout;
        this.productPriceLayout = productPriceLayout;

    }
    public ProductAddWatcher(View barcode, View productName, View productPrice, TextInputLayout barcodeLayout, TextInputLayout productNameLayout, TextInputLayout productPriceLayout) {
        this.barcode = barcode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productNameLayout = productNameLayout;
        this.barcodeLayout = barcodeLayout;
        this.productPriceLayout = productPriceLayout;

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //update product validation

    }

    @Override
    public void afterTextChanged(Editable s) {
        switch (inputView.getId()) {
            case R.id.update_product_name:
                validateProductName(inputView, textInputLayout);
                break;
            case R.id.update_product_price:
                validateProductPrice(inputView, textInputLayout);
                break;
        }

    }
    public boolean isUpdateFormValid(){
        if ( validateProductName(productName, productNameLayout) && validateProductPrice(productPrice, productPriceLayout)) {
            return true;
        }
        return false;
    }

    public boolean isFormValid() {

        if (validateBarcode(barcode, barcodeLayout) && validateProductName(productName, productNameLayout) && validateProductPrice(productPrice, productPriceLayout)) {
            return true;
        }
        return false;
    }

    private boolean validateBarcode(View inputView, TextInputLayout textInputLayout) { // TODO: 1/10/2017 check validateBarcode

        String barcode = ((EditText) inputView).getText().toString();

        if (barcode.isEmpty()) {
            textInputLayout.setError("Barcode Invalid");
            inputView.requestFocus();
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateProductName(View inputView, TextInputLayout textInputLayout) {// TODO: 1/10/2017  validateProductName

        String productName = ((EditText) inputView).getText().toString();
        if (productName.isEmpty()) {
            textInputLayout.setError("Product Name Invalid");
            inputView.requestFocus();
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validateProductPrice(View inputView, TextInputLayout textInputLayout) {// TODO: 1/10/2017   validateProductPrice

        String productPrice = ((EditText) inputView).getText().toString();
        if (productPrice.isEmpty()) {
            textInputLayout.setError("Product Price Invalid");
            inputView.requestFocus();
            return false;
        } else if (true) {
            try {
                float price = Float.parseFloat(productPrice);
                textInputLayout.setErrorEnabled(false);
            } catch (NumberFormatException e) {
                textInputLayout.setError("Product Price Invalid");
                inputView.requestFocus();
                return false;
            }
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;

    }
}
