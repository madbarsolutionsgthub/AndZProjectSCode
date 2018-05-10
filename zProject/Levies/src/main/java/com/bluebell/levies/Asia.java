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

import com.bluebell.levies.Adapter.CustomAdapter2;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Asia.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Asia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Asia extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context context;
    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.austrialia,R.drawable.china,R.drawable.india,R.drawable.indonessia,R.drawable.japan,R.drawable.new_ziland,R.drawable.pakistan,R.drawable.philippines,R.drawable.singapore,R.drawable.south_africa};
    public static String [] prgmNameList={"Australia","China","India","Indonesia","Japan","New Zealand","Pakistan","Philippines","Singapore","South Africa"};
    ListView listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Asia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Asia.
     */
    // TODO: Rename and change types and number of parameters
    public static Asia newInstance(String param1, String param2) {
        Asia fragment = new Asia();
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
        listView.setAdapter(new CustomAdapter2(this,prgmNameList ,prgmImages));
    }

    private void implemenation(View view) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long l) {
                if(arg2==0){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 27);
                    startActivity(i);
                }
                if(arg2==1){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 28);
                    startActivity(i);
                }
                if(arg2==2){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 29);
                    startActivity(i);
                }
                if(arg2==3){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 30);
                    startActivity(i);
                }
                if(arg2==4){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 31);
                    startActivity(i);
                }
                if(arg2==5){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 32);
                    startActivity(i);
                }

                if(arg2==6){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 33);
                    startActivity(i);
                }
                if(arg2==7){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 34);
                    startActivity(i);
                }
                if(arg2==8){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 35);
                    startActivity(i);
                }
                if(arg2==9){
                    Intent i=new Intent(getActivity(),Web.class);
                    i.putExtra("e", 36);
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
