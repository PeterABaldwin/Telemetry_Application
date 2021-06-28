package com.group_project_1_x;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 10/15/2019.
 */

public class AstronautFragmentManager extends FragmentStatePagerAdapter {
    public AstronautFragmentManager(FragmentManager fm) {
        super(fm);
    }

    private final List<Fragment> myFragments = new ArrayList<>();

    public void addFragment(Fragment newFragment) {
        myFragments.add(newFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }
}
