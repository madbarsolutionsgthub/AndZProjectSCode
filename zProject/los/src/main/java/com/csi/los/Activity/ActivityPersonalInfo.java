package com.csi.los.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ActivityPersonalInfo extends AppCompatActivity {
    Button buttonNext;
    Spinner spinnerGender,spinnerMaritalStatus,spinnerEducationLevel,spinnerCountry,spinnerCity,spinnerParmanentCountry,spinnerParmanentCity,spinnerCarOwnership,residentialStatus;
    CheckBox checkBoxSameAddress;
    EditText editTextPermanentAddress,editTextPermanentPostCode,editTextPermanentLivingPeriodYear,editTextPermanentLivingPeriodMonth,editTextDateOfBirth,editTextPasportExpireDate,editTextDrivingLicenseexpiredate;
    EditText bankAccountNumber,customerCode,fullName,nickName,noOfChildren,nationalId,tin,passportNumber,drivingLicenseNumber,residentialAddress,editTextPostCode;
    EditText editTextPresentAddress,editTextPresentPostCode,editTextPresentLivingPeriodYear,editTextPresentLivingPeriodMonth;
    EditText yearForPresentAddress,monthForPresentAddress;
    ImageView imageViewBackPress;
    //calender
    Calendar calendar;
    int year,day,month;

    public static  String globalPreferenceNameForPersonalInfo="com.personalInfo.rayhan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        spinnerDropDown();
        checkBoxClick();
        personalInfor();
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

    private void checkBoxClick() {
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
                        editTextPermanentAddress.setText(residentialAddress.getText().toString());
                        editTextPermanentPostCode.setText(editTextPostCode.getText().toString());
                        editTextPermanentLivingPeriodYear.setText(yearForPresentAddress.getText().toString());
                        editTextPermanentLivingPeriodMonth.setText(monthForPresentAddress.getText().toString());
                        int position = spinnerCity.getSelectedItemPosition();
                        Log.d("position", String.valueOf(position));
                        spinnerParmanentCity.setSelection(position);
                        //PRESENT.setText(editTextPermanentAddress.getText().toString());

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
                        editTextPermanentAddress.setText("");
                        editTextPermanentPostCode.setText("");
                        editTextPermanentLivingPeriodYear.setText("");
                        editTextPermanentLivingPeriodMonth.setText("");
                    }catch (Exception e){}
                }
            }
        });
    }
    private void spinnerDropDown() {
        ArrayAdapter gender = ArrayAdapter.createFromResource(this, R.array.GENDER, R.layout.spinner_item);
        gender.setDropDownViewResource(R.layout.spinner_list);
        spinnerGender.setAdapter(gender);
        ArrayAdapter city = ArrayAdapter.createFromResource(this, R.array.CITY, R.layout.spinner_item);
        city.setDropDownViewResource(R.layout.spinner_list);
        spinnerCity.setAdapter(city);
        ArrayAdapter country = ArrayAdapter.createFromResource(this, R.array.COUNTRY, R.layout.spinner_item);
        country.setDropDownViewResource(R.layout.spinner_list);
        spinnerCountry.setAdapter(country);
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
        spinnerParmanentCity.setAdapter(permanentCity);

        /*ArrayAdapter spinnerCarOwnerShipp = ArrayAdapter.createFromResource(this, R.array.CAR_OWNERSHIP, R.layout.spinner_item);
        spinnerCarOwnerShipp.setDropDownViewResource(R.layout.spinner_list);
        spinnerCarOwnership.setAdapter(spinnerCarOwnerShipp);*/

        ArrayAdapter spinnerResidentialStatus = ArrayAdapter.createFromResource(this, R.array.RESIDENTIAL, R.layout.spinner_item);
        spinnerResidentialStatus.setDropDownViewResource(R.layout.spinner_list);
        residentialStatus.setAdapter(spinnerResidentialStatus);



    }
    private void setupUI() {
        buttonNext = (Button) findViewById(R.id.buttonNext);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerMaritalStatus = (Spinner) findViewById(R.id.spinnerMaritalStatus);
        spinnerEducationLevel = (Spinner) findViewById(R.id.spinnerEducationLevel);
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        spinnerParmanentCountry = (Spinner) findViewById(R.id.spinnerPermanentCountry);
        spinnerParmanentCity = (Spinner) findViewById(R.id.spinnerPermanentCity);
        checkBoxSameAddress = (CheckBox) findViewById(R.id.chekboxSameAsResidence);//Later Add on
        editTextPermanentAddress = (EditText) findViewById(R.id.editTextPermanentAddress);
        editTextPermanentPostCode = (EditText) findViewById(R.id.editTextPermanentPostCode);
        editTextPermanentLivingPeriodYear = (EditText) findViewById(R.id.editTextPermanentYears);
        editTextPermanentLivingPeriodMonth = (EditText) findViewById(R.id.editTextPermanentMonth);
        editTextDateOfBirth = (EditText) findViewById(R.id.editTextDateOfBirth);
        bankAccountNumber = (EditText) findViewById(R.id.editTextBankAccountNo);
        //customerCode = (EditText) findViewById(R.id.editTextCustomerCode);
        fullName = (EditText) findViewById(R.id.editTextFullName);
        nickName = (EditText) findViewById(R.id.editTextNickName);
        noOfChildren =(EditText) findViewById(R.id.editTextNoOfChildren);
        nationalId = (EditText) findViewById(R.id.editTextNationID);
        //tin = (EditText) findViewById(R.id.editTextTIN);
        //passportNumber =(EditText) findViewById(R.id.editTextPassportNo);
        //drivingLicenseNumber =(EditText) findViewById(R.id.editTextDrivingLicenseNo);
        //editTextPasportExpireDate = (EditText) findViewById(R.id.passportExpireDate);
        //editTextDrivingLicenseexpiredate = (EditText) findViewById(R.id.drivingLicenseExpiredate);
        //spinnerCarOwnership =(Spinner) findViewById(R.id.spinnerCarOwnership);
        residentialStatus = (Spinner)findViewById(R.id.spinnerResidentialStatus);
        residentialAddress =(EditText) findViewById(R.id.editTextAddress);
        editTextPostCode = (EditText) findViewById(R.id.editTextPostCode);
        yearForPresentAddress = (EditText) findViewById(R.id.editTextYears);
        monthForPresentAddress = (EditText) findViewById(R.id.editTextMonth);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
    }


        public void personalInfor(){

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String spinnerGenderVariable =  spinnerGender.getSelectedItem().toString();
                String spinnerMaritalStatusvariable =  spinnerMaritalStatus.getSelectedItem().toString();
                String spinnerEducationLevelvariable =  spinnerEducationLevel.getSelectedItem().toString();
                String spinnerCountryvariable = spinnerCountry.getSelectedItem().toString();
                String spinnerCityvariable = spinnerCity.getSelectedItem().toString();
                String spinnerParmanentCountryvariable = spinnerParmanentCountry.getSelectedItem().toString();
                String spinnerParmanentCityVariable = spinnerParmanentCity.getSelectedItem().toString();
                String editTextPermanentAddressvariable = editTextPermanentAddress.getText().toString();
                String editTextPermanentPostCodevariable = editTextPermanentPostCode.getText().toString();
                String editTextPermanentLivingPeriodYearvariable = editTextPermanentLivingPeriodYear.getText().toString();
                String editTextPermanentLivingPeriodMonthvariable = editTextPermanentLivingPeriodMonth.getText().toString();
                String editTextDateOfBirthvariable = editTextDateOfBirth.getText().toString();
                String bankAccountNumbervariable = bankAccountNumber.getText().toString();
                //String customerCodevariable = customerCode.getText().toString();
                String fullNameVariable = fullName.getText().toString();
                String nickNameVariable =  nickName.getText().toString();
                String noOfChildrenvariable = noOfChildren.getText().toString();
                String nationalIdVariable = nationalId.getText().toString();
                //String tinvariable = tin.getText().toString();
                //String passportNumbervariable = passportNumber.getText().toString();
                //String drivingLicenseNumbervariable = drivingLicenseNumber.getText().toString();
                //String editTextPasportExpireDatevariable = editTextPasportExpireDate.getText().toString();
                //String editTextDrivingLicenseexpiredateVariable = editTextDrivingLicenseexpiredate.getText().toString();

                //String spinnerCarOwnershipVariable = spinnerCarOwnership.getSelectedItem().toString();
                String residentialStatusvariable = residentialStatus.getSelectedItem().toString();
                String residentialAddressvariable = residentialAddress.getText().toString();
                String editTextPostCodeVariable =  editTextPostCode.getText().toString();
                String yearForPresentAddressVariable = yearForPresentAddress.getText().toString();
                String monthForPresentAddressVariable = monthForPresentAddress.getText().toString();

                if (bankAccountNumbervariable.isEmpty()){
                    bankAccountNumber.setError("Enter Bank Account");
                    bankAccountNumber.requestFocus();
                }
                /*else if (customerCodevariable.isEmpty()){
                    customerCode.setError("Enter Customer Code");
                    customerCode.requestFocus();
                }*/
                else if (fullNameVariable.isEmpty()){
                    fullName.setError("Enter Full Name");
                    fullName.requestFocus();
                }
                else if (nationalIdVariable.isEmpty()){
                    nationalId.setError("Enter NID");
                    nationalId.requestFocus();
                }
                else if (residentialAddressvariable.isEmpty()){
                    residentialAddress.setError("Enter Address");
                    residentialAddress.requestFocus();
                }
                else if (editTextPostCodeVariable.isEmpty()){
                    editTextPostCode.setError("Enter Post Code");
                    editTextPostCode.requestFocus();
                }
                else if (editTextPermanentAddressvariable.isEmpty()){
                    editTextPermanentAddress.setError("Enter Address");
                    editTextPermanentAddress.requestFocus();
                }
                else if (editTextPermanentPostCodevariable.isEmpty()){
                    editTextPermanentPostCode.setError("Enter Post Code");
                    editTextPermanentPostCode.requestFocus();
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceNameForPersonalInfo, MODE_PRIVATE).edit();
                    editor.putString("spinnerGenderVariablePersonalInfo", spinnerGenderVariable);
                    editor.putString("spinnerMaritalStatusvariablePersonalInfo", spinnerMaritalStatusvariable);
                    editor.putString("spinnerEducationLevelvariablePersonalinfo", spinnerEducationLevelvariable);
                    editor.putString("spinnerCountryvariablePersonalInfo", spinnerCountryvariable);
                    editor.putString("spinnerCityvariablepersonalinfo", spinnerCityvariable);
                    editor.putString("spinnerParmanentCountryvariablePersonalInfo", spinnerParmanentCountryvariable);
                    editor.putString("spinnerParmanentCityVariablePersonalInfo", spinnerParmanentCityVariable);
                    editor.putString("editTextPermanentAddressvariablepersonalInfo", editTextPermanentAddressvariable);
                    editor.putString("editTextPermanentPostCodevariablePersonalInfo", editTextPermanentPostCodevariable);
                    editor.putString("editTextPermanentLivingPeriodYearvariablePersonalInfo", editTextPermanentLivingPeriodYearvariable);
                    editor.putString("editTextPermanentLivingPeriodMonthvariablePersonalinfo", editTextPermanentLivingPeriodMonthvariable);
                    editor.putString("editTextDateOfBirthvariableperswonalinfo", editTextDateOfBirthvariable);
                    editor.putString("bankAccountNumbervariablePersonalInfo", bankAccountNumbervariable);
                    //editor.putString("customerCodevariablepersonalInfo", customerCodevariable);
                    editor.putString("fullNameVariablePersonalInfo", fullNameVariable);
                    editor.putString("nickNameVariablePersonalInfo", nickNameVariable);
                    editor.putString("noOfChildrenvariablePersonalInfo", noOfChildrenvariable);
                    editor.putString("nationalIdVariablePersonalInfo", nationalIdVariable);
                    /*editor.putString("tinvariablePersonalInfo", tinvariable);
                    editor.putString("passportNumbervariablePersonalInfo", passportNumbervariable);
                    editor.putString("drivingLicenseNumbervariablePersonalInfo", drivingLicenseNumbervariable);
                    editor.putString("editTextPasportExpireDatevariablePersonalInfo", editTextPasportExpireDatevariable);
                    editor.putString("editTextDrivingLicenseexpiredateVariablePersonalInfo", editTextDrivingLicenseexpiredateVariable);
                    editor.putString("spinnerCarOwnershipVariablePersonalInfo", spinnerCarOwnershipVariable);*/
                    editor.putString("residentialStatusvariablePersonalInfo", residentialStatusvariable);
                    editor.putString("residentialAddressvariablePersonalInfo", residentialAddressvariable);
                    editor.putString("editTextPostCodeVariablePersonalInfo", editTextPostCodeVariable);
                    editor.putString("yearForPresentAddressVariablePersonalInfo", yearForPresentAddressVariable);
                    editor.putString("monthForPresentAddressVariablePersonalInfo", monthForPresentAddressVariable);
                    editor.commit();
                    startActivity(new Intent(ActivityPersonalInfo.this, ActivityFamilyInfo.class));
                }

            }
        });
    }
}
