package com.csi.los.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.csi.los.R;
import com.csi.los.Utility.MonthToText;

import java.util.Calendar;

public class ActivityFamilyInfo extends AppCompatActivity {
    EditText spouseName,spousedesignation,spouseBussinessLength,spouseMonthlyIncome,spouseOfficeMobile,spouseMobile,spousefatherName,spousefatherMobileNumber,spouseMotherName,spouseMotherMobileNumber,employmentDepartment,employmentDesignation,employmentBusinessNature,LivingPeriodYear,LivingPeriodMonth,LivingPeriodDate;
    Spinner spouseProfession,spouseCompany,spousefatherProfession,spouseFatherCompanyName,spouseMotherProfession,spouseMotherCompanyName,employmentProfession,employmentCompanyName;
    public static  String globalPreferenceNameForFamilyInfo="com.familyInfo.rayhan";
    Button buttonNext;
    ImageView imageViewBackPress;
    Calendar calendar;
    int year,day,month;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        spinnerDropDown();
        onClickForfamily();
        selectDate();

        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void selectDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);

        LivingPeriodYear.setText(MonthToText.mothNameText(day + "-" + (month + 1) + "-" + year));
        LivingPeriodYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {
                        LivingPeriodYear.setText(MonthToText.mothNameText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + mYear));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void setupUI(){

        buttonNext = (Button) findViewById(R.id.buttonNext);
        spouseName= (EditText) findViewById(R.id.editTextSpouseName);
        /*spousedesignation= (EditText) findViewById(R.id.editTextSpouseDesignation);
        spouseBussinessLength= (EditText) findViewById(R.id.editTextSpouseServiceBusinessLength);
        spouseMonthlyIncome= (EditText) findViewById(R.id.editTextSpouseMonthlyIncome);
        spouseOfficeMobile= (EditText) findViewById(R.id.editTextSpouseoffice_phone_number);*/
        spouseMobile= (EditText) findViewById(R.id.editTextSpouseMobileNo);
        spousefatherName= (EditText) findViewById(R.id.editTextFathersName);
        spousefatherMobileNumber= (EditText) findViewById(R.id.editTextFathersMobileNo);
        spouseMotherName= (EditText) findViewById(R.id.editTextMothersName);
        spouseMotherMobileNumber= (EditText) findViewById(R.id.editTextMothersMobileNo);
        employmentDepartment= (EditText) findViewById(R.id.editTextDepartment);
        employmentDesignation= (EditText) findViewById(R.id.editTextApplicantDesignation);
        employmentBusinessNature= (EditText) findViewById(R.id.editTextBusinessNature);
        LivingPeriodYear= (EditText) findViewById(R.id.editTextYearsFrom);
        //LivingPeriodMonth= (EditText) findViewById(R.id.editTextMonthFrom);
        //LivingPeriodDate= (EditText) findViewById(R.id.editTextDateFrom);
        spouseProfession= (Spinner) findViewById(R.id.spinnerSpouseProfession);
        //spouseCompany= (Spinner) findViewById(R.id.spinner_company_firm_name);
        spousefatherProfession= (Spinner) findViewById(R.id.spinnerFathersProfession);
        //spouseFatherCompanyName= (Spinner) findViewById(R.id.spinnerfatherCompanyName);
        spouseMotherProfession= (Spinner) findViewById(R.id.spinnerMothersProfession);
        //spouseMotherCompanyName= (Spinner) findViewById(R.id.spinnerMotherCompanyName);
        employmentProfession= (Spinner) findViewById(R.id.spinnerEmplymentProfession);
        employmentCompanyName=(Spinner) findViewById(R.id.spinnerCompanyName);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);

    }


    private void spinnerDropDown() {

        ArrayAdapter spouseProfessionVariable = ArrayAdapter.createFromResource(this, R.array.GENDER, R.layout.spinner_item);
        spouseProfessionVariable.setDropDownViewResource(R.layout.spinner_list);
        spouseProfession.setAdapter(spouseProfessionVariable);


        /*ArrayAdapter spouseCompanyVariable = ArrayAdapter.createFromResource(this, R.array.SPOUSE_COMPANY, R.layout.spinner_item);
        spouseCompanyVariable.setDropDownViewResource(R.layout.spinner_list);
        spouseCompany.setAdapter(spouseCompanyVariable);*/


        /*ArrayAdapter spousefatherProfessionVariable = ArrayAdapter.createFromResource(this, R.array.FATHER_PROFESSION, R.layout.spinner_item);
        spousefatherProfessionVariable.setDropDownViewResource(R.layout.spinner_list);
        spousefatherProfession.setAdapter(spousefatherProfessionVariable);*/



        /*ArrayAdapter spouseFatherCompanyVariable= ArrayAdapter.createFromResource(this, R.array.FATHER_COMPANY, R.layout.spinner_item);
        spousefatherProfessionVariable.setDropDownViewResource(R.layout.spinner_list);
        spouseFatherCompanyName.setAdapter(spouseFatherCompanyVariable);*/

        /*ArrayAdapter spouseMotherProfessionVariable= ArrayAdapter.createFromResource(this, R.array.MOTHER_PROFESSION, R.layout.spinner_item);
        spouseMotherProfessionVariable.setDropDownViewResource(R.layout.spinner_list);
        spouseMotherProfession.setAdapter(spouseMotherProfessionVariable);*/


/*        ArrayAdapter spouseMotherCompanyNameVariable= ArrayAdapter.createFromResource(this, R.array.MOTHER_COMPANY, R.layout.spinner_item);
        spouseMotherCompanyNameVariable.setDropDownViewResource(R.layout.spinner_list);
        spouseMotherCompanyName.setAdapter(spouseMotherCompanyNameVariable);*/


        ArrayAdapter employmentProfessionVariable= ArrayAdapter.createFromResource(this, R.array.EMPLOYMENT_PROFESSION, R.layout.spinner_item);
        employmentProfessionVariable.setDropDownViewResource(R.layout.spinner_list);
        employmentProfession.setAdapter(employmentProfessionVariable);


        ArrayAdapter employmentCompanyNameVariable= ArrayAdapter.createFromResource(this, R.array.EMPLOYMENT_COMPANY, R.layout.spinner_item);
        employmentCompanyNameVariable.setDropDownViewResource(R.layout.spinner_list);
        employmentCompanyName.setAdapter(employmentCompanyNameVariable);

    }

    public void onClickForfamily(){

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spouseameVariable=spouseName.getText().toString();
                String spouseProfessionVariable=spouseProfession.getSelectedItem().toString();
                //String spousedesignationVariable= spousedesignation.getText().toString();
                //String spouseBusinessLeagthVariable= spouseBussinessLength.getText().toString();
                // spouseMonthlyIncomevariable= spouseMonthlyIncome.getText().toString();
                //String spouseOfficeMobileVariable= spouseOfficeMobile.getText().toString();
                //String spouseMobileVariable=spouseMobile.getText().toString();
                String spouseFatherNameVariable= spousefatherName.getText().toString();
                //String spousefatherMobileNumbervariable=spousefatherMobileNumber.getText().toString();
                String spouseMothernamevariable= spouseMotherName.getText().toString();
                //String spouseMotherMobileNumberVariable= spouseMotherMobileNumber.getText().toString();
                String employmentDepartmentVariable= employmentDepartment.getText().toString();
                String employmentDesignationVariable= employmentDesignation.getText().toString();
                String employmentBusinessNaturevariable= employmentBusinessNature.getText().toString();
                String LivingPeriodYearvariable= LivingPeriodYear.getText().toString();
                //String LivingPeriodMonthVariable= LivingPeriodMonth.getText().toString();
                //String LivingPeriodDateVariable= LivingPeriodDate.getText().toString();
                //String spouseCompanyVariable= spouseCompany.getSelectedItem().toString();
                //String spousefatherProfessionVariable= spousefatherProfession.getSelectedItem().toString();
                //String spouseFatherCompanyNameVariable= spouseFatherCompanyName.getSelectedItem().toString();
                //String spouseMotherProfessionvariable= spouseMotherProfession.getSelectedItem().toString();
                //String spouseMotherCompanyNamevariable= spouseMotherCompanyName.getSelectedItem().toString();
                String employmentProfessionvariable= employmentProfession.getSelectedItem().toString();
                String employmentCompanyNameVariable=employmentCompanyName.getSelectedItem().toString();

                if (spouseFatherNameVariable.isEmpty()){
                    spousefatherName.setError("Enter Father's Name");
                    spousefatherName.requestFocus();
                }
                else if (spouseMothernamevariable.isEmpty()){
                    spouseMotherName.setError("Enter Mother's Name");
                    spouseMotherName.requestFocus();
                }
                else if (employmentDesignationVariable.isEmpty()){
                    employmentDesignation.setError("Enter Designation");
                    employmentDesignation.requestFocus();
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceNameForFamilyInfo, MODE_PRIVATE).edit();
                    editor.putString("spouseameVariablefamilyInfo", spouseameVariable);
                    /*editor.putString("spousedesignationVariableFamilyInfo", spousedesignationVariable);
                    editor.putString("spouseBusinessLeagthVariableFamilyInfo", spouseBusinessLeagthVariable);
                    editor.putString("spouseMonthlyIncomevariableFamilyInfo", spouseMonthlyIncomevariable);
                    editor.putString("spouseOfficeMobileVariablefamilyInfo", spouseOfficeMobileVariable);
                    editor.putString("spouseMobileVariableFamilyInfo", spouseMobileVariable);*/
                    editor.putString("spouseFatherNameVariableFamilyInfo", spouseFatherNameVariable);
                    //editor.putString("spousefatherMobileNumbervariableFamilyInfo", spousefatherMobileNumbervariable);
                    editor.putString("spouseMothernamevariableFamilyInfo", spouseMothernamevariable);
                    //editor.putString("spouseMotherMobileNumberVariableFamilyInfo", spouseMotherMobileNumberVariable);
                    editor.putString("employmentDepartmentVariableFamilyInfo", employmentDepartmentVariable);
                    editor.putString("employmentDesignationVariableFamilyInfo", employmentDesignationVariable);
                    editor.putString("employmentBusinessNaturevariablefamilyInfo", employmentBusinessNaturevariable);
                    editor.putString("LivingPeriodYearvariableFamilyInfo", LivingPeriodYearvariable);
                    //editor.putString("LivingPeriodMonthVariableFamilyInfo", LivingPeriodMonthVariable);
                    //editor.putString("LivingPeriodDateVariableFamilyInfo", LivingPeriodDateVariable);
//Spin data
                    editor.putString("spouseProfessionVariableFamilyInfo", spouseProfessionVariable);
                    /*editor.putString("spouseCompanyVariablefamilyInfo", spouseCompanyVariable);
                    editor.putString("spousefatherProfessionVariableFamilyInfo", spousefatherProfessionVariable);
                    editor.putString("spouseFatherCompanyNameVariablefamilyInfo", spouseFatherCompanyNameVariable);
                    editor.putString("spouseMotherProfessionvariableFamilyInfo", spouseMotherProfessionvariable);
                    editor.putString("spouseMotherCompanyNamevariableFamilyInfo", spouseMotherCompanyNamevariable);*/
                    editor.putString("employmentProfessionvariableFamilyInfo", employmentProfessionvariable);
                    editor.putString("employmentCompanyNameVariableFamilyInfo", employmentCompanyNameVariable);
                    editor.commit();
                    startActivity(new Intent(ActivityFamilyInfo.this, ActivityContactInfo.class));
                }

            }
        });

    }
}
