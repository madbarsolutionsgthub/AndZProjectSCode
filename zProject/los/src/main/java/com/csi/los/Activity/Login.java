package com.csi.los.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class Login extends AppCompatActivity {
    public static  String globalPreferenceNameForIdForAgent="com.honda.profile";
    CheckBox checkBoxShowPassword;
    EditText password,username;
    TextView textViewForgetPassword,textViewLoginTitle,textViewLoginDes;
    Button buttonLogin;
    ImageView imageViewLogo;
    Animation animation;
    ShowHidePasswordEditText showHidePasswordEditText;
    private SpotsDialog loadingDialog;
    Context context = this;
    String UserLoginWithEmailOrUserName,UserLoginWithpassword,image;
    SharedPreferences sharedPreferencesAgent;
    public static final String USER_NAME = "USERNAME_AGENT";
    public static final String EMAIL="EMAIL_AGENT";
    public static final String PASSWORD ="PASSWORD_AGENT";
    public static final String MOBILE = "MOBILE_AGENT";
    public static final String IMAGE = "IMAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        initUI();
        setAnimation();
        sharedPreferencesAgent = getSharedPreferences(globalPreferenceNameForIdForAgent,MODE_PRIVATE);
        if(sharedPreferencesAgent.contains(USER_NAME) && sharedPreferencesAgent.contains(EMAIL)){
            startActivity(new Intent(Login.this,ActivityHome.class));
            finish();   //finish current activity
        }

        Typeface typefaceThin = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Thin.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Bold.ttf");

        buttonLogin.setTypeface(typefaceBold);
        textViewLoginTitle.setTypeface(typefaceBold);
        textViewLoginDes.setTypeface(typefaceThin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLoginWithEmailOrUserName= username.getText().toString();
                UserLoginWithpassword= showHidePasswordEditText.getText().toString();
                if(UserLoginWithEmailOrUserName.isEmpty()){
                    username.setError("Please enter User Name");
                    username.requestFocus();
                }
                else if(UserLoginWithpassword.isEmpty()){
                    showHidePasswordEditText.setError("Please enter User Name");
                    showHidePasswordEditText.requestFocus();
                }
                else {
                    sendData();
                }

            }
        });
    }

    private void sendData() {
        loadingDialog.show();
        RequestQueue queue = Volley.newRequestQueue(Login.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.AGENT_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String responseText) {
                try {
                    JSONObject jsonObject= new JSONObject(responseText);
                    int Log_ID = jsonObject.getInt("log_id");
                    Log.d("LOG_ID", Integer.toString(Log_ID));
                    Log.d("Response", responseText);
                    String username= jsonObject.getString("username");
                    String message = jsonObject.getString("email_address");
                    image = jsonObject.getString("image");
                    //String password = jsonObject.getString("password");
                    //String mobile = jsonObject.getString("phone");
                    Log.d("RAYHAN",message);
                    if(message.equals(UserLoginWithEmailOrUserName) ){
                        Log.d("What","Winner");
                        Intent intent= new Intent(Login.this,ActivityHome.class);
                        SharedPreferences.Editor editor = sharedPreferencesAgent.edit();
                        editor.putInt("Log_id",Log_ID);
                        editor.putString(USER_NAME, username);
                        editor.putString(EMAIL, message);
                        editor.putString(IMAGE, image);
                        //editor.putString(PASSWORD, password);
                        //editor.putString(MOBILE, mobile);
                        editor.commit();
                        finish();
                        startActivity(intent);
                        loadingDialog.dismiss();
                    }
                    //Else will be removed later
                } catch (JSONException e) {
                    e.printStackTrace();
                    loadingDialog.dismiss();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "User Name or Password don't match", Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
            }
        })

        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", UserLoginWithEmailOrUserName);
                map.put("password",UserLoginWithpassword);
                return map;
            }};

        queue.add(stringRequest);
    }

    private void setAnimation() {
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        //imageViewLogo.setAnimation(animation);
    }
    private void initUI() {
        username = (EditText) findViewById(R.id.userId);
        buttonLogin = (Button) findViewById(R.id.login);
        imageViewLogo = (ImageView) findViewById(R.id.logo);
        textViewForgetPassword = (TextView) findViewById(R.id.forget_password);
        textViewLoginTitle = (TextView) findViewById(R.id.textViewLoginTitle);
        textViewLoginDes = (TextView) findViewById(R.id.textViewLoginTitleDes);
        showHidePasswordEditText = (ShowHidePasswordEditText) findViewById(R.id.password);
    }
}
