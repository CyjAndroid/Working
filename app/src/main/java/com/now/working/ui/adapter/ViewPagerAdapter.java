package com.now.working.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyj on 17/12/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> tabList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        tabList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return tabList.get(position);
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "新闻";
        } else if (position == 1) {
            return "影视";
        }
        return "";
    }
}
