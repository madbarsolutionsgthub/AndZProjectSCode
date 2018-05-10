package com.csi.institutionsprofile.model;

/**
 * Created by Jahid on 9/20/17.
 */

public class Student {
    public static final String TABLE = "Student";

    // Labels Table Columns names
    public static final String KEY_ROWID = "_id";
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_email = "email";
    public static final String KEY_age = "age";
    public  static final String KEY_principle = "principle";
    public  static final String KEY_principle_position = "principleposition";
    public  static final String KEY_division = "division";
    public  static final String KEY_district = "district";
    public  static final String KEY_thana = "thana";
    public  static final String KEY_mobile = "mobile";
    public  static final String KEY_type = "type";
    public  static final String KEY_category = "category";

    // property help us to keep data
    public int student_ID;
    public String name;
    public String email;
    public int age;
    public String principle;
    public String principle_position;
    public String division;
    public String district;
    public String thana;
    public String mobile;
    public String type;
    public String category;
}
