package com.example.yueh.ExpenseTracker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by yu-yu on 2017-09-30.
 */
//Fragment 1 -> paylogs.xml
public class PayLogs extends Fragment {
    //Id to identify Fragment
    int fragmentId;

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
        final PaymentsAdapter adapter = new PaymentsAdapter(getActivity(), arrayOfpayments);

        // Attach the adapter to a ListView
        ListView listView = (ListView) payLogsView.findViewById(R.id.lvItems);
        listView.setEmptyView( payLogsView.findViewById( R.id.empty) );
        listView.setAdapter(adapter);

        // Add Floating Action button
        FloatingActionButton myFab = (FloatingActionButton) payLogsView.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Get the layout inflater
                LayoutInflater inflater = getActivity().getLayoutInflater();

                // Set custom Title view for dialog title bar
                View customTitleView = inflater.inflate(R.layout.dialog_title_layout, null);
                TextView title = (TextView) customTitleView.findViewById(R.id.title);
                title.setText("New Expense");
                builder.setCustomTitle(customTitleView);

                // Inflate and set the layout for the dialog_border
                // Pass null as the parent view because its going in the dialog_border layout
                builder.setView(inflater.inflate(R.layout.dialog_add_item, null))
                        // Add action buttons
                        .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Dialog dialogObj = Dialog.class.cast(dialog);
                                // Add Payment to ListView ...
                                final EditText resto = (EditText) dialogObj.findViewById(R.id.resto);
                                final EditText cmt = (EditText) dialogObj.findViewById(R.id.Cmt);
                                final EditText buyer = (EditText) dialogObj.findViewById(R.id.buyer);
                                final EditText amt = (EditText) dialogObj.findViewById(R.id.amt);
                                Payment newP = new Payment(buyer.getText().toString(), Double.parseDouble(amt.getText().toString()), resto.getText().toString(),cmt.getText().toString() );
                                adapter.add(newP);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });

        return payLogsView;
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(outState);
//
//    }

//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mylog);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.Planets, android.R.layout.simple_list_item_1);
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
//    }


//    class PasswordAdapter extends ArrayAdapter {
//
//        public PasswordAdapter(Context context, int resource, String[] objects) {
//            super(context, resource, objects);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View v=((Activity)getContext()).getLayoutInflater().inflate(R.layout.passwordlay,null);
//            TextView txt1 = (TextView) v.findViewById(R.id.textViewpasslay);
//            txt1.setText(name[position]);
//            ImageView img = (ImageView) v.findViewById(R.id.imageViewpasslay);
//            img.setBackgroundResource(image[position]);
//
//            return v;
//        }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//                "Linux", "OS/2" };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, new String[]{"hello"});
//        setListAdapter(adapter);
//    }

}


