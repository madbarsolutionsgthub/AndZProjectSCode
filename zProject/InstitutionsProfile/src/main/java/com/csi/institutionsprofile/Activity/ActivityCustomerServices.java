package com.csi.institutionsprofile.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.institutionsprofile.R;

public class ActivityCustomerServices extends AppCompatActivity {
    Toolbar toolbar;
    TextView textViewTeleTalkNumber,textViewRocketNumber,textViewBkashNumber,textViewSureCashNumber,textViewUcashNumber,textViewGpNumber;
    ImageView imageViewCallTeletalk,imageViewCallRocket,imageViewCallBkash,imageViewCallSurecash,imageViewCallUcash,imageViewCallGp;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_services);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initToolBar();
        setupUI();
        onClick();
    }

    private void onClick() {
        try {
            imageViewCallTeletalk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number = textViewTeleTalkNumber.getText().toString();
                    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number)));
                }
            });
            imageViewCallRocket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number = textViewRocketNumber.getText().toString();
                    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number)));
                }
            });
            imageViewCallBkash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number = textViewBkashNumber.getText().toString();
                    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number)));
                }
            });

        }catch (Exception e){}
    }

    private void setupUI() {
        textViewTeleTalkNumber = (TextView) findViewById(R.id.textViewMobileNoTeletalk);
        textViewRocketNumber = (TextView) findViewById(R.id.textViewMobileNoRocket);
        textViewBkashNumber = (TextView) findViewById(R.id.textViewMobileNoBkash);
        imageViewCallTeletalk = (ImageView) findViewById(R.id.imageViewCallTeletalk);
        imageViewCallRocket = (ImageView) findViewById(R.id.imageViewCallRocket);
        imageViewCallBkash = (ImageView) findViewById(R.id.imageViewCallBkash);

    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.CustomerServicesTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
