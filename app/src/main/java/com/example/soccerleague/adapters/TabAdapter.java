package com.example.soccerleague.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.soccerleague.ui.fragments.FixtureFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public TabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return FixtureFragment.addfrag(position);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
