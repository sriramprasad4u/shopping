package com.manuvastra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FinishActivity extends AppCompatActivity {
    private static final String TAG = FinishActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void continueShopping(View view) {
        finish();
        Cart.getCart().clearCart();
        Intent intent = new Intent(this, BrowseActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        finish();
        Cart.getCart().clearCart();
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        startActivity(intent);
    }

}
