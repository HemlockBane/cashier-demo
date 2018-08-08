package com.example.android.cashier.models;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.cashier.ConfirmedQueueFragment;
import com.example.android.cashier.DepositQueueFragment;
import com.example.android.cashier.PostedQueueFragment;
import com.example.android.cashier.R;



public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public CategoryAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DepositQueueFragment();
        } else if (position == 1) {
            return new PostedQueueFragment();
        }else {
            return new ConfirmedQueueFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return mContext.getString(R.string.category_deposits);
        }else if (position == 1){
            return mContext.getString(R.string.category_posts);
        }else {
            return mContext.getString(R.string.category_confirmed);
        }
    }



}
