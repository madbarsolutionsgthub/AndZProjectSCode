package com.csi.institutionsprofile.adapter;

import com.csi.institutionsprofile.Utility.Constants;
import com.csi.institutionsprofile.model.District;
import com.csi.institutionsprofile.model.Market;
import com.csi.institutionsprofile.model.SugestGetSet;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class JsonParseDistrict {

    String apiToken;
    /*public JsonParseDistrict(String apiToken){
        this.apiToken = apiToken;
    }*/

    public List<SugestGetSet> getParseJsonWCF(String sName)
    {
        List<SugestGetSet> ListData = new ArrayList<SugestGetSet>();
        try {
            String temp=sName.replace(" ", "%20");
            URL js = new URL(Constants.Api.API_DISTRICT_ALL);

            URLConnection jc = js.openConnection();
            /*jc.addRequestProperty("Authorization","Bearer "+apiToken);*/
            jc.addRequestProperty("Accept","application/json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            String line = reader.readLine();
            JSONObject jsonResponse = new JSONObject(line);
            //JSONObject
            JSONArray jsonArray = jsonResponse.getJSONArray("result");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //try
                //Market market = new Market();
                District district = new District();
                district.setId(jsonObject.getString(Constants.District.DISTRICT_ID));
                district.setName(jsonObject.getString(Constants.District.DISTRICT_NAME));
                Constants.districtlist.add(district);

                ListData.add(new SugestGetSet(district.getId(),district.getName()));
                ListData.add(new SugestGetSet(district.getId(),district.getName()));
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return ListData;

    }

    
}

