package com.manuvastra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProductDescription extends AppCompatActivity {

    private static final String TAG = ProductDescription.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.product_image);
        TextView item_title = (TextView) findViewById(R.id.product_title);
        RatingBar product_rating = (RatingBar) findViewById(R.id.product_rating);
        TextView item_price = (TextView) findViewById(R.id.product_price);
        TextView description = (TextView) findViewById(R.id.description);
        TextView product_review = (TextView) findViewById(R.id.product_review);
        TextView product_addToCart = (TextView) findViewById(R.id.product_addToCart);

        final Item item = Cart.getCart().getProduct();

        if (item != null) {
            if(imageView != null){
                imageView.setImageResource(getApplicationContext().getResources().getIdentifier(item.getImage(), "drawable", getApplicationContext().getPackageName()));
            }

            if (item_title != null) {
                item_title.setText(item.getTitle());
            }

            if (product_rating != null){
                product_rating.setRating(item.getRating());
                product_rating.setFocusable(false);
            }

            if (item_price != null) {
                item_price.setText("$ "+item.getPrice().toString());
            }

            if(description != null){
                description.setText(item.getDescription());
            }
//
            if(product_review != null){
                product_review.setText(item.getReview());
            }
            if(product_addToCart !=null){

                product_addToCart.setText("ADD TO CART");
                product_addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cart.getCart().addProductToCart(item, 1);
//                        Intent intent = new Intent(getApplicationContext(), DisplayCart.class);
//                        getApplicationContext().startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cart) {

            Intent intent = new Intent(getApplicationContext(), DisplayCart.class);
            getApplicationContext().startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
