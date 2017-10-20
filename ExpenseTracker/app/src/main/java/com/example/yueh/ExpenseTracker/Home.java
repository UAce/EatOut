package com.example.yueh.ExpenseTracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yu-yu on 2017-09-30.
 */
//Fragment 2 -> home.xml
public class Home extends Fragment {

    //Constructor
    public Home(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View homeView = inflater.inflate(R.layout.home, container, false);

        return homeView;
    }

}
