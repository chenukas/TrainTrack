package com.mad.traintrack;


public class Reminder {

    private String remindID;
    private String name;
    private String content;
    private String from;
    private String where;
    private String date;

    public Reminder() {
    }

    public Reminder(String remindID,String name, String content, String from, String where, String date) {
        this.remindID = remindID;
        this.name = name;
        this.content = content;
        this.from = from;
        this.where = where;
        this.date = date;
    }

    public String getRemindID() {
        return remindID;
    }

    public void setRemindID(String remindID) {
        this.remindID = remindID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
