package com.jiva.com.jivaassigment.categoryevent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiva.com.jivaassigment.R;
import com.jiva.com.jivaassigment.listener.EventDetailsListener;
import com.jiva.com.jivaassigment.model.EventModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemCategoryViewHolder> {

    private Context mContext;
    private ArrayList<EventModel> mList;
    private EventDetailsListener eventListener;

    public CategoryAdapter(Context mContext, ArrayList<EventModel> mList, EventDetailsListener listener) {
        this.mContext = mContext;
        this.mList = mList;
        eventListener = listener;
    }

    public void removeList() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void addList(ArrayList<EventModel> modelArrayList) {
        mList = modelArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryAdapter.ItemCategoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoryViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mEventImage;
        private TextView mEventName, mEventDescription, mEventDate;

        public ItemCategoryViewHolder(View itemView) {
            super(itemView);
            mEventImage = itemView.findViewById(R.id.image);
            mEventName = itemView.findViewById(R.id.event_name);
            mEventDescription = itemView.findViewById(R.id.description);
            mEventDate = itemView.findViewById(R.id.event_time);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            mEventName.setText(mList.get(position).getEventName());
            mEventDescription.setText(mList.get(position).getEventDescription());
            mEventDate.setText(mList.get(position).getEventTime());
        }

        @Override
        public void onClick(View view) {
            eventListener.openDetail(getAdapterPosition(), mList);
        }
    }
}
