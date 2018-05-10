package com.csi.institutionsprofile.Utility;

import android.util.Log;

import com.csi.institutionsprofile.model.District;
import com.csi.institutionsprofile.model.Division;

public class Util {
    public static District getDistrictByName(String districtName) {
        for (District district: Constants.districtlist) {
            if (district.getName().equals(districtName)) {
                return district;
            }
        }
        return null;
    }

    public static String getDivisionByName(String divisionName) {

        Log.d("Division Test", Integer.toString(Constants.divisionlist.size()));
        for (Division division : Constants.divisionlist) {
            Log.d("division Name", division.getName());
        }

        return null;

        /*for (Division division: Constants.divisionlist) {
            if (division.getName().equals(divisionName)) {
                return division;
            }
        }
        return null;*/
    }

    }
