package com.example.cypher.invoice;

import android.util.Log;

import java.util.Objects;

/**
 * Created by cypher on 12/23/2016.
 */

public class Product implements Comparable<Product>{
    String product_id;
    String product_name;
    float product_price;

    @Override
    public int compareTo(Product o){
        if(this.product_id.equals(o.product_id)){

            return 0;
        }
        else return  1;


    }
    public String toString() {
        return "Product{" +
                "product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                '}';
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public  Product(){

    }
    public Product(String product_id, String product_name, float product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
    }

}
