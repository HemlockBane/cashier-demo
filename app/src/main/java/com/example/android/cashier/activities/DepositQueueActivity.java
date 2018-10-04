package com.example.android.cashier.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.cashier.R;
import com.example.android.cashier.fragments.DepositQueueFragment;

public class DepositQueueActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DepositQueueFragment())
                .commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //realm.close();
    }
}
