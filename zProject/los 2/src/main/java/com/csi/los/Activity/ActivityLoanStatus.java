package com.csi.los.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.csi.los.R;
import com.csi.los.Utility.Constants;
import com.csi.los.Utility.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.AdapterForLoanStatus;
import Model.ListItem;
import dmax.dialog.SpotsDialog;


public class ActivityLoanStatus extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    ImageView imageViewBackPress;
    private List<ListItem> listItems;
    private int sessionID;
    public static  String globalPreferenceName="ActivityUserProfilees";
    private SpotsDialog loadingDialog;
    Context context = this;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_status);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerId);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems= new ArrayList<>();
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        SharedPreferences sharedPreferences= getSharedPreferences(ActivityUserProfile.globalPreferenceName,MODE_PRIVATE);
        final String jsonArray= sharedPreferences.getString("DOOOO","jh");
        try {
            JSONArray array = new JSONArray(jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                ListItem item = new ListItem(
                        jsonObject.getString("case_no"),
                        jsonObject.getString("name"),
                        jsonObject.getString("loan_type"),
                        jsonObject.getString("loan_status"),
                        jsonObject.getString("loan_amount")
                );
                Log.d("jjjj",jsonObject.toString());

                listItems.add(item);

                adapter= new AdapterForLoanStatus(listItems,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
          /*  adapter= new AdapterForLoanStatus(listItems,getApplicationContext());
            recyclerView.setAdapter(adapter);*/

            // System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
