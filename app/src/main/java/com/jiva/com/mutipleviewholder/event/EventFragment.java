package com.jiva.com.mutipleviewholder.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiva.com.mutipleviewholder.R;
import com.jiva.com.mutipleviewholder.categoryevent.CategoryFragment;
import com.jiva.com.mutipleviewholder.eventdetail.DetailActivity;
import com.jiva.com.mutipleviewholder.listener.EventDetailsListener;
import com.jiva.com.mutipleviewholder.listener.ShowMoreListener;
import com.jiva.com.mutipleviewholder.model.EventModel;
import com.jiva.com.mutipleviewholder.morecategory.MoreCategoryFragment;
import com.jiva.com.mutipleviewholder.moreevents.MoreEventFragment;
import com.jiva.com.mutipleviewholder.search.SearchFragment;
import com.jiva.com.mutipleviewholder.utils.Constant;

import java.util.ArrayList;

public class EventFragment extends Fragment implements EventDetailsListener, View.OnClickListener, ShowMoreListener {

    private static final String TAG = EventFragment.class.getName();
    private RecyclerView mRecyclerView;
    private EventAdapter eventAdapter;
    private ArrayList<EventModel> mList;
    private TextView mSearch;

    public static EventFragment newInstance() {
        Bundle args = new Bundle();
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mSearch = view.findViewById(R.id.search);
        mSearch.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        eventAdapter = new EventAdapter(getActivity(), getListData(), this, this);
        mRecyclerView.setAdapter(eventAdapter);
        return view;
    }

    public ArrayList<EventModel> getListData() {
        mList = new ArrayList<>();
        mList.add(new EventModel("main", "Near You", "",
                "", "", "", "", "",
                "", "", "", ""));

        mList.add(new EventModel("category", "Near you",
                getResources().getResourceName(R.mipmap.demo), "Sports", "",
                "", "", "", "", "",
                "", ""));
        mList.add(new EventModel("main", "Today", "",
                "", "", "", "", "",
                "", "", "", ""));


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
    public void openDetail(int id, ArrayList<EventModel> mList) {
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
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.CATEGORY_NAME, name);
        categoryFragment.setArguments(bundle);
        goToNextFragment(categoryFragment);
    }

    public void goToNextFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.loginFrame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                SearchFragment searchFragment = new SearchFragment();
                goToNextFragment(searchFragment);
                break;
        }
    }

    @Override
    public void showMoreItem(String name) {
        if (name.equalsIgnoreCase("Near You")) {
            MoreCategoryFragment moreCategoryFragment = new MoreCategoryFragment();
            goToNextFragment(moreCategoryFragment);
        } else {
            MoreEventFragment moreEventFragment = new MoreEventFragment();
            goToNextFragment(moreEventFragment);
        }
    }
}
