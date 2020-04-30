package com.mad.traintrack;

public class PaymentHandle {

    private String cardID;
    private String cardNo;
    private String cvv;
    private String date;
    private String name;

    public PaymentHandle() {
    }

    public PaymentHandle(String cardID,String cardNo, String cvv, String date, String name) {
        this.cardID = cardID;
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.date = date;
        this.name = name;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
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