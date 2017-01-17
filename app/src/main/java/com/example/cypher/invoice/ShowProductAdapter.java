package com.example.cypher.invoice;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by cypher on 12/24/2016.
 */

public class ShowProductAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;


    public ShowProductAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        Log.d("abc1","abc");
        mContext=context;
        mLayoutInflater=LayoutInflater.from(context);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view=mLayoutInflater.inflate(R.layout.activity_product_row_view,parent,false);
        Log.d("abc2","abc");
        return view;
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        Log.d("abc3","abc");
        TextView product_id = (TextView) view.findViewById(R.id.product_id);
        TextView product_name = (TextView) view.findViewById(R.id.product_name);
        TextView product_price = (TextView) view.findViewById(R.id.product_price);
        // Extract properties from cursor
        String productId = cursor.getString(cursor.getColumnIndexOrThrow("product_id"));
        String productName = cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
        String productPrice = cursor.getString(cursor.getColumnIndexOrThrow("product_price"));

        // Populate fields with extracted properties
        product_id.setText("Id: "+productId);
        product_name.setText(productName);
        product_price.setText(productPrice+" Rs.");
    }
}

//
//public class ShowProductAdapter extends CursorAdapter {
//    // Cursor cursor;
//    Context context;
//
//    public ShowProductAdapter(Context context, int RId, Cursor cursor) {
//        super(context, cursor);
//        // this.cursor = cursor;
//        this.context = context;
//    }
//
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
///*
//
//        TextView team1 = (TextView) view.findViewById(R.id.listitem_team1);
//        team1.setText(cursor.getString(10));
//        TextView team2 = (TextView) view.findViewById(R.id.listitem_team2);
//        team2.setText(cursor.getString(11));
//        TextView date = (TextView) view.findViewById(R.id.listitem_date);
//        date.setText(cursor.getString(3));
//        TextView city = (TextView) view.findViewById(R.id.listitem_city);
//        city.setText(cursor.getString(2));
//        TextView time = (TextView) view.findViewById(R.id.listitem_time);
//        time.setText(cursor.getString(4));
//        TextView ground = (TextView) view.findViewById(R.id.listitem_ground);
//        date.setText(cursor.getString(1));
//
//        */}
//
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//      //  View v = inflater.inflate(R.layout.listitem, parent, false);
//        return null;
//    }
//}