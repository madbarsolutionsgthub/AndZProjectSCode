package com.csi.los.Utility;

/**
 * Created by Jahid on 5/22/17.
 */

public class Constants {

    private static int SPLASH_TIME_OUT = 1500;
    private static String CITY = "CITY";

    public class SharedprefItem {
        public static final String BRANCH_NAME = "BRANCH_NAME";
        public static final String BRANCH_NAME1 = "BRANCH_NAME1";
        public static final String CUSTOMER_CODE = "CUSTOMER_CODE";
        public static final String FULL_NAME = "FULL_NAME";

    }
    public class API {
        public static final String  URL = "http://onjonhossain.com/lms/api";
        public static final String  URL_BASE = "http://192.168.0.3/work-1.8/lms/api";
        public static final String  URL_BASE_Resg = URL_BASE+"/loan/apply";
        public static final String  LOAN_STATUS_LIST = URL+"/loan/list";
        public static final String  CUSTOMER_SIGN_UP = URL+"/user/registration";
        public static final String  LOAN_APPLY_AGENT = URL+"/loan/apply";
        public static final String  LOAN_APPLY_CUSTOMER = URL+"/loan/apply";
        public static final String  PROFILE_EDIT_AGENT = URL+"/user/profile/update";
        public static final String  PROFILE_EDIT_CUSTOMER = URL+"user/profile/update";
        public static final String  USER_LOGIN = URL+"/user/login";
        public static final String  AGENT_LOGIN = URL+"/user/login";
        public static final String  USER_IMAGE = "http://onjonhossain.com/lms/public/upload/user-image/";
        public static final String  USER_PROFILE = URL+"/user/profile";
        public static final String  NOTIFICATION = URL+"/gcm/registration";
    }

}
