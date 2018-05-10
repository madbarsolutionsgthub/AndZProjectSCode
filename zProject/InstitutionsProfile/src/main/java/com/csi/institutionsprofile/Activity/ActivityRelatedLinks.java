package com.csi.institutionsprofile.Activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.csi.institutionsprofile.R;

public class ActivityRelatedLinks extends AppCompatActivity {
    Toolbar toolbar;
    Button buttonBanbesis,buttonHssp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_related_links);
        initToolBar();
        setupUI();
        onClick();
    }

    private void onClick() {
        buttonBanbesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
                if ( connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
                    Intent intent = new Intent(ActivityRelatedLinks.this,Web.class);
                    intent.putExtra("e",1);
                    startActivity(intent);

                }
                else if( connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
                    Toast.makeText(ActivityRelatedLinks.this, R.string.noInternet, Toast.LENGTH_LONG).show();
                }
                }
        });
        buttonHssp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
                if ( connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
                    Intent intent = new Intent(ActivityRelatedLinks.this,Web.class);
                    intent.putExtra("e",2);
                    startActivity(intent);

                }
                else if( connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
                    Toast.makeText(ActivityRelatedLinks.this, R.string.noInternet, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void setupUI() {
        buttonBanbesis = (Button) findViewById(R.id.buttonLinkBANBASIS);
        buttonHssp = (Button) findViewById(R.id.buttonLinkHSSP);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.relatedLink);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
