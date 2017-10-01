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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ListAdapter, PaymentModel.onPaymentAddedListener {

    TabLayout MyTabs;
    ViewPager MyPage;

    //View
    ListView _paymentListView = null;

    //Data model
    PaymentModel _paymentModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


/*
        //Create data Model
        _paymentModel = new PaymentModel();
        Payment p1 = new Payment("Jennie", 22, "Momo");
        Payment p2 = new Payment("Yueh", 16.5, "A&W");
        Payment p3 = new Payment("Jennie", 9.75, "Mcdonald's");
        _paymentModel.addPayment(p1);
        _paymentModel.addPayment(p2);
        _paymentModel.addPayment(p3);

        //Create view hierarchy
        _paymentListView = new ListView(this);
        _paymentListView.setAdapter(this);
        //setContentView(_paymentListView);
        _paymentModel.setOnPaymentAddedListener(this);
*/

        setContentView(R.layout.activity_main);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); */

        //Hook ViewPager up to pagerAdapter
        MyPage = (ViewPager) findViewById(R.id.MyPage);
        PagerAdapter pagerAdapter =
                new MyViewPageAdapter(getSupportFragmentManager());
        MyPage.setAdapter(pagerAdapter);

        //Hook up TabLayout
        MyTabs = (TabLayout) findViewById(R.id.MyTabs);
        MyTabs.setupWithViewPager(MyPage);

    }

    public class MyViewPageAdapter extends FragmentPagerAdapter{
        public MyViewPageAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new Pay_Logs();
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

    }

    // Android.widget.listAdapter methods
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

