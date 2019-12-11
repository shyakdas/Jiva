package com.jiva.com.mutipleviewholder.listener;

import com.jiva.com.mutipleviewholder.model.EventModel;

import java.util.ArrayList;

public interface EventDetailsListener {

    void openDetail(int id, ArrayList<EventModel> modelArrayList);

    void categoryDetails(String name);
}