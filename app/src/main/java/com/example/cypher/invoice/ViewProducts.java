package com.example.cypher.invoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class ViewProducts extends AppCompatActivity {

   // static ListView listView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_product);
       // textView=(TextView)findViewById(R.id.text_view1) ;
      //  listView=(ListView) findViewById(R.id.all_products);
    }
    public  void viewAllProducts(View view){

//       ProductDB db=new ProductDB(this);
//        String s="Product Id\tProduct Name\tProduct Price\n";
//        for(Product p:db.getAllProducts()){
//            s+=p.getProduct_id()+"   \t"+p.getProduct_name()+"   \t"+p.getProduct_price()+"\n";
//
//        }
//        textView.setText(s);
//        Log.d("all:",db.getAllProducts().toString());
//
//




////
//        Cursor cursor=db.getAllProducts();
//        ShowProductAdapter showProductAdapter = new ShowProductAdapter(this, cursor);
//       // SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,null, null);
//       /* final ShowProductAdapter adapter = new ShowProductAdapter(getBaseContext(),
//                android.R.layout.simple_list_item_1, cursor);
//        list.setAdapter(adapter);
//*/
//        listView.setAdapter(showProductAdapter);
//       // listView.setAdapter(adapter);
//
//     /*   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
//            }
//        });*/
////


    }
}
