package com.csi.los.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.csi.los.R;
import com.csi.los.Utility.MonthToText;

import java.util.Calendar;

public class ActivityPersonalInfoCustomer extends AppCompatActivity {
    public static  String globalPreferenceName="SecondVariable";
    Button buttonNext;
    ImageView imageViewBackPress;
    Spinner spinnerGender,spinnerMaritalStatus,spinnerEducationLevel,spinnerCountry,spinnerCity,spinnerParmanentCountry,spinnerParmanentCity,spinnerResidentialField;
    CheckBox checkBoxSameAddress;
    EditText editTextPermanentAddress,editTextPermanentPostCode,editTextPermanentLivingPeriodYear,editTextPermanentLivingPeriodMonth,editTextDateOfBirth,editTextbankAccountNumberField,editTextCustomerCodeField,editTextFullnameField,editTextNationalIdField,editTextAddressField,editTextPostCodeField,editTextYearField,editTextMonthField;
    //calender
    Calendar calendar;
    int year,day,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info_customer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        spinnerDropDown();
        //checkBoxClick();
        selectDate();
        buttonNext = (Button) findViewById(R.id.buttonNext);
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//From here I have received data from ActivityLoancaseEntry.class and store in a variable....This flow of data will be increased by letter
                //Received number of Data 6
                SharedPreferences sharedPreferences= getSharedPreferences(ActivityLoanCaseEntryCustomer.globalPreferenceName,MODE_PRIVATE);
                String a=sharedPreferences.getString("A","no");//1.branchnameString
                String b=sharedPreferences.getString("B","no1");//2.loanTypeString
                String c= sharedPreferences.getString("C","no2");//3.subLoanTypeString
                String d= sharedPreferences.getString("D","no3");//4.editTextLoanAmountString
                String e=sharedPreferences.getString("E","no4");//5.editTextLoanAmountTendorString
                String f= sharedPreferences.getString("F","no5");//6.editTextNoOfBorrowerString


                Log.d("rcv_LonCasEntryfrmPerso",a+b+c+d+e+f);

                //Take ActivityPersonalInfoCustomer.class data
                String takendateInString= editTextDateOfBirth.getText().toString();//7
                String takenGender= spinnerGender.getSelectedItem().toString();//8
                String takenResidentialField=spinnerResidentialField.getSelectedItem().toString();//9
                String takenCountry= spinnerCountry.getSelectedItem().toString();//10
                String takenCity= spinnerCity.getSelectedItem().toString();//11
                String takenBankAccountNumberField=editTextbankAccountNumberField.getText().toString();//12
                String takenCustomerCodeField= editTextCustomerCodeField.getText().toString();//13
                String takenFullnameField= editTextFullnameField.getText().toString();//14
                String takennationalIdField= editTextNationalIdField.getText().toString();//15
                String takenAddressField= editTextAddressField.getText().toString();//16
                String takenPostCodeField= editTextPostCodeField.getText().toString();//17
                String takenYearField= editTextYearField.getText().toString();//18
                String takenMonthField= editTextMonthField.getText().toString();//19
                //Showing ActivityPersonalInfoCustomer+ActivityLoanCaseEntryCustomer
                Log.d("LoanCaseData+Personal",takendateInString+takenGender+takenResidentialField+takenCountry+takenCity+takenBankAccountNumberField+
                        takenCustomerCodeField+takenFullnameField+takennationalIdField+takenAddressField+takenPostCodeField+takenYearField+takenMonthField+
                a+b+c+d+e+f);

                //Sending ActivityLoancaseEntryCustomer+ActivityPersonalInfoCustomar
                //No of sending Field 19
                if (takenBankAccountNumberField.isEmpty()){
                    editTextbankAccountNumberField.setError("Enter Bank Account");
                    editTextbankAccountNumberField.requestFocus();
                }
                else if (takenCustomerCodeField.isEmpty()){
                    editTextCustomerCodeField.setError("Enter Customer Code");
                    editTextCustomerCodeField.requestFocus();
                }
                else if (takenFullnameField.isEmpty()){
                    editTextFullnameField.setError("Enter Full Name");
                    editTextFullnameField.requestFocus();
                }
                else if (takennationalIdField.isEmpty()){
                    editTextNationalIdField.setError("Enter NID");
                    editTextNationalIdField.requestFocus();
                }
                else {

                    SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceName, MODE_PRIVATE).edit();
                    editor.putString("A", a);//1.branchnameString
                    editor.putString("B", b);//2.loanTypeString
                    editor.putString("C", c);//3.subLoanTypeString
                    editor.putString("D", d);//4.editTextLoanAmountString
                    editor.putString("E", e);//5.editTextLoanAmountTendorString
                    editor.putString("F", f);//6.editTextNoOfBorrowerString
                    editor.putString("G", takendateInString);//7.DateOfBirth
                    editor.putString("H", takenGender);//8
                    editor.putString("I", takenResidentialField);//9
                    editor.putString("J", takenCountry);//10
                    editor.putString("K", takenCity);//11
                    editor.putString("L", takenBankAccountNumberField);//12
                    editor.putString("M", takenCustomerCodeField);//13
                    editor.putString("N", takenFullnameField);//14
                    editor.putString("O", takennationalIdField);//15
                    editor.putString("P", takenAddressField);//16
                    editor.putString("Q", takenPostCodeField);//17
                    editor.putString("R", takenYearField);//18
                    editor.putString("S", takenMonthField);//19
                    editor.commit();

