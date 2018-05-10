package com.csi.institutionsprofile.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.csi.institutionsprofile.Database.CustomAdapter;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;
import com.csi.institutionsprofile.adapter.InstituteAdapter;
import com.csi.institutionsprofile.model.Institute;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dmax.dialog.SpotsDialog;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class ActivityInstituteSearch extends AppCompatActivity {
    ImageView imageViewBackPress;
    private CustomAdapter customAdapter;
    ImageView imageViewSearch;
    AutoCompleteTextView searchView;
    Spinner spinnerInstitute;
    TextView textViewSearchTitle;
    public static FloatingActionMenu floatingActionMenu,floatingActionMenu1;
    public static FloatingActionButton floatingActionButtonSms,floatingActionButtonEmail,floatingActionButtonSms1,floatingActionButtonEmail1;
    CheckBox chekBoxGovt, chekBoxNonGovt, chekBoxMpo, chekBoxCollege, chekBoxMadrasha, chekBoxBmCollege, chekBoxSchoolCollege;
    public static CheckBox checkBoxMultiSms;
    Context context = this;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private static final String URL_INSTITUTE = Constants.Api.API_INSTITUTION_ALL;
    RecyclerView recyclerViewInstitute;
    //private Dialog loadingDialog;
    private SpotsDialog loadingDialog;
    public List<Institute> instituteList;
    LinearLayoutManager layoutManager;
    Uri.Builder builder;
    String institutionSearchUrl, searchText;
    int dropDownSelectedItem = -1;
    int searchDropdownIndex = 0;
    int institutionType = 0;
    int institutionCategory = 0;
    private ArrayList<String> division,district,upazilla, divisionClear,districtClear,upazillaClear;
    JSONArray jsonArray;
    int[] institutionTypeArr = new int[3];
    int[] institutionCategoryArr = new int[4];
    int suggesstionAddapterFlag = 0;

    Map<String, Integer> divisionMap = new HashMap<String, Integer>();
    Map<String, Integer> districtnMap = new HashMap<String, Integer>();
    Map<String, Integer> upazillaMap = new HashMap<String, Integer>();

    private ArrayList<String> numberArrayList = new ArrayList<>();
    private ArrayList<String> emailArrayList = new ArrayList<>();
    int position;
    public static int itemCount;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;
    boolean isLoading = true;
    boolean isLastPage = true;

    //private final static String TAG= ActivityInstituteSearch.class.getName().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_search);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        setupUI();
        onClick();
        spinnerDropDown();
        apiQueryBuilder();
        setPagination(Constants.currentPage, Constants.perPage);
        institutionSearchUrl = Uri.decode( builder.build().toString() );
        Log.d("URL BUILDER", institutionSearchUrl);
        //loadInstution( institutionSearchUrl );
        checkBoxSelection();
        loadDivision();
        loadDistrict();
        loadUpazilla();

        instituteList = new ArrayList<>();
        division = new ArrayList<>();
        divisionClear = new ArrayList<>();

        district = new ArrayList<>();
        districtClear = new ArrayList<>();

        upazilla = new ArrayList<>();
        upazillaClear = new ArrayList<>();


        /*String[] array = new String[] {"1","2","3"};
        Arrays.asList(array).indexOf("1");
        Log.d("Index", String.valueOf(Arrays.asList(array).indexOf("3")));*/





        recyclerViewInstitute.setHasFixedSize(true);
        recyclerViewInstitute.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInstitute.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).color(Color.TRANSPARENT).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());

        //pagination
        layoutManager = new LinearLayoutManager(ActivityInstituteSearch.this);
        recyclerViewInstitute.setLayoutManager(layoutManager);


        recyclerViewInstitute.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //recyclerView.computeHorizontalScrollOffset();
                //int i=recyclerView.computeHorizontalScrollOffset();
                //Log.d("mathod",Integer.toString(i));
                //Log.d("dx",Integer.toString(dy));
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        sendQuery();
                    }
                }


            }
        });

        //pagination try
        layoutManager = new LinearLayoutManager(this);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            int offset = 1;
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //loadNextDataFromApi(page);

            }

            private void loadNextDataFromApi(int offset) {
                this.offset= offset;
            }

        };


        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstitutionAndPrincipleSearch();
            }
        });

        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int searchKeyPress, KeyEvent keyEvent) {
                if ( searchKeyPress== EditorInfo.IME_ACTION_SEARCH) {
                    InstitutionAndPrincipleSearch();
                    return true;
                }
                return false;
            }
        });

    }

    private void sendQuery(){
        apiQueryBuilder();
        setPagination(Constants.currentPage, Constants.perPage);
        institutionSearchUrl = Uri.decode( builder.build().toString() ).replace( " ", "%20" );
        Log.d( "URL BUILDER", institutionSearchUrl );
        loadInstution( institutionSearchUrl );
    }

    private void autoSuggestionListView(int searchTypeId) {

        switch ( searchTypeId ) {
            case 2:
                suggesstionAddapterFlag = searchTypeId;
                ArrayAdapter divisionAdapter = new ArrayAdapter<String>(ActivityInstituteSearch.this, R.layout.spinner_item, division);
                searchView.setAdapter(divisionAdapter);
                divisionAdapter.notifyDataSetChanged();

                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String divisionName = searchView.getText().toString();
                        Log.d("divisionId", divisionMap.get( divisionName ).toString() );

                        searchDropdownIndex  =  Integer.parseInt( divisionMap.get( divisionName ).toString() );
                        dropDownSelectedItem = 2;
                        sendQuery();
                    }
                });
                break;
            case 3:
                suggesstionAddapterFlag = searchTypeId;
                ArrayAdapter districtAdapter = new ArrayAdapter<String>(ActivityInstituteSearch.this, R.layout.spinner_item, district);
                searchView.setAdapter(districtAdapter);
                districtAdapter.notifyDataSetChanged();

                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String districtName = searchView.getText().toString();
                        Log.d("districtId", districtnMap.get( districtName ).toString() );

                        searchDropdownIndex  =  Integer.parseInt( districtnMap.get( districtName ).toString() );
                        dropDownSelectedItem = 3;
                        sendQuery();

                    }
                });
                break;
            case 4:
                suggesstionAddapterFlag = searchTypeId;
                ArrayAdapter upazillaAdapter = new ArrayAdapter<String>(ActivityInstituteSearch.this, R.layout.spinner_item, upazilla);
                searchView.setAdapter(upazillaAdapter);
                upazillaAdapter.notifyDataSetChanged();

                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String upazillaName = searchView.getText().toString();
                        Log.d("upazillaId", upazillaMap.get( upazillaName ).toString() );

                        searchDropdownIndex  =  Integer.parseInt( upazillaMap.get( upazillaName ).toString() );
                        dropDownSelectedItem = 4;
                        sendQuery();

                    }
                });
                break;
            default:
                break;
        }
    }


    private void loadDivision() {
        StringRequest stringRequest = new StringRequest(Constants.Api.API_DIVISION_ALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("result");
                            //getStudents(result);
                            getDivision(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getDivision(JSONArray jsonArray) {
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                divisionMap.put(json.getString("division_name")+" "+"Division" , Integer.valueOf(json.getString("id")));
                division.add(json.getString("division_name")+" "+"Division");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Log.d("division", division.get(0));
        //spinnerInstitute.setAdapter(new ArrayAdapter<String>(ActivityInstituteSearch.this, R.layout.spinner_item, division));
    }
    private String getDivisionId(int position){
        String id="";
        try {
            JSONObject json = jsonArray.getJSONObject(position);
            id = json.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    //district
    private void loadDistrict() {
        StringRequest stringRequest = new StringRequest(Constants.Api.API_DISTRICT_ALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("result");
                            getDistrict(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getDistrict(JSONArray jsonArray) {
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                districtnMap.put(json.getString("district_name"), Integer.valueOf(json.getString("id")));
                district.add(json.getString("district_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Log.d("division", division.get(0));
        //spinnerInstitute.setAdapter(new ArrayAdapter<String>(ActivityInstituteSearch.this, R.layout.spinner_item, division));
    }

    //upazilla
    private void loadUpazilla() {
        StringRequest stringRequest = new StringRequest(Constants.Api.API_UPAZILLA_ALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsonArray = jsonObject.getJSONArray("result");
                            getUpazilla(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getUpazilla(JSONArray jsonArray) {
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                upazillaMap.put(json.getString("upazilla_name"), Integer.valueOf(json.getString("id")));
                upazilla.add(json.getString("upazilla_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Log.d("division", division.get(0));
        //spinnerInstitute.setAdapter(new ArrayAdapter<String>(ActivityInstituteSearch.this, R.layout.spinner_item, division));
    }

    private void apiQueryBuilder() {
        builder = new Uri.Builder();
        builder.scheme(Constants.Api.API_PROTOCOL)
                .authority( Constants.Api.API_HOST_ADDR )
                .appendPath( Constants.Api.API_INSTITUTION_ALL );

        Log.d("Spinner Item", Integer.toString(dropDownSelectedItem));

            switch ( dropDownSelectedItem ) {
                case 0:
                    builder.appendQueryParameter( "search_by", "institution_name" );
                    builder.appendQueryParameter( "search",  searchText );
                    break;
                case 1:
                    builder.appendQueryParameter( "search_by", "institution_head" );
                    builder.appendQueryParameter( "search", searchText );
                    break;
                case 2:
                    builder.appendQueryParameter( "search_by", "division" );
                    builder.appendQueryParameter( "search", Integer.toString( searchDropdownIndex ) );
                    break;
                case 3:

                    builder.appendQueryParameter( "search_by", "district" );
                    builder.appendQueryParameter( "search", Integer.toString( searchDropdownIndex ) );
                    break;
                case 4:
                    builder.appendQueryParameter( "search_by", "upazilla" );
                    builder.appendQueryParameter( "search", Integer.toString( searchDropdownIndex ) );
                    break;
                default:
                    //builder.appendQueryParameter( "search_by", "division" );
                    //builder.appendQueryParameter( "search", Integer.toString( searchDropdownIndex ) );
                    break;
            }

            for ( int typeId : institutionTypeArr ) {
                if ( 0 != typeId ) {
                    builder.appendQueryParameter( "type[]", Integer.toString( typeId ) );
                }
            }

            for ( int categoryId : institutionCategoryArr ) {
                if ( 0 != categoryId ) {
                    builder.appendQueryParameter( "category[]", Integer.toString( categoryId ) );
                }
            }

            /*switch ( institutionType ) {
                case 1:
                    builder.appendQueryParameter( "type", Integer.toString( institutionType ) );
                    break;
                case 2:
                    builder.appendQueryParameter( "type", Integer.toString( institutionType ) );
                    break;
                case 3:
                    builder.appendQueryParameter( "type", Integer.toString( institutionType ) );
                    break;
                default:
                    builder.appendQueryParameter( "type", Integer.toString( 0 ) );
                    break;
            }*/

        /*switch ( institutionCategory ) {
            case 1:
                builder.appendQueryParameter( "category", Integer.toString( institutionCategory ) );
                break;
            case 2:
                builder.appendQueryParameter( "category", Integer.toString( institutionCategory ) );
                break;
            case 3:
                builder.appendQueryParameter( "category", Integer.toString( institutionCategory ) );
                break;
            case 4:
                builder.appendQueryParameter( "category", Integer.toString( institutionCategory ) );
                break;
            default:
                builder.appendQueryParameter( "category", Integer.toString( 0 ) );
                break;
        }*/
    }

    private void setPagination(String currentPage, String perPage) {
        builder.appendQueryParameter( "page", currentPage );
        builder.appendQueryParameter( "per_page", perPage );
    }

    private void checkBoxSelection() {

        chekBoxGovt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chekBoxNonGovt.setChecked(false);
                //chekBoxMpo.setChecked(false);
                //chekBoxGovt.setChecked(b);
                if(chekBoxGovt.isChecked()) {
                    institutionTypeArr[0] = 1;
                    //institutionType = 1;
                } else {
                    //institutionType = 0;
                    institutionTypeArr[0] = 0;
                }
                sendQuery();
            }
        });

        chekBoxNonGovt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chekBoxGovt.setChecked(false);
                //chekBoxMpo.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxNonGovt.isChecked()) {
                    //institutionType = 2;
                    institutionTypeArr[1] = 2;
                } else {
                    //institutionType = 0;
                    institutionTypeArr[1] = 0;
                }
                sendQuery();
            }
        });
        chekBoxMpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chekBoxNonGovt.setChecked(false);
                //chekBoxGovt.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxMpo.isChecked()) {
                    //institutionType = 3;
                    institutionTypeArr[2] = 3;
                } else {
                    //institutionType = 0;
                    institutionTypeArr[2] = 0;
                }
                sendQuery();
            }
        });



        chekBoxCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*chekBoxMadrasha.setChecked(false);
                chekBoxSchoolCollege.setChecked(false);
                chekBoxBmCollege.setChecked(false);*/
                //chekBoxGovt.setChecked(b);

                if(chekBoxCollege.isChecked()) {
                    //institutionCategory = 1;
                    institutionCategoryArr[0] = 1;
                } else {
                    //institutionCategory = 0;
                    institutionCategoryArr[0] = 0;
                }
                sendQuery();
            }
        });
        chekBoxSchoolCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*chekBoxMadrasha.setChecked(false);
                chekBoxCollege.setChecked(false);
                chekBoxBmCollege.setChecked(false);*/
                //chekBoxGovt.setChecked(b);

                if(chekBoxSchoolCollege.isChecked()) {
                    //institutionCategory = 2;
                    institutionCategoryArr[1] = 2;
                } else {
                    //institutionCategory = 0;
                    institutionCategoryArr[1] = 0;
                }
                sendQuery();
            }
        });
        chekBoxMadrasha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*chekBoxCollege.setChecked(false);
                chekBoxSchoolCollege.setChecked(false);
                chekBoxBmCollege.setChecked(false);*/
                //chekBoxGovt.setChecked(b);

                if(chekBoxMadrasha.isChecked()) {
                    //institutionCategory = 3;
                    institutionCategoryArr[2] = 3;
                } else {
                    //institutionCategory = 0;
                    institutionCategoryArr[2] = 0;
                }
                sendQuery();
            }
        });

        chekBoxBmCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*chekBoxMadrasha.setChecked(false);
                chekBoxSchoolCollege.setChecked(false);
                chekBoxCollege.setChecked(false);*/
                //chekBoxGovt.setChecked(b);

                if(chekBoxBmCollege.isChecked()) {
                    //institutionCategory = 4;
                    institutionCategoryArr[3] = 4;
                } else {
                    //institutionCategory = 0;
                    institutionCategoryArr[3] = 0;
                }
                sendQuery();
            }
        });



        //send Serching data

    }

    private void InstitutionAndPrincipleSearch(){
        searchText = searchView.getText().toString();

        if  ( ( 0 == dropDownSelectedItem || 1 == dropDownSelectedItem ) && searchText.length() != 0 ) {
            sendQuery();
        } else {
            Toast.makeText( ActivityInstituteSearch.this, "Please provide institution or principal name in the search box.", Toast.LENGTH_LONG).show();
        }

    }
    private void loadInstution(String apiQueryString) {
        //loadingDialog = ProgressDialog.show(ActivityInstituteSearch.this, "", "Loading......");
        loadingDialog.show();
        try {
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, apiQueryString,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("response", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                if (0 == jsonArray.length()) {
                                    Toast.makeText(ActivityInstituteSearch.this, "No Institution found !!!", Toast.LENGTH_SHORT).show();
                                    checkBoxMultiSms.setVisibility(View.INVISIBLE);
                                }
                                else {
                                    checkBoxMultiSms.setVisibility(View.VISIBLE);
                                }
                                //Log.d("data", response);
                                instituteList.clear();
                                numberArrayList.clear();
                                emailArrayList.clear();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json_data = jsonArray.getJSONObject(i);
                                    instituteList.add(new Institute(
                                            json_data.getInt("id"),
                                            json_data.getString("institution_name_en"),
                                            json_data.getString("institution_division_name"),
                                            json_data.getString("institution_district_name"),
                                            json_data.getString("institution_upazila_name"),
                                            json_data.getString("institution_type_name"),
                                            json_data.getString("institution_category_name"),
                                            json_data.getString("institution_head_name"),
                                            //json_data.getString("designition"),
                                            json_data.getString("institution_head_mobile"),
                                            json_data.getString("institution_head_email")
                                    ));
                                    String mobile = json_data.getString("institution_head_mobile");
                                    String email = json_data.getString("institution_head_email");
                                    Institute inti= new Institute(mobile,email);
                                    numberArrayList.add(inti.getMobile().toString());
                                    emailArrayList.add(inti.getEmail().toString());
                                    Log.d("allMobile",numberArrayList.toString());
                                    Log.d("allEmail",emailArrayList.toString());
                                }
                                InstituteAdapter adapter = new InstituteAdapter(ActivityInstituteSearch.this, instituteList);
                                adapter.notifyDataSetChanged();
                                recyclerViewInstitute.setAdapter(adapter);

                                itemCount = recyclerViewInstitute.getAdapter().getItemCount();
                                if ( 0 != itemCount ){
                                Toast.makeText(ActivityInstituteSearch.this,Integer.toString(itemCount)+" Institution found", Toast.LENGTH_LONG).show();
                                //Log.d("item",Integer.toString(itemCount));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            loadingDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ActivityInstituteSearch.this, "No internet connection!", Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();
                            //Log.d("error", error.getLocalizedMessage());
                        }
                    });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(this).add(stringRequest);
        } catch (Exception e) {
        }

        //Log.d("RESULT",Constants.JSON_RESULT);
    }

    private void clearSerchViewAdapter() {
        ArrayAdapter divisionAdapter = new ArrayAdapter<String>(ActivityInstituteSearch.this,R.layout.spinner_item, divisionClear);
        searchView.setAdapter(divisionAdapter);
        divisionAdapter.notifyDataSetChanged();
    }

    private void spinnerDropDown() {
        ArrayAdapter saerch = ArrayAdapter.createFromResource(this, R.array.INSTITUTION, R.layout.spinner_item);
        saerch.setDropDownViewResource(R.layout.spinner_list);
        spinnerInstitute.setAdapter(saerch);

        spinnerInstitute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    searchText = "";
                    searchView.setHint("Institution Search");
                    textViewSearchTitle.setText("Search By Institution");
                    dropDownSelectedItem = position;
                    clearSerchViewAdapter();
                }
                else if (position == 1) {
                    searchText = "";
                    searchView.setHint("Institution Head");
                    textViewSearchTitle.setText("Search By Institution Head");
                    dropDownSelectedItem = position;
                    clearSerchViewAdapter();
                }
                else if (position == 2) {
                    searchView.setHint("Division");
                    textViewSearchTitle.setText("Search By Division");
                    autoSuggestionListView(position);

                }else if (position == 3) {
                    searchView.setHint("District");
                    textViewSearchTitle.setText("Search By District");
                    autoSuggestionListView(position);

                }else  if( position == 4){
                    searchView.setHint("Upazilla");
                    textViewSearchTitle.setText("Search By Upazilla");
                    autoSuggestionListView(position);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void onClick() {
        try {
            imageViewBackPress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            checkBoxMultiSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBoxMultiSms.isChecked()) {
                        floatingActionMenu1.setVisibility(View.VISIBLE);
                    }else {
                        floatingActionMenu1.setVisibility(View.INVISIBLE);
                    }
                }
            });

            floatingActionButtonSms1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
                    View promptsView = layoutInflater.inflate(R.layout.popup_sms, null);
                    final EditText editTextMobileNo = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                    final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                    //editTextMobileNo.setText(holder.textViewMobile.getText().toString());
                    editTextMobileNo.setText(String.valueOf(numberArrayList.toString().replace(',',';')));
                    String title;
                    title= "SEND SMS";
                    new android.support.v7.app.AlertDialog.Builder(context,R.style.MyDialogTheme)
                            .setTitle(title)
                            .setView(promptsView)
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i("Send SMS", "");
                                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                    smsIntent.setData(Uri.parse("smsto:"));
                                    smsIntent.setType("vnd.android-dir/mms-sms");
                                    smsIntent.putExtra("address", editTextMobileNo.getText().toString());
                                    smsIntent.putExtra("sms_body", editTextTextMessage.getText().toString());
                                    try {
                                        context.startActivity(smsIntent);
                                        Log.i("Finished sending SMS...", "");
                                    } catch (android.content.ActivityNotFoundException ex) {

                                    }

                                }


                            })
                            .show();
                }
            });

            floatingActionButtonEmail1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
                    View promptsView = layoutInflater.inflate(R.layout.popup_email, null);
                    final EditText editTextEmailAddress = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                    final EditText editTextEmailSubject = (EditText) promptsView.findViewById(R.id.editTextEmailSubject);
                    final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                    editTextEmailAddress.setText(String.valueOf(emailArrayList.toString().replace("[","").replace("]","")));

                    String title;
                    title= "SEND EMAIL";
                    new android.support.v7.app.AlertDialog.Builder(context,R.style.MyDialogTheme)
                            .setTitle(title)
                            .setView(promptsView)
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                                    intent.setData(Uri.parse("mailto:"));
                                    //intent.setType("message/rfc822");
                                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{editTextEmailAddress.getText().toString()} );
                                    intent.putExtra(Intent.EXTRA_SUBJECT, editTextEmailSubject.getText().toString());
                                    intent.putExtra(Intent.EXTRA_TEXT, editTextTextMessage.getText().toString());
                                    try {
                                        Log.i(ActivityDeveloper.class.getName(), "Sending email. " + "\n title : " + editTextEmailSubject.getText().toString()
                                                + "\n description : " + editTextTextMessage.getText().toString());
                                        context.startActivity(Intent.createChooser(intent, "Send Email"));
                                    } catch (android.content.ActivityNotFoundException ex) {
                                        ex.printStackTrace();
                                        Log.e(ActivityDeveloper.class.getName(), ex.getMessage());
                                    }
                                }

                            })
                            .show();
                }
            });

            /*floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
                    View promptsView = layoutInflater.inflate(R.layout.popup_sms, null);
                    final EditText editTextMobileNo = (EditText) promptsView.findViewById(R.id.editTextEmailAddress);
                    final EditText editTextTextMessage = (EditText) promptsView.findViewById(R.id.editTextTextMessage);
                    //editTextMobileNo.setText(holder.textViewMobile.getText().toString());
                    editTextMobileNo.setText(String.valueOf(numberArrayList.toString().replace(',',';')));
                    String title;
                    title= "SEND SMS";
                    new android.support.v7.app.AlertDialog.Builder(context,R.style.MyDialogTheme)
                            .setTitle(title)
                            .setView(promptsView)
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i("Send SMS", "");
                                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                    smsIntent.setData(Uri.parse("smsto:"));
                                    smsIntent.setType("vnd.android-dir/mms-sms");
                                    smsIntent.putExtra("address", editTextMobileNo.getText().toString());
                                    smsIntent.putExtra("sms_body", editTextTextMessage.getText().toString());
                                    try {
                                        context.startActivity(smsIntent);
                                        Log.i("Finished sending SMS...", "");
                                    } catch (android.content.ActivityNotFoundException ex) {

                                    }

                                }


                            })
                            .show();
                }
            });*/

        } catch (Exception e) {
        }
    }

    private void setupUI() {
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        searchView = (AutoCompleteTextView) findViewById(R.id.editTextSearch);
        chekBoxGovt = (CheckBox) findViewById(R.id.chekboxGovt);
        chekBoxNonGovt = (CheckBox) findViewById(R.id.chekboxNonGovt);
        chekBoxMpo = (CheckBox) findViewById(R.id.chekboxMpo);
        chekBoxCollege = (CheckBox) findViewById(R.id.chekboxDEO);
        chekBoxBmCollege = (CheckBox) findViewById(R.id.chekboxPS);
        chekBoxSchoolCollege = (CheckBox) findViewById(R.id.chekboxDC);
        chekBoxMadrasha = (CheckBox) findViewById(R.id.chekboxUSEO);
        spinnerInstitute = (Spinner) findViewById(R.id.spinnerSearch);
        recyclerViewInstitute = (RecyclerView) findViewById(R.id.recylcerViewInstitute);
        imageViewSearch = (ImageView) findViewById(R.id.imageViewSearch);
        textViewSearchTitle = (TextView) findViewById(R.id.textViewStakeholderSearchTitle);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.fabButton);
        floatingActionButtonSms = (FloatingActionButton) findViewById(R.id.floatSms);
        floatingActionButtonEmail = (FloatingActionButton) findViewById(R.id.floatEmail);
        floatingActionMenu1 = (FloatingActionMenu) findViewById(R.id.fabButton1);
        floatingActionButtonSms1 = (FloatingActionButton) findViewById(R.id.floatSms1);
        floatingActionButtonEmail1 = (FloatingActionButton) findViewById(R.id.floatEmail1);
        checkBoxMultiSms = (CheckBox) findViewById(R.id.chekboxMultiSms);
    }

}