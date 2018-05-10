package com.csi.institutionsprofile.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.institutionsprofile.R;

public class ActivityMilestoneView extends AppCompatActivity {
    ImageView imageviewMilestone;
    TextView textviewMilestoneDetails;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone_view);
        setupUI();
        initToolBar();

        int i = getIntent().getIntExtra("e", 0);
        if (i == 1) {
            imageviewMilestone.setImageResource(R.drawable.achievement1);
            textviewMilestoneDetails.setText(R.string.banbasisDes);
            toolbar.setTitle("First Milestone");
        }
        if (i == 2) {
            imageviewMilestone.setImageResource(R.drawable.achievement2);
            textviewMilestoneDetails.setText(R.string.banbasisDes);
            toolbar.setTitle("Second Milestone");
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.mileStone);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupUI() {
        imageviewMilestone = (ImageView) findViewById(R.id.imageViewMilestone);
        textviewMilestoneDetails = (TextView) findViewById(R.id.textViewDescription);
    }
}
