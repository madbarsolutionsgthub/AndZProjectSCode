package com.csi.institutionsprofile.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.csi.institutionsprofile.model.Student;

/**
 * Created by Jahid on 9/20/17.
 */

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 7;

    // Database Name
    private static final String DATABASE_NAME = "searchwidget.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Student.TABLE  + "("
                + Student.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Student.KEY_name + " TEXT, "
                + Student.KEY_principle + " TEXT, "
                + Student.KEY_principle_position + " TEXT, "
                + Student.KEY_age + " INTEGER, "
                + Student.KEY_division + " TEXT, "
                + Student.KEY_district + " TEXT, "
                + Student.KEY_thana + " TEXT, "
                + Student.KEY_mobile + " TEXT, "
                + Student.KEY_type + " TEXT, "
                + Student.KEY_category + " TEXT, "
                + Student.KEY_email + " TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE);

        // Create tables again
        onCreate(db);

    }

}