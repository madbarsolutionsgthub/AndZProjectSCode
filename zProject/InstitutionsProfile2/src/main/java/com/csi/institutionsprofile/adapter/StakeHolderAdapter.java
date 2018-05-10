package com.csi.institutionsprofile.adapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.institutionsprofile.Activity.ActivityDeveloper;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.model.Stakeholder;

import java.util.List;

/**
 * Created by Jahid on 10/29/17.
 */

public class StakeHolderAdapter extends RecyclerView.Adapter<StakeHolderAdapter.StakeHolderViewHolder> {
    private Context context;
    private List<Stakeholder> stakeholderList;

    public StakeHolderAdapter(Context context, List<Stakeholder> stakeholderList) {
        this.context = context;
        this.stakeholderList = stakeholderList;
    }
    @Override
    public StakeHolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stakeholder_card, null);
        return new StakeHolderViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final StakeHolderViewHolder holder, int position) {
        Stakeholder stakeholder = stakeholderList.get(position);

        //holder.textViewName.setText(stakeholder.getName());
        holder.textViewName.setText(stakeholder.getDesignation()+" of "+stakeholder.getDistrict());
        //holder.textViewDistrict.setText(stakeholder.getDistrict());
        holder.textViewMobile.setText(stakeholder.getMobile());
        //holder.textViewEmail.setText(stakeholder.getEmail());


        String mobieNo,emailNo;
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
        /*holder.imageViewEmail.setOnClickListener(new View.OnClickListener() {
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
        });*/
    }
    @Override
    public int getItemCount() {
        return stakeholderList.size();
    }

    public class StakeHolderViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName,textViewDesignation,textViewDivision,textViewDistrict,textViewMobile,textViewEmail;
        ImageView imageViewCall,imageViewSMS,imageViewEmail;
        public StakeHolderViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewPrincipleName);
            textViewDesignation = (TextView) itemView.findViewById(R.id.textViewPosition);
            textViewDistrict = (TextView) itemView.findViewById(R.id.textViewDistrictName);
            textViewMobile = (TextView) itemView.findViewById(R.id.textViewMobileNo);
            textViewEmail = (TextView) itemView.findViewById(R.id.textViewEmailNo);
            imageViewCall = (ImageView) itemView.findViewById(R.id.imageViewCall);
            imageViewSMS = (ImageView) itemView.findViewById(R.id.imageViewSMS);
            imageViewEmail = (ImageView) itemView.findViewById(R.id.imageViewEmail);

        }
    }
}
