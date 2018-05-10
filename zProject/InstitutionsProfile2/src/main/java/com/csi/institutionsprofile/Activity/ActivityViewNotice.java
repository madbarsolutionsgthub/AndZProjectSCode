package com.csi.institutionsprofile.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.institutionsprofile.R;

public class ActivityViewNotice extends AppCompatActivity {
    TextView textViewTitle,textViewDetails,textViewDate;
    ImageView imageViewBackPress;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice);
        setupUI();
        Intent intent = getIntent();
        textViewTitle.setText(intent.getStringExtra("title"));
        textViewDetails.setText(intent.getStringExtra("description"));
        date = intent.getStringExtra("date");
        textViewDate.setText("Published On: "+" "+date);
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupUI() {
        textViewTitle = (TextView) findViewById(R.id.textViewNoticeTitle);
        textViewDetails = (TextView) findViewById(R.id.textViewNoticeDetails);
        textViewDate = (TextView) findViewById(R.id.textViewNoticePublishDate);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
    }
}
