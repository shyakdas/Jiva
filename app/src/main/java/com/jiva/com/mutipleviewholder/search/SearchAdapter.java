package com.jiva.com.mutipleviewholder.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jiva.com.mutipleviewholder.R;
import com.jiva.com.mutipleviewholder.listener.EventDetailsListener;
import com.jiva.com.mutipleviewholder.model.EventModel;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context mContext;
    private ArrayList<EventModel> modelArrayList;
    private EventDetailsListener eventDetailsListener;

    public SearchAdapter(Context mContext, EventDetailsListener listener) {
        this.mContext = mContext;
        eventDetailsListener = listener;
    }

    public void updateList(ArrayList<EventModel> list) {
        modelArrayList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchAdapter.SearchViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return modelArrayList == null ? 0 : modelArrayList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mEventImage;
        private TextView mEventName, mEventDescription, mEventDate;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mEventImage = itemView.findViewById(R.id.image);
            mEventName = itemView.findViewById(R.id.event_name);
            mEventDescription = itemView.findViewById(R.id.description);
            mEventDate = itemView.findViewById(R.id.event_time);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            mEventName.setText(modelArrayList.get(position).getEventName());
            mEventDescription.setText(modelArrayList.get(position).getEventDescription());
            mEventDate.setText(modelArrayList.get(position).getEventTime());
        }

        @Override
        public void onClick(View view) {
            eventDetailsListener.openDetail(getAdapterPosition(), modelArrayList);
        }
    }
}
