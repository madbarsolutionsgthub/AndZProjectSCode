package com.csi.institutionsprofile.adapter;

/**
 * Created by shohan on 12/19/16.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csi.institutionsprofile.R;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    public final String[] name;
    public final String[] designition;
    public final String[] mobile;
    public final String[] email;
    int [] images;

    public CustomListAdapter(Activity context, String[] name,String[] designition,String[] mobile,String[] email,int[] images ) {
        super(context, R.layout.authority_card, name);

        // TODO Auto-generated constructor stub

        this.context=context;
        this.name=name;
        this.designition=designition;
        this.mobile=mobile;
        this.email=email;
        this.images=images;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.authority_card, null,true);

        TextView name1 = (TextView) rowView.findViewById(R.id.textViewPrincipleName);
        TextView  designition1= (TextView) rowView.findViewById(R.id.textViewPosition);
        TextView mobile1 = (TextView) rowView.findViewById(R.id.textViewMobileNo);
        TextView email1 = (TextView) rowView.findViewById(R.id.textViewEmailNo);
        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imageViewProfilePic);

        name1.setText(name[position]);
        designition1.setText(designition[position]);
        mobile1.setText(mobile[position]);
        email1.setText(email[position]);
        imageView1.setImageResource(images[position]);


        return rowView;

    };
}
