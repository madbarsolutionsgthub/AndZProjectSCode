package com.csi.los.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityEditProfile extends AppCompatActivity {
    CircleImageView imageViewProfilePicture;
    ImageView imageViewBackPress;
    EditText UserName,UserEmail,Userpassword,UserMobile;
    ImageView imageViewPromotCustomerName,imageViewPromotCustomerEmail,imageViewPromotCustomerPassword,imageViewPromotCustomerMobileNo;
    Button senUserdata;
    String oldPassword;
    private int RESULT_LOAD_IMAGE = 1,REQUEST_CAMERA = 0;
    Context context = this;
    Bitmap bitmap;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        updateUserdata();
        promotClick();

        sharedPreferences = getSharedPreferences(ActivityUserProfile.globalPreferenceNameForId,MODE_PRIVATE);
        UserName.setText(sharedPreferences.getString(ActivityUserProfile.USER_NAME,""));
        UserEmail.setText(sharedPreferences.getString(ActivityUserProfile.EMAIL,""));
        UserMobile.setText(sharedPreferences.getString(ActivityUserProfile.PHONE,""));
        Log.d("Mobile",sharedPreferences.getString(ActivityUserProfile.PHONE,""));
        Glide.with(context).load(sharedPreferences.getString(ActivityUserProfile.IMAGE,"IMAGE"))
                .placeholder(R.drawable.profile_thumb)
                .error(R.drawable.profile_thumb)
                .into(imageViewProfilePicture);

        imageViewProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void promotClick() {
        imageViewPromotCustomerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editTextPromptValue = (EditText) promptsView.findViewById(R.id.editTextPromptValue);
                String title;
                title= "Name";
                new android.app.AlertDialog.Builder(context,R.style.MyDialogTheme)
                        .setTitle(title)
                        .setView(promptsView)
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //alarmEntry.setName(editTextPromptValue.getText().toString());
                                String name= editTextPromptValue.getText().toString();
                                UserName.setText(name);
                            }
                        })
                        .show();
            }
        });
        imageViewPromotCustomerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editTextPromptValue = (EditText) promptsView.findViewById(R.id.editTextPromptValue);
                editTextPromptValue.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                String title;
                title= "Email";
                new android.app.AlertDialog.Builder(context,R.style.MyDialogTheme)
                        .setTitle(title)
                        .setView(promptsView)
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //alarmEntry.setName(editTextPromptValue.getText().toString());
                                String name= editTextPromptValue.getText().toString();
                                UserEmail.setText(name);
                            }
                        })
                        .show();
            }
        });
        imageViewPromotCustomerMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editTextPromptValue = (EditText) promptsView.findViewById(R.id.editTextPromptValue);
                editTextPromptValue.setInputType(InputType.TYPE_CLASS_PHONE);
                String title;
                title= "Mobile";
                new android.app.AlertDialog.Builder(context,R.style.MyDialogTheme)
                        .setTitle(title)
                        .setView(promptsView)
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //alarmEntry.setName(editTextPromptValue.getText().toString());
                                String name= editTextPromptValue.getText().toString();
                                UserMobile.setText(name);
                            }
                        })
                        .show();
            }
        });
        imageViewPromotCustomerPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts_password, null);
                final EditText editTextPromptOldPassword = (EditText) promptsView.findViewById(R.id.editTextPromptOldPassword);
                final EditText editTextPromptNewPassword = (EditText) promptsView.findViewById(R.id.editTextPromptNewPassword);
                final EditText editTextPromptConfirmPassword = (EditText) promptsView.findViewById(R.id.editTextPromptNewConfirmPassword);

                String title;
                oldPassword = Userpassword.getText().toString();
                editTextPromptOldPassword.setText(oldPassword);
                editTextPromptConfirmPassword.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        String password1,password2;
                        password1 = editTextPromptNewPassword.getText().toString();
                        password2 = editTextPromptConfirmPassword.getText().toString();
                        if(password1.equals(password2)){
                        }
                        else {
                            editTextPromptConfirmPassword.setError("Password Do Not Match");
                            editTextPromptConfirmPassword.requestFocus();
                        }
                    }
                });
                title= "Change Passwword";
                new android.app.AlertDialog.Builder(context,R.style.MyDialogTheme)
                        .setTitle(title)
                        .setView(promptsView)
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String password= editTextPromptConfirmPassword.getText().toString();
                                Userpassword.setText(password);
                            }
                        })
                        .show();
            }
        });
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[which].equals("Choose from Library")) {
                    Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                } else if (items[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }

    private Context getActivity() {
        return this;
    }

    private void initUI() {
        imageViewProfilePicture = (CircleImageView) findViewById(R.id.user_profile_photo);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        UserName=(EditText) findViewById(R.id.editTextCustomerName);
        UserEmail=(EditText) findViewById(R.id.editTextCustomerEmail);
        Userpassword=(EditText) findViewById(R.id.editTextCustomerPassword);
        UserMobile= (EditText) findViewById(R.id.editTextCustomerMobileNo);
        senUserdata= (Button) findViewById(R.id.buttonProfileUpdate);
        imageViewPromotCustomerName = (ImageView) findViewById(R.id.imagePromotCustomerName);
        imageViewPromotCustomerEmail = (ImageView) findViewById(R.id.imagePromotCustomerEmail);
        imageViewPromotCustomerPassword = (ImageView) findViewById(R.id.imagePromotCustomerPassword);
        imageViewPromotCustomerMobileNo = (ImageView) findViewById(R.id.imagePromotCustomerMobileNo);
    }

    private void updateUserdata(){

          senUserdata.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  SharedPreferences sharedPreferencesId= getSharedPreferences(ActivityLoginUser.globalPreferenceNameForId,MODE_PRIVATE);
                  //This field is received by API ...This is not an entry..But this is needed in every session login
                  final int currentSessionId= sharedPreferencesId.getInt("Log_id",7676);
                 final String userName=  UserName.getText().toString();
                  final String userPassword=Userpassword.getText().toString();
                  final String userMobile= UserMobile.getText().toString();

                  RequestQueue queue = Volley.newRequestQueue(ActivityEditProfile.this);
                  StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.PROFILE_EDIT_CUSTOMER, new Response.Listener<String>() {
                      @Override
                      public void onResponse(String responseText) {

                          try {
                              JSONObject jsonObject= new JSONObject(responseText);
                              String status = jsonObject.getString("status");
                              Log.d("SEEEEE",status);

                          } catch (JSONException e) {

                              Log.d("Error","HI");
                              e.printStackTrace();
                          }

                      }
                  },new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {

                      }
                  })

                  {

                      protected Map<String, String> getParams() throws AuthFailureError {
                          Map<String, String> map = new HashMap<String, String>();
                          map.put("log_id*",String.valueOf(currentSessionId));
                          map.put("username*",userName);
                          map.put("password",userPassword);
                          map.put("phone**",userMobile);

                          return map;
                      }};

                  queue.add(stringRequest);
              }
          });

    }

    //image choose for profile picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            File destination = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageViewProfilePicture.setImageBitmap(thumbnail);
        }
        else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            imageViewProfilePicture.setImageBitmap(bmp);
        }
    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}

