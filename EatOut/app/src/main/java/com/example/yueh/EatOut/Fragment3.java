package com.example.yueh.EatOut;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yu-yu on 2017-09-30.
 */

public class Fragment3 extends Fragment {
    //Id to identify Fragment
    int fragmentId;

    //Constructor
    public Fragment3(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View Fragment3 = inflater.inflate(R.layout.frag3, container, false);

        return Fragment3;
    }

}
