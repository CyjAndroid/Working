package com.now.working.ui.activity;


import android.content.ComponentName;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.android.cyj.router.RouterConfig;
import com.now.plugin.PluginHelper;
import com.now.working.R;
import com.now.working.ui.adapter.ViewPagerAdapter;
import com.now.working.ui.base.BaseActivity;
import com.now.working.ui.fragment.NewsFragment;
import com.now.working.ui.fragment.TestFragment2;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import com.android.annotation.Dispatcher;


public class MainActivity extends BaseActivity {

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private ViewPager mViewPager;
    private List<Fragment> mTabList = new ArrayList<>();
    private TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RouterConfig.open("cyj://test");

        initViewPager();
        initTabLayout();

    }

    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initViewPager() {
        mTabList.add(new NewsFragment());
        mTabList.add(new TestFragment2());

        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        ViewPagerAdapter mPagerAdapter = new ViewPagerAdapter(mFragmentManager, mTabList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

}
