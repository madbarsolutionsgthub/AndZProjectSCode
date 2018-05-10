package com.appstune.the100;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appstune.the100.Adapter.CustomAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivityPart_1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivityPart_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityPart_1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context context;
    ArrayList prgmName;

    public static String [] prgmNameList={"Canada","Mexico","United States"};
    ListView listView;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10,cardView11,cardView12,cardView13,cardView14,cardView15,
    cardView16,cardView17,cardView18,cardView19,cardView20;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ActivityPart_1() {
        // Required empty public constructor
    }


    public static ActivityPart_1 newInstance(String param1, String param2) {
        ActivityPart_1 fragment = new ActivityPart_1();
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
        OnClick(view);
        return view;
    }

    private void OnClick(View view) {
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 1);
                startActivity(i);

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 2);
                startActivity(i);

            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 3);
                startActivity(i);

            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 4);
                startActivity(i);

            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 5);
                startActivity(i);

            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 6);
                startActivity(i);

            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 7);
                startActivity(i);

            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 8);
                startActivity(i);

            }
        });
        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 9);
                startActivity(i);

            }
        });
        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 10);
                startActivity(i);

            }
        });
        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 11);
                startActivity(i);

            }
        });
        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 12);
                startActivity(i);

            }
        });
        cardView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 13);
                startActivity(i);

            }
        });
        cardView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 14);
                startActivity(i);

            }
        });
        cardView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 15);
                startActivity(i);

            }
        });
        cardView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 16);
                startActivity(i);

            }
        });
        cardView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 17);
                startActivity(i);

            }
        });
        cardView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 18);
                startActivity(i);

            }
        });
        cardView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 19);
                startActivity(i);

            }
        });
        cardView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ActivityStory.class);
                i.putExtra("e", 20);
                startActivity(i);

            }
        });
    }

    private void setUI(View view) {
        cardView1 = (CardView) view.findViewById(R.id.card);
        cardView2 = (CardView) view.findViewById(R.id.card2);
        cardView3 = (CardView) view.findViewById(R.id.card3);
        cardView4 = (CardView) view.findViewById(R.id.card4);
        cardView5 = (CardView) view.findViewById(R.id.card5);
        cardView6 = (CardView) view.findViewById(R.id.card6);
        cardView7 = (CardView) view.findViewById(R.id.card7);
        cardView8 = (CardView) view.findViewById(R.id.card8);
        cardView9 = (CardView) view.findViewById(R.id.card9);
        cardView10 = (CardView) view.findViewById(R.id.card10);
        cardView11 = (CardView) view.findViewById(R.id.card11);
        cardView12 = (CardView) view.findViewById(R.id.card12);
        cardView13 = (CardView) view.findViewById(R.id.card13);
        cardView14 = (CardView) view.findViewById(R.id.card14);
        cardView15 = (CardView) view.findViewById(R.id.card15);
        cardView16 = (CardView) view.findViewById(R.id.card16);
        cardView17 = (CardView) view.findViewById(R.id.card17);
        cardView18 = (CardView) view.findViewById(R.id.card18);
        cardView19 = (CardView) view.findViewById(R.id.card19);
        cardView20= (CardView) view.findViewById(R.id.card20);
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
