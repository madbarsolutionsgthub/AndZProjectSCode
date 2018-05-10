package com.csi.institutionsprofile.Activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;

import info.hoang8f.android.segmented.SegmentedGroup;

public class ActivityProjectDetails extends AppCompatActivity {
    ImageView imageViewBackPress,imageViewAmount;
    TextView textViewDetailsHSSP,textViewTitlePurpose,textViewPurposeDetails,textViewPurposeExactTitle,textViewPurposeExactDetails,textViewHsspDoTitle,textViewHsspDoDetails,textViewAmount,textViewTootbarTitleName;
    SegmentedGroup segmentedGroupBanEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        onClick();

        FirstData();



        segmentedGroupBanEnd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                if(checkId == R.id.buttonLanguageEng){
                    textViewDetailsHSSP.setText(Constants.Data.HSSP_ENG);
                    textViewTitlePurpose.setText(Constants.Data.HSSP_PURPOSE_TITLE_ENG);
                    textViewPurposeDetails.setText(Constants.Data.HSSP_PURPOSE_ENG);
                    textViewPurposeExactTitle.setText(Constants.Data.HSSP_PURPOSE_EXACT_TITLE_ENG);
                    textViewPurposeExactDetails.setText(Constants.Data.HSSP_PURPOSE_EXACT_ENG);
                    textViewHsspDoTitle.setText(Constants.Data.WHAT_HSSP_DO_TITLE_ENG);
                    textViewHsspDoDetails.setText(Constants.Data.WHAT_HSSP_DO_ENG);
                    textViewAmount.setText(Constants.Data.RATE);
                    imageViewAmount.setImageResource(R.drawable.amount);
                    textViewTootbarTitleName.setText("Project Details");
                }
                else {
                    FirstData();
                }
            }
        });
    }

    private void FirstData() {
        textViewDetailsHSSP.setText(Constants.Data.HSSP);
        textViewTitlePurpose.setText(Constants.Data.HSSP_PURPOSE_TITLE);
        textViewPurposeDetails.setText(Constants.Data.HSSP_PURPOSE);
        textViewPurposeExactTitle.setText(Constants.Data.HSSP_PURPOSE_EXACT_TITLE);
        textViewPurposeExactDetails.setText(Constants.Data.HSSP_PURPOSE_EXACT);
        textViewHsspDoTitle.setText(Constants.Data.WHAT_HSSP_DO_TITLE);
        textViewHsspDoDetails.setText(Constants.Data.WHAT_HSSP_DO);
        textViewAmount.setText(Constants.Data.HSSP_AMOUNT);
        imageViewAmount.setImageResource(R.drawable.amount_bn);
        textViewTootbarTitleName.setText("প্রকল্প সারসংক্ষেপ");
    }

    private void onClick() {
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupUI() {
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        imageViewAmount = (ImageView) findViewById(R.id.imageViewAmount);
        textViewDetailsHSSP = (TextView) findViewById(R.id.textViewDesBanbesis);
        textViewTitlePurpose = (TextView) findViewById(R.id.textViewTitlePurpose);
        textViewPurposeDetails = (TextView) findViewById(R.id.textViewPurposeDetails);
        textViewPurposeExactTitle = (TextView) findViewById(R.id.textViewPurposeExactTitle);
        textViewPurposeExactDetails = (TextView) findViewById(R.id.textViewPurposeExactDetails);
        textViewHsspDoTitle = (TextView) findViewById(R.id.textViewHsspDoTitle);
        textViewHsspDoDetails = (TextView) findViewById(R.id.textViewHsspDoDetails);
        textViewAmount = (TextView) findViewById(R.id.textViewHsspAmountTitle);
        textViewTootbarTitleName = (TextView) findViewById(R.id.textViewTootbarTitleName);
        segmentedGroupBanEnd = (SegmentedGroup) findViewById(R.id.segmentedGroupLanguageBanEng);
    }
}
