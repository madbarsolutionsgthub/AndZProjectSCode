package com.csi.institutionsprofile.adapter;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.institutionsprofile.Activity.ActivityDeveloper;
import com.csi.institutionsprofile.Activity.ActivityInstituteSearch;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.model.Email;
import com.csi.institutionsprofile.model.Institute;
import com.csi.institutionsprofile.model.Number;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Created by Jahid on 10/29/17.
 */

public class InstituteAdapter extends RecyclerView.Adapter<InstituteAdapter.InstituteViewHolder> {
    private Context context;
    private List<Institute> instituteList;

    private ArrayList<String> numberArrayList = new ArrayList<>();
    private ArrayList<String> emailArrayList = new ArrayList<>();
    List<String> myList;
    //Trying to make an arraylist and store into list
   // private ArrayList<Number> hold_mobile_numberinList= new ArrayList<Number>();
    public InstituteAdapter(Context context, List<Institute> instituteList) {
        this.context = context;
        this.instituteList = instituteList;
    }
    @Override
    public InstituteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.institute_card, null);
        return new InstituteViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final InstituteViewHolder holder, int position) {
        final Institute institute = instituteList.get(position);

        holder.textViewInstituteName.setText(institute.getInstitute_name());
        holder.textViewDivision.setText(institute.getDivision()+" Division"+",");
        holder.textViewDistrict.setText(institute.getDistrict()+",");
        holder.textViewUpazilla.setText(institute.getUpazilla());
        holder.textViewType.setText(institute.getType());
        holder.textViewCategory.setText(institute.getCategory());
        holder.textViewPrincipleName.setText(institute.getPrinciple_name());
        holder.textViewDesignation.setText("Principal");
        holder.textViewMobile.setText(institute.getMobile());
        holder.textViewEmail.setText(institute.getEmail());


        final String mobieNo,emailNo;
        try {
            mobieNo = holder.textViewMobile.getText().toString();
            emailNo = holder.textViewEmail.getText().toString();
            if(0 == mobieNo.length()){
                holder.imageViewCall.setVisibility(View.INVISIBLE);
                holder.imageViewSMS.setVisibility(View.INVISIBLE);
            }
            if(0 == emailNo.length()){
                holder.imageViewEmail.setVisibility(View.INVISIBLE);
            }
        }catch (Exception e){}

        holder.imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + holder.textViewMobile.getText().toString())));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

            }
        });
        holder.imageViewSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.popup_sms, null);
                final EditText editTextMobileNo = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                editTextMobileNo.setText(holder.textViewMobile.getText().toString());
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
                                smsIntent.putExtra("address"  , editTextMobileNo.getText().toString());
                                smsIntent.putExtra("sms_body"  , editTextTextMessage.getText().toString());
                                try {
                                    context.startActivity(smsIntent);
                                    Log.i("Finished sending SMS...", "");
                                } catch (android.content.ActivityNotFoundException ex) {

                                }
                            }
                        })
                        .show();
            }
        });
        holder.imageViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.popup_email, null);
                final EditText editTextEmailAddress = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                final EditText editTextEmailSubject = (EditText) promptsView.findViewById(R.id.editTextEmailSubject);
                final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                editTextEmailAddress.setText(holder.textViewEmail.getText().toString());

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
                                    context.startActivity(Intent.createChooser(intent, "Send Email"));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    ex.printStackTrace();
                                    Log.e(ActivityDeveloper.class.getName(), ex.getMessage());
                                }
                            }

                        })
                        .show();
            }
        });

        //checkBox
        /*holder.checkBoxMultipleSms.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (holder.checkBoxMultipleSms.isChecked()) {
                    String mobile = holder.textViewMobile.getText().toString();
                    //myList = new ArrayList<String>(Arrays.asList(mobile.split(",")));
                    myList = new ArrayList<String>();
                    myList.addAll(Arrays.asList(mobile.split(",")));
                    for(int i=0;i<myList.size();i++)

                    {

                        Log.d("mobile", myList.get(i)+",");

                    }
                  //  Log.d("mobile", myList.toString());

                }else {
                    Log.d("mobile", "Unchecked");
                }
            }
        });*/

        holder.checkBoxMultipleSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = holder.textViewMobile.getText().toString();
                String email = holder.textViewEmail.getText().toString();
                Number number = new Number(mobile);
                Email email1 = new Email(email);
                ActivityInstituteSearch.floatingActionMenu.setVisibility(View.VISIBLE);
                Institute inti= new Institute(mobile,email);
                //Institute institute1 = new Institute(email);
                if (holder.checkBoxMultipleSms.isChecked()){
                    ActivityInstituteSearch.checkBoxMultiSms.setChecked(false);
                    number.setNumber(mobile);
                    email1.setEmail(email);
                   // Log.d("mobileOrginal", inti.getMobile().toString());
                    numberArrayList.add(inti.getMobile().toString());
                    emailArrayList.add(inti.getEmail().toString());

                    Log.d("mobilerrrrrrrr", numberArrayList.toString());
                    Log.d("email", emailArrayList.toString());
                }
                else {
                    ActivityInstituteSearch.floatingActionMenu.setVisibility(View.INVISIBLE);
                }
            }
        });

        ActivityInstituteSearch.checkBoxMultiSms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    holder.checkBoxMultipleSms.setChecked(false);
                }
            }
        });
        /*ActivityInstituteSearch.checkBoxMultiSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityInstituteSearch.floatingActionMenu.setVisibility(View.VISIBLE);
               *//* for (int i=0;i<instituteList.size();i++){
                    holder.checkBoxMultipleSms.setChecked(true);
                    String mobile = institute.getMobile();
                    String email = holder.textViewEmail.getText().toString();
                    Number number = new Number(mobile);
                    Email email1 = new Email(email);
                    Institute inti= new Institute(mobile,email);
                    if(ActivityInstituteSearch.checkBoxMultiSms.isChecked()){
                        number.setNumber(mobile);
                        email1.setEmail(email);
                        numberArrayList.add(inti.getMobile().toString());
                        emailArrayList.add(inti.getEmail().toString());
                        Log.d("mobileAll", numberArrayList.toString());
                        Log.d("emailAll", emailArrayList.toString());
                    }
                }*//*
            }
        });*/


        //gGroup SMS
        ActivityInstituteSearch.floatingActionButtonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.popup_sms, null);
                final EditText editTextMobileNo = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                //editTextMobileNo.setText(holder.textViewMobile.getText().toString());
                editTextMobileNo.setText(String.valueOf(numberArrayList.toString().replace(',',';')));
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
                                smsIntent.putExtra("address", editTextMobileNo.getText().toString());
                                smsIntent.putExtra("sms_body", editTextTextMessage.getText().toString());
                                try {
                                    context.startActivity(smsIntent);
                                    Log.i("Finished sending SMS...", "");
                                } catch (android.content.ActivityNotFoundException ex) {

                                }

                            }


                        })
                        .show();
            }
        });

        //group email
        ActivityInstituteSearch.floatingActionButtonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
                View promptsView = layoutInflater.inflate(R.layout.popup_email, null);
                final EditText editTextEmailAddress = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                final EditText editTextEmailSubject = (EditText) promptsView.findViewById(R.id.editTextEmailSubject);
                final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                editTextEmailAddress.setText(String.valueOf(emailArrayList.toString().replace("[","").replace("]","")));

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
                                    context.startActivity(Intent.createChooser(intent, "Send Email"));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    ex.printStackTrace();
                                    Log.e(ActivityDeveloper.class.getName(), ex.getMessage());
                                }
                            }

                        })
                        .show();
            }
        });



    }
    @Override
    public int getItemCount() {
        return instituteList.size();
    }

    public class InstituteViewHolder extends RecyclerView.ViewHolder{
        TextView textViewInstituteName,textViewDivision,textViewDistrict,textViewUpazilla,textViewType,textViewCategory,textViewPrincipleName,textViewDesignation,textViewMobile,textViewEmail;
        ImageView imageViewCall,imageViewSMS,imageViewEmail;
        CheckBox checkBoxMultipleSms;
        FloatingActionButton f;


            public InstituteViewHolder(View itemView) {
            super(itemView);

            textViewInstituteName = (TextView) itemView.findViewById(R.id.textViewInstituteName);
            textViewDivision = (TextView) itemView.findViewById(R.id.textViewDivisionName);
            textViewDistrict = (TextView) itemView.findViewById(R.id.textViewDistrictName);
            textViewUpazilla = (TextView) itemView.findViewById(R.id.textViewThanaName);
            textViewType = (TextView) itemView.findViewById(R.id.textViewInstituteType);
            textViewCategory = (TextView) itemView.findViewById(R.id.textViewInstituteCategory);
            textViewPrincipleName = (TextView) itemView.findViewById(R.id.textViewPrincipleName);
            textViewDesignation = (TextView) itemView.findViewById(R.id.textViewPosition);
            textViewMobile = (TextView) itemView.findViewById(R.id.textViewMobileNo);
            textViewEmail = (TextView) itemView.findViewById(R.id.textViewEmailNo);
            imageViewCall = (ImageView) itemView.findViewById(R.id.imageViewCall);
            imageViewSMS = (ImageView) itemView.findViewById(R.id.imageViewSMS);
            imageViewEmail = (ImageView) itemView.findViewById(R.id.imageViewEmail);
            checkBoxMultipleSms = (CheckBox) itemView.findViewById(R.id.chekboxMultiSms);

        }
    }
}
