package com.csi.institutionsprofile.Activity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;

public class ActivityMilestoneView extends AppCompatActivity {
    ImageView imageviewMilestone,imageViewMileStone1,imageViewMileStone3;
    TextView textViewTitle1,textViewTitle2,textViewTitle3,textViewTitle4,textViewTitle5,textViewTitle6;
    TextView textViewDes1,textViewDes2,textViewDes3,textViewDes4,textViewDes5,textViewDes6,textViewAchievementSummary;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        initToolBar();

        textViewAchievementSummary.setText(R.string.achievement_summary);
        textViewTitle1.setText(R.string.achievement1Title);
        textViewTitle2.setText(R.string.achievement2Title);
        textViewTitle3.setText(R.string.achievement3Title);
        textViewTitle4.setText(R.string.achievement4Title);
        textViewTitle5.setText(R.string.achievement5Title);
        textViewTitle6.setText(R.string.achievement6Title);

        textViewDes1.setText(R.string.achievement1detais);
        textViewDes2.setText(R.string.achievement2detais);
        textViewDes3.setText(R.string.achievement3detais);
        textViewDes4.setText(R.string.achievement4detais);
        textViewDes5.setText(R.string.achievement5detais);
        textViewDes6.setText(R.string.achievement6detais);

        imageviewMilestone.setBackgroundResource(R.drawable.anim1);
        AnimationDrawable rocketAnimation1 = (AnimationDrawable) imageviewMilestone.getBackground();
        rocketAnimation1.run();

        imageViewMileStone1.setBackgroundResource(R.drawable.anim2);
        AnimationDrawable rocketAnimation2 = (AnimationDrawable) imageViewMileStone1.getBackground();
        rocketAnimation2.run();

        int i = getIntent().getIntExtra("e", 0);
        /*if (i == 1) {
            //imageviewMilestone.setImageResource(R.drawable.mobile_banking);
            imageviewMilestone.setBackgroundResource(R.drawable.mobile_banking);
            textviewMilestoneDetails.setText(R.string.achievement1detais);
            textViewTitle.setText(R.string.achievement1Title);
        }
        if (i == 2) {
            imageviewMilestone.setBackgroundResource(R.drawable.thumb);
            textviewMilestoneDetails.setText(R.string.achievement2detais);
            textViewTitle.setText(R.string.achievement2Title);
        }
        if (i == 3) {
            imageviewMilestone.setBackgroundResource(R.drawable.achievement2);
            textviewMilestoneDetails.setText(R.string.achievement3detais);
            textViewTitle.setText(R.string.achievement3Title);
        }
        if (i == 4) {
            imageviewMilestone.setBackgroundResource(R.drawable.teletalk_sim);
            textviewMilestoneDetails.setText(R.string.achievement4detais);
            textViewTitle.setText(R.string.achievement4Title);
        }
        if (i == 5) {
            imageviewMilestone.setBackgroundResource(R.drawable.mobile_apps);
            textviewMilestoneDetails.setText(R.string.achievement5detais);
            textViewTitle.setText(R.string.achievement5Title);
        }
        if (i == 6) {
            imageviewMilestone.setBackgroundResource(R.drawable.thumb);
            textviewMilestoneDetails.setText(R.string.achievement6detais);
            textViewTitle.setText(R.string.achievement6Title);
        }*/
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
        imageViewMileStone1 = (ImageView) findViewById(R.id.imageViewMilestone1);
        //imageViewMileStone3 = (ImageView) findViewById(R.id.imageViewMilestone3);
        //textviewMilestoneDetails = (TextView) findViewById(R.id.textViewDescription);
        textViewTitle1 = (TextView) findViewById(R.id.textViewTitle1);
        textViewTitle2 = (TextView) findViewById(R.id.textViewTitle2);
        textViewTitle3 = (TextView) findViewById(R.id.textViewTitle3);
        textViewTitle4 = (TextView) findViewById(R.id.textViewTitle4);
        textViewTitle5 = (TextView) findViewById(R.id.textViewTitle5);
        textViewTitle6 = (TextView) findViewById(R.id.textViewTitle6);
        textViewDes1 = (TextView) findViewById(R.id.textViewDescription1);
        textViewDes2 = (TextView) findViewById(R.id.textViewDescription2);
        textViewDes3 = (TextView) findViewById(R.id.textViewDescription3);
        textViewDes4 = (TextView) findViewById(R.id.textViewDescription4);
        textViewDes5 = (TextView) findViewById(R.id.textViewDescription5);
        textViewDes6 = (TextView) findViewById(R.id.textViewDescription6);
        textViewAchievementSummary = (TextView) findViewById(R.id.textViewAchievementSummary);
    }
}
