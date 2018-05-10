package com.j.nike;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Web extends AppCompatActivity {
    WebView w;
    private InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        //admob
        try {
            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

// Prepare the Interstitial Ad
            interstitial = new InterstitialAd(Web.this);
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

        w=(WebView) findViewById(R.id.webView1);
        w=new WebView(this);
        //w.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Resources rs=getResources();
        try {
            int i = getIntent().getIntExtra("e", 0);
            if (i == 1) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/mens-shoes/7puZoi3");
                setContentView(w);
            }
            if (i == 2) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/mens-clothing/1mdZ7pu");
                setContentView(w);
            }
            if (i == 3) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/new-mens/meZ7pu");
                setContentView(w);
            }
            if (i == 4) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/mens-nba/sa6Znnt");
                setContentView(w);
            }
            if (i == 5) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/mens-essentials-collection/7puZr3nZq4s");
                setContentView(w);
            }
            if (i == 6) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/womens-shoes/7ptZoi3");
                setContentView(w);
            }
            if (i == 7) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/womens-clothing/1mdZ7pt");
                setContentView(w);
            }
            if (i == 8) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/new-womens/meZ7pt");
                setContentView(w);
            }
            if (i == 9) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/womens-best/7ptZr3nZpi1");
                setContentView(w);
            }
            if (i == 10) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/womens-trends/7ptZr3nZppl");
                setContentView(w);
            }
            if (i == 11) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/boys-shoes/7pvZoi3");
                setContentView(w);
            }
            if (i == 12) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/boys-clothing/1mdZ7pv");
                setContentView(w);
            }
            if (i == 13) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/new-boys/meZ7pv?sm=0");
                setContentView(w);
            }
            if (i == 14) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/boys-fleece/7pvZr3nZory");
                setContentView(w);
            }
            if (i == 15) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/boys-trends/7pvZr3nZppl");
                setContentView(w);
            }
            if (i == 16) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/girls-shoes/7pwZoi3");
                setContentView(w);
            }
            if (i == 17) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/girls-clothing/1mdZ7pw");
                setContentView(w);
            }
            if (i == 18) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/new-girls/meZ7pw?sm=0");
                setContentView(w);
            }
            if (i == 19) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/girls-fleece/7pwZr3nZory");
                setContentView(w);
            }

            if (i == 20) {
                w.loadUrl("https://store.nike.com/us/en_us/pw/girls-trends/7pwZr3nZppl");
                setContentView(w);
            }
            if (i == 21) {
                w.loadUrl("https://www.nike.com/us/en_us/retail/");
                setContentView(w);
            }
            if (i == 22) {
                w.loadUrl("https://news.nike.com/");
                setContentView(w);
            }if (i == 23) {
                w.loadUrl("https://help-en-us.nike.com/app/contact");
                setContentView(w);
            }

        }catch (Exception e){}
        try {
            w.getSettings().setJavaScriptEnabled(true);
            final Activity activity = this;
            w.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){}
        try {
            w.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent,
                                            String contentDisposition, String mimetype, long contentLength) {
                    // TODO Auto-generated method stub
                    try {
                        DownloadManager.Request request = new DownloadManager.Request(
                                Uri.parse(url));
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
                        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        dm.enqueue(request);
                    }catch (Exception e){}
                }
            });
        }catch (Exception e){}
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && w.canGoBack()) {
            //if Back key pressed and webview can navigate to previous page
            w.goBack();
            // go back to previous page
            return true;
        }
        else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {

            return false;
        }
        else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){

            return false;
        }
        else
        {
            finish();

            // finish the activity
        }
        return super.onKeyDown(keyCode, event);

    }
    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

}

