package com.csi.institutionsprofile.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.csi.institutionsprofile.R;

public class ActivityProjectDetails extends AppCompatActivity {
    ImageView imageViewBackPress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        setupUI();
        onClick();
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
    }
}
