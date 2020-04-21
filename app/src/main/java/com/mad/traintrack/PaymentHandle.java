package com.mad.traintrack;

public class PaymentHandle {

    private Double cardNo;
    private Integer cvv;
    private String date;
    private String name;

    public Double getCardNo() {
        return cardNo;
    }

    public void setCardNo(Double cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
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
