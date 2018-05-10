package com.csi.institutionsprofile.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.csi.institutionsprofile.R;

public class ActivityFounder extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageViewCall,imageViewSMS,imageViewEmail;
    String number="+8801715171784";
    String email="ictbangla52@gmail.com";
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_founder);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initToolBar();
        setupUI();
        onClick();
    }

    private void onClick() {
        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number)));
            }
        });
        imageViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.popup_email, null);
                final EditText editTextEmailAddress = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                final EditText editTextEmailSubject = (EditText) promptsView.findViewById(R.id.editTextEmailSubject);
                final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                editTextEmailAddress.setText(email);

                String title;
                title= "SEND EMAIL";
                new AlertDialog.Builder(context,R.style.MyDialogTheme)
                        .setTitle(title)
                        .setView(promptsView)
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                intent.setData(Uri.parse("mailto:"));
                                //intent.setType("message/rfc822");
                                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{editTextEmailAddress.getText().toString()} );
                                intent.putExtra(Intent.EXTRA_SUBJECT, editTextEmailSubject.getText().toString());
                                intent.putExtra(Intent.EXTRA_TEXT, editTextTextMessage.getText().toString());
                                try {
                                    Log.i(ActivityDeveloper.class.getName(), "Sending email. " + "\n title : " + editTextEmailSubject.getText().toString()
                                            + "\n description : " + editTextTextMessage.getText().toString());
                                    startActivity(Intent.createChooser(intent, "Send Email"));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    ex.printStackTrace();
                                    Log.e(ActivityDeveloper.class.getName(), ex.getMessage());
                                }
                            }

                        })
                        .show();
            }
        });
        imageViewSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.popup_sms, null);
                final EditText editTextMobileNo = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                editTextMobileNo.setText(number);

                String title;
                title= "SEND SMS";
                new AlertDialog.Builder(context,R.style.MyDialogTheme)
                        .setTitle(title)
                        .setView(promptsView)
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Log.i("Send SMS", "");
                                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                smsIntent.setData(Uri.parse("smsto:"));
                                smsIntent.setType("vnd.android-dir/mms-sms");
                                smsIntent.putExtra("address"  , number);
                                smsIntent.putExtra("sms_body"  , editTextTextMessage.getText().toString());

                                try {
                                    startActivity(smsIntent);
                                    finish();
                                    Log.i("Finished sending SMS...", "");
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(ActivityFounder.this,
                                            "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
                                }
                                //Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+editTextMobileNo.getText().toString()));
                                //sendIntent.putExtra("sms_body", editTextTextMessage.getText().toString());
                                //startActivity(sendIntent);

                            }

                        })
                        .show();
            }
        });
    }

    private void setupUI() {
        imageViewCall = (ImageView) findViewById(R.id.imageViewCall);
        imageViewSMS = (ImageView) findViewById(R.id.imageViewSMS);
        imageViewEmail = (ImageView) findViewById(R.id.imageViewEmail);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Founder");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
