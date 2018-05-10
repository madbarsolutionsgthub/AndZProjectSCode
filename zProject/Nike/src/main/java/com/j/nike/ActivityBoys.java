package com.j.nike;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ActivityBoys extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout linearLayoutContact,linearLayoutStore,linearLayoutNews;
    private InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boys);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //admob
        try {
            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

// Prepare the Interstitial Ad
            interstitial = new InterstitialAd(ActivityBoys.this);
// Insert the Ad Unit ID
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

            interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    // Call displayInterstitial() function
                    //displayInterstitial();
                }
            });
        }catch (Exception e){}

        linearLayoutContact = (LinearLayout) findViewById(R.id.linearContactUs);
        linearLayoutStore = (LinearLayout) findViewById(R.id.linearStoreFinder);
        linearLayoutNews = (LinearLayout) findViewById(R.id.linearNews);

        linearLayoutStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityBoys.this, Web.class);
                intent.putExtra("e", 21);
                startActivity(intent);

            }
        });
        linearLayoutNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityBoys.this, Web.class);
                intent.putExtra("e", 22);
                startActivity(intent);

            }
        });
        linearLayoutContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityBoys.this, Web.class);
                intent.putExtra("e", 23);
                startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_boys, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shoes_boys) {
            // Handle the camera action
            Intent intent = new Intent(ActivityBoys.this, Web.class);
            intent.putExtra("e", 11);
            startActivity(intent);

        } else if (id == R.id.nav_clothing_boys) {
            Intent intent = new Intent(ActivityBoys.this, Web.class);
            intent.putExtra("e", 12);
            startActivity(intent);

        } else if (id == R.id.nav_new_boys) {
            Intent intent = new Intent(ActivityBoys.this, Web.class);
            intent.putExtra("e", 13);
            startActivity(intent);

        } else if (id == R.id.nav_fleece) {
            Intent intent = new Intent(ActivityBoys.this, Web.class);
            intent.putExtra("e", 14);
            startActivity(intent);

        } else if (id == R.id.nav_nowtredding_boys) {
            Intent intent = new Intent(ActivityBoys.this, Web.class);
            intent.putExtra("e", 15);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath = app.sourceDir;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");

            File originalApk = new File(filePath);

            try {
                File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
                if (!tempFile.isDirectory())
                    if (!tempFile.mkdirs())
                        return true;
                tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ", "").toLowerCase() + ".apk");
                if (!tempFile.exists()) {
                    if (!tempFile.createNewFile()) {
                        return true;
                    }
                }
                InputStream in = new FileInputStream(originalApk);
                OutputStream out = new FileOutputStream(tempFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                System.out.println("File copied.");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
                startActivity(Intent.createChooser(intent, "Share app via"));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else if (id == R.id.nav_rate) {
            String appPackage = this.getPackageName();
            String url = "https://play.google.com/store/apps/details?id=" + appPackage;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
