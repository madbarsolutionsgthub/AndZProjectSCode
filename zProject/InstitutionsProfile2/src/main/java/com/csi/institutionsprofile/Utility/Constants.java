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
    public static String perPage = "6500";
    public class Api{
        //https://computersourcebd.com/hsspapi/public/api/v1/division/all%5C
        //http://172.16.31.108:8000/api/v1/institution/search?search_by=division&search=3&category=0&page=1&per_page=15
        public static final String API_PROTOCOL = "http";//https
        public static final String API_HOST_ADDR = "103.11.136.159";//
        public static final String API_HOST= API_PROTOCOL + "://" + API_HOST_ADDR;
        public static final String API_PATH_PREFIX = "hsspapi/public";//
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
        public static final String API_AUTHORITY_PDF_URL= API_HOST + "/hsspapi/public/upload/pdf/";
    }

    public class District{
        public static final String DISTRICT_ID = "id";
        public static final String DISTRICT_NAME = "district_name";
    }

    public class Data{
        public static final String HSSP ="গনপ্রজাতন্ত্রী বাংলাদেশ সরকারের মাননীয় প্রধানমন্ত্রী শেখ হাসিনার সুদুরপ্রসারী পরিকল্পনা “ভিশন-২০২১” সফল করার লক্ষ্যে ব্যাপক উন্নয়ণ কার্যক্রম হাতে নেওয়া হয়েছে । সরকার শিক্ষাকে সর্বাধিক গুরুত্বপূর্ণ খাত হিসাবে চিহ্নিত করেছে । এর অংশ হিসেবে সবার জন্য শিক্ষার অধিকার নিশ্চিত করার লক্ষ্যে যুগোপযোগী " +
                "শিক্ষানীতি প্রণয়ন করা হয়েছে । আমাদের দেশে নারী শিক্ষার ব্যাপক প্রসার ঘটেছে । নারী শিক্ষাসহ সকল শিক্ষার্থীদের আরো বেশী শিক্ষামুখী করতে গ্রহন করা হয়েছে নানা বাস্তবমুখী প্রকল্প । উচ্চ মাধ্যমিক উপবৃত্তি প্রকল্প উপবৃত্তি প্রদানের মাধ্যমে শিক্ষার্থীদের উচ্চ মাধ্যমিক ও উচ্চ শিক্ষা গ্রহনে আগ্রহী করে" +
                "তুলে ভালো কর্মসংস্থান সৃষ্টিকরন, দারিদ্র দূরীকরণসহ সকল শিক্ষার্থীদের আর্থ –সামাজিক কর্মকান্ডে সম্পৃক্ত করছে । অর্থের অভাবে দরিদ্র, সুবিধাবঞ্চিত ছেলে-মেয়েরা যাতে উচ্চ মাধ্যমিক শিক্ষায় ঝরে না পড়ে তার ব্যবস্থা করা হচেছ । এ প্রকল্পের আওতায় সরকার সমগ্র বাংলাদেশে উচ্চ মাধ্যমিক পর্যায়ে ভর্তিকৃত সকল ছাত্রীর মধ্যে ৪০% ছাত্রী এবং ভর্তিকৃত সকল ছাত্রের মধ্যে ১০% ছাত্রকে উপবৃত্তি ও অন্যান্য সুবিধাদির অর্থ প্রদান করছে । দরিদ্র শিক্ষার্থী পড়াশুনা চালিয়ে নেওয়ার সুযোগ পাচ্ছে । নারীর সামাজিক মর্যাদা বাড়ছে, নারীর ক্ষমতায়ন হচ্ছে । সামাজিক গতিশীলতা বৃদ্ধি পাচ্ছে । সর্বোপরি দারিদ্র দূরীভূত হচ্ছে । ২০২১ সনে বাংলাদেশ মধ্যম আয়ের দেশ হিসেবে প্রতিষ্ঠা লাভ করবে ।";

        public static final String HSSP_PURPOSE ="বৃত্তি প্রদানের মাধ্যমে শিক্ষার্থীদের উচ্চ মাধ্যমিক ও উচ্চ শিক্ষা গ্রহনে আগ্রহী করে তোলা তথা ভালো কর্ম সংস্থানের  সৃষ্টিকরন, দারিদ্র দূরীকরনসহ তাদেরকে আর্থ-সামাজিক কর্মকাণ্ডে সম্পৃক্তকরন ।";
        public static final String HSSP_PURPOSE_EXACT ="ϖ\t\tএকাদশ ও দ্বাদশ শ্রেণীতে ভর্তিকৃত ছাত্রীদের মধ্যে ৪০% ছাত্রী এবং ভর্তিকৃত ছাত্রদের মধ্য হতে ১০% ছাত্রকে উপবৃত্তি প্রদান করা।\n" +
                "ϖ\t\tএসএসসি পাশ শিক্ষার্থীদের উপবৃত্তিসহ বই-পুস্তক ক্রয় এবং পরীক্ষার ফি প্রদানের মাধ্যমে উচ্চ শিক্ষা স্তরে তাদের শিক্ষাক্রম চালুরাখা এবং ছাত্র-ছাত্রীদের মধ্যে শিক্ষা ক্ষেএে সমতা অর্জন করা।\n" +
                "ϖ\t\tউচ্চ মাধ্যমিক পর্যায়ে বিজ্ঞান শিক্ষায় উৎসাহ সৃষ্টি করার লক্ষ্য বর্ধিত হারে উপবৃত্তি ও অন্যান্য আর্থিক সুবিধাদি প্রদান করা।\n" +
                "ϖ\t\tজনসংখ্যা বৃদ্ধির হার হ্রাস করার লক্ষ্যে শিক্ষার্থীদেরকে উচ্চ মাধ্যমিক" +
                " পরীক্ষা পর্যন্ত অবিবাহিত রাখা।\n" +
                "ϖ\t\tদরিদ্র পরিবার থেকে আগত উচ্চ মাধ্যমিক পর্যায়ের শিক্ষার্থীদের ঝরে পড়া রোধ করা।\n" +
                "ϖ\t\tশিক্ষার্থীদের উচ্চশিক্ষা গ্রহনে আগ্রহী করে তোলা।\n" +
                "ϖ\t\tশিক্ষার্থীরা যাতে ভালো কর্মসংস্থান সৃষ্টির মাধ্যমে দারিদ্র দুরকরে আর্থ-সামাজিক কর্মকাণ্ডে অংশ গ্রহণ করতে পারে সে বিষয়ে উদ্বুদ্ধ করা।" ;
        public static final String WHAT_HSSP_DO ="ক) শিক্ষার্থীদের উপবৃত্তি প্রদান\nখ) শিক্ষার্থীদের বই ক্রয় অনুদান প্রদান\nগ) টিউশন ফি প্রদান\nঘ) ফরম পূরণ ফি বাবদ অনুদান প্রদান";
        public static final String HSSP_PURPOSE_TITLE ="প্রকল্পের উদ্দেশ্যঃ";
        public static final String HSSP_PURPOSE_EXACT_TITLE ="প্রকল্পের সুনির্দিষ্ট উদ্দেশ্যঃ";
        public static final String WHAT_HSSP_DO_TITLE ="প্রকল্পের প্রধান কার্যক্রমঃ";
        public static final String HSSP_AMOUNT ="মাসিক উপবৃত্তি ও অন্যান্য সুবিধাদির হারঃ ";


        public static final String HSSP_ENG = "Many development programmes have been taken to implement ‘vision 2021’-a far-sighted plan by the leader and Honorable Prime Minister of the People’s Republic of Bangladesh, Sheikh Hasina." +
                " The government has marked education sector as the most important sector. As a part of this, modern education policy has been dropted to ensure the right of education for all. " +
                "Female education has become widespread in our country. Many practical measures have been taken to attract all the learners, including female learners to education. " +
                "Keeping this objective/goal, Higher SSP stranded functioning Providing stipend at the Higher secondary and Higher education level.  " +
                "It also involves learners in creating better employment opportunity, removing poverty and in various socio-economic activates.";
        public static final String HSSP_PURPOSE_ENG = "Enhancement of education in the higher Secondary and tertiary levels and involves them in the socio economic actives including better employment generation and reduction poverty by providing stipends.";
        public static final String HSSP_PURPOSE_EXACT_ENG = "** To encourage Pro-poor students with a view to continuing their study up to HSC level (Grade-XI and Grade-XII) by providing incentives to 40% of the total female and 10% of the total male enrolment .\n" +
                "** To encourage more male and female students to study in science group at HSC and tertiary levels.\n" +
                "** To reduce population growth rate by ensuring of being unmarried the stipend recipient students up to HSC/equivalent level.\n" +
                "** To encourage female participation in socio-economic activities together with them, when will expedite the women’s empowerment. \n" +
                "** To reduce the drop rate of the students who come from the pro-poor families\n" +
                "** To reduce disparity between male and female in the society.\n" +
                "** To provide support to the Government for accelerating poverty alleviation programm and thereby enhance the scope for employment and self employment.";
        public static final String WHAT_HSSP_DO_ENG =
                "1. Providing Stipend of the Students.\n" +
                "2. Assistances for purchasing books.\n" +
                "3. Providing Titution fees.\n" +
                "4. Providing examination fees\n" +
                "5. Financial help as stipend and other facilities for students.";
        public static final String HSSP_PURPOSE_TITLE_ENG = "Vision of Higher Secondary Stipend Project:";
        public static final String HSSP_PURPOSE_EXACT_TITLE_ENG = "Objectives of Higher Secondary Stipend Project:";
        public static final String WHAT_HSSP_DO_TITLE_ENG = "Mission of Higher Secondary Stipend Project:";
        public static final String RATE = "Rate of monthly stipend and other facilities :";

    }
}
