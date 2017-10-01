package com.example.yueh.ExpenseTracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yu-yu on 2017-09-30.
 */

public class Fragment2 extends Fragment {

    //Constructor
    public Fragment2(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View Fragment2 = inflater.inflate(R.layout.frag2, container, false);

        return Fragment2;
    }

}
