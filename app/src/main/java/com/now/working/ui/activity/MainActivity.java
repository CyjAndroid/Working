package com.now.working.ui.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.android.cyj.router.Router;
import com.cyj.libservice.WorkService;
import com.now.working.R;
import com.now.working.loader.WorkLoader;
import com.now.working.ui.adapter.ViewPagerAdapter;
import com.now.working.ui.base.BaseActivity;
import com.now.working.ui.fragment.NewsFragment;
import com.now.working.ui.fragment.TestFragment2;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private ViewPager mViewPager;
    private List<Fragment> mTabList = new ArrayList<>();
    private TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            WorkService service = WorkLoader.load(this, WorkService.class);
            service.testService();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        testRouter();
        initViewPager();
        initTabLayout();

    }

    private void testRouter() {
        //        Router.getInstance().getModuleService(BaseModuleService.ModuleInteractService.class).runModuleInteract(this);
        Router.getInstance().build("cyj://plugin").navigation();
//        Router.getInstance()
//                .build("cyj://test")
//                .withString("name", "cyj")
//                .withInt("age", 18)
//                .withParcelable("obj",new News(1L,"a","b","c"))
//                .withSerializable("obj_test",new TestBean())
//                .addInterceptor(new IInterceptor() {
//                    @Override
//                    public boolean intercept(RouterBuild build) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onIntercepted(RouterBuild build) {
//                        Toast.makeText(MainActivity.this,"拦截了",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                })
//                .navigation();
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
