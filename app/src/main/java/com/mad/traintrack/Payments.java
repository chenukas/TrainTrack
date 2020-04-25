package com.mad.traintrack;

public class Payments {

    private String tripId;
    private String ticketId;
    private double total;
    private String cardNo;



    public Payments(){}

    public Payments(String tripId, String ticketId, double total, String cardNo) {
        this.tripId = tripId;
        this.ticketId = ticketId;
        this.total = total;
        this.cardNo = cardNo;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}

