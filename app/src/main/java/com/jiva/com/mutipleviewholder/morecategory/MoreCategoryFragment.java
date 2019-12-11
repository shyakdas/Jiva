package com.jiva.com.mutipleviewholder.morecategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiva.com.mutipleviewholder.R;
import com.jiva.com.mutipleviewholder.categoryevent.CategoryFragment;
import com.jiva.com.mutipleviewholder.listener.CategoryListener;
import com.jiva.com.mutipleviewholder.model.CategoryModel;
import com.jiva.com.mutipleviewholder.utils.Constant;

import java.util.ArrayList;

public class MoreCategoryFragment extends Fragment implements CategoryListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private MoreCategoryAdapter categoryAdapter;
    private ArrayList<CategoryModel> mCategoryList;
    private ImageView mBackButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_category, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mBackButton = view.findViewById(R.id.back_button);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        categoryAdapter = new MoreCategoryAdapter(getContext(), getCategoryData(), this);
        mRecyclerView.setAdapter(categoryAdapter);
        return view;
    }

    public ArrayList<CategoryModel> getCategoryData() {
        mCategoryList = new ArrayList<>();
        mCategoryList.add(new CategoryModel(getResources().getResourceName(R.mipmap.demo), "Outdoor"));
        mCategoryList.add(new CategoryModel(getResources().getResourceName(R.mipmap.demo), "Indoor"));
        mCategoryList.add(new CategoryModel(getResources().getResourceName(R.mipmap.demo), "Food"));
        mCategoryList.add(new CategoryModel(getResources().getResourceName(R.mipmap.demo), "Sports"));
        return mCategoryList;
    }

    @Override
    public void category(String categoryName) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.CATEGORY_NAME, categoryName);
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
            case R.id.back_button:
                getActivity().onBackPressed();
                break;
        }
    }
}
