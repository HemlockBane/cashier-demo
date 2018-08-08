package com.example.android.cashier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.cashier.models.Payment;
import com.example.android.cashier.models.adapters.QueueAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class DepositQueueActivity extends AppCompatActivity {
    final String TAG = DepositQueueActivity.class.getSimpleName();

//    //Declare Firebase objects
//    private FirebaseDatabase mDatabase;
//    private DatabaseReference mDepositQueueReference;
//    private ChildEventListener mChildEventListener;
//
//    //Declare Realm object
//    private Realm realm;
//
//    //Declare ListView and Adapter objects
//    private QueueAdapter postAdapter;
//    private ListView queueListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DepositQueueFragment())
                .commit();


//        setContentView(R.layout.queue_list);
//        //Instantiate Realm object
//        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
//
//        //Instantiate Firebase objects
//        mDatabase = FirebaseDatabase.getInstance(); //instance of Firebase object(i.e. database root object)
//        mDepositQueueReference = mDatabase.getReference("depositQueue"); //reference to depositQueue child object in root object
//        //Instantiate other variables
//        queueListView = findViewById(R.id.list);
//        final List<Payment> paymentsList = new ArrayList<Payment>();
//        postAdapter = new QueueAdapter(this, R.layout.queue_view, paymentsList);
//        queueListView.setAdapter(postAdapter);
//
//        /**Delete this post adapter when you finish*/
////        postAdapter.add(new Payment("James Blunt", "0149603509", "4500", "WAEC", "07033513241", "igwenus619@gmail.com"));
//
//
//        //Add ChildEventListener, so that changes made in the database are reflected
//        mChildEventListener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                if (dataSnapshot.exists()){
//                    Payment payment = dataSnapshot.getValue(Payment.class);
//                    /**Uncomment this when you've finished*/
//
//                    postAdapter.add(payment); //here, you could use queueListView.add() too
//
//
//                }
//
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e(TAG, "postComments:onCancelled", databaseError.toException());
//
//            }
//
//
//        };
//        mDepositQueueReference.addChildEventListener(mChildEventListener);
//
//
//        queueListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Payment payment = paymentsList.get(position);
//                Intent intent = new Intent(DepositQueueActivity.this, DetailsActivity.class);
//                intent.putExtra("accountName", payment.getAccountName());
//                intent.putExtra("accountNumber", payment.getAccountNumber());
//                intent.putExtra("depositAmount", payment.getDepositAmount());
//                intent.putExtra("depositorName", payment.getDepositorName());
//                intent.putExtra("depositorPhoneNumber", payment.getDepositorPhoneNumber());
//                intent.putExtra("depositorEmail", payment.getDepositorEmail());
//
//                Log.e(TAG, "Phone Number" + payment.depositorPhoneNumber);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //realm.close();
    }
}
