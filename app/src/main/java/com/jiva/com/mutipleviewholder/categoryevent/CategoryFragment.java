package com.jiva.com.mutipleviewholder.categoryevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiva.com.mutipleviewholder.R;
import com.jiva.com.mutipleviewholder.eventdetail.DetailActivity;
import com.jiva.com.mutipleviewholder.listener.EventDetailsListener;
import com.jiva.com.mutipleviewholder.model.EventModel;
import com.jiva.com.mutipleviewholder.utils.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CategoryFragment extends Fragment implements View.OnClickListener, EventDetailsListener {

    private static final String TAG = CategoryFragment.class.getName();
    private ImageView mBackButton, mSort;
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
        mSort = view.findViewById(R.id.sort);
        mSort.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        Bundle bundle = getArguments();
        mCategoryName.setText(bundle.getString(Constant.CATEGORY_NAME));
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

            case R.id.sort:
                categoryAdapter.removeList();
                Collections.sort(getListData(), new SortCategory());
                categoryAdapter.addList(mList);
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

    public class SortCategory implements Comparator<EventModel> {

        @Override
        public int compare(EventModel eventModel, EventModel t1) {
            return eventModel.getEventName().compareTo(t1.getEventName());
        }
    }
}
