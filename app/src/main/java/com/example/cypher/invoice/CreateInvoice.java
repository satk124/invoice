package com.example.cypher.invoice;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateInvoice extends AppCompatActivity {
    ListView listView;
    TextView textView;
    TextView textView2;
    Map<String, Product>  products;
    Map<String, Integer> invoice;
    Map<Product, Integer> forview;
    InvoiceListViewAdapter invoiceListViewAdapter;
    String[] productNames;
    String [] productCounts;
    String [] productCosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);
        textView=(TextView)findViewById(R.id.text);
        textView2=(TextView)findViewById(R.id.text2);
        products=new LinkedHashMap<String,Product>();
        invoice= new LinkedHashMap<String, Integer>();
        listView=(ListView)findViewById(R.id.invoice);
        forview=new LinkedHashMap<Product, Integer>();


    }
    public void scanItem(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
//        Log.d("scan1"," ");
        integrator.initiateScan();
//        Log.d("scan2"," ");
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        Log.d("scan3"," ");
        String displayInvoice="Product Name\t\tNos\t\tCost\n";
        if (scanResult != null) {
            float total=0;
//            Log.d("scan4"," ");
            try {
                String barcode_content = scanResult.getContents();
                if(barcode_content.length()>16) throw new Exception("Barcode is not vaid") ;
                 total=0;
                ProductDB db = new ProductDB(this);
                Product product = db.fetchProductById(barcode_content);
                if (product == null) {
                    Toast.makeText(this, "Product not present, Please add this product first", Toast.LENGTH_LONG).show();
                } else {

                    if(invoice.containsKey(product.getProduct_id())){
//                        Log.d("same","same");
                        invoice.put(product.getProduct_id(), invoice.get(product.getProduct_id())+1);
                    }else {
                        invoice.put(product.getProduct_id(), 1);
                        products.put(product.product_id,product);
                    }

                }
                for(Map.Entry<String, Integer>  p:invoice.entrySet()){
                    displayInvoice=displayInvoice+products.get(p.getKey()).getProduct_name()+"\t    \t"+p.getValue()+"\t   \t"+products.get(p.getKey()).getProduct_price()*p.getValue()+"\n";
                    total+=products.get(p.getKey()).getProduct_price()*p.getValue();
                    forview.put(products.get(p.getKey()),p.getValue());
                }


                productCosts=new String[forview.size()];
                productNames=new String [forview.size()];
                productCounts=new String [forview.size()];
                Log.d("par",productCosts.toString()+" "+productNames.toString()+" "+productCounts.toString());
//                Log.d("adaptor3","adp");
                int i=0;
                for(Map.Entry<Product, Integer> entry:forview.entrySet()){
                    productCosts[i]=""+entry.getValue()*entry.getKey().getProduct_price();
                    productNames[i]=entry.getKey().getProduct_name();
                    productCounts[i++]=""+entry.getValue();
                }

                invoiceListViewAdapter=new InvoiceListViewAdapter(this,productNames,productCounts,productCosts);

                //textView.setText(displayInvoice);
                textView2.setText(" "+total+" Rs.");

                listView.setAdapter( invoiceListViewAdapter);

            }catch (Exception e){

                productCosts=new String[forview.size()];
                productNames=new String [forview.size()];
                productCounts=new String [forview.size()];
                Log.d("par",productCosts.toString()+" "+productNames.toString()+" "+productCounts.toString());
                Log.d("adaptor3","adp");
                int i=0;
                for(Map.Entry<Product, Integer> entry:forview.entrySet()){
                    productCosts[i]=""+entry.getValue()*entry.getKey().getProduct_price();
                    productNames[i]=entry.getKey().getProduct_name();
                    productCounts[i++]=""+entry.getValue();
                }

                invoiceListViewAdapter=new InvoiceListViewAdapter(this,productNames,productCounts,productCosts);

                //textView.setText(displayInvoice);
                textView2.setText("Total: "+total+" Rs.");

                listView.setAdapter( invoiceListViewAdapter);
                Toast.makeText(this,"Barcode is not valid", Toast.LENGTH_SHORT).show();
                Log.d("Scan error:",""+e);
            }
        }

    }


}
