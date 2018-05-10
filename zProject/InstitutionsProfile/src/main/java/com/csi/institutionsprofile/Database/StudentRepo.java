package com.csi.institutionsprofile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.csi.institutionsprofile.model.Student;

/**
 * Created by Jahid on 9/20/17.
 */

public class StudentRepo {
    private DBHelper dbHelper;

    public StudentRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Student student) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.KEY_age, student.age);
        values.put(Student.KEY_email,student.email);
        values.put(Student.KEY_name, student.name);
        values.put(Student.KEY_principle, student.principle);
        values.put(Student.KEY_principle_position, student.principle_position);
        values.put(Student.KEY_division, student.division+",");
        values.put(Student.KEY_district, student.district+",");
        values.put(Student.KEY_thana, student.thana);
        values.put(Student.KEY_mobile, student.mobile);
        values.put(Student.KEY_type, student.type);
        values.put(Student.KEY_category, student.category);

        // Inserting Row
        long student_Id = db.insert(Student.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;
    }
    public Cursor getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                Student.KEY_ROWID + "," +
                Student.KEY_ID + "," +
                Student.KEY_name + "," +
                Student.KEY_email + "," +
                Student.KEY_principle_position + "," +
                Student.KEY_age + "," +
                Student.KEY_division + "," +
                Student.KEY_district + "," +
                Student.KEY_thana + "," +
                Student.KEY_mobile + "," +
                Student.KEY_type + "," +
                Student.KEY_category + "," +
                Student.KEY_principle +
                " FROM " + Student.TABLE;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }


    public Cursor  getStudentListByKeyword(String search) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                Student.KEY_ROWID + "," +
                Student.KEY_ID + "," +
                Student.KEY_name + "," +
                Student.KEY_email + "," +
                Student.KEY_principle + "," +
                Student.KEY_principle_position + "," +
                Student.KEY_division + "," +
                Student.KEY_district + "," +
                Student.KEY_thana + "," +
                Student.KEY_mobile + "," +
                Student.KEY_type + "," +
                Student.KEY_category + "," +
                Student.KEY_age +
                " FROM " + Student.TABLE +
                " WHERE " +  Student.KEY_name+"  LIKE  '%" +search + "%' "+
                " OR " +  Student.KEY_principle+"  LIKE  '%" +search + "%' "+
                " OR " +  Student.KEY_category+"  LIKE  '%" +search + "%' "
                ;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }

    public Student getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT " +
                Student.KEY_ID + "," +
                Student.KEY_name + "," +
                Student.KEY_email + "," +
                Student.KEY_age + "," +
                Student.KEY_principle_position + "," +
                Student.KEY_division + "," +
                Student.KEY_district + "," +
                Student.KEY_thana + "," +
                Student.KEY_mobile + "," +
                Student.KEY_type + "," +
                Student.KEY_category + "," +
                Student.KEY_principle +
                " FROM " + Student.TABLE
                + " WHERE " +
                Student.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Student student = new Student();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                student.student_ID =cursor.getInt(cursor.getColumnIndex(Student.KEY_ID));
                student.name =cursor.getString(cursor.getColumnIndex(Student.KEY_name));
                student.email  =cursor.getString(cursor.getColumnIndex(Student.KEY_email));
                student.age =cursor.getInt(cursor.getColumnIndex(Student.KEY_age));
                student.principle =cursor.getString(cursor.getColumnIndex(Student.KEY_principle));
                student.principle_position =cursor.getString(cursor.getColumnIndex(Student.KEY_principle_position));
                student.division =cursor.getString(cursor.getColumnIndex(Student.KEY_division));
                student.district =cursor.getString(cursor.getColumnIndex(Student.KEY_district));
                student.thana =cursor.getString(cursor.getColumnIndex(Student.KEY_thana));
                student.mobile =cursor.getString(cursor.getColumnIndex(Student.KEY_mobile));
                student.type =cursor.getString(cursor.getColumnIndex(Student.KEY_type));
                student.category =cursor.getString(cursor.getColumnIndex(Student.KEY_category));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return student;
    }

}
