package com.csi.los.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.csi.los.R;

public class ActivityLoanCaseEntryCustomer extends AppCompatActivity {
    Button buttonNext;
    EditText editTextBranchName,editTextNoOfBorrowers,editTextInterestRate,editTextLoanAmount,editTextLoanTenor;
    ImageView imageViewBackPress;
    Spinner loanType,subLoanType,spinnerBranchName;
    Context mContext=this;
    String branchName,appliedLoanAmount,appliedLLoanTenor,noOfBorrowers,interestrate;
    public static  String globalPreferenceName="com.honda.profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_case_entry_customer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setUpUI();
        clickListener();
        spinnerDropDown();
        Typeface typefaceRegular = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Regular.ttf");
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Bold.ttf");
        Typeface typefaceThin = Typeface.createFromAsset(getAssets(),"font/San_Francisco_Thin.ttf");
        buttonNext.setTypeface(typefaceBold);
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private Context getActivity() {
        return this;
    }

    private void clickListener() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String branchnameString = spinnerBranchName.getSelectedItem().toString();
                String loanTypeString = loanType.getSelectedItem().toString();
                String subLoanTypeString = subLoanType.getSelectedItem().toString();
                String editTextLoanAmountString = editTextLoanAmount.getText().toString();
                String editTextLoanAmountTendorString = editTextLoanTenor.getText().toString();
                String editTextNoOfBorrowerString = editTextNoOfBorrowers.getText().toString();
                Log.d("LoanCaseEntryData",branchnameString+loanTypeString+subLoanTypeString+editTextLoanAmountString+editTextLoanAmountTendorString+editTextNoOfBorrowerString);

//From here I have submit ActivityLoanCaseEntry Data into ActivityPersonalInfoCustomer.class by sharedPreference....Although this is not necessary to accept data in next Activity.
                //Sending No of Data 6
                if(branchnameString.isEmpty()){
                    editTextBranchName.setError("Enter Branch Name");
                    editTextBranchName.requestFocus();
                }
               else if(editTextLoanAmountString.isEmpty()){
                    editTextLoanAmount.setError("Enter Loan Amount");
                    editTextLoanAmount.requestFocus();
                }

                else if(branchnameString.isEmpty()){

                    Toast.makeText(ActivityLoanCaseEntryCustomer.this,
                            "Branch Name is Required", Toast.LENGTH_LONG).show();
                }

                else{
                SharedPreferences.Editor editor=getSharedPreferences(globalPreferenceName,MODE_PRIVATE).edit();
                editor.putString("A",branchnameString);
                editor.putString("B",loanTypeString);
                editor.putString("C",subLoanTypeString);
                editor.putString("D",editTextLoanAmountString);
                editor.putString("E",editTextLoanAmountTendorString);
                editor.putString("F",editTextNoOfBorrowerString);
                editor.commit();


                Intent intent = new Intent(ActivityLoanCaseEntryCustomer.this,ActivityPersonalInfoCustomer.class);
                startActivity(intent);
               //overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right);

            }
            }
        });
       /* buttonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constants.SharedprefItem.BRANCH_NAME, editTextLoanAmount.getText().toString());

                    editor.commit();
                    editTextNoOfBorrowers.setText(sharedPreferences.getString(Constants.SharedprefItem.BRANCH_NAME, ""));
                }catch (Exception e){}

                Utility.putString(mContext,Constants.SharedprefItem.BRANCH_NAME,editTextLoanAmount.getText().toString());
                editTextNoOfBorrowers.setText(Utility.getString(mContext,Constants.SharedprefItem.BRANCH_NAME));

            }
        });
        */
    }

    public void spinnerDropDown(){
        ArrayAdapter loanTypeDropDown = ArrayAdapter.createFromResource(this, R.array.LOAN_TYPE, R.layout.spinner_item);
        loanTypeDropDown.setDropDownViewResource(R.layout.spinner_list);
        loanType.setAdapter(loanTypeDropDown);

        ArrayAdapter subloanTypeDropDown = ArrayAdapter.createFromResource(this, R.array.SUBLOAN_TYPE, R.layout.spinner_item);
        subloanTypeDropDown.setDropDownViewResource(R.layout.spinner_list);
        subLoanType.setAdapter(subloanTypeDropDown);

        ArrayAdapter branch = ArrayAdapter.createFromResource(this, R.array.Branch_Name, R.layout.spinner_item);
        branch.setDropDownViewResource(R.layout.spinner_list);
        spinnerBranchName.setAdapter(branch);


    }
    private void setUpUI() {
        buttonNext = (Button) findViewById(R.id.buttonNext);
        editTextBranchName = (EditText) findViewById(R.id.editTextBranchName);
        editTextNoOfBorrowers = (EditText) findViewById(R.id.editTextNoOfBorrowers);
        editTextLoanAmount = (EditText) findViewById(R.id.editTextAppliedLoanAmount);
        editTextLoanTenor = (EditText) findViewById(R.id.editTextAppliedLoanTenor);
        loanType= (Spinner) findViewById(R.id.spinnerLoanType);
        spinnerBranchName= (Spinner) findViewById(R.id.spinnerBranchName);
        subLoanType= (Spinner) findViewById(R.id.subLoanType);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
    }

}
