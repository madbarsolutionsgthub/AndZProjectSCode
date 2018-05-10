package com.csi.los.Activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
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
import java.util.Set;
import java.util.TreeMap;

import dmax.dialog.SpotsDialog;

public class ActivityContactInfoCustomer extends AppCompatActivity {
    EditText personalEmailAddress, personalPhoneNumber;
    Button btn, buttonAttach;
    ImageView imageViewBackPress, imageViewAttach;
    String persinalEmailString, personalPhoneNumberString;
    private SpotsDialog loadingDialog;
    String image_data;
    Context context = this;
    private int RESULT_LOAD_IMAGE = 1, REQUEST_CAMERA = 0;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_customer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        setupUI();
        onClick();
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
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
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RESULT_LOAD_IMAGE);
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

    //image choose for profile picture
    /*@Override
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
            imageViewAttach.setImageBitmap(thumbnail);
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
            imageViewAttach.setImageBitmap(bmp);
        }
    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }*/

    private void sendData() {
        SharedPreferences sharedPreferences = getSharedPreferences(ActivityFamilyInfoCustomer.globalPreferenceName, MODE_PRIVATE);
        SharedPreferences sharedPreferencesId = getSharedPreferences(ActivityLoginUser.globalPreferenceNameForId, MODE_PRIVATE);
        //This field is received by API ...This is not an entry..But this is needed in every session login
        final int currentSessionId = sharedPreferencesId.getInt("Log_id", 7676);

        //ActivityContactInfo.class Data... No of data=2


        /*startActivityLoanEntryFromHere ... No of entry 6*/
        final String a = sharedPreferences.getString("A", "no");//branchnameString...1
        final String b = sharedPreferences.getString("B", "no1");//loanTypeString...2
        final String c = sharedPreferences.getString("C", "no2");//subLoanTypeString...3
        final String d = sharedPreferences.getString("D", "no3");//editTextLoanAmountString....4
        final String e = sharedPreferences.getString("E", "no4");//editTextLoanAmountTendorString....5
        final String f = sharedPreferences.getString("F", "no5");//editTextNoOfBorrowerString....6

         /*EndAntivityLoanEntryHere*/


         /*start ActivityPersonalInfoCustomerFromHere... No of entry 13*/
        final String g = sharedPreferences.getString("G", "n6");//takendateInString.........1
        final String h = sharedPreferences.getString("H", "no7");//takenGender.....2
        final String i = sharedPreferences.getString("I", "no8");//takenResidentialField.....3
        final String j = sharedPreferences.getString("J", "no9");//takenCountry........4
        final String k = sharedPreferences.getString("K", "no10");//takenCity...........5
        final String l = sharedPreferences.getString("L", "no11");//takenBankAccountNumberField.........6
        final String m = sharedPreferences.getString("M", "no12");//takenCustomerCodeField................7
        final String n = sharedPreferences.getString("N", "no13");//takenFullnameField..................8
        final String o = sharedPreferences.getString("O", "no14");//takennationalIdField..............9
        final String p = sharedPreferences.getString("P", "no15");//takenAddressField................10
        final String q = sharedPreferences.getString("Q", "no16");//takenPostCodeField................11
        final String r = sharedPreferences.getString("R", "no17");//takenYearField...................12
        final String s = sharedPreferences.getString("S", "no18");//takenMonthField...................13
         /*End AvtivityPersonalInfoCustomerFromHere*/


         /*startActivity ActivityfamilyInfocustomer.... no of entry */
        final String t = sharedPreferences.getString("T", "no19");//employeeProfessionString............1
        final String u = sharedPreferences.getString("U", "no20");//employeeCompanyNameString............2
        final String v = sharedPreferences.getString("V", "no21");//spocenameString......................3
        final String w = sharedPreferences.getString("W", "no22");//fatherNameString.....................4
        final String x = sharedPreferences.getString("X", "no23");//motherNameString.....................5
        final String y = sharedPreferences.getString("Y", "no24");//combinedYearMonthdate...............6
        final String department = sharedPreferences.getString("departmentString1", "no25");//7
        final String designation = sharedPreferences.getString("designation1", "no26");//8
        final String businessNature = sharedPreferences.getString("businessnatureString1", "no27");//9
        /*final Bitmap image = ((BitmapDrawable) imageViewAttach.getDrawable()).getBitmap();
        final String imageBitmap = getStringImage(image);*/
                /*EndActivity ContactInfoCustomer*/

         /*From here the data will send*/
        //Log.d("HERE",a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t+u+v+w+x+y+persinalEmailString+personalPhoneNumberString);
        Log.d("LoanEntryClass", a + b + c + d + e + f);//6
        Log.d("PersonalInfocustomer", g + h + i + j + k + l + m + n + o + p + q + r + s);//13
        Log.d("Family", t + u + v + w + x + y + department + designation + businessNature);//9
        Log.d("ContactInfo", persinalEmailString + personalPhoneNumberString);//2...........6+13+9+2=30+LogID====31 field

        loadingDialog.show();
        RequestQueue queue = Volley.newRequestQueue(ActivityContactInfoCustomer.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.LOAN_APPLY_CUSTOMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String responseText) {

                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    String status = jsonObject.getString("status");




                    Log.d("Response", responseText);
                    if (status == Integer.toString(1)) {
                        Toast.makeText(ActivityContactInfoCustomer.this,
                                "Your Loan Request has been Submitted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActivityContactInfoCustomer.this, ActivityUserProfile.class);
                        startActivity(intent);
                        finish();

                        new Handler().postDelayed(new Runnable() {


                            @Override
                            public void run() {
                                NotificationManager notificationManager = (NotificationManager) getSystemService(context.NOTIFICATION_SERVICE);
                                Notification notification = new Notification.Builder(getApplicationContext()).setContentTitle("Loan Applied Successful").setContentText(d).setContentTitle(n).setSmallIcon(R.drawable.logo).build();
                                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                                notificationManager.notify(0, notification);
                            }
                        }, 10000);

                    } else {
                        Toast.makeText(ActivityContactInfoCustomer.this,
                                "Request failed,Try Again Later", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(ActivityContactInfoCustomer.this,ActivityUserProfile.class);
                        startActivity(intent);
                        loadingDialog.dismiss();

                    }


                } catch (JSONException e) {
                    loadingDialog.dismiss();
                    Toast.makeText(ActivityContactInfoCustomer.this,
                            "Network Problem.Try Again Later", Toast.LENGTH_SHORT).show();
                    /*Intent intent= new Intent(ActivityContactInfoCustomer.this,ActivityUserProfile.class);
                    startActivity(intent);*/
                    Log.d("Error", "HI");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityContactInfoCustomer.this, error.toString(), Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
            }
        })

        {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                image_data=getStringImage(bitmap);

                map.put("bank_branch", a);
                map.put("loan_type", b);
                map.put("sub_loan_type", c);//3
                map.put("loan_amount", d);//4
                map.put("loan_duration", e);//5
                map.put("no_of_borrowers", f);//6
                map.put("dob", g);//7
                map.put("gender", h);//8
                map.put("residential_status", i);//9
                map.put("country", j);//10
                map.put("city", k);//11
                map.put("ac_no", l);//12
                //map.put("code",m);//13
                map.put("customer_type", "customer");//13
                map.put("name", n);//14
                map.put("nid", o);//15
                map.put("address", p);//16
                map.put("post_code", q);//17
                map.put("living_period_year", r);//18
                map.put("living_period_month", s);//19
                map.put("profession", t);//20
                map.put("company_name", u);//21
                map.put("spouse_name", v);//22
                map.put("father_name", w);//23
                map.put("mother_name", x);//24
                map.put("from", y);//25
                map.put("email_address", persinalEmailString);//26
                map.put("phone", personalPhoneNumberString);//27
                map.put("department", department);//28
                map.put("designation", designation);//29
                map.put("business_nature", businessNature);//30
                map.put("attachment",image_data);
                map.put("log_id", String.valueOf(currentSessionId));//31
                //  params.put("confirm_password",borrowerConformationOfPassaword);
                Log.d("All Data", map.toString());
                //Toast.makeText(context,""+image_data,Toast.LENGTH_LONG).show();
                //personalEmailAddress.setText(image_data);
                return map;

            }
        };

        queue.add(stringRequest);

       // Log.d("OkKKKKKKKKK", persinalEmailString + personalPhoneNumberString);
    }

   /* private String getStringImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK)
        {
            if(requestCode==REQUEST_CAMERA)
            {
                Bundle extras=data.getExtras();
                bitmap= (Bitmap) extras.get("data");
                imageViewAttach.setImageBitmap(bitmap);

            }

            else if(requestCode==RESULT_LOAD_IMAGE)
            {
                Uri selected=data.getData();
                try {
                    bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selected);
                    image_data=getStringImage(bitmap);

                    Toast.makeText(getApplicationContext(),""+image_data,Toast.LENGTH_LONG).show();
                    imageViewAttach.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgbytes = byteArrayOutputStream.toByteArray();
        String f=Base64.encodeToString(imgbytes, Base64.DEFAULT);
        return Base64.encodeToString(imgbytes, Base64.DEFAULT);
    }

    private void onClick() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persinalEmailString = personalEmailAddress.getText().toString();
                personalPhoneNumberString = personalPhoneNumber.getText().toString();

                if (persinalEmailString.isEmpty()) {
                    personalEmailAddress.setError("Enter Email");
                    personalEmailAddress.requestFocus();
                } else if (personalPhoneNumberString.isEmpty()) {
                    personalPhoneNumber.setError("Enter Email");
                    personalPhoneNumber.requestFocus();
                } else {
                    sendData();

                }
            }
        });
    }

    private void setupUI() {
        personalEmailAddress = (EditText) findViewById(R.id.editTextPersonalEmail);
        personalPhoneNumber = (EditText) findViewById(R.id.editTextPersonalPhoneNo);
        btn = (Button) findViewById(R.id.buttonSubmit);
        buttonAttach = (Button) findViewById(R.id.buttonAttach);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        imageViewAttach = (ImageView) findViewById(R.id.imageViewAttachment);
    }

}