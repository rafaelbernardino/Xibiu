package com.rb.xibiu.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rb.xibiu.fragments.CarsFragment;

/**
 * Created by rm31243 on 19/11/2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    public static final int TOTAL_TABS = 2;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        bundle.putString("tipo", position == 0 ? "classicos" : "esportivos");

        Fragment fragment = new CarsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }
}
