package com.example.yueh.ExpenseTracker;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout MyTabs;
    ViewPager MyPage;
    Toolbar MyToolbar;

    private PayLogs fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(MyToolbar);

        //return button!
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Hook ViewPager up to pagerAdapter
        MyPage = (ViewPager) findViewById(R.id.MyPage);
        setupViewPager(MyPage);
        // TEMPORARY SOLUTION TO AVOID SAVING STATES
        MyPage.setOffscreenPageLimit(3);

        //Hook up TabLayout
        MyTabs = (TabLayout) findViewById(R.id.MyTabs);
        MyTabs.setupWithViewPager(MyPage);
        setupTabIcons();
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.ic_tab_favourite,
                R.drawable.ic_tab_call,
                R.drawable.ic_tab_contacts
        };

        MyTabs.getTabAt(0).setIcon(tabIcons[0]);
        MyTabs.getTabAt(1).setIcon(tabIcons[1]);
        MyTabs.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        MyViewPageAdapter adapter = new MyViewPageAdapter(getSupportFragmentManager());
        adapter.addFrag(new PayLogs(), "ONE");
        adapter.addFrag(new Fragment2(), "TWO");
        adapter.addFrag(new Fragment3(), "THREE");
        viewPager.setAdapter(adapter);
    }

    // ViewPagerAdapter
    public class MyViewPageAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public MyViewPageAdapter(FragmentManager manager) {
            super(manager);
        }

//        // Array of fragments to cache?
//        private final SparseArray<Fragment> registeredFragments = new SparseArray<>();

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            // returnm mFragmentTitleList.get(position) for pageTitle
            return null;
        }
    }
}

