package com.example.android.cashier.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.cashier.R;

import java.util.List;

public class RealmQueueAdapter extends ArrayAdapter<RealmPayment> {
    public RealmQueueAdapter(Context context, int resource, List<RealmPayment> payments){
        super(context, 0, payments);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.queue_view, parent, false);
        }
        //get current item
        RealmPayment currentRealmPayment = getItem(position);

        TextView accountView = convertView.findViewById(R.id.account_name_text);
        TextView accountNumberView = convertView.findViewById(R.id.account_number_text);
        TextView depositAmountView = convertView.findViewById(R.id.deposit_amount_text);


        accountView.setText(currentRealmPayment.getAccountName());
        accountNumberView.setText(currentRealmPayment.getAccountNumber());

        String depositAmount = currentRealmPayment.getDepositAmount();
        String formattedDepositAmount = formatDepositAmount(depositAmount);
        depositAmountView.setText(formattedDepositAmount);

        return convertView;
    }

    public String formatDepositAmount(String depositAmount){
        return ("N" + depositAmount);
    }
}
