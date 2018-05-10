package com.csi.los.Activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.csi.los.Fragment.FragmentContactInfo;
import com.csi.los.Fragment.FragmentFamilyInfo;
import com.csi.los.Fragment.FragmentPersonalInfo;
import com.csi.los.R;

public class ActivityBorrowerInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower_info);
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)   findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_personalInfo:
                                selectedFragment = new FragmentPersonalInfo();
                                break;
                            case R.id.action_familyInfo:
                                selectedFragment = new FragmentFamilyInfo();
                                break;
                            case R.id.action_contactInfo:
                                selectedFragment = new FragmentContactInfo();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new FragmentPersonalInfo());
        transaction.commit();
    }
}


