package com.tdevelopers.nasta.HotelOpenFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdevelopers.nasta.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaySpecialFragments extends Fragment {


    public DaySpecialFragments() {
        // Required empty public constructor
    }

    public static DaySpecialFragments newInstance() {

        Bundle args = new Bundle();

        DaySpecialFragments fragment = new DaySpecialFragments();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_special_fragments, container, false);
    }

}
