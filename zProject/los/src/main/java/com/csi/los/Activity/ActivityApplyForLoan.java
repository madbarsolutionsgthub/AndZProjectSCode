package com.csi.los.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.csi.los.R;
import com.csi.los.Utility.MonthToText;

import java.util.Calendar;

public class ActivityApplyForLoan extends AppCompatActivity {
    Spinner spinnerGender;
    EditText editTextDateOfBirth;
    //calender
    Calendar calendar;
    int year,day,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_loan);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initUI();
        spinnerDropDown();
        selectDate();
    }
    private void selectDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);

        editTextDateOfBirth.setText(MonthToText.mothNameText(day + "-" + (month + 1) + "-" + year));
        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {
                        editTextDateOfBirth.setText(MonthToText.mothNameText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + mYear));
                    }
                }, year, month, day);
                //datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });
    }
    private Context getActivity() {
        return this;
    }

    private void spinnerDropDown() {
        ArrayAdapter gender = ArrayAdapter.createFromResource(this, R.array.GENDER, R.layout.spinner_item);
        gender.setDropDownViewResource(R.layout.spinner_list);
        spinnerGender.setAdapter(gender);
    }
    private void initUI() {
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        editTextDateOfBirth = (EditText) findViewById(R.id.editTextDateOfBirth);
    }
}
