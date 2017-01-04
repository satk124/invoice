package com.example.cypher.invoice;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.Inflater;


/**
 * Created by cypher on 12/26/2016.
 */
public class InvoiceListViewAdapter extends ArrayAdapter<String> {
    Map<Product, Integer> products;
    String[] productNames;
    String [] productCounts;
    String [] productCosts;
    Activity context;
    public InvoiceListViewAdapter(Activity context, String[] productNames,
            String [] productCounts,
            String [] productCosts) {
        super(context, R.layout.activity_product_row_view,productNames);
        Log.d("adaptor2","adp");

       this.context=context;

        this.productCosts=productCosts;
        this.productNames=productNames;
        this.productCounts=productCounts;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View rowView= layoutInflater.inflate(R.layout.activity_product_row_view,null,true);
        TextView productName=(TextView)rowView.findViewById(R.id.product_id);
        TextView noOfItem=(TextView)rowView.findViewById(R.id.product_name);
        TextView cost=(TextView)rowView.findViewById(R.id.product_price);
        Log.d("row",productNames[position]+" "+productCounts[position]+" "+productCosts[position] );
        productName.setText(productNames[position]);
        noOfItem.setText(productCounts[position]);
        cost.setText(productCosts[position]);

        return rowView;
    }
}
