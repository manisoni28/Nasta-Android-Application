package com.tdevelopers.nasta.HotelOpenFragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tdevelopers.nasta.Adapters.ReviewAdapter;
import com.tdevelopers.nasta.ApiHelper;
import com.tdevelopers.nasta.Entities.Review;
import com.tdevelopers.nasta.Opens.HotelOpenActivity;
import com.tdevelopers.nasta.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {

    RecyclerView rv;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment newInstance() {

        Bundle args = new Bundle();

        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = (RecyclerView) view.findViewById(R.id.reviewsrv);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setNestedScrollingEnabled(false);

        if (HotelOpenActivity.id != null && HotelOpenActivity.id.trim().length() != 0)
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    return ApiHelper.getData(strings[0]);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Gson gson = new Gson();
                    Review[] data = gson.fromJson(s, Review[].class);
                    rv.setAdapter(new ReviewAdapter(data));
                }
            }.execute(ApiHelper.getCollectionUrl("reviews") + "&q={%22source%22:%22" + HotelOpenActivity.id + "%22}");
 }


}
