package com.example.android.cashier;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.android.cashier.models.RealmQueueAdapter;
import com.example.android.cashier.models.RealmPayment;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmedQueueFragment extends Fragment {
    private ListView realmQueueListView;
    private RealmQueueAdapter realmQueueAdapter;
    private List<RealmPayment> paymentList = new ArrayList<>();
    private ProgressBar progressBar;

    private Realm realm;


    public ConfirmedQueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.queue_list, container, false);



        progressBar = rootView.findViewById(R.id.loading_indicator);
        progressBar.setVisibility(View.GONE);

        // Get Realm instance
        realm = Realm.getDefaultInstance();

        // Clear current list contents
        paymentList.clear();

        viewDatabase();

        // Setup adapter
        realmQueueAdapter = new RealmQueueAdapter(getActivity(), R.layout.queue_view, paymentList);

        realmQueueListView = rootView.findViewById(R.id.list);
        // Link listView with adapter
        realmQueueListView.setAdapter(realmQueueAdapter);



        return rootView;
    }
    public void viewDatabase() {
        RealmResults<RealmPayment> confirmedPayments = realm.where(RealmPayment.class).findAll();

        //Use an iterator to invite all confirmedPayments

        //For all payment classes in confirmedPayments
        for (RealmPayment payment : confirmedPayments) {
            // Add all confirmed payments to arraylist
            paymentList.add(payment);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
