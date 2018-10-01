package com.example.android.cashier.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.cashier.R;
import com.example.android.cashier.models.Payment;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class QueueAdapter extends ArrayAdapter<Payment> {
    private static final String LOG_TAG = QueueAdapter.class.getSimpleName();
    public QueueAdapter(Context context, int resource, List<Payment> payments){
        super(context, 0, payments);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.queue_view, parent, false);
        }
        //get current item
        Payment currentPayment = getItem(position);

        TextView accountView = convertView.findViewById(R.id.account_name_text);
        TextView accountNumberView = convertView.findViewById(R.id.account_number_text);
        TextView depositAmountView = convertView.findViewById(R.id.deposit_amount_text);


        accountView.setText(currentPayment.getAccountName());
        accountNumberView.setText(currentPayment.getAccountNumber());

        String depositAmount = currentPayment.getDepositAmount();
        String formattedDepositAmount = formatDepositAmount(depositAmount);
        depositAmountView.setText(formattedDepositAmount);

        return convertView;
    }

    public String formatDepositAmount(String depositAmount){

        Locale nigerianLocale = new Locale("en", "NG");
        int amount = Integer.valueOf(depositAmount);
        Log.e(LOG_TAG, "Integer value:  " + amount);
        NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(nigerianLocale);
        return (numberFormatter.format(amount));
    }
}
