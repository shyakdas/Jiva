package com.jiva.com.mutipleviewholder.favorite;

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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context mContext;
    private ArrayList<EventModel> modelArrayList;
    private EventDetailsListener eventListener;

    public FavoriteAdapter(Context mContext, ArrayList<EventModel> modelArrayList, EventDetailsListener listener) {
        this.mContext = mContext;
        this.modelArrayList = modelArrayList;
        eventListener = listener;
    }

    public void removeList() {
        modelArrayList.clear();
        notifyDataSetChanged();
    }

    public void addList(ArrayList<EventModel> list) {
        modelArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoriteAdapter.FavoriteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mEventImage;
        private TextView mEventName, mEventDescription, mEventDate;

        public FavoriteViewHolder(View itemView) {
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
