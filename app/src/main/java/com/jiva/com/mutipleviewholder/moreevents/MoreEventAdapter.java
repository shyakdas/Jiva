package com.jiva.com.mutipleviewholder.moreevents;

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

public class MoreEventAdapter extends RecyclerView.Adapter<MoreEventAdapter.MoreEventViewHolder> {

    private Context mContext;
    private ArrayList<EventModel> modelArrayList;
    private EventDetailsListener eventListener;

    public MoreEventAdapter(Context mContext, ArrayList<EventModel> modelArrayList, EventDetailsListener listener) {
        this.mContext = mContext;
        this.modelArrayList = modelArrayList;
        eventListener = listener;
    }

    @NonNull
    @Override
    public MoreEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoreEventAdapter.MoreEventViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoreEventViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MoreEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mEventImage;
        private TextView mEventName, mEventDescription, mEventDate;

        public MoreEventViewHolder(View itemView) {
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
            eventListener.openDetail(getAdapterPosition(), modelArrayList);
        }
    }
}
