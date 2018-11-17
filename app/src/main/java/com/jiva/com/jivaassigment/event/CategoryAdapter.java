package com.jiva.com.jivaassigment.event;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiva.com.jivaassigment.R;
import com.jiva.com.jivaassigment.listener.CategoryListener;
import com.jiva.com.jivaassigment.model.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder> {

    private static final String TAG = CategoryAdapter.class.getName();
    private ArrayList<CategoryModel> modelArrayList;
    private Context mContext;
    private CategoryListener mCategoryListener;

    public CategoryAdapter(Context mContext, ArrayList<CategoryModel> mList, CategoryListener listener) {
        this.mContext = mContext;
        modelArrayList = mList;
        mCategoryListener = listener;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mCategoryImage;
        private TextView mCategoryText;

        public CategoryItemViewHolder(View itemView) {
            super(itemView);
            mCategoryImage = itemView.findViewById(R.id.category_image);
            mCategoryText = itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            mCategoryText.setText(modelArrayList.get(position).getCategoryName());
            mCategoryImage.setAlpha(0.6f);
            mCategoryText.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        }

        @Override
        public void onClick(View view) {
            mCategoryListener.category(modelArrayList.get(getAdapterPosition()).getCategoryName());
        }
    }
}