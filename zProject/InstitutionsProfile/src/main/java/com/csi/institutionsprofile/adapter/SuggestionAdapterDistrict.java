package com.csi.institutionsprofile.adapter;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.csi.institutionsprofile.R;
import com.csi.institutionsprofile.model.SugestGetSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shohan on 3/20/17.
 */

public class SuggestionAdapterDistrict extends ArrayAdapter<String> {

    protected static final String TAG = "SuggestionAdapter";
    private List<String> suggestions;
    private String apiToken;
    public SuggestionAdapterDistrict(Activity context, String nameFilter){
        super(context, R.layout.spinner_list);
        suggestions = new ArrayList<String>();
        this.apiToken=apiToken;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public String getItem(int index) {
        return suggestions.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                JsonParseDistrict jp=new JsonParseDistrict();

                if (constraint != null) {
                    // A class that queries a web API, parses the data and
                    // returns an ArrayList<GoEuroGetSet>
                    List<SugestGetSet> new_suggestions =jp.getParseJsonWCF(constraint.toString());
                    suggestions.clear();
                    for (int i=0;i<new_suggestions.size();i++) {
                        suggestions.add(new_suggestions.get(i).getName());
                    }

                    // Now assign the values and count to the FilterResults
                    // object
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                }
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence contraint,
                                          FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;
    }

}

