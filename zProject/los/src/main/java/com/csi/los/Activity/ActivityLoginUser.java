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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class ActivityLoginUser extends AppCompatActivity {

    public static  String globalPreferenceNameForId = "USER";
    TextView textViewSignUp,textViewForgetPassword;
    CheckBox checkBoxShowPassword;
    EditText password,username;
    Button buttonLogin,buttonSignUp;
    ImageView imageViewLogo;
    Animation animation;
    EditText userLoginNameField;
    EditText userLoginPasswordField;
    String userName,userPassword,image;
    private SpotsDialog loadingDialog;
    Context context = this;
    SharedPreferences sharedPreferencesUser;
    public static final String USER_NAME = "USERNAME";
    public static final String EMAIL="EMAIL";
    public static final String IMAGE="IMAGE";
    int Log_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        setupUI();
        OnClick();
        OnClickForLogin();
        setAnimation();

            sharedPreferencesUser = getSharedPreferences(globalPreferenceNameForId,MODE_PRIVATE);
            if(sharedPreferencesUser.contains(USER_NAME) && sharedPreferencesUser.contains(EMAIL)){
            startActivity(new Intent(ActivityLoginUser.this,ActivityUserProfile.class));
            finish();   //finish current activity
        }
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/dancing_script.ttf");
    }
    private void sendData() {
        loadingDialog.show();
        RequestQueue queue = Volley.newRequestQueue(ActivityLoginUser.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.USER_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String responseText) {

                try {
                    JSONObject jsonObject= new JSONObject(responseText);
                    Log_ID = jsonObject.getInt("log_id");
                    Log.d("Log_id",Integer.toString(Log_ID));
                    Log.d("Response",responseText);
                    String username= jsonObject.getString("username");
                    String message = jsonObject.getString("email_address");
                    image = jsonObject.getString("image");
                    if(message.equals(userName) ){
                        Intent intent= new Intent(ActivityLoginUser.this,ActivityUserProfile.class);
                        SharedPreferences.Editor editor = sharedPreferencesUser.edit();
                        editor.putInt("Log_id",Log_ID);
                        editor.putString("userNameForviewname",username);
                        editor.putString(USER_NAME, username);
                        editor.putString(EMAIL, message);
                        editor.putString(IMAGE, image);
                        editor.commit();
                        finish();
                        startActivity(intent);
                        loadingDialog.dismiss();
                    }


                } catch (JSONException e) {
                    Log.d("Error","HI");
                    e.printStackTrace();
                }
                loadingDialog.dismiss();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityLoginUser.this, "User name or password don't match", Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
            }
        })
        {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("username", userName);
                map.put("password",userPassword);
                return map;
            }};

        queue.add(stringRequest);
    }

    private void setAnimation() {
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
    }

    private void OnClick() {
        try {
            buttonSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ActivityLoginUser.this,ActivitySignUp.class);
                    startActivity(intent);
                }
            });
        }catch (Exception e){}}


         private void OnClickForLogin(){

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = userLoginNameField.getText().toString();
                userPassword = userLoginPasswordField.getText().toString();
                if (userName.isEmpty()){
                    userLoginNameField.setError("Pelase enter user name");
                    userLoginNameField.requestFocus();
                }
                else if (userPassword.isEmpty()){
                    userLoginPasswordField.setError("Pelase enter user password");
                    userLoginPasswordField.requestFocus();
                }
                else {
                    sendData();
                }
            }
        });

            }

    private void setupUI() {
        /*password = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.userId);*/
        //This button is for login
        buttonLogin = (Button) findViewById(R.id.login);
        imageViewLogo = (ImageView) findViewById(R.id.logo);
        textViewForgetPassword = (TextView) findViewById(R.id.forget_password);
        //This Button is for SignUp
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        userLoginNameField= (EditText) findViewById(R.id.userId);
        userLoginPasswordField= (EditText) findViewById(R.id.password);

    }
}
