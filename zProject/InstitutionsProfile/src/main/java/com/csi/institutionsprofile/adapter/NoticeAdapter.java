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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.csi.institutionsprofile.Activity.ActivityDeveloper;
import com.csi.institutionsprofile.Activity.ActivityViewNotice;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.model.Authority;
import com.csi.institutionsprofile.model.Notice;

import java.util.List;

/**
 * Created by Jahid on 10/29/17.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    private Context context;
    private List<Notice> noticeList;

    public NoticeAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }
    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notice_card, null);
        return new NoticeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final NoticeViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        //loading the image
       /* Glide.with(context)
                .load(authority.getImage())
                .into(holder.imageViewProfilePic);*/
        holder.textViewTitle.setText(notice.getTitle());
        holder.textViewDetails.setText(notice.getDetails());
        holder.textViewDate.setText(notice.getDate());

        holder.buttonNoticeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityViewNotice.class);
                intent.putExtra("title", holder.textViewTitle.getText().toString());
                intent.putExtra("description", holder.textViewDetails.getText().toString());
                intent.putExtra("date", holder.textViewDate.getText().toString());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewDetails, textViewDate;
        Button buttonNoticeDetails;
        public NoticeViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewNoticeTitle);
            textViewDetails = (TextView) itemView.findViewById(R.id.textViewNoticeDetails);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewNoticePublishDate);
            buttonNoticeDetails = (Button) itemView.findViewById(R.id.buttonNoticeDetails);

        }
    }
}
