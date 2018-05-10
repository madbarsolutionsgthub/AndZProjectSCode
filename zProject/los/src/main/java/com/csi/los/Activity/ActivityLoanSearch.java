package com.csi.los.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.los.R;

public class ActivityLoanSearch extends AppCompatActivity {
    ImageView imageViewBackPress,imageViewSetting;
    TextView textViewBankAgentName,textViewExistingLoanTitle;
    public static  String globalPreferenceNameForIdForAgent="com.honda.profile";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_search);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setUpUI();
        clickListstener();
        Typeface typefaceRegular = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Regular.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Bold.ttf");
        Typeface typefaceThin = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Thin.ttf");
        textViewBankAgentName.setTypeface(typefaceRegular);
        textViewExistingLoanTitle.setTypeface(typefaceThin);

        sharedPreferences= getSharedPreferences(Login.globalPreferenceNameForIdForAgent,MODE_PRIVATE);
        textViewBankAgentName.setText(sharedPreferences.getString(Login.USER_NAME,"name"));
    }

    private void clickListstener() {
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imageViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLoanSearch.this,ActivityAgentAccount.class));
            }
        });
    }

    private void setUpUI() {
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        textViewBankAgentName = (TextView) findViewById(R.id.textViewBankAgentUserName);
        textViewExistingLoanTitle = (TextView) findViewById(R.id.textViewExistingLoanSearch);
        imageViewSetting = (ImageView) findViewById(R.id.imageViewLogout);
    }
}
