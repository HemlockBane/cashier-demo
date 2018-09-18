package com.example.android.cashier;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

public class ConfirmDetailsActivity extends AppCompatActivity {

    final String LOG_TAG = ConfirmDetailsActivity.class.getSimpleName();

    public static final int REQUEST_PERM_WRITE_STORAGE = 102;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    Realm realm;
    private RealmAsyncTask realmAsyncTask;

    final String TAG = ConfirmDetailsActivity.class.getSimpleName();
    public static List<RealmPayment> paymentList = new ArrayList<>();
    private TextView accountNameText;
    private TextView accountNumberText;
    private TextView depositAmountText;
    private TextView depositorNameText;
    private TextView depositorPhoneNumberText;
    private TextView depositorEmailText;

    private Button confirmPaymentButton;


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
        setContentView(R.layout.activity_confirm_details);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        realm = Realm.getDefaultInstance();

        accountNameText = findViewById(R.id.account_name_details);
        accountNumberText = findViewById(R.id.account_number_details);
        depositAmountText = findViewById(R.id.deposit_amount_details);
        depositorNameText = findViewById(R.id.depositor_name_details);
        depositorPhoneNumberText = findViewById(R.id.depositor_phone_details);
        depositorEmailText = findViewById(R.id.depositor_email_details);

        confirmPaymentButton = findViewById(R.id.bt_confirm_details);


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
            Log.e(LOG_TAG, "Email is : " + depositorEmail);
        }

        Log.e(TAG, "Phone number is: " + depositorPhoneNumber);

        accountNameText.setText(accountName);
        accountNumberText.setText(accountNumber);
        depositAmountText.setText(depositAmount);
        depositorNameText.setText(depositorName);
        depositorPhoneNumberText.setText(depositorPhoneNumber);
        depositorEmailText.setText(depositorEmail);


        confirmPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmPaymentButton.setEnabled(false);

                // Save data to realm database
                saveToDatabase();

                sendToPostActivity();

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

                Intent mainActivityIntent = new Intent(ConfirmDetailsActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);

                return true;
            case R.id.action_print:
                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ConfirmDetailsActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERM_WRITE_STORAGE);
                } else {
                    createPdf();
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void createPdf() {
        Document document = new Document();

        String path = Environment.getExternalStorageDirectory() + "/Cashier_Pdf";

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        Log.e(LOG_TAG, "PDF path : " + path);

        String ID = UUID.randomUUID().toString();

        File file = new File(dir, ID + ".pdf");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            PdfWriter.getInstance(document, fileOutputStream);
            document.open();


            document.add(new Paragraph(accountName));
            document.add(new Paragraph(accountNumber));
            document.add(new Paragraph(depositAmount));
            document.add(new Paragraph(depositorName));
            document.add(new Paragraph(depositorPhoneNumber));
            document.add(new Paragraph(depositorEmail));


            Toast.makeText(this, "Pdf created", Toast.LENGTH_SHORT).show();
            document.close();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
//        finally {
//
//        }
    }

    public void sendToPostActivity() {
        Intent intent = new Intent(ConfirmDetailsActivity.this, PostDetailsActivity.class);
        intent.putExtra("pushID", pushID);
        intent.putExtra("accountName", accountName);
        intent.putExtra("accountNumber", accountNumber);
        intent.putExtra("depositAmount", depositAmount);
        intent.putExtra("depositorName", depositorName);
        intent.putExtra("depositorPhoneNumber", depositorPhoneNumber);
        intent.putExtra("depositorEmail", depositorEmail);
        startActivity(intent);
    }

    public void saveToDatabase() {
        final RealmPayment data = new RealmPayment(
                accountName,
                accountNumber,
                depositAmount,
                depositorName,
                depositorPhoneNumber,
                depositorEmail);

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
