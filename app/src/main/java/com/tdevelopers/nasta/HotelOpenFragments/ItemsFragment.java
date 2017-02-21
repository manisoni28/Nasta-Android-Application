package com.tdevelopers.nasta.HotelOpenFragments;


import android.annotation.SuppressLint;
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
import com.tdevelopers.nasta.Adapters.HotelItemsAdapter;
import com.tdevelopers.nasta.ApiHelper;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.Opens.HotelOpenActivity;
import com.tdevelopers.nasta.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ItemsFragment extends Fragment {

    RecyclerView tiffinsrv, fastfoodrv, mealsrv;

    @SuppressLint("ValidFragment")
    public ItemsFragment() {
        // Required empty public constructor

    }

    public static ItemsFragment newInstance() {

        Bundle args = new Bundle();

        ItemsFragment fragment = new ItemsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tiffinsrv = (RecyclerView) view.findViewById(R.id.tiffinsrv);
        fastfoodrv = (RecyclerView) view.findViewById(R.id.fastfoodrv);
        mealsrv = (RecyclerView) view.findViewById(R.id.mealsrv);
        tiffinsrv.setNestedScrollingEnabled(false);
        fastfoodrv.setNestedScrollingEnabled(false);
        mealsrv.setNestedScrollingEnabled(false);
        tiffinsrv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        fastfoodrv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        mealsrv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

        if (HotelOpenActivity.id != null && HotelOpenActivity.id.trim().length() != 0) {


            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    return ApiHelper.getData(strings[0]);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Gson gson = new Gson();
                    Dish[] data = gson.fromJson(s, Dish[].class);

                    tiffinsrv.setAdapter(new HotelItemsAdapter(data));
                }
            }.execute(ApiHelper.getCollectionUrl("dishes") + "&l=4&s={%22rating%22:-1}&q={source:%22" + HotelOpenActivity.id + "%22,category:0}");
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    return ApiHelper.getData(strings[0]);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Gson gson = new Gson();
                    Dish[] data = gson.fromJson(s, Dish[].class);

                    fastfoodrv.setAdapter(new HotelItemsAdapter(data));
                }
            }.execute(ApiHelper.getCollectionUrl("dishes") + "&l=4&s={%22rating%22:-1}&q={source:%22" + HotelOpenActivity.id + "%22,category:1}");
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    return ApiHelper.getData(strings[0]);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Gson gson = new Gson();
                    Dish[] data = gson.fromJson(s, Dish[].class);

                    mealsrv.setAdapter(new HotelItemsAdapter(data));
                }
            }.execute(ApiHelper.getCollectionUrl("dishes") + "&l=4&s={%22rating%22:-1}&q={source:%22" + HotelOpenActivity.id + "%22,category:2}");


        }
    }
}
