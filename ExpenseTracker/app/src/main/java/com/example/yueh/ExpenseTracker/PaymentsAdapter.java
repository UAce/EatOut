package com.example.yueh.ExpenseTracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yu-yu on 2017-10-18.
 */
public class PaymentsAdapter extends ArrayAdapter<Payment> {
    //Position of List element
    private int position;

    public PaymentsAdapter(Context context, ArrayList<Payment> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerView.ViewHolder holder = null;

        // Get the data item for this position
        Payment payment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_payment, parent, false);
        }
        // Lookup view for data population
        TextView pName = (TextView) convertView.findViewById(R.id.pName);
        TextView pRes = (TextView) convertView.findViewById(R.id.pRes);
        TextView pAmt = (TextView) convertView.findViewById(R.id.pAmt);
        TextView pCmt = (TextView) convertView.findViewById(R.id.pCmt);
        // Populate the data into the template view using the data object
        pName.setText("Paid: "+payment.getName());
        pRes.setText(payment.getRestaurant());
        pAmt.setText(Double.toString(payment.getAmount())+" $");
        pCmt.setText(payment.getComment());


        // Return the completed view to render on screen
        return convertView;
    }

    public void selectedItem(int position)
    {
        this.position = position; //position must be a global variable
    }
}