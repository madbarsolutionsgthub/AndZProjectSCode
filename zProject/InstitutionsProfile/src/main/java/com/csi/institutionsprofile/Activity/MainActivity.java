package com.csi.institutionsprofile.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.csi.institutionsprofile.R;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayoutSearchInstitute,linearLayoutCustomerServices,linearLayoutAuthority,linearLayoutDeveloper,linearLayoutRelatedLinks,linearLayoutMilestone,linearLayoutProjectDetails,linearLayoutStakeholder,linearLayoutNotice;
    EditText editTextSearch;
    ImageView imageViewSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        onClick();
    }

    private void onClick() {
        try {
            linearLayoutSearchInstitute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityInstituteSearch.class));
                }
            });
            linearLayoutCustomerServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityCustomerServices.class));
                }
            });
            linearLayoutAuthority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityAuthority.class));
                }
            });
            linearLayoutDeveloper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityDeveloper.class));
                }
            });
            linearLayoutRelatedLinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityRelatedLinks.class));
                }
            });
            linearLayoutMilestone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityMilestone.class));
                }
            });
            linearLayoutProjectDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityProjectDetails.class));
                }
            });
            linearLayoutStakeholder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityStakeholder.class));
                }
            });
            linearLayoutNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityNotice.class));
                }
            });
            editTextSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityInstituteSearch.class));
                }
            });

        }catch (Exception e){}
    }

    private void setupUI() {
        linearLayoutSearchInstitute = (LinearLayout) findViewById(R.id.linearlayoutSearch);
        linearLayoutCustomerServices = (LinearLayout) findViewById(R.id.linearCustomerService);
        linearLayoutAuthority = (LinearLayout) findViewById(R.id.linearAuthority);
        linearLayoutDeveloper = (LinearLayout) findViewById(R.id.linearConnectDeveloper);
        linearLayoutRelatedLinks = (LinearLayout) findViewById(R.id.linearRelatedLinks);
        linearLayoutMilestone = (LinearLayout) findViewById(R.id.linearAchievement);
        linearLayoutProjectDetails = (LinearLayout) findViewById(R.id.linearProjectDetails);
        linearLayoutStakeholder = (LinearLayout) findViewById(R.id.linearStackHolder);
        linearLayoutNotice = (LinearLayout) findViewById(R.id.linearNotice);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        imageViewSetting = (ImageView) findViewById(R.id.imageViewSetting);
    }
}
