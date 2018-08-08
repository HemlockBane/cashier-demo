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

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmedQueueFragment extends Fragment {
    private ListView realmQueueListView;
    private RealmQueueAdapter realmQueueAdapter;
    private List<RealmPayment> paymentList = new ArrayList<>();
    private ProgressBar progressBar;


    public ConfirmedQueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.queue_list, container, false);
        paymentList.clear();
        paymentList.addAll(DetailsActivity.paymentList);

        realmQueueAdapter = new RealmQueueAdapter(getActivity(), R.layout.queue_view, paymentList);

        realmQueueListView = rootView.findViewById(R.id.list);
        realmQueueListView.setAdapter(realmQueueAdapter);



        return rootView;
    }

}
