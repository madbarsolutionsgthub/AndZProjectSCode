package com.csi.institutionsprofile.Activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.Utility.Constants;
import com.csi.institutionsprofile.adapter.StakeHolderAdapter;
import com.csi.institutionsprofile.model.Stakeholder;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class ActivityStakeholder extends AppCompatActivity {
    ImageView imageViewBackPress;
    CheckBox checkBoxDC,checkBoxDEO,checkBoXUSEO,checkBoxPS,checkBoxUNO,checkBoxOC;
    TextView textViewSpinnerCodde;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private static final String URL_STAKEHOLDER = Constants.Api.API_NOTICE_ALL;
    private RecyclerView recyclerViewStakeHolder;
    //private StakeHolderAdapter stakeHolderAdapter;
    public List<Stakeholder> stakeholderList, innerStakeholderList;
    private ArrayList<String> division;
    Context context = this;
    TextView searchView= null;
    //private Dialog loadingDialog;
    private SpotsDialog loadingDialog;
    Spinner spinnerDivision;
    JSONArray jsonArray;
    //int divisionId;
    String divisionId;
    public static int itemCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stakeholder);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingDialog = new SpotsDialog(context, R.style.Custom);
        setupUI();
        onClick();
        loadDivision();
        spinnerDropDown();
        laodStakeHolder();
        checkBoxSelection();
        //generateStakeholderView();
        recyclerViewStakeHolder.setHasFixedSize(true);
        recyclerViewStakeHolder.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewStakeHolder.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).color(Color.TRANSPARENT).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
        division = new ArrayList<String>();
        stakeholderList = new ArrayList<>();

       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new AsyncFetch(query).execute();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });*/
    }

    private void generateStakeholderView(List<Stakeholder> stakeholderList) {
        StakeHolderAdapter adapter = new StakeHolderAdapter(ActivityStakeholder.this, stakeholderList);
        recyclerViewStakeHolder.setAdapter(adapter);
        itemCount = recyclerViewStakeHolder.getAdapter().getItemCount();
        if ( 0 != itemCount ) {
            //Toast.makeText(ActivityStakeholder.this, Integer.toString(itemCount) + " result found", Toast.LENGTH_LONG).show();
        }
    }

    //Load All StakeHolder
    private void laodStakeHolder() {
        //loadingDialog = ProgressDialog.show(ActivityStakeholder.this, "", "Loading......");
        loadingDialog.show();
        try {
            final StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.Api.API_STAKEHOLDER_ALL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.d("response", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                //JSONArray products = (JSONArray) jsonObject.getJSONObject("products").getJSONArray("data");
                                JSONArray jsonArray = jsonObject.getJSONArray("result");
                                //Toast.makeText(ActivityAuthority.this, response, Toast.LENGTH_LONG).show();
                                if ( 0 == jsonArray.length() ) {
                                    Toast.makeText(ActivityStakeholder.this, "No StakeHolder available", Toast.LENGTH_LONG).show();
                                }
                                Log.d("data", response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    stakeholderList.add(new Stakeholder(
                                            jsonObject1.getInt("id"),
                                            jsonObject1.getString("first_name")+" "+jsonObject1.getString("last_name"),
                                            jsonObject1.getString("designation"),
                                            jsonObject1.getInt("division_id"),
                                            jsonObject1.getString("district_name"),
                                            jsonObject1.getString("mobile_no"),
                                            jsonObject1.getString("email")
                                    ));
                                }
                                //Log.i("Data DDD",stakeholderList.toArray()[0].toString());
                                generateStakeholderView( stakeholderList );
                               } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            loadingDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ActivityStakeholder.this, "No internet connection!", Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();
                            //Log.d("error", error.getLocalizedMessage());
                        }
                    });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(this).add(stringRequest);
        }catch (Exception e){}

    }

    private void spinnerDropDown() {
        spinnerDivision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                searchView.setText(spinnerDivision.getSelectedItem().toString());
                divisionId = spinnerDivision.getSelectedItem().toString();
                Log.d("Spinner text", divisionId);
                if(checkBoxDC.isChecked()) {
                    filteredStakeholderList("dc", divisionId);
                }
                else if(checkBoxDEO.isChecked()) {
                    filteredStakeholderList("deo", divisionId);
                }
                else if(checkBoXUSEO.isChecked()) {
                    filteredStakeholderList("useo", divisionId);
                }
                else if(checkBoxUNO.isChecked()) {
                    filteredStakeholderList("uno", divisionId);
                }
                else if(checkBoxPS.isChecked()) {
                    filteredStakeholderList("sp", divisionId);
                }
                else if(checkBoxOC.isChecked()) {
                    filteredStakeholderList("oc", divisionId);
                }
                else  {
                    filteredStakeholderList("", divisionId);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void loadDivision() {
        StringRequest stringRequest = new StringRequest(Constants.Api.API_DISTRICT_ALL,
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
        division.add("All District");
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject json = jsonArray.getJSONObject(i);
                division.add(json.getString("district_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Log.d("division", division.get(0));
        spinnerDivision.setAdapter(new ArrayAdapter<String>(ActivityStakeholder.this, R.layout.spinner_item, division));
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


    private void checkBoxSelection() {
        checkBoxDC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("Check1", "Test1");
                checkBoXUSEO.setChecked(false);
                checkBoxDEO.setChecked(false);
                checkBoxPS.setChecked(false);
                checkBoxUNO.setChecked(false);
                checkBoxOC.setChecked(false);
                checkBoxDC.setChecked(b);
                if ( compoundButton.isChecked() ) {
                    filteredStakeholderList( "dc", divisionId );
                } else {
                    filteredStakeholderList("",divisionId);
                }
            }
        });

        checkBoxDEO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoXUSEO.setChecked(false);
                checkBoxDC.setChecked(false);
                checkBoxPS.setChecked(false);
                checkBoxUNO.setChecked(false);
                checkBoxOC.setChecked(false);
                checkBoxDEO.setChecked(b);
                Log.d("Check2", "Test2");
                if ( compoundButton.isChecked() ) {
                    filteredStakeholderList( "deo", divisionId );
                } else {
                    filteredStakeholderList("",divisionId);
                }
            }
        });

        checkBoXUSEO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxDC.setChecked(false);
                checkBoxDEO.setChecked(false);
                checkBoxPS.setChecked(false);
                checkBoxUNO.setChecked(false);
                checkBoxOC.setChecked(false);
                checkBoXUSEO.setChecked(b);
                Log.d("Check3", "Test3");
                Log.d("Check2", "Test2");
                if ( compoundButton.isChecked() ) {
                    filteredStakeholderList( "useo", divisionId );
                } else {
                    filteredStakeholderList("",divisionId);
                }
            }
        });
        checkBoxUNO.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxDC.setChecked(false);
                checkBoxDEO.setChecked(false);
                checkBoxPS.setChecked(false);
                checkBoXUSEO.setChecked(false);
                checkBoxOC.setChecked(false);
                checkBoxUNO.setChecked(b);

                if ( compoundButton.isChecked() ) {
                    filteredStakeholderList( "uno", divisionId );
                } else {
                    filteredStakeholderList("",divisionId);
                }
            }
        });
        checkBoxPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxDC.setChecked(false);
                checkBoxDEO.setChecked(false);
                checkBoxUNO.setChecked(false);
                checkBoXUSEO.setChecked(false);
                checkBoxOC.setChecked(false);
                checkBoxPS.setChecked(b);

                if ( compoundButton.isChecked() ) {
                    filteredStakeholderList( "sp", divisionId );
                } else {
                    filteredStakeholderList("",divisionId);
                }
            }
        });
        checkBoxOC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checkBoxDC.setChecked(false);
                checkBoxDEO.setChecked(false);
                checkBoxUNO.setChecked(false);
                checkBoXUSEO.setChecked(false);
                checkBoxPS.setChecked(false);
                checkBoxOC.setChecked(b);

                if ( compoundButton.isChecked() ) {
                    filteredStakeholderList( "oc", divisionId );
                } else {
                    filteredStakeholderList("",divisionId);
                }
            }
        });
    }

    private void filteredStakeholderList(String designation, String division) {
        Collection<Stakeholder> filtered = Collections2.filter(stakeholderList, new customPredicate( designation, division ));
        ArrayList<Stakeholder> filteredList = new ArrayList<Stakeholder>(filtered);
        generateStakeholderView( filteredList );
    }

    private void onClick() {
        imageViewBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void setupUI() {
        imageViewBackPress = (ImageView) findViewById(R.id.imageViewBackPress);
        checkBoxDC = (CheckBox) findViewById(R.id.chekboxDC);
        checkBoxDEO = (CheckBox) findViewById(R.id.chekboxDEO);
        checkBoXUSEO = (CheckBox) findViewById(R.id.chekboxUSEO);
        checkBoxPS = (CheckBox) findViewById(R.id.chekboxPS);
        checkBoxUNO = (CheckBox) findViewById(R.id.chekboxUNO);
        checkBoxOC = (CheckBox) findViewById(R.id.chekboxOC);
        searchView = (TextView) findViewById(R.id.searchViewStakeHolder);
        spinnerDivision = (Spinner) findViewById(R.id.spinnerSearchDivision);
        recyclerViewStakeHolder = (RecyclerView) findViewById(R.id.recylcerViewStakeHolder);

    }

    private static class customPredicate implements Predicate<Stakeholder> {

        //private int divisionId;
        private String divisionId;
        private String customDesignation = "";

        public customPredicate(String designation, String division) {
            this.divisionId = division;
            this.customDesignation = designation;
        }

        @Override
        public boolean apply(Stakeholder stakeholder) {

            //Log.d( "Test Division", "Test" );
           // Log.d( "Division", divisionId);
            //Log.d( "Designation", Integer.toString(this.customDesignation.length()));
            String all_district = "All District";
            //Log.d("Stake lenth", Integer.toString(customDesignation.length()));
            if ( all_district != this.divisionId && this.customDesignation.length() > 0 ) {
                //Log.d("Stake Dis ", stakeholder.getDistrictSingle());
                //Log.d("Stake Dis2 ", this.divisionId);
                return stakeholder.getDesignation().toLowerCase().equals( this.customDesignation.toLowerCase() ) && stakeholder.getDistrictSingle().equals(this.divisionId);
            } else if ( all_district == this.divisionId  && this.customDesignation.length() > 0 ){
                return stakeholder.getDesignation().toLowerCase().equals( this.customDesignation.toLowerCase() );
            } else if( all_district != this.divisionId  && this.customDesignation.length() < 1 ) {
                return stakeholder.getDistrictSingle().equals(this.divisionId);
            }
            else {
                return true;
            }
        }
    }

   /* private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(ActivityStakeholder.this);
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;

        public AsyncFetch(String searchQuery){
            this.searchQuery=searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("\tLoading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(URL_STAKEHOLDER);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {
                //send request
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                //setDoInput and setDoOutput to true as I send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);
                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery);
                String query = builder.build().getEncodedQuery();

                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                outputStream.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }
            try {

                int response_code = conn.getResponseCode();
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //this method will be running on UI thread
            progressDialog.dismiss();
            stakeholderList = new ArrayList<>();
            progressDialog.dismiss();
            if (result.equals("no rows")) {
                Toast.makeText(ActivityStakeholder.this, "No Results found for entered query", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                   *//* JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");*//*
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json_data = jsonArray.getJSONObject(i);
                        stakeholderList.add(new Stakeholder(
                                json_data.getInt("id"),
                                json_data.getString("name"),
                                json_data.getString("designation"),
                                json_data.getInt("division_id"),
                                json_data.getString("district"),
                                json_data.getString("mobile"),
                                json_data.getString("email")
                        ));
                    }
                    StakeHolderAdapter adapter = new StakeHolderAdapter(ActivityStakeholder.this, stakeholderList);
                    recyclerViewStakeHolder.setAdapter(adapter);
                    recyclerViewStakeHolder.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).color(Color.TRANSPARENT).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
                    // Setup and Handover data to recyclerview
                    *//*recyclerViewStakeHolder = (RecyclerView) findViewById(R.id.recylcerViewStakeHolder);
                    recyclerViewStakeHolder = new StakeHolderAdapter(ActivityStakeholder.this, data);
                    recyclerViewStakeHolder.setAdapter(mAdapter);
                    recyclerViewStakeHolder.setLayoutManager(new LinearLayoutManager(ActivityStakeholder.this));
*//*
                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(ActivityStakeholder.this, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(ActivityStakeholder.this, result.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }*/
}
