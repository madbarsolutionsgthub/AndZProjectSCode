package com.csi.los.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Jahid on 7/25/17.
 */

public class Utility {
    public static String getString(Context mContext, String key){
        try {
            SharedPreferences prefs = mContext.getSharedPreferences("MY_PREFS_NAME", Context.MODE_PRIVATE);
            return prefs.getString(key, "Los"); //Los is the default value.
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Error", ex.getLocalizedMessage());
            return "";
        }
    }

    public static String getString(Context mContext, String key,  String defaultValue){
        try {
            SharedPreferences prefs = mContext.getSharedPreferences("MY_PREFS_NAME", Context.MODE_PRIVATE);
            return prefs.getString(key, "Los");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Error", ex.getLocalizedMessage());
            return "";
        }
    }

    public static void putString(Context mContext, String key, String value ){
        try {
            SharedPreferences pref= mContext.getSharedPreferences("MY_PREFS_NAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Error", ex.getLocalizedMessage());
        }
    }
    public static void clear(Context mContext) {
        try {
            SharedPreferences pref = mContext.getSharedPreferences("MY_PREFS_NAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Error", ex.getLocalizedMessage());
        }
    }
}
