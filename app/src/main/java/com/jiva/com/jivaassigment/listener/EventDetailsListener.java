package com.jiva.com.jivaassigment.listener;

import com.jiva.com.jivaassigment.model.EventModel;

import java.util.ArrayList;

public interface EventDetailsListener {

    void openDetail(int id, ArrayList<EventModel> modelArrayList);
}