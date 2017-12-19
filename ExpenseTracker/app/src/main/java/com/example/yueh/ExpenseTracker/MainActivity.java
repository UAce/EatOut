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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ListAdapter, PaymentList.onPaymentAddedListener {

    TabLayout MyTabs;
    ViewPager MyPage;
    private PayLogs fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    //View
    ListView _paymentListView = null;

    //Data model
    PaymentList _paymentModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Hook ViewPager up to pagerAdapter
        MyPage = (ViewPager) findViewById(R.id.MyPage);

        // TEMPORARY SOLUTION TO AVOID SAVING STATES
        MyPage.setOffscreenPageLimit(3);
        PagerAdapter pagerAdapter = new MyViewPageAdapter(getSupportFragmentManager());
        MyPage.setAdapter(pagerAdapter);

        //Hook up TabLayout
        MyTabs = (TabLayout) findViewById(R.id.MyTabs);
        MyTabs.setupWithViewPager(MyPage);
    }

    //
    // ViewPagerAdapter
    //
    public class MyViewPageAdapter extends FragmentPagerAdapter{
        public MyViewPageAdapter(FragmentManager fm){
            super(fm);
        }

        // Array of fragments to cache?
        private final SparseArray<Fragment> registeredFragments = new SparseArray<>();

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new PayLogs();
                case 1:
                    return new Fragment2();
                case 2:
                    return new Fragment3();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch(position) {
                case 0:
                    return getString(R.string.Fragment1_title);
                case 1:
                    return getString(R.string.Fragment2_title);
                case 2:
                    return getString(R.string.Fragment3_title);
                default:
                    return null;
            }
        }

        // Safely save a reference to the created Fragment,
        // no matter where it came from (either getItem() or
        // FragmentManger). Simply save the returned Fragment from
        // super.instantiateItem() into an appropriate reference depending
        // on the ViewPager position.
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
            // save the appropriate reference depending on position
            switch (position) {
                case 0:
                    fragment1 = (PayLogs) createdFragment;
                    break;
                case 1:
                    fragment2 = (Fragment2) createdFragment;
                    break;
                case 2:
                    fragment3 = (Fragment3) createdFragment;
                    break;
            }
            return createdFragment;
        }

        // Possibly useful methods for caching fragments??
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        public SparseArray<Fragment> getRegisteredFragments() {
            return registeredFragments;
        }
    }


    //
    // Android.widget.listAdapter methods
    //
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCount() {
        return _paymentModel.getCount();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return _paymentModel.getPayment(i);
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView messageView = new TextView(this);
        messageView.setText(_paymentModel.getPayment(i).getName());
//        messageView.setText(Double.toString(_paymentModel.getPayment(i).getAmount()));
//        messageView.setText(_paymentModel.getPayment(i).getRestaurant());

        return messageView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    // Implementation of onPaymentAddedListener methods
    @Override
    public void onPaymentAdded(Payment[] payments) {
        _paymentListView.invalidateViews();
    }

}

