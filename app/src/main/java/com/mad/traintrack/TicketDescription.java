package com.mad.traintrack;


public class TicketDescription {
    private String ticketId;
    private String displayName;
    private String nic;
    private String mobile;
    private String date;
    private String trainNo;
    private String startTime;
    private String from;
    private String to;
    private String classType;
    private int noOfPassengers;
    private double total;
    private String paymentStatus;
    private String purchasedBy;

    public TicketDescription() {
    }

    public TicketDescription(String ticketId, String displayName, String nic, String mobile, String date, String trainNo, String startTime, String from, String to, String classType, int noOfPassengers, double total, String paymentStatus, String purchasedBy) {
        this.ticketId = ticketId;
        this.displayName = displayName;
        this.nic = nic;
        this.mobile = mobile;
        this.date = date;
        this.trainNo = trainNo;
        this.startTime = startTime;
        this.from = from;
        this.to = to;
        this.classType = classType;
        this.noOfPassengers = noOfPassengers;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.purchasedBy = purchasedBy;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }
}
