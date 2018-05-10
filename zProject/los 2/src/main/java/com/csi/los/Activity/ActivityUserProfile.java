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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

public class ActivityUserProfile extends AppCompatActivity {
    public static  String globalPreferenceName="ActivityUserProfile";
    public static  String globalPreferenceNameForId="USER";
    CircleImageView circleImageViewProfile;
    ScrollView scrollViewDown;
    LinearLayout linearLayoutApplyForLoan,linearLayoutLoanStatus;
    private TextView bankUsername;
    ImageView imageViewLogout;
    SharedPreferences sharedPreferencesUser;
    Context context = this;
    private SpotsDialog loadingDialog;
    String image,userName;
    public static final String USER_NAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String IMAGE = "IMAGE";
    public static final String PHONE = "PHONE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);

        Typeface typefaceRegular = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Regular.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Bold.ttf");
        Typeface typefaceThin = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Thin.ttf");

        initUI();
        ClickListener();
        logoutForCustomer();
        bankUsername.setTypeface(typefaceThin);
        sharedPreferencesUser = getSharedPreferences(globalPreferenceNameForId,MODE_PRIVATE);
        SharedPreferences sharedPreferencesId= getSharedPreferences(ActivityLoginUser.globalPreferenceNameForId,MODE_PRIVATE);
        bankUsername.setText(sharedPreferencesId.getString(ActivityLoginUser.USER_NAME,"name"));
        userName =sharedPreferencesId.getString(ActivityLoginUser.USER_NAME,"name");
        //Log.d("Image URL",sharedPreferencesId.getString(ActivityLoginUser.IMAGE));
        String d =sharedPreferencesId.getString(ActivityLoginUser.IMAGE,"image");
        Log.d("user name",userName);
        Glide.with(context).load(sharedPreferencesId.getString(ActivityLoginUser.IMAGE,"IMAGE"))
                .placeholder(R.drawable.profile_thumb)
                .error(R.drawable.profile_thumb)
                .into(circleImageViewProfile);
    }
    private void ClickListener() {
        linearLayoutApplyForLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This should be replaced
                startActivity(new Intent(ActivityUserProfile.this,ActivityLoanCaseEntryCustomer.class));
            }
        });
        circleImageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.show();
                SharedPreferences sharedPreferencesId= getSharedPreferences(ActivityLoginUser.globalPreferenceNameForId,MODE_PRIVATE);
                final int currentSessionId= sharedPreferencesId.getInt("Log_id",7676);
                RequestQueue queue = Volley.newRequestQueue(ActivityUserProfile.this);
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
                                Intent intent= new Intent(ActivityUserProfile.this,ActivityEditProfile.class);
                                SharedPreferences.Editor editor = sharedPreferencesUser.edit();
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
                        Toast.makeText(ActivityUserProfile.this, "User Name or Password don't match", Toast.LENGTH_LONG).show();
                        loadingDialog.dismiss();
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

        linearLayoutLoanStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.show();
                SharedPreferences sharedPreferencesId= getSharedPreferences(ActivityLoginUser.globalPreferenceNameForId,MODE_PRIVATE);
                final int currentSessionId= sharedPreferencesId.getInt("Log_id",7676);
                RequestQueue queue = Volley.newRequestQueue(ActivityUserProfile.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.LOAN_STATUS_LIST, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseText) {
                        try {
                            JSONObject jsonObject= new JSONObject(responseText);
                            String loanArray = jsonObject.getString("loan");
                            Log.d("RESPONSE",responseText);
                            //Toast.makeText(context,responseText,Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor=getSharedPreferences(globalPreferenceName,MODE_PRIVATE).edit();
                            editor.putString("DOOOO",loanArray);
                            editor.commit();
                            Intent intent = new Intent(ActivityUserProfile.this, ActivityLoanStatus.class);
                            startActivity(intent);
                            loadingDialog.dismiss();
                        } catch (JSONException e) {
                            loadingDialog.dismiss();
                            Toast.makeText(ActivityUserProfile.this, "Not applied for any loan yet" , Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadingDialog.dismiss();
                         Toast.makeText(ActivityUserProfile.this, "Not applied for any loan yet" , Toast.LENGTH_LONG).show();
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
    public void logoutForCustomer(){
        imageViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context, R.style.MyDialogTheme);
                alertDialogBuilder.setTitle(R.string.logout);
                alertDialogBuilder.setMessage("Do You Want to Logout??")
                        .setCancelable(false)

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sharedPreferencesUser = getSharedPreferences(globalPreferenceNameForId, MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferencesUser.edit();
                                editor.clear();
                                editor.commit();
                                startActivity(new Intent(ActivityUserProfile.this,ActivityLoginUser.class));
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

    }
    private void initUI() {
        linearLayoutApplyForLoan = (LinearLayout) findViewById(R.id.linearApplyForLoan);
        linearLayoutLoanStatus = (LinearLayout) findViewById(R.id.linearLoanStatus);
        circleImageViewProfile = (CircleImageView) findViewById(R.id.imageViewProfile);
        imageViewLogout = (ImageView) findViewById(R.id.imageViewLogout);
        bankUsername= (TextView) findViewById(R.id.textViewBankAgentUserName);
    }
}


