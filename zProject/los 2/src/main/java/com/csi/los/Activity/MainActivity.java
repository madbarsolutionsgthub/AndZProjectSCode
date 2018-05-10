package com.csi.los.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csi.los.R;

public class MainActivity extends AppCompatActivity {
    TextView textViewChooseAccount,textViewBankAgent,textViewBankAgentDes,textViewPersonal,textViewPersonalDes;
    LinearLayout linearLayoutBankAgentSelect,linearLayoutPersonalAccSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        clickListener();
        Typeface typefaceThin = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Thin.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Bold.ttf");
        Typeface typefaceRegular = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Regular.ttf");
        textViewChooseAccount.setTypeface(typefaceRegular);
        textViewBankAgent.setTypeface(typefaceBold);
        textViewBankAgentDes.setTypeface(typefaceRegular);
        textViewPersonal.setTypeface(typefaceBold);
        textViewPersonalDes.setTypeface(typefaceRegular);
    }

    private void clickListener() {
        try{
            linearLayoutBankAgentSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
            });
            linearLayoutPersonalAccSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,ActivityLoginUser.class);
                    startActivity(intent);
                }
            });
        }catch (Exception e){}
    }

    private void setupUI() {
        textViewChooseAccount = (TextView) findViewById(R.id.chooseAccount);
        textViewBankAgent = (TextView) findViewById(R.id.textViewBankAgent);
        textViewBankAgentDes = (TextView) findViewById(R.id.textViewBankAgentDes);
        textViewPersonal = (TextView) findViewById(R.id.textViewPersonalTitle);
        textViewPersonalDes = (TextView) findViewById(R.id.textViewPersonalDes);
        linearLayoutBankAgentSelect = (LinearLayout) findViewById(R.id.linearBankAgentSelect);
        linearLayoutPersonalAccSelect = (LinearLayout) findViewById(R.id.linearPersonalAccSelect);
    }
}
