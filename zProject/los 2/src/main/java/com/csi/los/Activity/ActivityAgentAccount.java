package com.csi.los.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import dmax.dialog.SpotsDialog;

public class ActivityAgentAccount extends AppCompatActivity {
    CircleImageView circleImageView;
    ImageView imageViewPromotAgentName,imageViewPromotAgentEmail,imageViewPromotAgentPassword,imageViewPromotAgentMobileNo,imageViewBackPress;
    EditText editTextAgentName,editTextAgentEmail,editTextAgentMobileNo,editTextAgentPassword;
    Button buttonProfileUpdate;
    Context context=this;
    String oldPassword,oldPasswordMatch;
    private int RESULT_LOAD_IMAGE = 1,REQUEST_CAMERA = 0;
    SharedPreferences sharedPreferences;
    private SpotsDialog loadingDialog;
    StringRequest stringRequest;
    String message;
    public static  String globalPreferenceNameForIdForAgent="com.honda.profile";
    SharedPreferences sharedPreferencesAgent;
    public static final String USER_NAME = "USERNAME_AGENT";
    public static final String EMAIL="EMAIL_AGENT";
    public static final String PASSWORD="PASSWORD_AGENT";
    public static final String MOBILE="MOBILE_AGENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        sharedPreferencesAgent = getSharedPreferences(globalPreferenceNameForIdForAgent,MODE_PRIVATE);

        sharedPreferences = getSharedPreferences(Login.globalPreferenceNameForIdForAgent,MODE_PRIVATE);
        String mobile = sharedPreferences.getString(Login.MOBILE,"");
        Log.d("MOBILE",mobile);

