package com.mad.traintrack;

public class PaymentHandle {

    private Float cardNo;
    private Float cvv;
    private String date;
    private String name;
    private  Float amount;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getCardNo() {
        return cardNo;
    }

    public void setCardNo(Float cardNo) {
        this.cardNo = cardNo;
    }

    public Float getCvv() {
        return cvv;
    }

    public void setCvv(Float cvv) {
        this.cvv = cvv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
