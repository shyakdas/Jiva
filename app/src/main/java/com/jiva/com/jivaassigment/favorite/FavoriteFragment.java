package com.jiva.com.jivaassigment.favorite;

import android.content.Intent;
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
import android.widget.ImageView;

import com.jiva.com.jivaassigment.R;
import com.jiva.com.jivaassigment.eventdetail.DetailActivity;
import com.jiva.com.jivaassigment.listener.EventDetailsListener;
import com.jiva.com.jivaassigment.model.EventModel;
import com.jiva.com.jivaassigment.utils.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FavoriteFragment extends Fragment implements EventDetailsListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<EventModel> mList;
    private ImageView mSort;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mSort = view.findViewById(R.id.sort);
        mSort.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        favoriteAdapter = new FavoriteAdapter(getActivity(), getListData(), this);
        mRecyclerView.setAdapter(favoriteAdapter);
        return view;
    }

    public ArrayList<EventModel> getListData() {
        mList = new ArrayList<>();
        mList.add(new EventModel("event", "", "", "",
                "Ladies Night", getResources().getResourceName(R.mipmap.demo),
                "Ladies night party", "7th Dec",
                "₹2000", "1pm-11pm", "10 Guests", "Party"));
        mList.add(new EventModel("event", "", "", "",
                "Airplane Joyride", getResources().getResourceName(R.mipmap.demo),
                "Let's have ride", "7th Dec",
                "₹3000", "1pm-10pm", "2 Guests", "Outdoor"));
        mList.add(new EventModel("event", "", "", "",
                "Bike Ride", getResources().getResourceName(R.mipmap.demo),
                "Let's see the world", "7th Dec",
                "₹1000", "8am-11pm", "12 Guests", "Outdoor"));
        mList.add(new EventModel("event", "", "", "",
                "Long Drive", getResources().getResourceName(R.mipmap.demo),
                "Long drive with friends", "7th Dec",
                "₹1000", "6am-11pm", "15 Guest", "Outdoor"));
        mList.add(new EventModel("event", "", "", "",
                "Bing Bang Party", getResources().getResourceName(R.mipmap.demo),
                "Let's celebrate Christmas party together", "7th Dec",
                "₹8000", "8pm-11pm", "1 Guest", "Indoor"));
        mList.add(new EventModel("event", "", "", "",
                "Party House", getResources().getResourceName(R.mipmap.demo),
                "Let's rock the house", "7th Dec",
                "₹6000", "7pm-11pm", "2 Guests", "Indoor"));
        mList.add(new EventModel("event", "", "", "",
                "Happy House", getResources().getResourceName(R.mipmap.demo),
                "Last day of the year", "7th Dec",
                "₹5000", "6pm-11pm", "1 Guest", "Indoor"));

        return mList;
    }

    @Override
    public void openDetail(int id, ArrayList<EventModel> modelArrayList) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constant.PRICE, mList.get(id).getEventPrice());
        intent.putExtra(Constant.DATE, mList.get(id).getEventTime());
        intent.putExtra(Constant.TIME, mList.get(id).getEventTimeLimit());
        intent.putExtra(Constant.GUEST, mList.get(id).getEventGuest());
        intent.putExtra(Constant.DESCRIPTION, mList.get(id).getEventDescription());
        intent.putExtra(Constant.CATEGORY, mList.get(id).getEventCategory());
        startActivity(intent);
    }

    @Override
    public void categoryDetails(String name) {

    }

    public static FavoriteFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sort:
                favoriteAdapter.removeList();
                Collections.sort(getListData(), new SortCategory());
                favoriteAdapter.addList(mList);
                break;
        }
    }

    public class SortCategory implements Comparator<EventModel> {

        @Override
        public int compare(EventModel eventModel, EventModel t1) {
            return eventModel.getEventName().compareTo(t1.getEventName());
        }
    }
}
