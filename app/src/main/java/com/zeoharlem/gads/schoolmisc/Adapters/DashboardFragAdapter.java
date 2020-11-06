package com.zeoharlem.gads.schoolmisc.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragAdapter extends FragmentPagerAdapter {
    ArrayList<String> mStringArrayList  = new ArrayList<>();
    List<Fragment> mFragmentList        = new ArrayList<>();

    public DashboardFragAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mStringArrayList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        mStringArrayList.add(title);
        mFragmentList.add(fragment);
    }
}