                    startActivity(new Intent(ActivityPersonalInfoCustomer.this, ActivityFamilyInfoCustomer.class));
                }
            }
        });
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
                datePickerDialog.show();
            }
        });
    }

    private Context getActivity() {
        return this;
    }

 /*   private void checkBoxClick() {
        checkBoxSameAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    try {
                        editTextPermanentAddress.setEnabled(false);
                        editTextPermanentPostCode.setEnabled(false);
                        editTextPermanentLivingPeriodYear.setEnabled(false);
                        editTextPermanentLivingPeriodMonth.setEnabled(false);
                        spinnerParmanentCountry.setEnabled(false);
                        spinnerParmanentCity.setEnabled(false);
                    }catch (Exception e){
                    }
                }
                else {
                    try {
                        editTextPermanentAddress.setEnabled(true);
                        editTextPermanentPostCode.setEnabled(true);
                        editTextPermanentLivingPeriodYear.setEnabled(true);
                        editTextPermanentLivingPeriodMonth.setEnabled(true);
                        spinnerParmanentCountry.setEnabled(true);
                        spinnerParmanentCity.setEnabled(true);
                    }catch (Exception e){}
                }
            }
        });
    }*/
    private void spinnerDropDown() {
        ArrayAdapter gender = ArrayAdapter.createFromResource(this, R.array.GENDER, R.layout.spinner_item);
        gender.setDropDownViewResource(R.layout.spinner_list);
        spinnerGender.setAdapter(gender);

        ArrayAdapter country = ArrayAdapter.createFromResource(this, R.array.COUNTRY, R.layout.spinner_item);
        country.setDropDownViewResource(R.layout.spinner_list);
        spinnerCountry.setAdapter(country);

        ArrayAdapter city = ArrayAdapter.createFromResource(this, R.array.CITY, R.layout.spinner_item);
        city.setDropDownViewResource(R.layout.spinner_list);
        spinnerCity.setAdapter(city);

        ArrayAdapter residential = ArrayAdapter.createFromResource(this, R.array.RESIDENTIAL, R.layout.spinner_item);
        residential.setDropDownViewResource(R.layout.spinner_list);
        spinnerResidentialField.setAdapter(residential);
       /* ArrayAdapter city = ArrayAdapter.createFromResource(this, R.array.CITY, R.layout.spinner_item);
        city.setDropDownViewResource(R.layout.spinner_list);
        spinnerCity.setAdapter(city);

        ArrayAdapter maritalStatus = ArrayAdapter.createFromResource(this, R.array.MARITAL_STATUS, R.layout.spinner_item);
        maritalStatus.setDropDownViewResource(R.layout.spinner_list);
        spinnerMaritalStatus.setAdapter(maritalStatus);
        ArrayAdapter educationLevel = ArrayAdapter.createFromResource(this, R.array.EDUCATION, R.layout.spinner_item);
        educationLevel.setDropDownViewResource(R.layout.spinner_list);
        spinnerEducationLevel.setAdapter(educationLevel);
        ArrayAdapter permanentCountry = ArrayAdapter.createFromResource(this, R.array.COUNTRY, R.layout.spinner_item);
        permanentCountry.setDropDownViewResource(R.layout.spinner_list);
        spinnerParmanentCountry.setAdapter(permanentCountry);
        ArrayAdapter permanentCity = ArrayAdapter.createFromResource(this, R.array.CITY, R.layout.spinner_item);
        permanentCity.setDropDownViewResource(R.layout.spinner_list);
        spinnerParmanentCity.setAdapter(permanentCity);*/
    }
    private void setupUI() {
        spinnerMaritalStatus = (Spinner) findViewById(R.id.spinnerMaritalStatus);
        spinnerEducationLevel = (Spinner) findViewById(R.id.spinnerEducationLevel);
        spinnerParmanentCountry = (Spinner) findViewById(R.id.spinnerPermanentCountry);
        spinnerParmanentCity = (Spinner) findViewById(R.id.spinnerPermanentCity);
        checkBoxSameAddress = (CheckBox) findViewById(R.id.chekboxSameAsResidence);
        editTextPermanentAddress = (EditText) findViewById(R.id.editTextPermanentAddress);
        editTextPermanentPostCode = (EditText) findViewById(R.id.editTextPermanentPostCode);
        editTextPermanentLivingPeriodYear = (EditText) findViewById(R.id.editTextPermanentYears);
        editTextPermanentLivingPeriodMonth = (EditText) findViewById(R.id.editTextPermanentMonth);
        /*...............>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.......................*/
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerResidentialField= (Spinner) findViewById(R.id.spinnerResidentialStatus);
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        editTextbankAccountNumberField= (EditText) findViewById(R.id.editTextBankAccountNo);
        editTextCustomerCodeField= (EditText) findViewById(R.id.editTextCustomerCode);
        editTextFullnameField= (EditText) findViewById(R.id.editTextFullName);
        editTextNationalIdField=(EditText) findViewById(R.id.editTextNationID);
        editTextAddressField= (EditText) findViewById(R.id.editTextAddress);
        editTextPostCodeField= (EditText) findViewById(R.id.editTextPostCode);
        editTextYearField= (EditText) findViewById(R.id.editTextYears);
        editTextMonthField= (EditText) findViewById(R.id.editTextMonth);
        editTextDateOfBirth = (EditText) findViewById(R.id.editTextDateOfBirth);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
    }
}
