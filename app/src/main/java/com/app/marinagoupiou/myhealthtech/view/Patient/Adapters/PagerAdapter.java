package com.app.marinagoupiou.myhealthtech.view.Patient.Adapters;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.marinagoupiou.myhealthtech.view.Patient.NestedFragments.*;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MyMeds myMeds_fragment = new MyMeds();
                return myMeds_fragment;
            case 1:
                PendingOrders pendingOrders_fragment = new PendingOrders();
                return pendingOrders_fragment;
            case 2:
                ConfirmedOrders confirmedOrders_fragment = new ConfirmedOrders();
                return confirmedOrders_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
