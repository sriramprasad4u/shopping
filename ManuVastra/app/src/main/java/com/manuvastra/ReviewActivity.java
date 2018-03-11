package com.manuvastra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.manuvastra.R;


public class ReviewActivity extends AppCompatActivity {

    private Double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Bundle extra = getIntent().getExtras();
        String message = extra.getString("total");
        TextView tv = (TextView) findViewById(R.id.total_price_review);
        tv.setText("Total Price:   $ " + message);
        price = Double.parseDouble(message);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public void deleteCartClick(View view) {
        finish();
    }
}
