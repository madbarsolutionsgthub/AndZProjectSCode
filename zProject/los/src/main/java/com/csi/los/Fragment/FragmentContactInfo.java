package com.csi.los.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.csi.los.Activity.ActivityHome;
import com.csi.los.R;


public class FragmentContactInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Toolbar toolbar;
    //linealayout home and commit
    LinearLayout linearLayoutHome,linearLayoutCommit;
    //Button home and commit
    Button buttonHome,buttonCommit;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentContactInfo() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentContactInfo newInstance(String param1, String param2) {
        FragmentContactInfo fragment = new FragmentContactInfo();
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
        View view = inflater.inflate(R.layout.fragment_fragment_contact_info, container, false);
        setUpUI(view);
        initToolBar(view);
        onClick(view);
        return view;
    }

    private void onClick(View view) {
        linearLayoutHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityHome.class);
                startActivity(intent);
            }
        });
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityHome.class);
                startActivity(intent);
            }
        });
        Typeface typefaceLobster = Typeface.createFromAsset(getActivity().getAssets(),"font/lobster.ttf");
        buttonHome.setTypeface(typefaceLobster);
        buttonCommit.setTypeface(typefaceLobster);
    }

    private void initToolBar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.black_arrow);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(R.string.contactInfo);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setUpUI(View view) {
        linearLayoutHome = (LinearLayout) view.findViewById(R.id.linearlayoutHome);
        buttonHome = (Button) view.findViewById(R.id.buttonHome);
        buttonCommit = (Button) view.findViewById(R.id.buttonCommit);
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
