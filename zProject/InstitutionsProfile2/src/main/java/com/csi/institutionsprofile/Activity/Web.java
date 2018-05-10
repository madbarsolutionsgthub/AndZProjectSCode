package com.csi.institutionsprofile.Activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.pm.ActivityInfo;
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

import com.csi.institutionsprofile.R;

public class Web extends AppCompatActivity {
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        w=(WebView) findViewById(R.id.webViewRelatedLinks);
        w=new WebView(this);
        w.getSettings().setBuiltInZoomControls(true);
        w.getSettings().setDisplayZoomControls(true);
        //w.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Resources rs=getResources();
        try {
            int i = getIntent().getIntExtra("e", 0);
            if (i == 1) {
                w.loadUrl("http://www.banbeis.gov.bd/");
                setContentView(w);
            }
            if (i == 2) {
                w.loadUrl("http://www.hssp.gov.bd/");
                setContentView(w);
            }
            if (i == 3) {
                w.loadUrl("http://www.moedu.gov.bd/");
                setContentView(w);
            }
            if (i == 4) {
                w.loadUrl("http://www.dshe.gov.bd/");
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
        }
        return super.onKeyDown(keyCode, event);


    }
}


