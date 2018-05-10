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

import com.bumptech.glide.Glide;
import com.csi.institutionsprofile.Activity.ActivityDeveloper;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;
import com.csi.institutionsprofile.model.Authority;

import java.util.List;

/**
 * Created by Jahid on 10/29/17.
 */

public class AuthorityAdapter extends RecyclerView.Adapter<AuthorityAdapter.AuthorityViewHolder> {
    private Context context;
    private List<Authority> authorityList;

    public AuthorityAdapter(Context context, List<Authority> authorityList) {
        this.context = context;
        this.authorityList = authorityList;
    }

    @Override
    public AuthorityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.authority_card, null);
        return new AuthorityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AuthorityViewHolder holder, int position) {
        Authority authority = authorityList.get(position);

        //loading the image

        Glide.with(context).load(Constants.Api.API_AUTHORITY_IMAGE_URL+ authority.getImage())
                .placeholder(R.drawable.profile_thumb)
                .error(R.drawable.profile_thumb)
                .into(holder.imageViewProfilePic);

        holder.textViewName.setText(authority.getName());
        holder.textViewPosition.setText(authority.getDesignation());
        holder.textViewMobileNo.setText(authority.getMobile());
        holder.textViewEmailNo.setText(authority.getEmail());

        String mobieNo,emailNo;
        String temp = "null";
        try {
            mobieNo = holder.textViewMobileNo.getText().toString();
            emailNo = holder.textViewEmailNo.getText().toString();
            if(0 == mobieNo.length()){
                holder.imageViewCall.setVisibility(View.INVISIBLE);
                holder.imageViewSMS.setVisibility(View.INVISIBLE);
                holder.textViewMobileTitle.setText("");
            }
            if (mobieNo==temp){
                holder.imageViewCall.setVisibility(View.INVISIBLE);
                holder.imageViewSMS.setVisibility(View.INVISIBLE);
                holder.textViewMobileNo.setText("");
            }
            if (emailNo==temp){
                holder.imageViewEmail.setVisibility(View.INVISIBLE);
                holder.textViewEmailNo.setText("");
            }
            if(0 == emailNo.length()){
                holder.imageViewEmail.setVisibility(View.INVISIBLE);
                holder.textViewEmailTitle.setText("");
            }
        }catch (Exception e){}

        holder.imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + holder.textViewMobileNo.getText().toString())));
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
                editTextMobileNo.setText(holder.textViewMobileNo.getText().toString());
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
                editTextEmailAddress.setText(holder.textViewEmailNo.getText().toString());

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
        return authorityList.size();
    }

    public class AuthorityViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName, textViewPosition, textViewMobileNo, textViewEmailNo,textViewMobileTitle,textViewEmailTitle;
        ImageView imageViewProfilePic,imageViewCall,imageViewSMS,imageViewEmail;

        public AuthorityViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewPrincipleName);
            textViewPosition = (TextView) itemView.findViewById(R.id.textViewPosition);
            textViewMobileNo = (TextView) itemView.findViewById(R.id.textViewMobileNo);
            textViewEmailNo = (TextView) itemView.findViewById(R.id.textViewEmailNo);
            imageViewProfilePic = (ImageView) itemView.findViewById(R.id.imageViewProfilePic);
            imageViewCall = (ImageView) itemView.findViewById(R.id.imageViewCall);
            imageViewSMS = (ImageView) itemView.findViewById(R.id.imageViewSMS);
            imageViewEmail = (ImageView) itemView.findViewById(R.id.imageViewEmail);
            textViewMobileTitle = (TextView) itemView.findViewById(R.id.textViewMobileNoTitle);
            textViewEmailTitle = (TextView) itemView.findViewById(R.id.textViewEmailNoTitle);
        }
    }
}
