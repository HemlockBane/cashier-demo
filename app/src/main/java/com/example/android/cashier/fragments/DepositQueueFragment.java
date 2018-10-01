package com.example.android.cashier.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.cashier.activities.ConfirmDetailsActivity;
import com.example.android.cashier.R;
import com.example.android.cashier.models.Payment;
import com.example.android.cashier.adapters.QueueAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepositQueueFragment extends Fragment {
    final String TAG = DepositQueueFragment.class.getSimpleName();

    //Declare Firebase objects
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDepositQueueReference;
    private ChildEventListener mChildEventListener;

    //Declare Realm object
    private Realm realm;

    //Declare ListView and Adapter objects
    private QueueAdapter postAdapter;
    private ListView queueListView;
    private View loadingIndicator;


    public DepositQueueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.queue_list, container, false);
        //Instantiate Firebase objects
        mDatabase = FirebaseDatabase.getInstance(); //instance of Firebase object(i.e. database root object)
        mDepositQueueReference = mDatabase.getReference("depositQueue"); //reference to depositQueue child object in root object

        //Instantiate other variables
        queueListView = rootView.findViewById(R.id.list);
        loadingIndicator = rootView.findViewById(R.id.loading_indicator);
        final List<Payment> paymentsList = new ArrayList<Payment>();
        postAdapter = new QueueAdapter(getActivity(), R.layout.queue_view, paymentsList);
        queueListView.setAdapter(postAdapter);

        /**Delete this post adapter when you finish*/
//        postAdapter.add(new Payment("James Blunt", "0149603509", "4500", "WAEC", "07033513241", "igwenus619@gmail.com"));


        //Add ChildEventListener, so that changes made in the database are reflected
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    loadingIndicator.setVisibility(View.GONE);
                    Payment payment = dataSnapshot.getValue(Payment.class);
                    /**Uncomment this when you've finished*/

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
                Log.e(TAG, "postComments:onCancelled", databaseError.toException());

            }


        };
        mDepositQueueReference.addChildEventListener(mChildEventListener);


        queueListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Payment payment = paymentsList.get(position);

                Intent intent = new Intent(getActivity(), ConfirmDetailsActivity.class);
                intent.putExtra("pushID", payment.getPushID());
                intent.putExtra("accountName", payment.getAccountName());
                intent.putExtra("accountNumber", payment.getAccountNumber());
                intent.putExtra("depositAmount", payment.getDepositAmount());
                intent.putExtra("depositorName", payment.getDepositorName());
                intent.putExtra("depositorPhoneNumber", payment.getDepositorPhoneNumber());
                intent.putExtra("depositorEmail", payment.getDepositorEmail());

                Log.e(TAG, "Phone Number" + payment.depositorPhoneNumber);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
