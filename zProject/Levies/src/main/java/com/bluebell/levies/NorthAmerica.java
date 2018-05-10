package com.bluebell.levies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bluebell.levies.Adapter.CustomAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NorthAmerica.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NorthAmerica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NorthAmerica extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context context;
    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.canada,R.drawable.mexico,R.drawable.usa,};
    public static String [] prgmNameList={"Canada","Mexico","United States"};
    ListView listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NorthAmerica() {
        // Required empty public constructor
    }


    public static NorthAmerica newInstance(String param1, String param2) {
        NorthAmerica fragment = new NorthAmerica();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_north_america, container, false);
        setUI(view);
        implemenation(view);
        listItem(view);
        return view;
    }
    private void listItem(View view) {
        listView.setAdapter(new CustomAdapter(this,prgmNameList ,prgmImages));
    }

    private void implemenation(View view) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long l) {
                if(arg2==0){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 1);
                    startActivity(i);
                }
                if(arg2==1){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 2);
                    startActivity(i);
                }
                if(arg2==2){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 3);
                    startActivity(i);
                }

            }
        });
    }

    private void setUI(View view) {
        listView=(ListView) view.findViewById(R.id.listView);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
