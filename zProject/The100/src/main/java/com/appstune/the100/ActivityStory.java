package com.appstune.the100;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ActivityStory extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout relativeLayoutStory;
    InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        TextView textViewStory = (TextView) findViewById(R.id.textViewStory);
        relativeLayoutStory = (RelativeLayout) findViewById(R.id.relativeStory);
        initToolBar();

        try {
            AdView mAdView = (AdView) findViewById(R.id.adView1);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            interstitial = new InterstitialAd(ActivityStory.this);
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    //displayInterstitial();
                }
            });
        }catch (Exception e){

        }

        Resources rs = getResources();
        int i = getIntent().getIntExtra("e", 0);
        if (i == 1) {
            textViewStory.setText(rs.getString(R.string.OneMUHAMMAD));
            toolbar.setTitle(R.string.one);
            relativeLayoutStory.setBackgroundResource(R.drawable.muhammad);
        }
        if (i == 2) {
            textViewStory.setText(rs.getString(R.string.two_ISAAC_NEWTON));
            toolbar.setTitle(R.string.two);
            relativeLayoutStory.setBackgroundResource(R.drawable.newton);
        }
        if (i == 3) {
            textViewStory.setText(rs.getString(R.string.three_JESUS_CHRIST));
            toolbar.setTitle(R.string.three);
            relativeLayoutStory.setBackgroundResource(R.drawable.jesus);
        }
        if (i == 4) {
            textViewStory.setText(rs.getString(R.string.four_BUDDHA));
            toolbar.setTitle(R.string.four);
            relativeLayoutStory.setBackgroundResource(R.drawable.buddha);
        }
        if (i == 5) {
            textViewStory.setText(rs.getString(R.string.five_CONFUCIUS));
            toolbar.setTitle(R.string.five);
            relativeLayoutStory.setBackgroundResource(R.drawable.confucius);
        }
        if (i == 6) {
            textViewStory.setText(rs.getString(R.string.six_ST_PAUL));
            toolbar.setTitle(R.string.six);
            relativeLayoutStory.setBackgroundResource(R.drawable.st_paul);
        }
        if (i == 7) {
            textViewStory.setText(rs.getString(R.string.seven_TS_AI_LUN));
            toolbar.setTitle(R.string.seven);
            relativeLayoutStory.setBackgroundResource(R.drawable.ts_ai_lun);
        }
        if (i == 8) {
            textViewStory.setText(rs.getString(R.string.eight_JOHANN_GUTENBERG));
            toolbar.setTitle(R.string.eight);
            relativeLayoutStory.setBackgroundResource(R.drawable.gutenburg);
        }
        if (i == 9) {
            textViewStory.setText(rs.getString(R.string.nine_CHRISTOPHER_COLUMBUS));
            toolbar.setTitle(R.string.nine);
            relativeLayoutStory.setBackgroundResource(R.drawable.columbus);
        }
        if (i == 10) {
            textViewStory.setText(rs.getString(R.string.ten_ALBERT_EINSTEIN));
            toolbar.setTitle(R.string.ten);
            relativeLayoutStory.setBackgroundResource(R.drawable.einstian);
        }
        if (i == 11) {
            textViewStory.setText(rs.getString(R.string.eleven_LOUIS_PASTEUR));
            toolbar.setTitle(R.string.eleven);
            relativeLayoutStory.setBackgroundResource(R.drawable.pasture);
        }
        if (i == 12) {
            textViewStory.setText(rs.getString(R.string.twelve_GALILEO_GALILEI));
            toolbar.setTitle(R.string.twelve);
            relativeLayoutStory.setBackgroundResource(R.drawable.galilio);
        }
        if (i == 13) {
            textViewStory.setText(rs.getString(R.string.thirteen_ARISTOTLE));
            toolbar.setTitle(R.string.thirteen);
            relativeLayoutStory.setBackgroundResource(R.drawable.aristotle);
        }
        if (i == 14) {
            textViewStory.setText(rs.getString(R.string.forteen_EUCLID));
            toolbar.setTitle(R.string.forteen);
            relativeLayoutStory.setBackgroundResource(R.drawable.euclid);
        }
        if (i == 15) {
            textViewStory.setText(rs.getString(R.string.fifteen_MOSES));
            toolbar.setTitle(R.string.fifteen);
            relativeLayoutStory.setBackgroundResource(R.drawable.moses);
        }
        if (i == 16) {
            textViewStory.setText(rs.getString(R.string.sixteen_CHARLES_DARWIN));
            toolbar.setTitle(R.string.sixteen);
            relativeLayoutStory.setBackgroundResource(R.drawable.darwin);
        }
        if (i == 17) {
            textViewStory.setText(rs.getString(R.string.seventeen_SHIH_HUANG_TI));
            toolbar.setTitle(R.string.seventeen);
            relativeLayoutStory.setBackgroundResource(R.drawable.shihhuangti);
        }
        if (i == 18) {
            textViewStory.setText(rs.getString(R.string.eighteen_AUGUSTUS_CAESAR));
            toolbar.setTitle(R.string.eightteen);
            relativeLayoutStory.setBackgroundResource(R.drawable.augusto);
        }
        if (i == 19) {
            textViewStory.setText(rs.getString(R.string.ninteen_NICOLAUS_COPERNICUS));
            toolbar.setTitle(R.string.ninteen);
            relativeLayoutStory.setBackgroundResource(R.drawable.nicolaus);
        }
        if (i == 20) {
            textViewStory.setText(rs.getString(R.string.twenty_ANTOINE_LAURENTLA_VOISIER));
            toolbar.setTitle(R.string.twenty);
            relativeLayoutStory.setBackgroundResource(R.drawable.lavoisier);
        }
        if (i == 21) {
            textViewStory.setText(rs.getString(R.string.twenty1_CONSTANTINE_THE_GREAT));
            toolbar.setTitle(R.string.twenty1);
            relativeLayoutStory.setBackgroundResource(R.drawable.constantine);
        }
        if (i == 22) {
            textViewStory.setText(rs.getString(R.string.twenty2_JAMES_WATT));
            toolbar.setTitle(R.string.twenty2);
            relativeLayoutStory.setBackgroundResource(R.drawable.watt);
        }
        if (i == 23) {
            textViewStory.setText(rs.getString(R.string.twenty3_MICHAEL_FARADAY));
            toolbar.setTitle(R.string.twenty3);
            relativeLayoutStory.setBackgroundResource(R.drawable.faraday);
        }
        if (i == 24) {
            textViewStory.setText(rs.getString(R.string.twenty4_JAMES_CLERK_MAXWELL));
            toolbar.setTitle(R.string.twenty4);
            relativeLayoutStory.setBackgroundResource(R.drawable.maxwell);
        }
        if (i == 25) {
            textViewStory.setText(rs.getString(R.string.twenty5_MARTIN_LUTHER));
            toolbar.setTitle(R.string.twenty5);
            relativeLayoutStory.setBackgroundResource(R.drawable.martin_luther);
        }
        if (i == 26) {
            textViewStory.setText(rs.getString(R.string.twenty6_GEORGE_WASHINGTON));
            toolbar.setTitle(R.string.twenty6);
            relativeLayoutStory.setBackgroundResource(R.drawable.washington);
        }
        if (i == 27) {
            textViewStory.setText(rs.getString(R.string.twenty7_KARL_MARX));
            toolbar.setTitle(R.string.twenty7);
            relativeLayoutStory.setBackgroundResource(R.drawable.karl_marx);
        }
        if (i == 28) {
            textViewStory.setText(rs.getString(R.string.twenty8_ORVILLE_WRIGHT_and_WILBUR_WRIGHT));
            toolbar.setTitle(R.string.twenty8);
            relativeLayoutStory.setBackgroundResource(R.drawable.wright_bro);
        }
        if (i == 29) {
            textViewStory.setText(rs.getString(R.string.twenty9_GENGHIS_KHAN));
            toolbar.setTitle(R.string.twenty9);
            relativeLayoutStory.setBackgroundResource(R.drawable.gengish_khan);
        }
        if (i == 30) {
            textViewStory.setText(rs.getString(R.string.thirty_ADAM_SMITH));
            toolbar.setTitle(R.string.thirty);
            relativeLayoutStory.setBackgroundResource(R.drawable.adam_smith);
        }
        if (i == 31) {
            textViewStory.setText(rs.getString(R.string.thirty1_WILLIAM_SHAKESPEARE));
            toolbar.setTitle(R.string.thirty1);
            relativeLayoutStory.setBackgroundResource(R.drawable.shakespear);
        }
        if (i == 32) {
            textViewStory.setText(rs.getString(R.string.thirty2_JOHN_DALTON));
            toolbar.setTitle(R.string.thirty2);
            relativeLayoutStory.setBackgroundResource(R.drawable.dalton);
        }
        if (i == 33) {
            textViewStory.setText(rs.getString(R.string.thirty3_ALEXANDER_THE_GREAT));
            toolbar.setTitle(R.string.thirty3);
            relativeLayoutStory.setBackgroundResource(R.drawable.alexander);
        }
        if (i == 34) {
            textViewStory.setText(rs.getString(R.string.thirty4_NAPOLEON_BONAPARTE));
            toolbar.setTitle(R.string.thirty4);
            relativeLayoutStory.setBackgroundResource(R.drawable.nepoleon);
        }
        if (i == 35) {
            textViewStory.setText(rs.getString(R.string.thirty5_THOMAS_EDISON));
            toolbar.setTitle(R.string.thirty5);
            relativeLayoutStory.setBackgroundResource(R.drawable.thomas_edison);
        }
        if (i == 36) {
            textViewStory.setText(rs.getString(R.string.thirty6_ANTONY_VAN_LEEUWENHOEK));
            toolbar.setTitle(R.string.thirty6);
            relativeLayoutStory.setBackgroundResource(R.drawable.leeuwenhuk);
        }
        if (i == 37) {
            textViewStory.setText(rs.getString(R.string.thirty7_WILLIAM_T_C_MORTON));
            toolbar.setTitle(R.string.thirty7);
            relativeLayoutStory.setBackgroundResource(R.drawable.morton);
        }
        if (i == 38) {
            textViewStory.setText(rs.getString(R.string.thirty8_GUGLIELMO_MARCONI));
            toolbar.setTitle(R.string.thirty8);
            relativeLayoutStory.setBackgroundResource(R.drawable.marconi);
        }
        if (i == 39) {
            textViewStory.setText(rs.getString(R.string.thirty9_ADOLF_HITLER));
            toolbar.setTitle(R.string.thirty9);
            relativeLayoutStory.setBackgroundResource(R.drawable.hitler);
        }
        if (i == 40) {
            textViewStory.setText(rs.getString(R.string.forty_PLATO));
            toolbar.setTitle(R.string.forty);
            relativeLayoutStory.setBackgroundResource(R.drawable.plato);
        }
        if (i == 41) {
            textViewStory.setText(rs.getString(R.string.fortyOne_OLIVERCROMWELL));
            toolbar.setTitle(R.string.forty1);
            relativeLayoutStory.setBackgroundResource(R.drawable.oliver_cromwell);
        }
        if (i == 42) {
            textViewStory.setText(rs.getString(R.string.forty2_ALEXANDER_GRAHAM_BELL));
            toolbar.setTitle(R.string.forty2);
            relativeLayoutStory.setBackgroundResource(R.drawable.alexander_graham_bell);
        }
        if (i == 43) {
            textViewStory.setText(rs.getString(R.string.forty3_ALEXANDER_FLEMING));
            toolbar.setTitle(R.string.forty3);
            relativeLayoutStory.setBackgroundResource(R.drawable.alexander_fleming);
        }
        if (i == 44) {
            textViewStory.setText(rs.getString(R.string.forty4_JOHN_LOCKE));
            toolbar.setTitle(R.string.forty4);
            relativeLayoutStory.setBackgroundResource(R.drawable.john_locke);
        }
        if (i == 45) {
            textViewStory.setText(rs.getString(R.string.forty5_LUDWIG_VAN_BEETHOVEN));
            toolbar.setTitle(R.string.forty5);
            relativeLayoutStory.setBackgroundResource(R.drawable.ludwig);
        }
        if (i == 46) {
            textViewStory.setText(rs.getString(R.string.forty6_WERNER_HEISENBERG));
            toolbar.setTitle(R.string.forty6);
            relativeLayoutStory.setBackgroundResource(R.drawable.heisenberg);
        }
        if (i == 47) {
            textViewStory.setText(rs.getString(R.string.forty7_LOUIS_DAGUERRE));
            toolbar.setTitle(R.string.forty7);
            relativeLayoutStory.setBackgroundResource(R.drawable.louis_daguerre);
        }
        if (i == 48) {
            textViewStory.setText(rs.getString(R.string.forty8_SIMON_BOLIVAR));
            toolbar.setTitle(R.string.forty8);
            relativeLayoutStory.setBackgroundResource(R.drawable.simon_bolivar);
        }
        if (i == 49) {
            textViewStory.setText(rs.getString(R.string.forty9_RENE_DESCARTES));
            toolbar.setTitle(R.string.forty9);
            relativeLayoutStory.setBackgroundResource(R.drawable.descarte);
        }
        if (i == 50) {
            textViewStory.setText(rs.getString(R.string.fifty_MICHEL_ANGELO));
            toolbar.setTitle(R.string.fifty);
            relativeLayoutStory.setBackgroundResource(R.drawable.michle_angelo);
        }
        if (i == 51) {
            textViewStory.setText(rs.getString(R.string.fifty1_POPE_URBAN_II));
            toolbar.setTitle(R.string.fifty1);
            relativeLayoutStory.setBackgroundResource(R.drawable.pope_urban);
        }
        if (i == 52) {
            textViewStory.setText(rs.getString(R.string.fifty2_UMAR_IBN_AL_KHATTAB));
            toolbar.setTitle(R.string.fifty2);
            relativeLayoutStory.setBackgroundResource(R.drawable.umr_al_khattab);
        }
        if (i == 53) {
            textViewStory.setText(rs.getString(R.string.fifty3_ASOKA));
            toolbar.setTitle(R.string.fifty3);
            relativeLayoutStory.setBackgroundResource(R.drawable.asoka);
        }
        if (i == 54) {
            textViewStory.setText(rs.getString(R.string.fifty4_ST_AUGUSTINE));
            toolbar.setTitle(R.string.fifty4);
            relativeLayoutStory.setBackgroundResource(R.drawable.augustine);
        }
        if (i == 55) {
            textViewStory.setText(rs.getString(R.string.fifty5_WILLIAM_HARVEY));
            toolbar.setTitle(R.string.fifty5);
            relativeLayoutStory.setBackgroundResource(R.drawable.william_harvey);
        }
        if (i == 56) {
            textViewStory.setText(rs.getString(R.string.fifty6_ERNEST_RUTHERFORD));
            toolbar.setTitle(R.string.fifty6);
            relativeLayoutStory.setBackgroundResource(R.drawable.rutherford);
        }
        if (i == 57) {
            textViewStory.setText(rs.getString(R.string.fifty7_JOHN_CAL_VIN));
            toolbar.setTitle(R.string.fifty7);
            relativeLayoutStory.setBackgroundResource(R.drawable.john_calvin);
        }
        if (i == 58) {
            textViewStory.setText(rs.getString(R.string.fifty8_GREGOR_MENDEL));
            toolbar.setTitle(R.string.fifty8);
            relativeLayoutStory.setBackgroundResource(R.drawable.mendel);
        }
        if (i == 59) {
            textViewStory.setText(rs.getString(R.string.fifty9_MAX_PLANCK));
            toolbar.setTitle(R.string.fifty9);
            relativeLayoutStory.setBackgroundResource(R.drawable.max_plank);
        }
        if (i == 60) {
            textViewStory.setText(rs.getString(R.string.sixty_JOSEPH_LISTER));
            toolbar.setTitle(R.string.sixty);
            relativeLayoutStory.setBackgroundResource(R.drawable.joseph_lister);
        }
    }

    private void initToolBar() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.ten);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
