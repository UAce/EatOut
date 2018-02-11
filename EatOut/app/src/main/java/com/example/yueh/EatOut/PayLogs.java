package com.example.yueh.EatOut;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by yu-yu on 2017-09-30.
 */
//Fragment 1 -> paylogs.xml
public class PayLogs extends Fragment {
    //Id to identify Fragment
    int fragmentId;
    final String dialogName = "New Expenses";
    PaymentsAdapter adapter;
    Toast toast;

    Dialog dialog;
    ArrayList<Payment> arrayOfpayments;

    //Constructor
    public PayLogs(){};

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        //Inflate the layout for this fragment
        //Layout contains my view list
        final View payLogsView = inflater.inflate(R.layout.paylogs, container, false);

        // Construct the data source
        arrayOfpayments = new ArrayList<Payment>();

        // Create the adapter to convert the array to views
        adapter = new PaymentsAdapter(getActivity(), arrayOfpayments);

        // Attach the adapter to a ListView
        ListView listView = (ListView) payLogsView.findViewById(R.id.lvItems);
        listView.setClickable(true);
        listView.setEmptyView( payLogsView.findViewById( R.id.empty) );
        listView.setAdapter(adapter);

        // Add Floating Action button
        FloatingActionButton myFab = (FloatingActionButton) payLogsView.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            //On click, create dialog builder
            public void onClick(View v) {
            addItem();
            }
        });

        // Edit Item if clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked perform some action...
                Payment currentP = arrayOfpayments.get(position);
                updateItemList(currentP.getRestaurant(), currentP.getName(), Double.toString(currentP.getAmount()), currentP.getComment(), position);
//                Toast.makeText(getActivity(), "Name: "+arrayOfpayments.get(position).getRestaurant()+", Position:"+position+":"+id,
//                        Toast.LENGTH_SHORT).show();
            }
        });


        return payLogsView;
    }



    public void addItem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Set custom Title view for dialog title bar
        View customTitleView = inflater.inflate(R.layout.dialog_title_layout, null);
        TextView title = (TextView) customTitleView.findViewById(R.id.title);
        title.setText(dialogName);

        // Set view for adding item
        View itemView = inflater.inflate(R.layout.dialog_add_item, null);

        builder.setCustomTitle(customTitleView);
        builder.setPositiveButton("ADD", null);
        builder.setNegativeButton("CANCEL", null);


        // Create Layout for dialogbox items ...
        final TextInputLayout resto_layout = itemView.findViewById(R.id.text_input_layout_resto);
        final TextInputLayout cmt_layout = itemView.findViewById(R.id.text_input_layout_cmt);
        final TextInputLayout buyer_layout = itemView.findViewById(R.id.text_input_layout_buyer);
        final TextInputLayout amt_layout = itemView.findViewById(R.id.text_input_layout_amt);

        // Add Payment to ListView ...
        final EditText resto = itemView.findViewById(R.id.resto);
        final EditText cmt = itemView.findViewById(R.id.Cmt);
        final EditText buyer = itemView.findViewById(R.id.buyer);
        final EditText amt = itemView.findViewById(R.id.amt);

        // Create Toast
        Context context = getActivity();
        CharSequence success = "Payment Added!";
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, success, duration);

        // Create actual dialog
        // Keep dialog box open when textboxes are empty
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(itemView);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {

                // ADD BUTTON
                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //get value of each textbox
                        boolean resto_isEmpty = resto.getText().toString().equals("");
                        boolean buyer_isEmpty = buyer.getText().toString().equals("");
                        boolean amt_isEmpty = amt.getText().toString().equals("");
                        if (!resto_isEmpty && !buyer_isEmpty && !amt_isEmpty) {
                            // Add items to Listview Then
                            // CLOSE DIALOG
                            String buyerValue = buyer.getText().toString();
                            Double amtValue = Double.parseDouble(amt.getText().toString());
                            String restoValue = resto.getText().toString();
                            String cmtValue = cmt.getText().toString();
                            Payment newP = new Payment(buyerValue, amtValue, restoValue, cmtValue);
                            adapter.add(newP);
                            dialog.dismiss();
                            toast.show();
                        } else {
                            // DO NOT CLOSE DIALOG
                            // Show Error messages
                            if(resto_isEmpty){
                                resto.setError("Enter a restaurant name");
                            }
                            if(amt_isEmpty){
                                amt.setError("Enter the amount paid");
                            }
                            if(buyer_isEmpty){
                                buyer.setError("Enter the buyer's name");
                            }
                        }
                    }
                });

                // CANCEL BUTTON
                Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //CLOSE THE DIALOG
                        dialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void updateItemList(String oldResto, String oldName, String oldAmt, String oldCmt, int position){
        final int index = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Set custom Title view for dialog title bar
        View customTitleView = inflater.inflate(R.layout.dialog_title_layout, null);
        TextView title = (TextView) customTitleView.findViewById(R.id.title);
        title.setText("Update Expense");

        // Set view for adding item
        View itemView = inflater.inflate(R.layout.dialog_add_item, null);

        builder.setCustomTitle(customTitleView);
        builder.setPositiveButton("UPDATE", null);
        builder.setNegativeButton("CANCEL", null);

        // Create Layout for dialogbox items ...
        final TextInputLayout resto_layout = itemView.findViewById(R.id.text_input_layout_resto);
        final TextInputLayout cmt_layout = itemView.findViewById(R.id.text_input_layout_cmt);
        final TextInputLayout buyer_layout = itemView.findViewById(R.id.text_input_layout_buyer);
        final TextInputLayout amt_layout = itemView.findViewById(R.id.text_input_layout_amt);

        // Add Payment to ListView ...
        final EditText resto = itemView.findViewById(R.id.resto);
        final EditText cmt = itemView.findViewById(R.id.Cmt);
        final EditText buyer = itemView.findViewById(R.id.buyer);
        final EditText amt = itemView.findViewById(R.id.amt);
        resto.setText(oldResto);
        cmt.setText(oldCmt);
        buyer.setText(oldName);
        amt.setText(oldAmt);

        //Reinitialize Toast
        Context context = getActivity();
        CharSequence updated = "Payment Updated!";
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, updated, duration);


        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(itemView);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {

                // ADD BUTTON
                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean resto_isEmpty = resto.getText().toString().equals("");
                        boolean buyer_isEmpty = buyer.getText().toString().equals("");
                        boolean amt_isEmpty = amt.getText().toString().equals("");
                        //get value of each textbox
                        if (!resto_isEmpty && !buyer_isEmpty && !amt_isEmpty) {
                            // Add items to Listview Then
                            // CLOSE DIALOG
                            String buyerValue = buyer.getText().toString();
                            Double amtValue = Double.parseDouble(amt.getText().toString());
                            String restoValue = resto.getText().toString();
                            String cmtValue = cmt.getText().toString();
                            Payment newP = new Payment(buyerValue, amtValue, restoValue, cmtValue);
                            adapter.setMyExpense(newP, index);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                            toast.show();
                        } else {
                            // DO NOT CLOSE DIALOG
                            // Show Error messages
                            if(resto_isEmpty){
                                resto.setError("Enter a restaurant name");
                            }
                            if(amt_isEmpty){
                                amt.setError("Enter the amount paid");
                            }
                            if(buyer_isEmpty){
                                buyer.setError("Enter the buyer's name");
                            }
                        }
                    }
                });

                // CANCEL BUTTON
                Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //CLOSE THE DIALOG
                        dialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }
}


