package com.example.yueh.ExpenseTracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yu-yu on 2017-09-30.
 */

public class Pay_Logs extends Fragment {

    //Constructor
    public Pay_Logs(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View pay_logs = inflater.inflate(R.layout.Pay_Logs, container, false);

        return pay_logs;
    }

}
