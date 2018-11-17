package com.jiva.com.jivaassigment.event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiva.com.jivaassigment.model.EventModel;
import com.jiva.com.jivaassigment.R;

import java.util.ArrayList;

public class EventFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private EventAdapter eventAdapter;
    private ArrayList<EventModel> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventAdapter = new EventAdapter(getActivity(), getListData());
        mRecyclerView.setAdapter(eventAdapter);
        return view;
    }

    public static EventFragment newInstance() {
        Bundle args = new Bundle();
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ArrayList<EventModel> getListData() {
        mList = new ArrayList<>();
        mList.add(new EventModel("main", "Near you", "", "", "", ""));

        mList.add(new EventModel("category", "Near you",
                getResources().getResourceName(R.mipmap.demo), "Sports", "", ""));
        mList.add(new EventModel("main", "For you", "", "", "", ""));


        mList.add(new EventModel("event", "", "", "",
                "Ladies Night", getResources().getResourceName(R.mipmap.demo)));
        mList.add(new EventModel("event", "", "", "",
                "Airplane Joyride", getResources().getResourceName(R.mipmap.demo)));

        return mList;
    }
}
