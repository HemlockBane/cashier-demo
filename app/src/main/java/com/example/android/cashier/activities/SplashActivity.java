package com.example.android.cashier.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent mainActivtyIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(mainActivtyIntent);

        // Finish splash screen
        finish();
    }

}
