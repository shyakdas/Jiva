package com.jiva.com.jivaassigment.model;

public class EventModel {

    public String type;
    public String mainEventName;
    public String categoryImage;
    public String categoryName;
    public String eventName;
    public String eventImage;

    public EventModel(String type, String mainEventName, String categoryImage, String categoryName, String eventName, String eventImage) {
        this.type = type;
        this.mainEventName = mainEventName;
        this.categoryImage = categoryImage;
        this.categoryName = categoryName;
        this.eventName = eventName;
        this.eventImage = eventImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMainEventName() {
        return mainEventName;
    }

    public void setMainEventName(String mainEventName) {
        this.mainEventName = mainEventName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}