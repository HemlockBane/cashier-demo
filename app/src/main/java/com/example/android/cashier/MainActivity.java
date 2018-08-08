package com.example.android.cashier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button depositButton = findViewById(R.id.deposit_queue);
        Button postsButton = findViewById(R.id.posted_queue);
        Button comfirmedButton = findViewById(R.id.confirmed_queue);



        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DepositQueueActivity.class);
                startActivity(intent);

            }
        });

        postsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostedQueueActivity.class);
                startActivity(intent);

            }
        });

        comfirmedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
