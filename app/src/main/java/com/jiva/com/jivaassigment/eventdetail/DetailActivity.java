package com.jiva.com.jivaassigment.eventdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiva.com.jivaassigment.R;
import com.jiva.com.jivaassigment.utils.Constant;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = DetailActivity.class.getName();
    private ImageView mBackPress, mShare;
    private TextView mEventPrice, mEventGuest, mEventTime, mEventDescription, mEventType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mBackPress = findViewById(R.id.back_arrow);
        mEventPrice = findViewById(R.id.event_entry_fee);
        mShare = findViewById(R.id.share);
        mEventGuest = findViewById(R.id.number_of_guest);
        mEventTime = findViewById(R.id.event_time);
        mEventDescription = findViewById(R.id.event_description);
        mEventType = findViewById(R.id.event_type);
        mShare.setOnClickListener(this);
        mBackPress.setOnClickListener(this);
        Bundle bundle = this.getIntent().getExtras();
        mEventPrice.setText(bundle.getString(Constant.PRICE));
        mEventGuest.setText(bundle.getString(Constant.GUEST));
        mEventTime.setText(bundle.getString(Constant.DATE) + " " + bundle.getString(Constant.TIME) + " ,");
        mEventDescription.setText(bundle.getString(Constant.DESCRIPTION));
        mEventType.setText(bundle.getString(Constant.CATEGORY));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_arrow:
                onBackPressed();
                break;

            case R.id.share:
                String shareBody = "Here is the share content body";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share)));
                break;
        }
    }
}
