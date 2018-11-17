package com.jiva.com.jivaassigment.event;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiva.com.jivaassigment.R;
import com.jiva.com.jivaassigment.model.CategoryModel;
import com.jiva.com.jivaassigment.model.EventModel;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter {

    private static final String TAG = EventAdapter.class.getName();
    private Context mContext;
    private ArrayList<EventModel> mEventList;

    public EventAdapter(Context mContext, ArrayList<EventModel> mList) {
        this.mContext = mContext;
        mEventList = mList;
        Log.e(TAG, "listSize==" + mEventList.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 101:
                return new EventMainViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_main, parent, false));
            case 102:
                return new CategoryViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_category, parent, false));
            case 103:
                return new EventViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_event, parent, false));
            default:
                return new EventMainViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_main, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 101:
                ((EventMainViewHolder) holder).bindView(position);
                break;
            case 102:
                ((CategoryViewHolder) holder).bindView(position);
                break;
            case 103:
                ((EventViewHolder) holder).bindView(position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mEventList.get(position).getType().equalsIgnoreCase("main")) {
            return 101;
        } else if (mEventList.get(position).getType().equalsIgnoreCase("category")) {
            return 102;
        } else if (mEventList.get(position).getType().equalsIgnoreCase("event")) {
            return 103;
        } else {
            return 101;
        }
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }

    public class EventMainViewHolder extends RecyclerView.ViewHolder {

        public EventMainViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(int position) {

        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private CategoryAdapter categoryAdapter;
        private ArrayList<CategoryModel> mCategoryList;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            categoryAdapter = new CategoryAdapter(mContext, getCategoryData());
            mRecyclerView.setAdapter(categoryAdapter);
        }

        public ArrayList<CategoryModel> getCategoryData() {
            mCategoryList = new ArrayList<>();
            mCategoryList.add(new CategoryModel(mContext.getResources().getResourceName(R.mipmap.demo), "Outdoor"));
            mCategoryList.add(new CategoryModel(mContext.getResources().getResourceName(R.mipmap.demo), "Indoor"));
            mCategoryList.add(new CategoryModel(mContext.getResources().getResourceName(R.mipmap.demo), "Food"));
            mCategoryList.add(new CategoryModel(mContext.getResources().getResourceName(R.mipmap.demo), "Sports"));
            return mCategoryList;
        }

        public void bindView(int position) {

        }
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(int position) {

        }
    }
}