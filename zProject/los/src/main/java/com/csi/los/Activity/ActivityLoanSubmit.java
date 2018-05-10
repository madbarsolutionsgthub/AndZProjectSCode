package com.csi.los.Activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.csi.los.R;
import com.csi.los.Utility.Constants;
import com.csi.los.Utility.Utility;

import info.hoang8f.android.segmented.SegmentedGroup;

public class ActivityLoanSubmit extends AppCompatActivity {
    Toolbar toolbar;
    TextView textViewNatureOfProposal;
    RadioButton radioButtonNewProposal,radioButtonExistingProposal;
    AutoCompleteTextView autoCompleteBankAccountNo;
    SegmentedGroup segmentedGroupNatureOfProposal;
    EditText editText;
    EditText editTextCustomerCode;
    Context mContext=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_submit);
        initUI();
        initToolBar();

        Typeface typefaceLobster = Typeface.createFromAsset(getAssets(),"font/lobster.ttf");
        textViewNatureOfProposal.setTypeface(typefaceLobster);

        segmentedGroupNatureOfProposal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                if(checkId==R.id.radioExistingLoan){
                    autoCompleteBankAccountNo.setEnabled(true);


                }
                else {
                    autoCompleteBankAccountNo.setEnabled(false);
                }

            }
        });
       // editTextCustomerCode.setText(Utility.getSharedPrefString(getApplicationContext(),Constants.SharedprefItem.BANK_ACC_NO));
        try {
            editTextCustomerCode.setText(Utility.getString(mContext,Constants.SharedprefItem.BRANCH_NAME1));
        }catch (Exception e){}

    }

    private void initToolBar() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.loanSubmit);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initUI() {
        textViewNatureOfProposal = (TextView) findViewById(R.id.textViewNatureOfProposal);
        radioButtonExistingProposal = (RadioButton) findViewById(R.id.radioExistingLoan);
        autoCompleteBankAccountNo = (AutoCompleteTextView) findViewById(R.id.autoCompleteBankAccountNo);
        segmentedGroupNatureOfProposal = (SegmentedGroup) findViewById(R.id.segmentedGroupNatureOfProposal);
        editText =(EditText) findViewById(R.id.editTextCustomerType);
        editTextCustomerCode = (EditText) findViewById(R.id.editTextCustomerCode);
    }
}
