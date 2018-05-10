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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.csi.los.R;
import com.csi.los.Utility.MonthToText;

import java.util.Calendar;

public class ActivityFamilyInfoCustomer extends AppCompatActivity {
    public static  String globalPreferenceName = "ActivityFamilyInfoCustomer";
    Button buttonNext;
    ImageView imageViewBackPress;
    EditText spouceName,fathers_name,MothersName,devision,designation,businessNature,editTextYear;
    Spinner EmployeeProfession,EmployeeCompanyName;
    Calendar calendar;
    int year,day,month;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_info_customer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setupUI();
        spinnerDropDown();
        selectDate();
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= getSharedPreferences(ActivityPersonalInfoCustomer.globalPreferenceName,MODE_PRIVATE);
/*startActivityLoanEntryFromHere*/
                String a=sharedPreferences.getString("A","no");//1.branchnameString
                String b=sharedPreferences.getString("B","no1");//2.loanTypeString
                String c= sharedPreferences.getString("C","no2");//3.subLoanTypeString
                String d= sharedPreferences.getString("D","no3");//4.editTextLoanAmountString
                String e=sharedPreferences.getString("E","no4");//5.editTextLoanAmountTendorString
                String f= sharedPreferences.getString("F","no5");//6.editTextNoOfBorrowerString

         /*EndAntivityLoanEntryHere*/

         /*start ActivityPersonalInfoCustomerFromHere*/
                String g=sharedPreferences.getString("G","n6");//7.DateOfBirth
                String h=sharedPreferences.getString("H","no7");//8.takengender
                String i= sharedPreferences.getString("I","no8");//9.takenResidentialField
                String j= sharedPreferences.getString("J","no9");//10.takenCountry
                String k=sharedPreferences.getString("K","no10");//11.takenCity
                String l= sharedPreferences.getString("L","no11");//12.takenBankAccountNumberField
                String m=sharedPreferences.getString("M","no12");//13.takenCustomerCodeField
                String n=sharedPreferences.getString("N","no13");//14takenFullnameField
                String o= sharedPreferences.getString("O","no14");//15.takennationalIdField
                String p= sharedPreferences.getString("P","no15");//16.takenAddressField
                String q=sharedPreferences.getString("Q","no16");//17.takenPostCodeField
                String r= sharedPreferences.getString("R","no17");//18.takenYearField
                String s= sharedPreferences.getString("S","no18");//19.takenMonthField
/*End AvtivityPersonalInfoCustomerFromHere*/
                Log.d("INFAMILYINFO",a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s);

                //ActivityfamilyInfoCustomer data
                String employeeProfessionString=EmployeeProfession.getSelectedItem().toString();//1
                String employeeCompanyNameString= EmployeeCompanyName.getSelectedItem().toString();//2
                String spocenameString=spouceName.getText().toString();//3
                String fatherNameString= fathers_name.getText().toString();//4
                String motherNameString= MothersName.getText().toString();//5
                String devisionString= devision.getText().toString();//6
                String designationString=designation.getText().toString();//7
                String businessnatureString=businessNature.getText().toString();//8
                String yearString= editTextYear.getText().toString();//combinedYearMonthdate
                //String monthString= month.getText().toString();//combinedYearMonthdate
                //String dateString= date.getText().toString();//combinedYearMonthdate
                //take Year+Month+date in a Single String
                String combinedYearMonthdate = yearString;//9

                if (fatherNameString.isEmpty()){
                    fathers_name.setError("Enter Father's Name");
                    fathers_name.requestFocus();
                }
                else if (motherNameString.isEmpty()){
                    MothersName.setError("Enter Mother's Name");
                    MothersName.requestFocus();
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences(globalPreferenceName, MODE_PRIVATE).edit();

                /*From here get all data for shared*/

                    editor.putString("A", a);//1
                    editor.putString("B", b);//2
                    editor.putString("C", c);//3
                    editor.putString("D", d);//4
                    editor.putString("E", e);//5
                    editor.putString("F", f);//6
                    editor.putString("G", g);//7
                    editor.putString("H", h);//8
                    editor.putString("I", i);//9
                    editor.putString("J", j);//10
                    editor.putString("K", k);//11
                    editor.putString("L", l);//12
                    editor.putString("M", m);//13
                    editor.putString("N", n);//14
                    editor.putString("O", o);//15
                    editor.putString("P", p);//16
                    editor.putString("Q", q);//17
                    editor.putString("R", r);//18
                    editor.putString("S", s);//19
                    editor.putString("T", employeeProfessionString);//20
                    editor.putString("U", employeeCompanyNameString);//21
                    editor.putString("V", spocenameString);//22
                    editor.putString("W", fatherNameString);//23
                    editor.putString("X", motherNameString);//24
                    editor.putString("Y", combinedYearMonthdate);//25
                    editor.putString("departmentString1", devisionString);//26
                    editor.putString("designation1", designationString);//27
                    editor.putString("businessnatureString1", businessnatureString);//28
                    editor.commit();

                    Log.d("Combined", combinedYearMonthdate);

                    startActivity(new Intent(ActivityFamilyInfoCustomer.this, ActivityContactInfoCustomer.class));
                }
            }
        });
    }

    private void selectDate() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);

        editTextYear.setText(MonthToText.mothNameText(day + "-" + (month + 1) + "-" + year));
        editTextYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {
                        editTextYear.setText(MonthToText.mothNameText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + mYear));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void setupUI(){
        spouceName= (EditText) findViewById(R.id.editTextSpouseName);
        fathers_name=(EditText) findViewById(R.id.editTextFathersName);
        MothersName=(EditText) findViewById(R.id.editTextMothersName);
        devision=(EditText) findViewById(R.id.editTextDepartment);
        designation=(EditText) findViewById(R.id.editTextApplicantDesignation);
        businessNature=(EditText) findViewById(R.id.editTextBusinessNature);
        editTextYear=(EditText) findViewById(R.id.editTextYearsFrom);
        //month= (EditText) findViewById(R.id.editTextMonthFrom);
        //date=(EditText) findViewById(R.id.editTextDateFrom);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        EmployeeProfession= (Spinner) findViewById(R.id.spinnerEmplymentProfession);
        EmployeeCompanyName= (Spinner) findViewById(R.id.spinnerCompanyName);
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
    }

    private void spinnerDropDown() {
        ArrayAdapter employeeProfession = ArrayAdapter.createFromResource(this, R.array.EMPLOYEE_PROFESSION, R.layout.spinner_item);
        employeeProfession.setDropDownViewResource(R.layout.spinner_list);
        EmployeeProfession.setAdapter(employeeProfession);

        ArrayAdapter employeeCompanyName = ArrayAdapter.createFromResource(this, R.array.EMPLOYEE_COMPANY, R.layout.spinner_item);
        employeeCompanyName.setDropDownViewResource(R.layout.spinner_list);
        EmployeeCompanyName.setAdapter(employeeCompanyName);


    }


}