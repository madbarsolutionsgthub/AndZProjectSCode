package com.csi.institutionsprofile.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.csi.institutionsprofile.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayoutSearchInstitute,linearLayoutCustomerServices,linearLayoutAuthority,linearLayoutDeveloper,linearLayoutRelatedLinks,linearLayoutMilestone,linearLayoutProjectDetails,linearLayoutStakeholder,linearLayoutNotice,linearLayoutFounder;
    EditText editTextSearch;
    ImageView imageViewSetting,imageViewGuidlines;
    Context context = this;
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        onClick();
    }

    private void onClick() {
        try {
            linearLayoutSearchInstitute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        if (checkPermission()) {
                            try {
                                startActivity(new Intent(MainActivity.this,ActivityInstituteSearch.class));
                            }catch (Exception e){}
                            Log.e("value", "Permission already Granted, Now you can call.");
                        } else {
                            requestPermission();
                        }
                    } else {
                        Log.e("value", "Not required for requesting runtime permission");
                    }
                }
            });
            linearLayoutCustomerServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityCustomerServices.class));
                }
            });
            linearLayoutAuthority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        if (checkPermission()) {
                            try {
                                startActivity(new Intent(MainActivity.this,ActivityAuthority.class));
                            }catch (Exception e){}
                            Log.e("value", "Permission already Granted, Now you can call.");
                        } else {
                            requestPermission();
                            startActivity(new Intent(MainActivity.this,ActivityAuthority.class));
                        }
                    } else {
                        Log.e("value", "Not required for requesting runtime permission");
                    }
                }
            });
            linearLayoutDeveloper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityDeveloper.class));
                }
            });
            linearLayoutRelatedLinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityRelatedLinks.class));
                }
            });
            linearLayoutMilestone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        startActivity(new Intent(MainActivity.this, ActivityMilestoneView.class));
                    }catch (Exception e){}
                }
            });
            linearLayoutProjectDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityProjectDetails.class));
                }
            });
            linearLayoutStakeholder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        if (checkPermission()) {
                            try {
                                startActivity(new Intent(MainActivity.this,ActivityStakeholder.class));
                            }catch (Exception e){}
                            Log.e("value", "Permission already Granted, Now you can call.");
                        } else {
                            requestPermission();
                        }
                    } else {
                        Log.e("value", "Not required for requesting runtime permission");
                    }
                }
            });
            linearLayoutNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        if (checkPermission()) {
                            try {
                                startActivity(new Intent(MainActivity.this,ActivityNotice.class));
                            }catch (Exception e){}
                            Log.e("value", "Permission already Granted, Now you can call.");
                        } else {
                            requestPermission();
                        }
                    } else {
                        Log.e("value", "Not required for requesting runtime permission");
                    }
                }
            });
            linearLayoutFounder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ActivityFounder.class));
                }
            });
            editTextSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        if (checkPermission()) {
                            try {
                                startActivity(new Intent(MainActivity.this,ActivityInstituteSearch.class));
                            }catch (Exception e){}
                            Log.e("value", "Permission already Granted, Now you can call.");
                        } else {
                            requestPermission();
                        }
                    } else {
                        Log.e("value", "Not required for requesting runtime permission");
                    }
                }
            });
            imageViewSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,TestActivity.class));
                }
            });

        }catch (Exception e){}
    }
    private boolean checkPermission() {
        int permissionCALL = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        int readStoragePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeStoragePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (readStoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (writeStoragePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionCALL != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,


                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.CALL_PHONE)) {
            Toast.makeText(MainActivity.this, "Call Phone permission allows us to do a call. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
        }
    }
    @Override    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Permission Granted Successfully. Write working code here.
                } else {
                    //You did not accept the request can not use the functionality.
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context, R.style.MyDialogThemeExit);
        alertDialogBuilder.setTitle("বন্ধ করুন");
        alertDialogBuilder.setIcon(R.drawable.warning);

        alertDialogBuilder.setMessage("আপনি কি অ্যাপটি বন্ধ করতে চান?")
                .setCancelable(false)
                .setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("না", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void setupUI() {
        linearLayoutSearchInstitute = (LinearLayout) findViewById(R.id.linearlayoutSearch);
        linearLayoutCustomerServices = (LinearLayout) findViewById(R.id.linearCustomerService);
        linearLayoutAuthority = (LinearLayout) findViewById(R.id.linearAuthority);
        linearLayoutDeveloper = (LinearLayout) findViewById(R.id.linearConnectDeveloper);
        linearLayoutRelatedLinks = (LinearLayout) findViewById(R.id.linearRelatedLinks);
        linearLayoutMilestone = (LinearLayout) findViewById(R.id.linearAchievement);
        linearLayoutProjectDetails = (LinearLayout) findViewById(R.id.linearProjectDetails);
        linearLayoutStakeholder = (LinearLayout) findViewById(R.id.linearStackHolder);
        linearLayoutNotice = (LinearLayout) findViewById(R.id.linearNotice);
        linearLayoutFounder = (LinearLayout) findViewById(R.id.linearFounder);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        imageViewSetting = (ImageView) findViewById(R.id.imageViewSetting);
    }
}
