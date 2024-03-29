package com.example.android.cashier;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.cashier.models.Payment;
import com.example.android.cashier.models.QueueAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PostedQueueFragment extends Fragment {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mPostedQueueReference;
    private ChildEventListener mChildEventListener;

    //Declare ListView and Adapter objects
    private QueueAdapter postAdapter;
    private ListView queueListView;
    private View loadingIndicator;

    public PostedQueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.queue_list, container, false);


        mDatabase = FirebaseDatabase.getInstance();
        mPostedQueueReference = mDatabase.getReference("postedDeposits");


        queueListView = rootView.findViewById(R.id.list);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator);
        final List<Payment> paymentsList = new ArrayList<>();
        postAdapter = new QueueAdapter(getActivity(), R.layout.queue_view, paymentsList);
        queueListView.setAdapter(postAdapter);



        //Add ChildEventListener, so that changes made in the database are reflected
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()){
                    loadingIndicator.setVisibility(View.GONE);
                    Payment payment = dataSnapshot.getValue(Payment.class);

                    postAdapter.add(payment); //here, you could use queueListView.add() too


                }


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }


        };
        mPostedQueueReference.addChildEventListener(mChildEventListener);


        return rootView;
    }


}
