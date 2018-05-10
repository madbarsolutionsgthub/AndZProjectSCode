package com.j.nike;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    Button buttonBoys,buttonGirls,buttonMen,buttonWomen;
    private InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //admob
        try {
            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

// Prepare the Interstitial Ad
            interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

            interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    // Call displayInterstitial() function
                   // displayInterstitial();
                }
            });
        }catch (Exception e){}

        buttonBoys = (Button) findViewById(R.id.buttonBoys);
        buttonGirls = (Button) findViewById(R.id.buttonGirl);
        buttonMen = (Button) findViewById(R.id.buttonMen);
        buttonWomen = (Button) findViewById(R.id.buttonWomen);
        buttonBoys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityBoys.class));
            }
        });
        buttonGirls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityGirls.class));
            }
        });
        buttonMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityMen.class));
            }
        });
        buttonWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityWomen.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        displayInterstitial();
    }
    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
