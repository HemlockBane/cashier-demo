package com.example.android.cashier;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import com.example.android.cashier.models.CategoryAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = findViewById(R.id.viewpager);

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(categoryAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);







//        depositButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DepositQueueActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        postsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, PostedQueueActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        comfirmedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



    }
}
