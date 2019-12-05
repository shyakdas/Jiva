package com.jiva.com.jivaassigment.utils;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jiva.com.jivaassigment.event.EventFragment;
import com.jiva.com.jivaassigment.favorite.FavoriteFragment;
import com.jiva.com.jivaassigment.profile.ProfileFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"follow", "explore", "Nearby"};
    private Context mContext;

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {

            case 0:
                return new EventFragment().newInstance();

            case 1:
                return new FavoriteFragment().newInstance();

            case 2:
                return new ProfileFragment().newInstance();

            default:
                return new EventFragment().newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}