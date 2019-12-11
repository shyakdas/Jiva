package com.jiva.com.mutipleviewholder;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.jiva.com.mutipleviewholder.R;
import com.jiva.com.mutipleviewholder.utils.HomePagerAdapter;
import com.jiva.com.mutipleviewholder.utils.NonSwipeableViewpager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private LinearLayout mEventLayout, mFavoriteLayout, mProfileLayout;
    private NonSwipeableViewpager pager;
    private int count = 0;
    private TextView mEventText, mFavoriteText, mProfileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mEventLayout = findViewById(R.id.event_layout);
        mFavoriteLayout = findViewById(R.id.favorite_layout);
        mProfileLayout = findViewById(R.id.profile_layout);
        pager = findViewById(R.id.non_swipe_view_pager);
        mEventText = findViewById(R.id.event_text);
        mFavoriteText = findViewById(R.id.favorite_text);
        mProfileText = findViewById(R.id.profile_text);
        mEventLayout.setOnClickListener(this);
        mFavoriteLayout.setOnClickListener(this);
        mProfileLayout.setOnClickListener(this);
        pager.setOffscreenPageLimit(2);
        FragmentManager fm = getSupportFragmentManager();
        HomePagerAdapter pagerAdapter = new HomePagerAdapter(fm, this);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
        mEventText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
    }

    // Setting up previous Icon
    public void setMyPreviousIcon(int choice) {
        if (choice == 0) {
            mEventText.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        } else if (choice == 1) {
            mFavoriteText.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        } else if (choice == 2) {
            mProfileText.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.event_layout:
                event();
                break;

            case R.id.favorite_layout:
                favorite();
                break;

            case R.id.profile_layout:
                profile();
                break;
        }
    }

    private void profile() {
        setMyPreviousIcon(count);
        pager.setCurrentItem(2);
        mProfileText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        count = 2;
    }

    private void favorite() {
        setMyPreviousIcon(count);
        pager.setCurrentItem(1);
        mFavoriteText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        count = 1;
    }

    private void event() {
        setMyPreviousIcon(count);
        pager.setCurrentItem(0);
        mEventText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        count = 0;
    }
}