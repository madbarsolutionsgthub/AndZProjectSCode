package com.csi.institutionsprofile.Utility;

import com.csi.institutionsprofile.model.Market;

import java.util.ArrayList;

/**
 * Created by Jahid on 10/30/17.
 */

public class Constants {
    public static ArrayList<com.csi.institutionsprofile.model.District> districtlist = new ArrayList<>();
    public static ArrayList<com.csi.institutionsprofile.model.Division> divisionlist = new ArrayList<>();
    public static String[] division = { "Dhaka", "Khulna" };
    public static String currentPage = "1";
    public static String perPage = "500";
    public class Api{
        //https://computersourcebd.com/hsspapi/public/api/v1/division/all%5C
        public static final String API_PROTOCOL = "https";
        public static final String API_HOST_ADDR = "computersourcebd.com";
        public static final String API_HOST= API_PROTOCOL + "://" + API_HOST_ADDR;
        public static final String API_PATH_PREFIX = "hsspapi/public";
        public static final String API_PATH = API_PATH_PREFIX +  "/" + "api/v1";
        public static final String API_URI = API_HOST+ "/" + API_PATH;
        public static final String API_NOTICE_ALL= API_URI + "/notice/all";
        public static final String API_AUTHORITY_ALL= API_URI + "/authority/all";
        public static final String API_DIVISION_ALL= API_URI + "/division/all";
        public static final String API_DISTRICT_ALL= API_URI + "/district/all";
        public static final String API_UPAZILLA_ALL= API_URI + "/upazilla/all";
        public static final String API_STAKEHOLDER_ALL= API_URI + "/stakeholder/all";
        public static final String API_INSTITUTION_ALL= API_PATH + "/institution/search";
        public static final String API_AUTHORITY_IMAGE_URL= API_HOST + "/hsspapi/public/upload/authority/";
    }

    public class District{
        public static final String DISTRICT_ID = "id";
        public static final String DISTRICT_NAME = "district_name";
    }
}
