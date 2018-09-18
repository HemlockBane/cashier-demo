package com.example.android.cashier;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class PostDetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = PostDetailsActivity.class.getSimpleName();
    private DatabaseReference mPostReference;

    private Button postPaymentButton;

    private TextView accountNameText;
    private TextView accountNumberText;
    private TextView depositAmountText;
    private TextView depositorNameText;
    private TextView depositorPhoneNumberText;
    private TextView depositorEmailText;

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
        setContentView(R.layout.activity_post_details);

        postPaymentButton = findViewById(R.id.bt_post_post_details);

        accountNameText = findViewById(R.id.tv_account_name_post_details);
        accountNumberText = findViewById(R.id.tv_account_number_post_details);
        depositAmountText = findViewById(R.id.tv_deposit_amount_post_details);
        depositorNameText = findViewById(R.id.tv_depositor_name_post_details);
        depositAmountText = findViewById(R.id.tv_deposit_amount_post_details);
        depositorPhoneNumberText = findViewById(R.id.tv_depositor_phone_post_details);
        depositorEmailText = findViewById(R.id.tv_depositor_email_post_details);

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
            Log.e(LOG_TAG, "Email: " + depositorEmail);
        }


        accountNameText.setText(accountName);
        accountNumberText.setText(accountNumber);
        depositAmountText.setText(depositAmount);
        depositorNameText.setText(depositorName);
        depositorPhoneNumberText.setText(depositorPhoneNumber);
        depositorEmailText.setText(depositorEmail);


//        postPaymentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainActivityIntent = new Intent(ConfirmDetailsActivity.this, MainActivity.class);
//                startActivity(mainActivityIntent);
//                Payment post = new Payment(pushID, accountName, accountNumber, depositAmount, depositorName, depositorPhoneNumber, depositorEmail);
//                mDatabaseReference.child("postedDeposits").push().setValue(post)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                // Write was successful!
//                                Toast.makeText(ConfirmDetailsActivity.this, "Payment is successful", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Write failed
//                                Toast.makeText(ConfirmDetailsActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//            }
//        });


    }
}
