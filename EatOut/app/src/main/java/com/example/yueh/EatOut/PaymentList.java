package com.example.yueh.EatOut;

import java.util.ArrayList;

/**
 * Created by yu-yu on 2017-09-29.
 */

public class PaymentList {
    public interface onPaymentAddedListener{
        //public void onPaymentAdded(PaymentList model, Payment[] payments)
        public void onPaymentAdded(Payment[] payments);
    }

    ArrayList<Payment> payments = null;
    onPaymentAddedListener _paymentsAddedListener = null;

    public PaymentList(){
        //TODO: make payment persistent..
        payments = new ArrayList<Payment>();

        //Schedule Polling of payment
    }

    public ArrayList<Payment> getPayments(){
        return payments;
    }

    public int getCount(){
        return payments.size();
    }

    public Payment getPayment(int index){
        return payments.get(index);
    }

    public void addPayment(Payment p){
        payments.add(p);
        if(_paymentsAddedListener!=null){
                _paymentsAddedListener.onPaymentAdded((Payment[])payments.toArray());}
    }

    public void removePayment(int index){
        payments.remove(index);
        if(_paymentsAddedListener!=null){
            _paymentsAddedListener.onPaymentAdded((Payment[])payments.toArray());}
    }

    public onPaymentAddedListener getOnPaymentAddedListener(){
        return _paymentsAddedListener;
    }

    public void setOnPaymentAddedListener(onPaymentAddedListener p){
        this._paymentsAddedListener=p;
    }

}
