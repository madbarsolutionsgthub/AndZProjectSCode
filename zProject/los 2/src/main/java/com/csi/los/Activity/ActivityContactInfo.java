package com.csi.los.Activity;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.csi.los.R;
import com.csi.los.Utility.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class ActivityContactInfo extends AppCompatActivity {

    EditText personalEmailAddress,officeEmailAddress,personalPhoneNumber,PresentResPhoneNumber,PermanentResPhoneNumber,PersonalPhoneNo,officePhoneNumber,cardNumber,LoanAccountNumber,SecurityCollateral,nameOfBank,sectionLimit,monthlyInstallment,presentOutstanding,YearsFrom_facilitydate,MonthFrom_facilitydate,DateFrom_facilitydate,
            YearsFrom_expirydate,MonthsFrom_expiraydate,dateFrom_expiryDate;
    ImageView imageViewBackPress;
    Button submit;
    Spinner facilityType,branchName;
    private SpotsDialog loadingDialog;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        setupUI();
        spinnerDropDown();
        onClickForContact();
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupUI(){

        personalEmailAddress= (EditText) findViewById(R.id.editTextPersonalEmail);
        officeEmailAddress= (EditText) findViewById(R.id.editTextOfficeEmail);
        personalPhoneNumber= (EditText) findViewById(R.id.editTextPersonalPhoneNo);
        //PresentResPhoneNumber= (EditText) findViewById(R.id.editTextPresentResPhone);
        //PermanentResPhoneNumber=(EditText) findViewById(R.id.editTextPermanentResPhone);
        //PersonalPhoneNo=(EditText) findViewById(R.id.editTextPersonalPhoneNo);
        officePhoneNumber=(EditText) findViewById(R.id.editTextOfficePhoneNo);
        //cardNumber= (EditText) findViewById(R.id.editTextcardNumber);
        //LoanAccountNumber= (EditText) findViewById(R.id.editTextLoanAccountNumber);
        //SecurityCollateral= (EditText) findViewById(R.id.editTextSecurityCollateral);
        //nameOfBank= (EditText) findViewById(R.id.editTextNameOfbankFI);
        //sectionLimit= (EditText) findViewById(R.id.editTextSactionLimit);
        //monthlyInstallment=(EditText) findViewById(R.id.editTextMonthlyInstallment);
        //presentOutstanding= (EditText) findViewById(R.id.editTextPresentOutstanding);
        //YearsFrom_facilitydate= (EditText) findViewById(R.id.editTextYearsFrom_facilitydate);
        //MonthFrom_facilitydate= (EditText) findViewById(R.id.editTextMonthFrom_facilitydate);
        //DateFrom_facilitydate= (EditText) findViewById(R.id.editTextDateFrom_facilitydate);

        /*YearsFrom_expirydate= (EditText) findViewById(R.id.editTextYearsFrom_expirydate);
        MonthsFrom_expiraydate=(EditText) findViewById(R.id.editTextMonthFrom_expirydate);
        dateFrom_expiryDate= (EditText) findViewById(R.id.editTextDateFrom_expirydate);*/

        /*facilityType=(Spinner) findViewById(R.id.spinnerfacilityType);
        branchName= (Spinner) findViewById(R.id.spinnerBranchName);*/
        submit= (Button) findViewById(R.id.buttonSubmit);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
    }


    private void spinnerDropDown() {


        /*ArrayAdapter facilityTypeVariable = ArrayAdapter.createFromResource(this, R.array.CREDIT_FACILITY_TYPE, R.layout.spinner_item);
        facilityTypeVariable.setDropDownViewResource(R.layout.spinner_list);
        facilityType.setAdapter(facilityTypeVariable);


        ArrayAdapter branchNameVariable = ArrayAdapter.createFromResource(this, R.array.CREDIT_BRANCH_NAME, R.layout.spinner_item);
        branchNameVariable.setDropDownViewResource(R.layout.spinner_list);
        branchName.setAdapter(branchNameVariable);*/
    }

    public void onClickForContact(){

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Log.d("CLICKED","I am clicked");
        SharedPreferences sharedPreferencesId= getSharedPreferences(Login.globalPreferenceNameForIdForAgent,MODE_PRIVATE);
        SharedPreferences sharedPreferencesForLoancaseEntry= getSharedPreferences(ActivityLoanCaseEntry.globalPreferenceNameForLoancaseEntry,MODE_PRIVATE);
        SharedPreferences sharedPreferencesForPersonalInfo= getSharedPreferences(ActivityPersonalInfo.globalPreferenceNameForPersonalInfo,MODE_PRIVATE);
        SharedPreferences sharedPreferencesForFamilyInfo= getSharedPreferences(ActivityFamilyInfo.globalPreferenceNameForFamilyInfo,MODE_PRIVATE);

        final int currentSessionId= sharedPreferencesId.getInt("Log_id",7676);
//Data get from ContactInformation
       final String  personalEmailAddressvzariable =   personalEmailAddress.getText().toString();
        final String officeEmailAddressvariable=  officeEmailAddress.getText().toString();
        final String personalPhoneNumbervariable= personalPhoneNumber.getText().toString();
        final String officePhoneNumberVariable= officePhoneNumber.getText().toString();
        //final String PresentResPhoneNumberVariable=  PresentResPhoneNumber.getText().toString();
        //final String PermanentResPhoneNumbervariable=PermanentResPhoneNumber.getText().toString();
        //final String PersonalPhoneNovariable=PersonalPhoneNo.getText().toString();

        //final String cardNumberVariable=cardNumber.getText().toString();
        //final String LoanAccountNumberVariable=LoanAccountNumber.getText().toString();
        //final String SecurityCollateralvariable=SecurityCollateral.getText().toString();
        //final String nameOfBankVariable= nameOfBank.getText().toString();
        //final String sectionLimitvariable=  sectionLimit.getText().toString();
        //final String monthlyInstallmentvariable=monthlyInstallment.getText().toString();
        //final String presentOutstandingvariable=presentOutstanding.getText().toString();
        //final String YearsFrom_facilitydatevariable=  YearsFrom_facilitydate.getText().toString();
        //final String MonthFrom_facilitydatevariable= MonthFrom_facilitydate.getText().toString();
        //final String DateFrom_facilitydatevariable= DateFrom_facilitydate.getText().toString();

        /*final String YearsFrom_expirydateVariable=YearsFrom_expirydate.getText().toString();
        final String MonthsFrom_expiraydateVariable= MonthsFrom_expiraydate.getText().toString();
        final String dateFrom_expiryDatevariable= dateFrom_expiryDate.getText().toString();
        final String facilityTypevariable=facilityType.getSelectedItem().toString();
        final String branchNameVariable= branchName.getSelectedItem().toString();*/



        //Data get from LoanCaseEntry
        final String branchNameLoancaseEntry=sharedPreferencesForLoancaseEntry.getString("branchNameLoancaseEntry","no1");
        //final String interestLoanCaseEntry= sharedPreferencesForLoancaseEntry.getString("interestLoanCaseEntry","no2");
        final String noOfBorrowerLoancaseEntry= sharedPreferencesForLoancaseEntry.getString("noOfBorrowerLoancaseEntry","no3");
        //final String loanAmountLoancaseEntry= sharedPreferencesForLoancaseEntry.getString("loanAmountLoancaseEntry","no4");
        final String loanTendorLoancaseEntry= sharedPreferencesForLoancaseEntry.getString("loanTendorLoancaseEntry","no5");
        //final String caseNumberLoancaseEntry= sharedPreferencesForLoancaseEntry.getString("caseNumberLoancaseEntry","no6");
        final String appliedLoanAmountLoancaseEntry= sharedPreferencesForLoancaseEntry.getString("appliedLoanAmountLoancaseEntry","no7");
        //final String spinnerCustomerTypeLoancaseEntry= sharedPreferencesForLoancaseEntry.getString("spinnerCustomerTypeLoancaseEntry","no8");
        final String spinnerLoanTypeLoanCaseEntry= sharedPreferencesForLoancaseEntry.getString("spinnerLoanTypeLoanCaseEntry","no8");
        final String spinnerSubLoanTypeLoanCaseEntry= sharedPreferencesForLoancaseEntry.getString("spinnerSubLoanTypeLoanCaseEntry","no9");
        //final String spinnerApplicationTypeLoanCaseEntry= sharedPreferencesForLoancaseEntry.getString("spinnerApplicationTypeLoanCaseEntry","no10");
        //final String spinnerNatureOfProposalTypeLoanCaseEntry= sharedPreferencesForLoancaseEntry.getString("spinnerNatureOfProposalTypeLoanCaseEntry","no11");

        //data get from personalInfo
        final String spinnerGenderVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("spinnerGenderVariablePersonalInfo","no12");
        final String spinnerMaritalStatusvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("spinnerMaritalStatusvariablePersonalInfo","no112");
        final String spinnerEducationLevelvariablePersonalinfo= sharedPreferencesForPersonalInfo.getString("spinnerEducationLevelvariablePersonalinfo","no121");
        final String spinnerCountryvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("spinnerCountryvariablePersonalInfo","no12321");
        final String spinnerCityvariablepersonalinfo= sharedPreferencesForPersonalInfo.getString("spinnerCityvariablepersonalinfo","no134321");
        final String spinnerParmanentCountryvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("spinnerParmanentCountryvariablePersonalInfo","no43411");
        final String spinnerParmanentCityVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("spinnerParmanentCityVariablePersonalInfo","no11343");
        final String editTextPermanentAddressvariablepersonalInfo= sharedPreferencesForPersonalInfo.getString("editTextPermanentAddressvariablepersonalInfo","no342311");
        final String editTextPermanentPostCodevariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("editTextPermanentPostCodevariablePersonalInfo","no134321");
        final String editTextPermanentLivingPeriodYearvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("editTextPermanentLivingPeriodYearvariablePersonalInfo","no143241");
        final String editTextPermanentLivingPeriodMonthvariablePersonalinfo= sharedPreferencesForPersonalInfo.getString("editTextPermanentLivingPeriodMonthvariablePersonalinfo","no13423421");
        final String editTextDateOfBirthvariableperswonalinfo= sharedPreferencesForPersonalInfo.getString("editTextDateOfBirthvariableperswonalinfo","no134234231");
        final String bankAccountNumbervariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("bankAccountNumbervariablePersonalInfo","no13242341");
        //final String customerCodevariablepersonalInfo= sharedPreferencesForPersonalInfo.getString("customerCodevariablepersonalInfo","no1343251");
        final String fullNameVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("fullNameVariablePersonalInfo","no165461");
        final String nickNameVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("nickNameVariablePersonalInfo","no1134211");
        final String noOfChildrenvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("noOfChildrenvariablePersonalInfo","no11534534");
        final String nationalIdVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("nationalIdVariablePersonalInfo","no1135343");

        //final String tinvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("tinvariablePersonalInfo","no11353465");
        //final String passportNumbervariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("passportNumbervariablePersonalInfo","no13242351");
        //final String drivingLicenseNumbervariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("drivingLicenseNumbervariablePersonalInfo","no13243251");
        //final String editTextPasportExpireDatevariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("editTextPasportExpireDatevariablePersonalInfo","no134325341");
        //final String editTextDrivingLicenseexpiredateVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("editTextDrivingLicenseexpiredateVariablePersonalInfo","no3453511");
        //final String spinnerCarOwnershipVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("spinnerCarOwnershipVariablePersonalInfo","no13254351");
        final String residentialStatusvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("residentialStatusvariablePersonalInfo","no134351");
        final String residentialAddressvariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("residentialAddressvariablePersonalInfo","no124351");
        final String editTextPostCodeVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("editTextPostCodeVariablePersonalInfo","no187561");
        final String yearForPresentAddressVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("yearForPresentAddressVariablePersonalInfo","no11867435");
        final String monthForPresentAddressVariablePersonalInfo= sharedPreferencesForPersonalInfo.getString("monthForPresentAddressVariablePersonalInfo","no11453453");

        //data get from family
        final String spouseameVariablefamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseameVariablefamilyInfo","no5675711");
        //final String spousedesignationVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spousedesignationVariableFamilyInfo","no14654761");
        //final String spouseBusinessLeagthVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseBusinessLeagthVariableFamilyInfo","no167681");
        //final String spouseMonthlyIncomevariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseMonthlyIncomevariableFamilyInfo","no143654671");
        //final String spouseOfficeMobileVariablefamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseOfficeMobileVariablefamilyInfo","no143534521");
        //final String spouseMobileVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseMobileVariableFamilyInfo","no153461");
        final String spouseFatherNameVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseFatherNameVariableFamilyInfo","no11345436");
        //final String spousefatherMobileNumbervariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spousefatherMobileNumbervariableFamilyInfo","no1657345631");
        final String spouseMothernamevariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseMothernamevariableFamilyInfo","no11645734");
        //final String spouseMotherMobileNumberVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseMotherMobileNumberVariableFamilyInfo","no114654624");
        final String employmentDepartmentVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("employmentDepartmentVariableFamilyInfo","no154765631");
        final String employmentDesignationVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("employmentDesignationVariableFamilyInfo","no14645741");
        final String employmentBusinessNaturevariablefamilyInfo= sharedPreferencesForFamilyInfo.getString("employmentBusinessNaturevariablefamilyInfo","no1456346541");
        final String LivingPeriodYearvariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("LivingPeriodYearvariableFamilyInfo","no143654761");
        //final String LivingPeriodMonthVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("LivingPeriodMonthVariableFamilyInfo","no14353461");
        //final String LivingPeriodDateVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("LivingPeriodDateVariableFamilyInfo","no14365467541");
        final String spouseProfessionVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseProfessionVariableFamilyInfo","no15346541");
        //final String spouseCompanyVariablefamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseCompanyVariablefamilyInfo","no1456541");
        //final String spousefatherProfessionVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spousefatherProfessionVariableFamilyInfo","no134634765461");
        //final String spouseFatherCompanyNameVariablefamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseFatherCompanyNameVariablefamilyInfo","no1436731");
        //final String spouseMotherProfessionvariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseMotherProfessionvariableFamilyInfo","no143673451");
        //final String spouseMotherCompanyNamevariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("spouseMotherCompanyNamevariableFamilyInfo","no14354367431");
        final String employmentProfessionvariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("employmentProfessionvariableFamilyInfo","no1534256436341");
        final String employmentCompanyNameVariableFamilyInfo= sharedPreferencesForFamilyInfo.getString("employmentCompanyNameVariableFamilyInfo","no1435346346341");

        if (personalEmailAddressvzariable.isEmpty()){
            personalEmailAddress.setError("Enter Email");
            personalEmailAddress.requestFocus();
        }
        else if (personalPhoneNumbervariable.isEmpty()){
            personalPhoneNumber.setError("Enter Mobile No");
            personalPhoneNumber.requestFocus();
        }
        /*else if (nameOfBankVariable.isEmpty()){
            nameOfBank.setError("Enter Bank Name");
            nameOfBank.requestFocus();
        }*/
        else {
            //Sending data to the server
            loadingDialog.show();
            RequestQueue queue = Volley.newRequestQueue(ActivityContactInfo.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API.LOAN_APPLY_AGENT, new Response.Listener<String>() {
                @Override
                public void onResponse(String responseText) {

                    try {
                        JSONObject jsonObject = new JSONObject(responseText);
                        String status = jsonObject.getString("status");
                        //String statuss = (String) jsonObject.get(String.valueOf(jsonObject));
                        //Log.d("OKKKKKKKKKKK", status);

                        if (status == Integer.toString(1)) {
                            Toast.makeText(ActivityContactInfo.this,
                                    "Your Loan Request has been Submitted", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(ActivityContactInfo.this,ActivityHome.class);
                            startActivity(intent);
                            finish();
                            loadingDialog.dismiss();
                        } else {
                        }
                    } catch (JSONException e) {
                        loadingDialog.dismiss();
                        Toast.makeText(ActivityContactInfo.this,
                                "Network Problem.Try Again Later", Toast.LENGTH_SHORT).show();
                        /*Intent intent = new Intent(ActivityContactInfo.this, ActivityUserProfile.class);
                        startActivity(intent);*/
                        Log.d("Error", "HI");
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ActivityContactInfo.this, error.toString(), Toast.LENGTH_LONG).show();
                    loadingDialog.dismiss();
                }
            })

            {

                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    // loancase Entry
                    map.put("bank_branch", branchNameLoancaseEntry);//1
                    //map.put("", interestLoanCaseEntry);//2
                    map.put("no_of_borrowers", noOfBorrowerLoancaseEntry);//3
                    map.put("loan_amount", appliedLoanAmountLoancaseEntry);//4
                    map.put("loan_duration", loanTendorLoancaseEntry);//5
                    /*map.put("case_no", caseNumberLoancaseEntry);//6
                    map.put("", spinnerCustomerTypeLoancaseEntry);//8*/
                    map.put("loan_type", spinnerLoanTypeLoanCaseEntry);//9
                    map.put("sub_loan_type", spinnerSubLoanTypeLoanCaseEntry);//10
                    /*map.put("application_type", spinnerApplicationTypeLoanCaseEntry);//11
                    map.put("", spinnerNatureOfProposalTypeLoanCaseEntry);//12*/

                    //Start personalInfo
                    map.put("gender", spinnerGenderVariablePersonalInfo);//13
                    map.put("marital_status", spinnerMaritalStatusvariablePersonalInfo);//14
                    map.put("education_level", spinnerEducationLevelvariablePersonalinfo);//15
                    map.put("country", spinnerCountryvariablePersonalInfo);//16
                    map.put("city", spinnerCityvariablepersonalinfo);//17
                    /*map.put("", spinnerParmanentCountryvariablePersonalInfo);//18
                    map.put("", spinnerParmanentCityVariablePersonalInfo);//19
                    map.put("", editTextPermanentAddressvariablepersonalInfo);//20
                    map.put("", editTextPermanentPostCodevariablePersonalInfo);//21
                    map.put("", editTextPermanentLivingPeriodYearvariablePersonalInfo);//22
                    map.put("", editTextPermanentLivingPeriodMonthvariablePersonalinfo);//23*/
                    map.put("dob", editTextDateOfBirthvariableperswonalinfo);//24
                    map.put("ac_no", bankAccountNumbervariablePersonalInfo);//25
                    //map.put("code", customerCodevariablepersonalInfo);//26
                    map.put("name", fullNameVariablePersonalInfo);//27
                    map.put("nick_name", nickNameVariablePersonalInfo);//28
                    map.put("children", noOfChildrenvariablePersonalInfo);//29
                    map.put("nid", nationalIdVariablePersonalInfo);//30
                   /* map.put("", tinvariablePersonalInfo);
                    map.put("", passportNumbervariablePersonalInfo);
                    map.put("", drivingLicenseNumbervariablePersonalInfo);
                    map.put("", editTextPasportExpireDatevariablePersonalInfo);
                    map.put("", editTextDrivingLicenseexpiredateVariablePersonalInfo);
                    map.put("", spinnerCarOwnershipVariablePersonalInfo);*/
                    map.put("residential_status", residentialStatusvariablePersonalInfo);
                    //map.put("", residentialAddressvariablePersonalInfo);
                    map.put("post_code", editTextPostCodeVariablePersonalInfo);
                    map.put("living_period_year", yearForPresentAddressVariablePersonalInfo);
                    map.put("living_period_month", monthForPresentAddressVariablePersonalInfo);

                    //start family
                    map.put("spouse_name", spouseameVariablefamilyInfo);
                    /*map.put("designation", spousedesignationVariableFamilyInfo);
                    map.put("spouse_service", spouseBusinessLeagthVariableFamilyInfo);
                    map.put("spouse_monthly_income", spouseMonthlyIncomevariableFamilyInfo);
                    map.put("spouse_office_phone_number", spouseOfficeMobileVariablefamilyInfo);
                    map.put("spouse_mobile_number", spouseMobileVariableFamilyInfo);*/
                    map.put("father_name", spouseFatherNameVariableFamilyInfo);
                    //map.put("father_mobile_number", spousefatherMobileNumbervariableFamilyInfo);
                    map.put("mother_name", spouseMothernamevariableFamilyInfo);
                    //map.put("mother_mobile_number", spouseMotherMobileNumberVariableFamilyInfo);
                    map.put("department", employmentDepartmentVariableFamilyInfo);
                    map.put("designation", employmentDesignationVariableFamilyInfo);
                    map.put("business_nature", employmentBusinessNaturevariablefamilyInfo);
                    map.put("from", LivingPeriodYearvariableFamilyInfo);
                   /* map.put("", LivingPeriodMonthVariableFamilyInfo);
                    map.put("", LivingPeriodDateVariableFamilyInfo);*/
                    map.put("spouse_profession", spouseProfessionVariableFamilyInfo);
                    /*map.put("spouse_company", spouseCompanyVariablefamilyInfo);
                    map.put("father_profession", spousefatherProfessionVariableFamilyInfo);
                    map.put("father_company_name", spouseFatherCompanyNameVariablefamilyInfo);
                    map.put("mother_profession", spouseMotherProfessionvariableFamilyInfo);
                    map.put("mother_company_name", spouseMotherCompanyNamevariableFamilyInfo);*/
                    map.put("profession", employmentProfessionvariableFamilyInfo);
                    map.put("company_name", employmentCompanyNameVariableFamilyInfo);

                    //Start contact Information
                    map.put("email_address", personalEmailAddressvzariable);
                    map.put("office_email_address", officeEmailAddressvariable);
                    map.put("phone", personalPhoneNumbervariable);

                    map.put("office_phone_number", officePhoneNumberVariable);
                    map.put("customer_type", "agent");
                    map.put("log_id", String.valueOf(currentSessionId));

                    Log.d("Final",map.toString());
                    return map;
                }
            };

            queue.add(stringRequest);
        }
    }
});


    }
}
