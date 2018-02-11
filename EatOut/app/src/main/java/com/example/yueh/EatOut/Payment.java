package com.example.yueh.EatOut;

/**
 * Created by yu-yu on 2017-09-29.
 */

//Payment Model
public class Payment {
    private String name;
    private double amount;
    private String restaurant;
    private String comment;

    public Payment(String name, double amt, String res, String com){
        this.name=name;
        this.amount=amt;
        this.restaurant=res;
        this.comment=com;
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

    public String getComment(){
        return comment;
    }

    public void setComment(String com){
        this.comment=com;
    }
}
