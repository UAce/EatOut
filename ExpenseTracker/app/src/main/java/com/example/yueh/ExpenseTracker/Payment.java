package com.example.yueh.ExpenseTracker;

/**
 * Created by yu-yu on 2017-09-29.
 */

//Payment Model
public class Payment {
    private String name;
    private double amount;
    private String restaurant;

    public Payment(String name, double amt, String res){
        this.name=name;
        this.amount=amt;
        this.restaurant=res;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amt){
        this.amount=amt;
    }

    public String getRestaurant(){
        return restaurant;
    }

    public void setRestaurant(String res){
        this.restaurant=res;
    }

}
