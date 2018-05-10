package com.csi.institutionsprofile.adapter;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.csi.institutionsprofile.Activity.ActivityDeveloper;
import com.csi.institutionsprofile.Activity.ActivityViewNotice;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;
import com.csi.institutionsprofile.model.Authority;
import com.csi.institutionsprofile.model.Notice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jahid on 10/29/17.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    private Context context;
    private List<Notice> noticeList;
    private ProgressDialog pDialog;
    DownloadManager downloadManager;
    ArrayList<Long> list = new ArrayList<>();
    private long refid;
    Notice notice;

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
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        context.registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
         notice = noticeList.get(position);
        //loading the image
       /* Glide.with(context)
                .load(authority.getImage())
                .into(holder.imageViewProfilePic);*/
        holder.textViewTitle.setText(notice.getTitle());
        holder.textViewDetails.setText(Constants.Api.API_AUTHORITY_PDF_URL+notice.getPdf());
        //holder.textViewDate.setText(notice.getDate());

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        /*holder.buttonNoticeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityViewNotice.class);
                intent.putExtra("title", holder.textViewTitle.getText().toString());
                intent.putExtra("description", holder.textViewDetails.getText().toString());
                intent.putExtra("date", holder.textViewDate.getText().toString());
                context.startActivity(intent);
            }
        });*/
        holder.buttonNoticeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Download_Uri = Uri.parse(holder.textViewDetails.getText().toString());
                //Uri Download_Uri = Uri.parse("http://103.11.136.159/hsspapi/public/upload/pdf/1514263310.pdf");

                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle(notice.getPdf());
                request.setDescription("HSSP Notice");
                request.setVisibleInDownloadsUi(true);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/HSSP"  + "/" + notice.getPdf());

                refid = downloadManager.enqueue(request);
                list.add(refid);
                //new DownloadFile().execute(holder.textViewDetails.getText().toString());
                Log.d("text", holder.textViewDetails.getText().toString());
            }
        });
    }
    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Log.e("IN", "" + referenceId);
            list.remove(referenceId);
            if (list.isEmpty())
            {
                Log.e("INSIDE", "" + referenceId);
                File pdfFile = new File("/storage/emulated/0/Download/HSSP/"+notice.getPdf());
                Uri path = Uri.fromFile(pdfFile);
                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                pdfIntent.setDataAndType(path, "application/pdf");
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, pdfIntent, 0);
                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Notification mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.hssp_logo)
                                .setContentTitle("HSSP Notice")
                                .setContentText("Download completed")
                                .setContentIntent(pendingIntent).build() ;

                mBuilder.flags |= Notification.FLAG_AUTO_CANCEL;
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(455, mBuilder);

            }

        }
    };
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

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //showpDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {

            String fileUrl = strings[0];

            String fileName = strings[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //hidepDialog();
            Toast.makeText(context, "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
