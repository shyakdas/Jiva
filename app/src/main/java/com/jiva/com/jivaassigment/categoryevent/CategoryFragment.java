package com.jiva.com.jivaassigment.categoryevent;

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
import android.widget.TextView;

import com.jiva.com.jivaassigment.R;
import com.jiva.com.jivaassigment.eventdetail.DetailActivity;
import com.jiva.com.jivaassigment.listener.EventDetailsListener;
import com.jiva.com.jivaassigment.model.EventModel;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements View.OnClickListener, EventDetailsListener {

    private ImageView mBackButton;
    private TextView mCategoryName;
    private RecyclerView mRecyclerView;
    private CategoryAdapter categoryAdapter;
    private ArrayList<EventModel> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mBackButton = view.findViewById(R.id.back_button);
        mCategoryName = view.findViewById(R.id.category_image);
        mBackButton.setOnClickListener(this);
        Bundle bundle = getArguments();
        mCategoryName.setText(bundle.getString("category_name"));
        mRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryAdapter = new CategoryAdapter(getActivity(), getListData(), this);
        mRecyclerView.setAdapter(categoryAdapter);
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
        intent.putExtra("price", mList.get(id).getEventPrice());
        intent.putExtra("date", mList.get(id).getEventTime());
        intent.putExtra("time", mList.get(id).getEventTimeLimit());
        intent.putExtra("guest", mList.get(id).getEventGuest());
        intent.putExtra("description", mList.get(id).getEventDescription());
        intent.putExtra("category", mList.get(id).getEventCategory());
        startActivity(intent);
    }

    @Override
    public void categoryDetails(String name) {

    }
}
