package com.csi.los.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.csi.los.R;
import com.csi.los.Utility.Constants;
import com.csi.los.Utility.Utility;

public class ActivityLoanCaseEntry extends AppCompatActivity {
    Button buttonNext,buttonCommit;
    EditText editTextBranchName,editTextCaseNumber,editTextAppliedLoanAmount,editTextNoOfBorrowers,editTextInterestRate,editTextLoanAmount,editTextLoanTenor;
    Spinner SpinnercustomerType,SpinnerLoanType,SpinnersubloanType,SpinnerApplicationType,SpinnerNatureofProposalType;
    Context mContext=this;
    String branchName,appliedLoanAmount,appliedLLoanTenor,noOfBorrowers,interestrate;
    ImageView imageViewBackPress;
    public static  String globalPreferenceNameForLoancaseEntry="com.LoanCaseEntry.rayhan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_case_entry);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setUpUI();
       // clickListener();
        spinnerDropDown();
        onClickForLoanCaseEntry();
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


    public void onClickForLoanCaseEntry(){


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String branchName = editTextBranchName.getText().toString();
                //String interestRate = editTextInterestRate.getText().toString();
                String noOfBorrowers = editTextNoOfBorrowers.getText().toString();
                //String loanAmount = editTextLoanAmount.getText().toString();
                String loanTendor = editTextLoanTenor.getText().toString();
                //String caseNumber = editTextCaseNumber.getText().toString();
                String appliedLoanAmount = editTextAppliedLoanAmount.getText().toString();


                //String spinnerCustomerType = SpinnercustomerType.getSelectedItem().toString();
                String spinnerLoanType = SpinnerLoanType.getSelectedItem().toString();
                String spinnerSubLoanType = SpinnersubloanType.getSelectedItem().toString();
                //String spinnerApplicationType = SpinnerApplicationType.getSelectedItem().toString();
                //String spinnerNatureOfProposalType = SpinnerNatureofProposalType.getSelectedItem().toString();

                Log.d("All Data",branchName+" "+" "+noOfBorrowers+" "+loanTendor+" "+" "+appliedLoanAmount+" "+" "+spinnerLoanType+" "+spinnerSubLoanType);
                if (branchName.isEmpty()){
                    editTextBranchName.setError("Enter Branch Name");
                    editTextBranchName.requestFocus();
                }
                else if (appliedLoanAmount.isEmpty()){
                    editTextAppliedLoanAmount.setError("Enter Loan Amount");
                    editTextAppliedLoanAmount.requestFocus();
                }
                else if (loanTendor.isEmpty()){
                    editTextLoanTenor.setError("Enter Loan Tenor");
                    editTextLoanTenor.requestFocus();
                }
                else if (noOfBorrowers.isEmpty()){
                    editTextNoOfBorrowers.setError("Enter Borrowers");
                    editTextNoOfBorrowers.requestFocus();
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceNameForLoancaseEntry, MODE_PRIVATE).edit();
                    editor.putString("branchNameLoancaseEntry", branchName);
                    //editor.putString("interestLoanCaseEntry", interestRate);
                    editor.putString("noOfBorrowerLoancaseEntry", noOfBorrowers);
                   // editor.putString("loanAmountLoancaseEntry", loanAmount);
                    editor.putString("loanTendorLoancaseEntry", loanTendor);
                    //editor.putString("caseNumberLoancaseEntry", caseNumber);
                    editor.putString("appliedLoanAmountLoancaseEntry", appliedLoanAmount);

                    //editor.putString("spinnerCustomerTypeLoancaseEntry", spinnerCustomerType);
                    editor.putString("spinnerLoanTypeLoanCaseEntry", spinnerLoanType);
                    editor.putString("spinnerSubLoanTypeLoanCaseEntry", spinnerSubLoanType);
                    //editor.putString("spinnerApplicationTypeLoanCaseEntry", spinnerApplicationType);
                    //editor.putString("spinnerNatureOfProposalTypeLoanCaseEntry", spinnerNatureOfProposalType);
                    editor.commit();
                    startActivity(new Intent(ActivityLoanCaseEntry.this, ActivityPersonalInfo.class));
                }
            }
        });
    }

    private void setUpUI() {
        buttonNext = (Button) findViewById(R.id.buttonNext);
        editTextBranchName = (EditText) findViewById(R.id.editTextBranchName);
        //editTextInterestRate = (EditText) findViewById(R.id.editTextInterestRate);
        editTextNoOfBorrowers = (EditText) findViewById(R.id.editTextNoOfBorrowers);
        editTextAppliedLoanAmount = (EditText) findViewById(R.id.editTextAppliedLoanAmount);
        editTextLoanTenor = (EditText) findViewById(R.id.editTextAppliedLoanTenor);
        //editTextCaseNumber= (EditText)findViewById(R.id.editTextCaseNumberr);
        //Spinner Type
        //SpinnercustomerType= (Spinner) findViewById(R.id.spinnerCustomerType);
        SpinnerLoanType=(Spinner) findViewById(R.id.spinnerLoanType);
        SpinnersubloanType= (Spinner)findViewById(R.id.spinnersubLoanType);
        //SpinnerApplicationType= (Spinner) findViewById(R.id.spinnerApplicationType);
        //SpinnerNatureofProposalType= (Spinner)findViewById(R.id.spinnerNatureOfProposalType);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
    }

    public void spinnerDropDown(){

        /*ArrayAdapter customerTypeDropDown = ArrayAdapter.createFromResource(this, R.array.LOAN_TYPE, R.layout.spinner_item);
        customerTypeDropDown.setDropDownViewResource(R.layout.spinner_list);
        SpinnercustomerType.setAdapter(customerTypeDropDown);*/


        ArrayAdapter loanTypeDropDown = ArrayAdapter.createFromResource(this, R.array.LOAN_TYPE, R.layout.spinner_item);
        loanTypeDropDown.setDropDownViewResource(R.layout.spinner_list);
        SpinnerLoanType.setAdapter(loanTypeDropDown);

        ArrayAdapter subloanTypeDropDown = ArrayAdapter.createFromResource(this, R.array.SUBLOAN_TYPE, R.layout.spinner_item);
        subloanTypeDropDown.setDropDownViewResource(R.layout.spinner_list);
        SpinnersubloanType.setAdapter(subloanTypeDropDown);


        /*ArrayAdapter applicationTypeDropDown = ArrayAdapter.createFromResource(this, R.array.APPLICATION_TYPE, R.layout.spinner_item);
        applicationTypeDropDown.setDropDownViewResource(R.layout.spinner_list);
        SpinnerApplicationType.setAdapter(applicationTypeDropDown);*/

        /*ArrayAdapter natureOfProposalType = ArrayAdapter.createFromResource(this, R.array.NATURE_OF_PROPOSAL, R.layout.spinner_item);
        natureOfProposalType.setDropDownViewResource(R.layout.spinner_list);
        SpinnerNatureofProposalType.setAdapter(natureOfProposalType);*/

    }



}
