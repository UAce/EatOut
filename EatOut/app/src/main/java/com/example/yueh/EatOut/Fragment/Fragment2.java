package com.example.yueh.EatOut.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yueh.EatOut.R;

/**
 * Created by yu-yu on 2017-09-30.
 */
//Fragment 2 -> frag2.xmll
public class Fragment2 extends Fragment {
    //Id to identify Fragment
    int fragmentId;

    //Constructor
    public Fragment2(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View frag2View = inflater.inflate(R.layout.frag2, container, false);

        return frag2View;
    }

}
