package com.bluebell.levies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    ImageView imageView;
    int [] photos={R.drawable.l1, R.drawable.l2,R.drawable.l3,R.drawable.l4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTheme(R.style.SplashTheme);
       /* imageView = (ImageView) findViewById(R.id.imageView);
        Random ran=new Random();
        int i=ran.nextInt(photos.length);
        imageView.setImageResource(photos[i]);
        */
        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Splash.this, Home.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }
}
