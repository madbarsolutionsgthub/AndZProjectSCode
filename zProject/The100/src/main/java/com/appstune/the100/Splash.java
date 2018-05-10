package com.appstune.the100;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
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
        //check connection
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                    connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(Splash.this, Home.class));
                        finish();
                    }
                }, secondsDelayed * 1000);
            } else if (connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                    connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
                Toast.makeText(getApplicationContext(), "No internet connection",
                        Toast.LENGTH_LONG).show();

            }
        }catch (Exception e){}
    }
}
