package com.csi.los.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.csi.los.R;
import com.csi.los.Utility.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class ActivityHome extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout linearLayoutLoanCaseEntry,linearLayoutSearch,linearLayoutNotInitiatedLoans;
    TextView textViewLoanCaseEntry,textViewBankAgentName,textViewExistingLoanTitle,textViewNotInitiatedLoan;
    EditText editTextSearch;
    CircleImageView circleImageViewProfilePicAgent;
    ImageView imageViewLogout;
    public static  String globalPreferenceNameForIdForAgent="com.honda.profile";
    Context context = this;
    SharedPreferences sharedPreferences;
    String image,userName;
    private SpotsDialog loadingDialog;
    public static final String USER_NAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String IMAGE = "IMAGE";
    public static final String PHONE = "PHONE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        setUpUI();
        clickListstener();
        Typeface typefaceRegular = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Regular.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Bold.ttf");
        Typeface typefaceThin = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Thin.ttf");
        textViewBankAgentName.setTypeface(typefaceThin);
        textViewExistingLoanTitle.setTypeface(typefaceThin);
        textViewLoanCaseEntry.setTypeface(typefaceRegular);
        textViewNotInitiatedLoan.setTypeface(typefaceRegular);

        sharedPreferences = getSharedPreferences(Login.globalPreferenceNameForIdForAgent,MODE_PRIVATE);
        textViewBankAgentName.setText(sharedPreferences.getString(Login.USER_NAME,"name"));
        userName = sharedPreferences.getString(Login.USER_NAME,"name");
        Glide.with(context).load(sharedPreferences.getString(Login.IMAGE,"IMAGE"))
                .placeholder(R.drawable.profile_thumb)
                .error(R.drawable.profile_thumb)
                .into(circleImageViewProfilePicAgent);
    }

    private void clickListstener() {
        linearLayoutLoanCaseEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,ActivityLoanCaseEntry.class);
                startActivity(intent);
            }
        });
        linearLayoutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,ActivityLoanSearch.class);
                startActivity(intent);
            }
        });
        editTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityHome.this,ActivityLoanSearch.class);
                startActivity(intent);
            }
        });
        imageViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context, R.style.MyDialogTheme);
                alertDialogBuilder.setTitle(R.string.logout);
                alertDialogBuilder.setMessage("Do You Want to Logout??")
                        .setCancelable(false)
                        //.setIcon(R.drawable.logout_icon)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sharedPreferences = getSharedPreferences(globalPreferenceNameForIdForAgent, MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.clear();
                                editor.commit();
                                startActivity(new Intent(ActivityHome.this,Login.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        linearLayoutNotInitiatedLoans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        circleImageViewProfilePicAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.show();
                SharedPreferences sharedPreferencesId = getSharedPreferences(Login.globalPreferenceNameForIdForAgent,MODE_PRIVATE);
                final int currentSessionId = sharedPreferencesId.getInt("Log_id",7676);
                RequestQueue queue = Volley.newRequestQueue(ActivityHome.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.USER_PROFILE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseText) {
                        try {
                            JSONObject jsonObject= new JSONObject(responseText);
                            int Log_ID = jsonObject.getInt("log_id");
                            Log.d("LOG_ID", Integer.toString(Log_ID));
                            Log.d("Response", responseText);
                            String username= jsonObject.getString("username");
                            String message = jsonObject.getString("email_address");
                            String phone = jsonObject.getString("phone");
                            image = jsonObject.getString("image");
                            if(username.equals(userName) ){
                                Intent intent= new Intent(ActivityHome.this,ActivityAgentAccount.class);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("Log_id",Log_ID);
                                editor.putString(USER_NAME, username);
                                editor.putString(EMAIL, message);
                                editor.putString(PHONE, phone);
                                editor.putString(IMAGE, image);
                                editor.commit();
                                //finish();
                                startActivity(intent);
                               loadingDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loadingDialog.dismiss();
                        }
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityHome.this, "User Name or Password don't match", Toast.LENGTH_LONG).show();
                        //loadingDialog.dismiss();
                    }
                })

                {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("log_id",String.valueOf(currentSessionId));
                        return map;
                    }};

                queue.add(stringRequest);
            }
        });
    }

    private void setUpUI() {
        linearLayoutLoanCaseEntry = (LinearLayout) findViewById(R.id.linearLoanCaseEntry);
        linearLayoutNotInitiatedLoans = (LinearLayout) findViewById(R.id.linearNotInitiationLoans);
        linearLayoutSearch = (LinearLayout) findViewById(R.id.linearlayoutSearch);
        textViewLoanCaseEntry = (TextView) findViewById(R.id.textViewLoanCaseEntry);
        textViewBankAgentName = (TextView) findViewById(R.id.textViewBankAgentUserName);
        textViewExistingLoanTitle = (TextView) findViewById(R.id.textViewExistingLoanSearch);
        textViewNotInitiatedLoan = (TextView) findViewById(R.id.textViewNotInitiationLoans);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        imageViewLogout = (ImageView) findViewById(R.id.imageViewLogout);
        circleImageViewProfilePicAgent = (CircleImageView) findViewById(R.id.imageViewProfile);
    }
}
