package com.manuvastra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReviewCart extends AppCompatActivity {
    private static final String TAG = ReviewCart.class.getSimpleName();
    private ReviewAdapter adapter;
    List<CartItem> cartItems = new ArrayList<CartItem>();
    private TextView totalPrice;
    TextView address1;
    TextView address;
    TextView payment1;
    TextView payment;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        address1 = (TextView) findViewById(R.id.address1);
        address = (TextView) findViewById(R.id.address);
        payment1 = (TextView) findViewById(R.id.payment1);
        payment = (TextView) findViewById(R.id.payment);
        linearLayout = (LinearLayout) findViewById(R.id.checkout1);

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

        adapter = new ReviewAdapter(
                this,
                R.layout.content_dipslay_cart,   // custom layout for the list
                cartItems
        );

        itemListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        totalPrice = (TextView) findViewById(R.id.total_price);

        address1.setVisibility(View.VISIBLE);
        address.setVisibility(View.VISIBLE);
        payment1.setVisibility(View.VISIBLE);
        payment.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        setPrice();
        itemListView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

    }

    public void checkoutButtonClick(View view) {
        placeOrder();
    }

    public void placeOrder() {

        //tracker
        //TrackerEvents.trackAll(tracker);

        //tracker.pauseEventTracking();

        Intent intent = new Intent(this, FinishActivity.class);
        startActivity(intent);

//        Context context = getApplicationContext();
//        Tracker tracker = DemoUtils.getAndroidTrackerClassic(context, getCallback());
//        //tracker.resumeEventTracking();
//
//
//        Emitter e = tracker.getEmitter();
//        String uri = "10.209.47.193:58080";
//        HttpMethod method = HttpMethod.POST;
//        RequestSecurity security = RequestSecurity.HTTP;
//
//        if (!e.getEmitterStatus()) {
//            e.setEmitterUri(uri);
//            e.setRequestSecurity(security);
//            e.setHttpMethod(method);
//        }


//        TextView totalPriceView = (TextView) findViewById(R.id.total_price_review);
//        Double totalPrice = Double.parseDouble(totalPriceView.getText().toString());

        List<CartItem> items = Cart.getCart().getCartItemsList();



        CartItem item;
        for(int i=0;i<items.size();i++){
            item = items.get(i);

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
