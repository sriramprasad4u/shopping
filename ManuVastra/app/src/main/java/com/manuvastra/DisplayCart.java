package com.manuvastra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayCart extends AppCompatActivity {

    private static final String TAG = DisplayCart.class.getSimpleName();

    private CartAdapter adapter;
    List<CartItem> cartItems = new ArrayList<CartItem>();
    private TextView totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dipslay_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),BrowseActivity.class);
                    startActivity(intent);
                }
            }
        );

        ListView itemListView = (ListView) findViewById(R.id.item_list);

        cartItems = Cart.getCart().getCartItemsList();

        adapter = new CartAdapter(
                this,
                R.layout.content_dipslay_cart,   // custom layout for the list
                cartItems
        );

        itemListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        totalPrice = (TextView) findViewById(R.id.total_price);
        Cart.getCart().setTextView(totalPrice);


        setPrice();

    }


    public void checkoutButtonClick(View view) {
        if(Cart.getCart().getCartItemsList().size()>0) {
            Intent intent = new Intent(this, ReviewCart.class);
            startActivity(intent);
        }
        else{
            Cart.getCart().showDialog(this, true);
        }
    }



    public void setPrice(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totalPrice.setText(Cart.getCart().getPrice()+"");
            }
        });
    }
}