        setUpUI();
        promotClick();
        profileUpdate();
        Typeface typefaceRegular = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Regular.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Bold.ttf");
        buttonProfileUpdate.setTypeface(typefaceBold);
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });


        editTextAgentName.setText(sharedPreferences.getString(ActivityHome.USER_NAME,""));
        editTextAgentEmail.setText(sharedPreferences.getString(ActivityHome.EMAIL,""));
        editTextAgentMobileNo.setText(sharedPreferences.getString(ActivityHome.PHONE,""));
        Glide.with(context).load(sharedPreferences.getString(ActivityHome.IMAGE,"IMAGE"))
                .placeholder(R.drawable.profile_thumb)
                .error(R.drawable.profile_thumb)
                .into(circleImageView);
        //editTextAgentMobileNo.setText(sharedPreferences.getString(Login.MOBILE,""));
        //editTextAgentPassword.setText(sharedPreferences.getString(Login.PASSWORD,""));
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
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

    private void promotClick() {
        imageViewPromotAgentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editTextPromptValue = (EditText) promptsView.findViewById(R.id.editTextPromptValue);
                String title;
                title= "Name";
                new AlertDialog.Builder(context,R.style.MyDialogTheme)
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
                                editTextAgentName.setText(name);
                            }
                        })
                        .show();
            }
        });
        imageViewPromotAgentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Email Can't be Changed",Toast.LENGTH_LONG).show();
                /*LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editTextPromptValue = (EditText) promptsView.findViewById(R.id.editTextPromptValue);
                editTextPromptValue.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                String title;
                title= "Email";
                new AlertDialog.Builder(context,R.style.MyDialogTheme)
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
                                editTextAgentEmail.setText(name);
                            }
                        })
                        .show();*/
            }
        });
        imageViewPromotAgentMobileNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editTextPromptValue = (EditText) promptsView.findViewById(R.id.editTextPromptValue);
                editTextPromptValue.setInputType(InputType.TYPE_CLASS_PHONE);
                String title;
                title= "Mobile";
                new AlertDialog.Builder(context,R.style.MyDialogTheme)
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
                                editTextAgentMobileNo.setText(name);
                            }
                        })
                        .show();
            }
        });
        imageViewPromotAgentPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.prompts_password, null);
                final EditText editTextPromptOldPassword = (EditText) promptsView.findViewById(R.id.editTextPromptOldPassword);
                final EditText editTextPromptNewPassword = (EditText) promptsView.findViewById(R.id.editTextPromptNewPassword);
                final EditText editTextPromptConfirmPassword = (EditText) promptsView.findViewById(R.id.editTextPromptNewConfirmPassword);

                String title;
                oldPassword=editTextAgentPassword.getText().toString();
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
                new AlertDialog.Builder(context,R.style.MyDialogTheme)
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
                                editTextAgentPassword.setText(password);
                            }
                        })
                        .show();
            }
        });
    }

    private void setUpUI() {
        imageViewPromotAgentName = (ImageView) findViewById(R.id.imagePromotAgentName);
        imageViewPromotAgentEmail = (ImageView) findViewById(R.id.imagePromotAgentEmail);
        imageViewPromotAgentPassword = (ImageView) findViewById(R.id.imagePromotAgentPassword);
        imageViewPromotAgentMobileNo = (ImageView) findViewById(R.id.imagePromotAgentMobileNo);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        editTextAgentName = (EditText) findViewById(R.id.editTextAgentName);
        editTextAgentEmail = (EditText) findViewById(R.id.editTextAgentEmail);
        editTextAgentMobileNo = (EditText) findViewById(R.id.editTextAgentMobileNo);
        editTextAgentPassword = (EditText) findViewById(R.id.editTextAgentPassword);
        buttonProfileUpdate = (Button)findViewById(R.id.buttonProfileUpdatee);
        circleImageView=(CircleImageView) findViewById(R.id.user_profile_photo);
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
            circleImageView.setImageBitmap(thumbnail);
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
            circleImageView.setImageBitmap(bmp);
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

    private void profileUpdate(){

        buttonProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.show();
                SharedPreferences sharedPreferencesId= getSharedPreferences(Login.globalPreferenceNameForIdForAgent,MODE_PRIVATE);
                final int currentSessionId= sharedPreferencesId.getInt("Log_id",7676);
                Log.d("OKK1KKKKKKKKKKK", String.valueOf(currentSessionId));
                final String editTextUsername = editTextAgentName.getText().toString();
                final  String editTextUserpassword = editTextAgentPassword.getText().toString();
                final String editTextMobileNumber = editTextAgentMobileNo.getText().toString();
                final Bitmap image = ((BitmapDrawable) circleImageView.getDrawable()).getBitmap();
                final String imageBitmap = getStringImage(image);

                RequestQueue queue = Volley.newRequestQueue(ActivityAgentAccount.this);
                stringRequest = new StringRequest(Request.Method.POST, Constants.API.PROFILE_EDIT_AGENT, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseText) {
                        loadingDialog.dismiss();
                        try {
                            JSONObject jsonObject= new JSONObject(responseText);
                            String status = jsonObject.getString("status");
                            String username = jsonObject.getString("username");
                            //String email = jsonObject.getString("email_address");
                            Log.d("Ok",status);
                            // String forNewIntentStatus=status.toString();
                            if(status==Integer.toString(1)){
                                Toast.makeText(context, "Profile udpate successful", Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor = sharedPreferencesAgent.edit();
                                editor.putString(USER_NAME, username);
                                editor.commit();
                            }

                            else if (status == Integer.toString(0)){
                                Toast.makeText(context, "Username already exists.", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }


                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Username already exists.", Toast.LENGTH_LONG).show();
                        loadingDialog.dismiss();
                    }
                })

                {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("username",editTextUsername);
                        map.put(" password",editTextUserpassword);
                        map.put("phone",editTextMobileNumber);
                        map.put("image",imageBitmap);
                        map.put("log_id",String.valueOf(currentSessionId));
                        Log.d("MAP",map.toString());
                        return map;
                    }};
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                queue.add(stringRequest);
                 }

            private String getStringImage(Bitmap image) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                return encodedImage;
            }
        });

    }

}

