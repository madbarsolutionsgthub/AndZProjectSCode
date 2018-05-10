package com.csi.los.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.csi.los.Activity.ActivityHome;
import com.csi.los.R;
import com.csi.los.Utility.Constants;
import com.csi.los.Utility.MonthToText;
import com.csi.los.Utility.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FragmentPersonalInfo extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Toolbar toolbar;
    //present address
    ArrayAdapter city;
    private Context myContext = null;
    EditText editTextPresentAddress,editTextPresentPostCode,editTextLivingPeriodYears,editTextLivingPeriodMonth;
    //permanent address
    EditText editTextPermanentAddress,editTextPermanentPostCode,editTextPermanentLivingPeriodYears,editTextPermanentLivingPeriodMonth;
    //date input editText
    EditText editTextDateOfBirth,editTextPassExpDate,editTextDlExpDate;
    //linealayout home and commit
    LinearLayout linearLayoutHome,linearLayoutCommit;
    //Button home and commit
    Button buttonHome,buttonCommit;
    //Spinner
    Spinner spinnerGender,spinnerMaritalStatus,spinnerEducationLevel,spinnerCountry,spinnerCity,spinnerParmanentCountry,spinnerParmanentCity;
    EditText editTextCustomerCode;
    Context mContext=getActivity();
    //calender
    Calendar calendar;
    int year,day,month;
    CheckBox checkBoxSameAsResidence;
    //sharedPreference
    SharedPreferences sharedPreferences;
    public static final String DEFAULT_DISTRICT = "DEFAULT_DISTRICT";
    String getAddress,getPost,getYears,getMonth,getCity;

    private OnFragmentInteractionListener mListener;

    public FragmentPersonalInfo() {
    }
    // TODO: Rename and change types and number of parameters
    public static FragmentPersonalInfo newInstance(String param1, String param2) {
        FragmentPersonalInfo fragment = new FragmentPersonalInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_personal_info, container, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setUpUI(view);
        initToolBar(view);
        checkBoxClick();
        selectDate(view);
        onClick(view);
        spinnerDropDown(view);
        return view;
    }

    private void spinnerDropDown(View view) {
        ArrayAdapter gender = ArrayAdapter.createFromResource(this.getActivity(), R.array.GENDER, android.R.layout.simple_spinner_item);
        gender.setDropDownViewResource(R.layout.spinner_list);
        spinnerGender.setAdapter(gender);
         city = ArrayAdapter.createFromResource(this.getActivity(), R.array.CITY, android.R.layout.simple_spinner_item);
        city.setDropDownViewResource(R.layout.spinner_list);
        spinnerCity.setAdapter(city);
        ArrayAdapter country = ArrayAdapter.createFromResource(this.getActivity(), R.array.COUNTRY, android.R.layout.simple_spinner_item);
        country.setDropDownViewResource(R.layout.spinner_list);
        spinnerCountry.setAdapter(country);
        ArrayAdapter maritalStatus = ArrayAdapter.createFromResource(this.getActivity(), R.array.MARITAL_STATUS, android.R.layout.simple_spinner_item);
        maritalStatus.setDropDownViewResource(R.layout.spinner_list);
        spinnerMaritalStatus.setAdapter(maritalStatus);
        ArrayAdapter educationLevel = ArrayAdapter.createFromResource(this.getActivity(), R.array.EDUCATION, android.R.layout.simple_spinner_item);
        educationLevel.setDropDownViewResource(R.layout.spinner_list);
        spinnerEducationLevel.setAdapter(educationLevel);
        ArrayAdapter permanentCountry = ArrayAdapter.createFromResource(this.getActivity(), R.array.COUNTRY, android.R.layout.simple_spinner_item);
        permanentCountry.setDropDownViewResource(R.layout.spinner_list);
        spinnerParmanentCountry.setAdapter(permanentCountry);
        ArrayAdapter permanentCity = ArrayAdapter.createFromResource(this.getActivity(), R.array.CITY, android.R.layout.simple_spinner_item);
        permanentCity.setDropDownViewResource(R.layout.spinner_list);
        spinnerParmanentCity.setAdapter(permanentCity);

    }

    private void onClick(View view) {
        linearLayoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityHome.class);
                startActivity(intent);
            }
        });
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityHome.class);
                startActivity(intent);
            }
        });
        buttonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.putString(getActivity(),Constants.SharedprefItem.BRANCH_NAME1,editTextCustomerCode.getText().toString());
                editTextPresentPostCode.setText(Utility.getString(getActivity(),Constants.SharedprefItem.BRANCH_NAME1));
                editTextPermanentPostCode.setText("vjsjavhc");
            }
        });

        Typeface typefaceLobster = Typeface.createFromAsset(getActivity().getAssets(),"font/lobster.ttf");
        buttonHome.setTypeface(typefaceLobster);
        buttonCommit.setTypeface(typefaceLobster);
    }

    private void selectDate(View view) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);

        editTextDateOfBirth.setText(MonthToText.mothNameText(day + "-" + (month + 1) + "-" + year));
        editTextPassExpDate.setText(MonthToText.mothNameText(day + "-" + (month + 1) + "-" + year));
        editTextDlExpDate.setText(MonthToText.mothNameText(day + "-" + (month + 1) + "-" + year));

        editTextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {
                        editTextDateOfBirth.setText(MonthToText.mothNameText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + mYear));
                    }
                }, year, month, day);
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();
            }
        });
        editTextPassExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {
                        editTextDateOfBirth.setText(MonthToText.mothNameText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + mYear));
                    }
                }, year, month, day);
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();

            }
        });
        editTextDlExpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {
                        editTextDateOfBirth.setText(MonthToText.mothNameText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + mYear));
                    }
                }, year, month, day);
                datePickerDialog.setTitle("Select Date");
                datePickerDialog.show();

            }
        });
        
    }
    private void checkBoxClick() {
        checkBoxSameAsResidence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    try {
                        getAddress = editTextPresentAddress.getText().toString();
                        getPost = editTextPresentPostCode.getText().toString();
                        getYears = editTextLivingPeriodYears.getText().toString();
                        getMonth = editTextLivingPeriodMonth.getText().toString();



                        editTextPermanentAddress.setText(getAddress);
                        editTextPermanentPostCode.setText(getPost);
                        editTextPermanentLivingPeriodYears.setText(getYears);
                        editTextPermanentLivingPeriodMonth.setText(getMonth);
                    }catch (Exception e){
                    }
                }
                else {
                    try {
                        editTextPermanentAddress.setText("");
                        editTextPermanentPostCode.setText("");
                        editTextPermanentLivingPeriodYears.setText("");
                        editTextPermanentLivingPeriodMonth.setText("");
                    }catch (Exception e){}
                }
            }
        });
    }

    private void initToolBar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.black_arrow);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(R.string.personalInfo);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setUpUI(View view) {
        editTextPresentAddress = (EditText) view.findViewById(R.id.editTextAddress);
        editTextPresentPostCode = (EditText) view.findViewById(R.id.editTextPostCode);
        editTextLivingPeriodMonth = (EditText) view.findViewById(R.id.editTextMonth);
        editTextLivingPeriodYears = (EditText) view.findViewById(R.id.editTextYears);
        editTextPermanentAddress = (EditText) view.findViewById(R.id.editTextPermanentAddress);
        editTextPermanentPostCode = (EditText) view.findViewById(R.id.editTextPermanentPostCode);
        editTextPermanentLivingPeriodYears = (EditText) view.findViewById(R.id.editTextPermanentYears);
        editTextPermanentLivingPeriodMonth = (EditText) view.findViewById(R.id.editTextPermanentMonth);
        checkBoxSameAsResidence = (CheckBox) view.findViewById(R.id.chekboxSameAsResidence);
        editTextDateOfBirth = (EditText) view.findViewById(R.id.editTextDateOfBirth);
        editTextPassExpDate = (EditText) view.findViewById(R.id.editTextPassExpDate);
        editTextDlExpDate = (EditText) view.findViewById(R.id.editTextDlExpDate);
        linearLayoutHome = (LinearLayout) view.findViewById(R.id.linearlayoutHome);
        linearLayoutCommit = (LinearLayout) view.findViewById(R.id.linearlayoutCommit);
        buttonHome = (Button) view.findViewById(R.id.buttonHome);
        buttonCommit = (Button) view.findViewById(R.id.buttonCommit);
        spinnerGender = (Spinner) view.findViewById(R.id.spinnerGender);
        spinnerMaritalStatus = (Spinner) view.findViewById(R.id.spinnerMaritalStatus);
        spinnerEducationLevel = (Spinner) view.findViewById(R.id.spinnerEducationLevel);
        spinnerCountry = (Spinner) view.findViewById(R.id.spinnerCountry);
        spinnerCity = (Spinner) view.findViewById(R.id.spinnerCity);
        spinnerParmanentCountry = (Spinner) view.findViewById(R.id.spinnerPermanentCountry);
        spinnerParmanentCity = (Spinner) view.findViewById(R.id.spinnerPermanentCity);
        editTextCustomerCode = (EditText) view.findViewById(R.id.editTextCustomerCode);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
