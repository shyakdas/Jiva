package com.jiva.com.mutipleviewholder.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.jiva.com.mutipleviewholder.R;
import com.jiva.com.mutipleviewholder.eventdetail.DetailActivity;
import com.jiva.com.mutipleviewholder.listener.EventDetailsListener;
import com.jiva.com.mutipleviewholder.model.EventModel;
import com.jiva.com.mutipleviewholder.utils.Constant;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements View.OnClickListener, EventDetailsListener {

    private ImageView mBackButton;
    private TextInputEditText mSearch;
    private RecyclerView mRecyclerView;
    private SearchAdapter searchAdapter;
    private ArrayList<EventModel> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mBackButton = view.findViewById(R.id.back_button);
        mSearch = view.findViewById(R.id.search);
        mBackButton.setOnClickListener(this);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchAdapter = new SearchAdapter(getActivity(), this);
        mRecyclerView.setAdapter(searchAdapter);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    void filter(String text) {
        ArrayList<EventModel> temp = new ArrayList();
        for (EventModel d : getListData()) {
            if (d.getEventName().contains(text)) {
                temp.add(d);
            }
        }
        searchAdapter.updateList(temp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                getActivity().onBackPressed();
                break;
        }
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
}