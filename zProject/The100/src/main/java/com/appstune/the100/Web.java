package com.appstune.the100;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Web extends AppCompatActivity {
    WebView webView;
    private InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView=(WebView) findViewById(R.id.webView);
        webView=new WebView(this);

        AdView mAdView = (AdView) findViewById(R.id.adView1);
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
                displayInterstitial();
            }
        });
        Resources rs=getResources();
        int i=getIntent().getIntExtra("e", 0);
try {
    if (i == 1) {
        webView.loadUrl("http://www.levi.com/US/en_US/?country=CA");
        setContentView(webView);
    }
    if (i == 2) {
        webView.loadUrl("http://www.levi.com.mx/");
        setContentView(webView);
    }
    if (i == 3) {
        webView.loadUrl("http://www.levi.com/US/en_US/");
        setContentView(webView);
    }
    if (i == 4) {
        webView.loadUrl("www.levi.com.ar");
        setContentView(webView);
    }
    if (i == 5) {
        webView.loadUrl("http://www.levi.com.br/");
        setContentView(webView);
    }
    if (i == 6) {
        webView.loadUrl("www.levi.com.bo");
        setContentView(webView);
    }
    if (i == 7) {
        webView.loadUrl("www.levi.cl");
        setContentView(webView);
    }
    if (i == 8) {
        webView.loadUrl("www.levi.com.co");
        setContentView(webView);
    }
    if (i == 9) {
        webView.loadUrl("www.levi.com.ec");
        setContentView(webView);
    }
    if (i == 10) {
        webView.loadUrl("www.levi.com.pa");
        setContentView(webView);
    }
    if (i == 11) {
        webView.loadUrl("http://www.levi.com.py/paraguay");
        setContentView(webView);
    }
    if (i == 12) {
        webView.loadUrl("http://www.pe.levi.com/peru");
        setContentView(webView);
    }
    if (i == 13) {
        webView.loadUrl("http://www.levi.com.uy/uruguay");
        setContentView(webView);
    }
    if (i == 14) {
        webView.loadUrl("http://www.levi.com.ve/venezuela");
        setContentView(webView);
    }
    if (i == 15) {
        webView.loadUrl("http://www.levi.com/AT/de_DE/");
        setContentView(webView);
    }
    if (i == 16) {
        webView.loadUrl("http://www.levi.com/BE/en_GB/");
        setContentView(webView);
    }
    if (i == 17) {
        webView.loadUrl("http://www.levi.com/CZ/en_GB/");
        setContentView(webView);
    }
    if (i == 18) {
        webView.loadUrl("http://www.levi.com/DK/da_DK/");
        setContentView(webView);
    }
    if (i == 19) {
        webView.loadUrl("http://www.levi.com/FI/en_GB/");
        setContentView(webView);
    }
    if (i == 20) {
        webView.loadUrl("http://www.levi.com/FR/fr_FR/");
        setContentView(webView);
    }
    if (i == 21) {
        webView.loadUrl("http://www.levi.com/DE/de_DE/");
        setContentView(webView);
    }
    if (i == 22) {
        webView.loadUrl("http://www.levi.com/GB/en_GB/");
        setContentView(webView);
    }
    if (i == 23) {
        webView.loadUrl("http://www.levi.com/IT/it_IT/");
        setContentView(webView);
    }
    if (i == 24) {
        webView.loadUrl("http://www.levi.com/NL/nl_NL/");
        setContentView(webView);
    }
    if (i == 25) {
        webView.loadUrl("http://www.levi.com/RU/ru_RU/");
        setContentView(webView);
    }
    if (i == 26) {
        webView.loadUrl("http://www.levi.com/ES/es_ES/");
        setContentView(webView);
    }
    if (i == 27) {
        webView.loadUrl("http://www.levis.com.au/");
        setContentView(webView);
    }
    if (i == 28) {
        webView.loadUrl("http://www.levi.com.cn/");
        setContentView(webView);
    }
    if (i == 29) {
        webView.loadUrl("http://levi.in/");
        setContentView(webView);
    }
    if (i == 30) {
        webView.loadUrl("www.levi.co.id");
        setContentView(webView);
    }
    if (i == 31) {
        webView.loadUrl("www.levi.jp");
        setContentView(webView);
    }
    if (i == 32) {
        webView.loadUrl("http://www.levis.com.au/");
        setContentView(webView);
    }
    if (i == 33) {
        webView.loadUrl("www.levi.com.pk/");
        setContentView(webView);
    }
    if (i == 34) {
        webView.loadUrl("www.levi.com.ph");
        setContentView(webView);
    }
    if (i == 35) {
        webView.loadUrl("http://levi.com.sg/");
        setContentView(webView);
    }
    if (i == 36) {
        webView.loadUrl("http://www.levis.co.za/");
        setContentView(webView);
    }
}catch (Exception e){}
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setInitialScale(1);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setUserAgentString("Android");
        webView.setClickable(true);
        webView.setWebChromeClient(new WebChromeClient());

        final Activity activity=this;

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView w, String url){
                webView.loadUrl(url);
                return true;
            }
            @Override
            public void onLoadResource(WebView view, String url) {
                // Notice Here.
                webView.clearHistory();
                super.onLoadResource(webView, url);
            }
            @Override
            public void onPageFinished(WebView w, String url) {
                // And Here.
                webView.clearHistory();
                super.onPageFinished(webView,url);
            }

        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            //if Back key pressed and webview can navigate to previous page
            webView.goBack();
            webView.clearCache(true);
            webView.clearView();
            webView.destroyDrawingCache();
            webView.loadUrl("about:blank");
            finish();


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

