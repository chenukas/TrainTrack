package com.mad.traintrack;

public class Route {
    private String routeId;
    private String trainNo;
    private String from;
    private String startTime;
    private String to;
    private String endTime;
    private String type;
    private String firstClass;
    private String secondClass;
    private String thirdClass;

    public Route() {
    }

    public Route(String routeId, String trainNo, String from, String startTime, String to, String endTime, String type, String firstClass, String secondClass, String thirdClass) {
        this.routeId = routeId;
        this.trainNo = trainNo;
        this.from = from;
        this.startTime = startTime;
        this.to = to;
        this.endTime = endTime;
        this.type = type;
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.thirdClass = thirdClass;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getFrom() {
        return from;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getTo() {
        return to;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getType() {
        return type;
    }

    public String getFirstClass() {
        return firstClass;
    }

    public String getSecondClass() {
        return secondClass;
    }

    public String getThirdClass() {
        return thirdClass;
    }
}
