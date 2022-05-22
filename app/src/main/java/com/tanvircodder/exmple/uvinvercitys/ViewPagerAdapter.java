package com.tanvircodder.exmple.uvinvercitys;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DefaultFragment();
            case 1:
                return new SearchFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
