package com.example.android.cashier;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.cashier.models.Payment;
import com.example.android.cashier.models.RealmPayment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

public class DetailsActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mPostReference;

    private Realm realm;
    private RealmAsyncTask realmAsyncTask;

    final String TAG = DetailsActivity.class.getSimpleName();
    public static List<RealmPayment> paymentList = new ArrayList<>();
    private TextView accountNameText;
    private TextView accountNumberText;
    private TextView depositAmountText;
    private TextView depositorNameText;
    private TextView depositorPhoneNumberText;
    private TextView depositorEmailText;
    private FloatingActionButton postPaymentButton;
    private FloatingActionButton confirmPaymentButton;


    String pushID;
    String accountName;
    String accountNumber;
    String depositAmount;
    String depositorName;
    String depositorPhoneNumber;
    String depositorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        realm = Realm.getDefaultInstance();

        accountNameText = findViewById(R.id.account_name_details);
        accountNumberText = findViewById(R.id.account_number_details);
        depositAmountText = findViewById(R.id.deposit_amount_details);
        depositorNameText = findViewById(R.id.depositor_name_details);
        depositorPhoneNumberText = findViewById(R.id.depositor_phone_details);
        depositorEmailText = findViewById(R.id.depositor_email_details);
        postPaymentButton = findViewById(R.id.post_button_details);
        confirmPaymentButton = findViewById(R.id.confirm_button_details);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            pushID = bundle.getString("pushID");
            accountName = bundle.getString("accountName");
            accountNumber = bundle.getString("accountNumber");
            depositAmount = bundle.getString("depositAmount");
            depositorName = bundle.getString("depositorName");
            depositorPhoneNumber = bundle.getString("depositorPhoneNumber");
            depositorEmail = bundle.getString("depositorEmail");
        }

        Log.e(TAG, "Phone number is: " + depositorPhoneNumber);

        accountNameText.setText(accountName);
        accountNumberText.setText(accountNumber);
        depositAmountText.setText(depositAmount);
        depositorNameText.setText(depositorName);
        depositorPhoneNumberText.setText(depositorPhoneNumber);
        depositorEmailText.setText(depositorEmail);


        postPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
                Payment post = new Payment(pushID, accountName, accountNumber, depositAmount, depositorName, depositorPhoneNumber, depositorEmail);
                mDatabaseReference.child("postedDeposits").push().setValue(post)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Write was successful!
                                Toast.makeText(DetailsActivity.this, "Payment is successful", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                                Toast.makeText(DetailsActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

        confirmPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmPaymentButton.setEnabled(false);
                saveToDatabase();
                viewDatabase();
                Intent mainActivityIntent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        //This actually adds items to the action bar if present
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_delete:
                //Fill out some code here
                mDatabaseReference.child("depositQueue").child(pushID).removeValue();

                Intent mainActivityIntent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);

                return true;
            case R.id.action_print:
                //Fill out some code here
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void saveToDatabase() {
        final RealmPayment data = new RealmPayment(accountName, accountNumber, depositAmount, depositorName, depositorPhoneNumber, depositorEmail);
//        RealmPayment data = bgRealm.createObject(RealmPayment.class);
//        data.setAccountName(accountName);
//        data.setAccountNumber(accountNumber);
//        data.setDepositAmount(depositAmount);
//        data.setDepositorName(depositorName);
//        data.setDepositorPhoneNumber(depositorPhoneNumber);
//        data.setDepositorEmail(depositorEmail);

        realmAsyncTask = realm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        RealmPayment payment = realm.copyToRealm(data);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        // Transaction was a success.
                        Log.e(TAG, "Save successful");

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        // Transaction failed and was automatically canceled.
                        Log.e(TAG, "Save failed");
                    }
                });

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
    protected void onStop() {
        super.onStop();

        // If transaction exists, and has not been cancelled, cancel task
        if (realmAsyncTask != null && !realmAsyncTask.isCancelled()) {
            realmAsyncTask.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
