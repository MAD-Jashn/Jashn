package com.example.puneet.jashn;

public class Event {

    private String eventID;
    private String eventName;
    private String date;
    private String time;
    private String Location;
    private String Category;
    private String MinAge;
    private String EntryFee;
    private int Color;
    private String Host;


    public Event(String eventID, String eventName, String date, String time, String location, String category, String minAge, String entryFee, int color, String host) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.Location = location;
        this.Category = category;
        this.MinAge = minAge;
        this.EntryFee = entryFee;
        this.Color = color;
        this.Host = host;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getMinAge() {
        return MinAge;
    }

    public void setMinAge(String minAge) {
        MinAge = minAge;
    }

    public String getEntryFee() {
        return EntryFee;
    }

    public void setEntryFee(String entryFee) {
        EntryFee = entryFee;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

}
