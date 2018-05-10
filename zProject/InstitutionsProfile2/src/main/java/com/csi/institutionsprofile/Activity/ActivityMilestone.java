package com.csi.institutionsprofile.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.csi.institutionsprofile.R;

public class ActivityMilestone extends AppCompatActivity {
    Toolbar toolbar;
    Button buttonFirstMilestone,buttonSecondMilestone,buttonThirdMilestone,buttonForthMilestone,buttonFifthMilestone,buttonSixthMilestone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initToolBar();
        setupUI();
        onClick();
    }

    private void onClick() {
        buttonFirstMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMilestone.this, ActivityMilestoneView.class);
                intent.putExtra("e", 1);
                startActivity(intent);
            }
        });
        buttonSecondMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMilestone.this, ActivityMilestoneView.class);
                intent.putExtra("e", 2);
                startActivity(intent);
            }
        });
        buttonThirdMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMilestone.this, ActivityMilestoneView.class);
                intent.putExtra("e", 3);
                startActivity(intent);
            }
        });
        buttonForthMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMilestone.this, ActivityMilestoneView.class);
                intent.putExtra("e", 4);
                startActivity(intent);
            }
        });
        buttonFifthMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMilestone.this, ActivityMilestoneView.class);
                intent.putExtra("e", 5);
                startActivity(intent);
            }
        });
        buttonSixthMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMilestone.this, ActivityMilestoneView.class);
                intent.putExtra("e", 6);
                startActivity(intent);
            }
        });
    }

    private void setupUI() {
        buttonFirstMilestone = (Button) findViewById(R.id.buttonDetailsFirstMilestone);
        buttonSecondMilestone = (Button) findViewById(R.id.buttonDetailsSecondMilestone);
        buttonThirdMilestone = (Button) findViewById(R.id.buttonDetailsThirdMilestone);
        buttonForthMilestone = (Button) findViewById(R.id.buttonDetailsForthMilestone);
        buttonFifthMilestone = (Button) findViewById(R.id.buttonDetailsFifthMilestone);
        buttonSixthMilestone = (Button) findViewById(R.id.buttonDetailsSixthMilestone);
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
}
