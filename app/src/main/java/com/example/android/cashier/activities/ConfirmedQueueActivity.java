package com.example.android.cashier.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.cashier.R;
import com.example.android.cashier.adapters.RealmQueueAdapter;
import com.example.android.cashier.fragments.ConfirmedQueueFragment;
import com.example.android.cashier.models.RealmPayment;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedQueueActivity extends AppCompatActivity {
    private ListView realmQueueListView;
    private RealmQueueAdapter realmQueueAdapter;
    private List<RealmPayment> paymentList = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ConfirmedQueueFragment())
                .commit();

//        paymentList.clear();
//        paymentList.addAll(DetailsActivity.paymentList);
//
//        realmQueueAdapter = new RealmQueueAdapter(this, R.layout.queue_view, paymentList);
//
//        realmQueueListView = findViewById(R.id.list);
//        realmQueueListView.setAdapter(realmQueueAdapter);
    }
}
