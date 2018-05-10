package com.csi.institutionsprofile.Database;

import android.content.Context;
import android.widget.TextView;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.model.Student;

/**
 * Created by Jahid on 9/20/17.
 */

public class CustomAdapter extends CursorAdapter {
    TextView txtId,txtName,txtEmail,textAge;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View   view    =    mInflater.inflate(R.layout.institute_card, parent, false);
        ViewHolder holder  =   new ViewHolder();
        //holder.txtId    =   (TextView)  view.findViewById(R.id.textViewDivisionName);
        holder.txtName    =   (TextView)  view.findViewById(R.id.textViewInstituteName);
        holder.txtEmail   =   (TextView)  view.findViewById(R.id.textViewEmailNo);
        //holder.textMobile = (TextView) view.findViewById(R.id.textViewMobileNo);
        holder.textPrinciple = (TextView) view.findViewById(R.id.textViewPrincipleName);
        holder.textPrinciple_position = (TextView) view.findViewById(R.id.textViewPosition);
        holder.district = (TextView) view.findViewById(R.id.textViewDistrictName);
        holder.division = (TextView) view.findViewById(R.id.textViewDivisionName);
        holder.thana = (TextView) view.findViewById(R.id.textViewThanaName);
        holder.mobile = (TextView) view.findViewById(R.id.textViewMobileNo);
        holder.type = (TextView) view.findViewById(R.id.textViewInstituteType);
        holder.category = (TextView) view.findViewById(R.id.textViewInstituteCategory);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //If you want to have zebra lines color effect uncomment below code
        /*if(cursor.getPosition()%2==1) {
             view.setBackgroundResource(R.drawable.item_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor2);
        }*/

        ViewHolder holder  =   (ViewHolder)    view.getTag();
        //holder.txtId.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
        holder.txtName.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
        holder.txtEmail.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_email)));
       // holder.textMobile.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_age)));
        holder.textPrinciple.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_principle)));
        holder.textPrinciple_position.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_principle_position)));
        holder.division.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_division)));
        holder.district.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_district)));
        holder.thana.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_thana)));
        holder.mobile.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_mobile)));
        holder.type.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_type)));
        holder.category.setText(cursor.getString(cursor.getColumnIndex(Student.KEY_category)));

    }

    static class ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtEmail;
        TextView textMobile;
        TextView textPrinciple;
        TextView textPrinciple_position;
        TextView division;
        TextView district;
        TextView thana;
        TextView mobile;
        TextView type;
        TextView category;
    }
}
