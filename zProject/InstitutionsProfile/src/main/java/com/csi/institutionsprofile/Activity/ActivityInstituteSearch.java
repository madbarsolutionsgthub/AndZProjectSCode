package com.csi.institutionsprofile.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.csi.institutionsprofile.Database.CustomAdapter;
import com.csi.institutionsprofile.Database.StudentRepo;
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;
import com.csi.institutionsprofile.Utility.Util;
import com.csi.institutionsprofile.adapter.AuthorityAdapter;
import com.csi.institutionsprofile.adapter.InstituteAdapter;
import com.csi.institutionsprofile.adapter.SuggestionAdapterDistrict;
import com.csi.institutionsprofile.model.Authority;
import com.csi.institutionsprofile.model.Division;
import com.csi.institutionsprofile.model.Institute;
import com.csi.institutionsprofile.model.Stakeholder;
import com.csi.institutionsprofile.model.Student;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class ActivityInstituteSearch extends AppCompatActivity {
    ImageView imageViewBackPress;
    private CustomAdapter customAdapter;
    ImageView imageViewSearch;
    AutoCompleteTextView searchView;
    Spinner spinnerInstitute;
    TextView number, emailAddress;
    CheckBox chekBoxGovt, chekBoxNonGovt, chekBoxMpo, chekBoxCollege, chekBoxMadrasha, chekBoxBmCollege, chekBoxSchoolCollege;
    Context context = this;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private static final String URL_INSTITUTE = Constants.Api.API_INSTITUTION_ALL;
    RecyclerView recyclerViewInstitute;
    private Dialog loadingDialog;
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

    int suggesstionAddapterFlag = 0;

    Map<String, Integer> divisionMap = new HashMap<String, Integer>();
    Map<String, Integer> districtnMap = new HashMap<String, Integer>();
    Map<String, Integer> upazillaMap = new HashMap<String, Integer>();
    int position;
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
        setupUI();
        onClick();
        spinnerDropDown();
        apiQueryBuilder();
        setPagination(Constants.currentPage, Constants.perPage);
        institutionSearchUrl = Uri.decode( builder.build().toString() );
        Log.d("URL BUILDER", institutionSearchUrl);
        loadInstution( institutionSearchUrl );
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

            switch ( institutionType ) {
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
            }

        switch ( institutionCategory ) {
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
        }
    }

    private void setPagination(String currentPage, String perPage) {
        builder.appendQueryParameter( "page", currentPage );
        builder.appendQueryParameter( "per_page", perPage );
    }

    private void checkBoxSelection() {

        chekBoxGovt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekBoxNonGovt.setChecked(false);
                chekBoxMpo.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxGovt.isChecked()) {
                    institutionType = 1;
                } else {
                    institutionType = 0;
                }
                sendQuery();
            }
        });

        chekBoxNonGovt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekBoxGovt.setChecked(false);
                chekBoxMpo.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxNonGovt.isChecked()) {
                    institutionType = 2;
                } else {
                    institutionType = 0;
                }
                sendQuery();
            }
        });
        chekBoxMpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekBoxNonGovt.setChecked(false);
                chekBoxGovt.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxMpo.isChecked()) {
                    institutionType = 3;
                } else {
                    institutionType = 0;
                }
                sendQuery();
            }
        });

        chekBoxCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekBoxMadrasha.setChecked(false);
                chekBoxSchoolCollege.setChecked(false);
                chekBoxBmCollege.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxCollege.isChecked()) {
                    institutionCategory = 1;
                } else {
                    institutionCategory = 0;
                }
                sendQuery();
            }
        });
        chekBoxSchoolCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekBoxMadrasha.setChecked(false);
                chekBoxCollege.setChecked(false);
                chekBoxBmCollege.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxSchoolCollege.isChecked()) {
                    institutionCategory = 2;
                } else {
                    institutionCategory = 0;
                }
                sendQuery();
            }
        });
        chekBoxMadrasha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekBoxCollege.setChecked(false);
                chekBoxSchoolCollege.setChecked(false);
                chekBoxBmCollege.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxMadrasha.isChecked()) {
                    institutionCategory = 3;
                } else {
                    institutionCategory = 0;
                }
                sendQuery();
            }
        });

        chekBoxBmCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekBoxMadrasha.setChecked(false);
                chekBoxSchoolCollege.setChecked(false);
                chekBoxCollege.setChecked(false);
                //chekBoxGovt.setChecked(b);

                if(chekBoxBmCollege.isChecked()) {
                    institutionCategory = 4;
                } else {
                    institutionCategory = 0;
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
        loadingDialog = ProgressDialog.show(ActivityInstituteSearch.this, "", "Loading......");
        try {
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, apiQueryString,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.d("response", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                if (0 == jsonArray.length()) {
                                    Toast.makeText(ActivityInstituteSearch.this, "No Institution found !!!", Toast.LENGTH_SHORT).show();
                                }
                                //Log.d("data", response);
                                instituteList.clear();
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
                                }
                                InstituteAdapter adapter = new InstituteAdapter(ActivityInstituteSearch.this, instituteList);
                                adapter.notifyDataSetChanged();
                                recyclerViewInstitute.setAdapter(adapter);

                                int itemCount = recyclerViewInstitute.getAdapter().getItemCount();
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
                            Toast.makeText(ActivityInstituteSearch.this, "Server maybe down. Please try again later...", Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();
                            //Log.d("error", error.getLocalizedMessage());
                        }
                    });
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
                    dropDownSelectedItem = position;
                    clearSerchViewAdapter();
                }
                else if (position == 1) {
                    searchText = "";
                    searchView.setHint("Institution Head");
                    dropDownSelectedItem = position;
                    clearSerchViewAdapter();
                }
                else if (position == 2) {
                    searchView.setHint("Division");
                    autoSuggestionListView(position);

                }else if (position == 3) {
                    searchView.setHint("District");
                    autoSuggestionListView(position);

                }else  if( position == 4){
                    searchView.setHint("Upazilla");
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

        } catch (Exception e) {
        }
    }

    private void setupUI() {
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        searchView = (AutoCompleteTextView) findViewById(R.id.editTextSearch);
        chekBoxGovt = (CheckBox) findViewById(R.id.chekboxGovt);
        chekBoxNonGovt = (CheckBox) findViewById(R.id.chekboxNonGovt);
        chekBoxMpo = (CheckBox) findViewById(R.id.chekboxMpo);
        chekBoxCollege = (CheckBox) findViewById(R.id.chekboxCollege);
        chekBoxBmCollege = (CheckBox) findViewById(R.id.chekboxBMCollege);
        chekBoxSchoolCollege = (CheckBox) findViewById(R.id.chekboxSchoolCollege);
        chekBoxMadrasha = (CheckBox) findViewById(R.id.chekboxMadrasha);
        spinnerInstitute = (Spinner) findViewById(R.id.spinnerSearch);
        recyclerViewInstitute = (RecyclerView) findViewById(R.id.recylcerViewInstitute);
        imageViewSearch = (ImageView) findViewById(R.id.imageViewSearch);
    }

}