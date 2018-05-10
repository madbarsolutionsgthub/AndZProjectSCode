package com.csi.los.Activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class ActivitySignUp extends AppCompatActivity {
    EditText editTextEmail,editTextPassword,editTextConfirmPassword,editTextName,
            editTextMobile;
    EditText borrowerName,borrowerEmail,borrowerPassword,borrowerMobile;
    Button buttonSignUp,buttonLogin;
    TextView textViewSignUp;
    String name,email,password,mobile,confirmPassword;
    private SpotsDialog loadingDialog;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        setUpUI();
        onClick();
        onClickForSignup();

        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/lobster.ttf");
        //password matching
        /*editTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String password1,password2;
                password1 = editTextPassword.getText().toString();
                password2 = editTextConfirmPassword.getText().toString();
                if(password1.equals(password2)){
                }
                else {
                    editTextConfirmPassword.setError("Password Do Not Match");
                    editTextConfirmPassword.requestFocus();
                }
            }
        });
        */
        /*buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password1,password2,email;
                try {
                    password1 = editTextPassword.getText().toString();
                    password2 = editTextConfirmPassword.getText().toString();
                    email = editTextEmail.getText().toString();
                    if (TextUtils.isEmpty(email)) {
                        editTextEmail.setError("Enter Email address");
                        editTextEmail.requestFocus();
                    } else if (TextUtils.isEmpty(password1)) {
                        editTextPassword.setError("Enter Password");
                        editTextPassword.requestFocus();
                    } else if (TextUtils.isEmpty(password2)) {
                        editTextConfirmPassword.setError("Re-Enter Password");
                        editTextConfirmPassword.requestFocus();
                    } else if (password1.equals(password2)) {
                    } else {
                        editTextConfirmPassword.setError("Password do not match");
                        editTextConfirmPassword.requestFocus();
                    }
                }catch (Exception e){}
            }
        });
        */
    }

    private void sendData() {
        loadingDialog.show();
        RequestQueue queue = Volley.newRequestQueue(ActivitySignUp.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.CUSTOMER_SIGN_UP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response",response);
                Toast.makeText(ActivitySignUp.this, "Sign up success", Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivitySignUp.this, error.toString(), Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", name);
                params.put("password",password);
                params.put("phone",mobile);
                params.put("email_address",email);
                return params;
            }};
        queue.add(stringRequest);

    }

    private void onClick() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void onClickForSignup(){

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                mobile = editTextMobile.getText().toString();
                password = editTextPassword.getText().toString();
                confirmPassword = editTextConfirmPassword.getText().toString();
                if (name.isEmpty()){
                    editTextName.setError("Please enter your name");
                    editTextName.requestFocus();
                }
                else if (email.isEmpty()){
                    editTextEmail.setError("Please enter your email");
                    editTextEmail.requestFocus();
                }
                else if (mobile.isEmpty()){
                    editTextMobile.setError("Please enter mobile no");
                    editTextMobile.requestFocus();
                }
                else if (password.isEmpty()){
                    editTextPassword.setError("Please enter password");
                    editTextPassword.requestFocus();
                }
                else if (password.equals(confirmPassword) ){
                    sendData();
                }
                else {
                    Toast.makeText(ActivitySignUp.this, "Password don't match!!", Toast.LENGTH_LONG).show();
                }

            }});}

    private void setUpUI() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSignUp= (Button) findViewById(R.id.login);
        editTextName= (EditText) findViewById(R.id.editTextYourName);
        editTextEmail= (EditText) findViewById(R.id.editTextYourEmail);
        editTextMobile=(EditText) findViewById(R.id.editTextYourMobile);
        editTextPassword=(EditText)findViewById(R.id.password);
        editTextConfirmPassword=(EditText)findViewById(R.id.confirmPassword);
    }
}
