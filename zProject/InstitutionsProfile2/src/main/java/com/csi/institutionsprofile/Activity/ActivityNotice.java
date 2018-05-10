package com.csi.institutionsprofile.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;
import com.csi.institutionsprofile.adapter.AuthorityAdapter;
import com.csi.institutionsprofile.adapter.NoticeAdapter;
import com.csi.institutionsprofile.model.Authority;
import com.csi.institutionsprofile.model.Notice;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class ActivityNotice extends AppCompatActivity {
    Toolbar toolbar;
    Context context=this;
    private static final String URL_NOTICE = Constants.Api.API_NOTICE_ALL;
    List<Notice> noticeList;
    RecyclerView recyclerViewNotice;
    //private Dialog loadingDialog;
    private SpotsDialog loadingDialog;
    ImageView imageViewNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        initToolBar();
        setupUI();
        loadNotice();

        recyclerViewNotice.setHasFixedSize(true);
        recyclerViewNotice.setLayoutManager(new LinearLayoutManager(this));
        noticeList = new ArrayList<>();
    }
    private void loadNotice() {
        //loadingDialog = ProgressDialog.show(ActivityNotice.this, "", "Loading......");
        //loadingDialog = new SpotsDialog(context, R.style.Custom);
        loadingDialog.show();
        try {
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_NOTICE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("response", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                //Toast.makeText(ActivityAuthority.this, response, Toast.LENGTH_LONG).show();
                                if ( 0 == jsonArray.length() ) {
                                    //Toast.makeText(ActivityNotice.this, "No Notice available", Toast.LENGTH_LONG).show();
                                    imageViewNoData.setVisibility(View.VISIBLE);
                                } else  {
                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                        noticeList.add(new Notice(
                                                jsonObject1.getInt("id"),
                                                jsonObject1.getString("notice_title"),
                                                jsonObject1.getString("notice_description"),
                                                jsonObject1.getString("notice_pdf"),
                                                jsonObject1.getString("created_at")
                                        ));
                                    }
                                    NoticeAdapter adapter = new NoticeAdapter(ActivityNotice.this, noticeList);
                                    recyclerViewNotice.setAdapter(adapter);
                                    recyclerViewNotice.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).color(Color.TRANSPARENT).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());

                                    int itemCount = recyclerViewNotice.getAdapter().getItemCount();
                                    if ( 0 != itemCount ){
                                        Toast.makeText(ActivityNotice.this,"Currently "+Integer.toString(itemCount)+" notice are available", Toast.LENGTH_LONG).show();
                                        //Log.d("item",Integer.toString(itemCount));
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            loadingDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ActivityNotice.this, "No internet connection!", Toast.LENGTH_LONG).show();
                            //Log.d("error", error.getLocalizedMessage());
                            loadingDialog.dismiss();
                        }
                    });
            Volley.newRequestQueue(this).add(stringRequest);
        }catch (Exception e){}
    }
    private void setupUI() {
        recyclerViewNotice = (RecyclerView) findViewById(R.id.recylcerViewNotice);
        imageViewNoData = (ImageView) findViewById(R.id.imageViewNoData);
    }
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notice");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
