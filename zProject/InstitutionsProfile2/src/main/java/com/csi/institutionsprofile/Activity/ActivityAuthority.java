package com.csi.institutionsprofile.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.csi.institutionsprofile.adapter.CustomListAdapter;
import com.csi.institutionsprofile.model.Authority;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dmax.dialog.SpotsDialog;

import static android.R.id.list;

public class ActivityAuthority extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewAuthority;
    Context context=this;
    TextView number,emailAddress;
    private static final String URL_AUTHORITY = Constants.Api.API_AUTHORITY_ALL;
    List<Authority> authorityList;
    RecyclerView recyclerViewAuthority;
    //private Dialog loadingDialog;
    private SpotsDialog loadingDialog;
     ImageView imageViewNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        initToolBar();
        setupUI();
        OnClick();
        laodAuthority();

        recyclerViewAuthority.setHasFixedSize(true);
        recyclerViewAuthority.setLayoutManager(new LinearLayoutManager(this));
        authorityList = new ArrayList<>();

            }

    private void OnClick() {

    }
    private void laodAuthority() {
        //loadingDialog = ProgressDialog.show(ActivityAuthority.this, "", "Loading......");
        loadingDialog.show();
        try {
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_AUTHORITY,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("response", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                //JSONArray products = (JSONArray) jsonObject.getJSONObject("products").getJSONArray("data");
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                //Toast.makeText(ActivityAuthority.this, response, Toast.LENGTH_LONG).show();
                                if ( 0 == jsonArray.length() ) {
                                    //Toast.makeText(ActivityAuthority.this, "No Authority available", Toast.LENGTH_LONG).show();
                                    imageViewNoData.setVisibility(View.VISIBLE);
                                }
                                Log.d("data", response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    authorityList.add(new Authority(
                                            jsonObject1.getInt("id"),
                                            jsonObject1.getString("first_name")+" "+jsonObject1.getString("last_name"),
                                            jsonObject1.getString("designation"),
                                            jsonObject1.getString("mobile_no"),
                                            jsonObject1.getString("email"),
                                            jsonObject1.getString("image_url")
                                    ));
                                }
                                AuthorityAdapter adapter = new AuthorityAdapter(ActivityAuthority.this, authorityList);
                                recyclerViewAuthority.setAdapter(adapter);
                                recyclerViewAuthority.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).color(Color.TRANSPARENT).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            loadingDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ActivityAuthority.this, "No internet connection!", Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();
                            //Log.d("error", error.getLocalizedMessage());
                        }
                    });
            Volley.newRequestQueue(this).add(stringRequest);
        }catch (Exception e){}
            //Log.d("RESULT",Constants.JSON_RESULT);
        }

    private void setupUI() {
        //listViewAuthority = (ListView) findViewById(R.id.listviewAuthority);
        recyclerViewAuthority = (RecyclerView) findViewById(R.id.recylcerViewAuthority);
        imageViewNoData = (ImageView) findViewById(R.id.imageViewNoData);
    }
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.AuthorityTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
