package com.tdevelopers.nasta.Opens;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.google.gson.Gson;
import com.tdevelopers.nasta.ApiHelper;
import com.tdevelopers.nasta.CartActivity;
import com.tdevelopers.nasta.HotelOpenFragments.DaySpecialFragments;
import com.tdevelopers.nasta.HotelOpenFragments.ItemsFragment;
import com.tdevelopers.nasta.HotelOpenFragments.OffersFragment;
import com.tdevelopers.nasta.HotelOpenFragments.ReviewsFragment;
import com.tdevelopers.nasta.R;

public class HotelOpenActivity extends AppCompatActivity implements View.OnClickListener {

    TabLayout tabLayout;
    ViewPager mpager;
    CollapsingToolbarLayout collapsingToolbarLayout;
    public static String id;

    public void init() {
        cart = (FloatingActionButton) findViewById(R.id.cart);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mpager = (ViewPager) findViewById(R.id.mpager);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        if (mpager != null && tabLayout != null) {
            mpager.setOffscreenPageLimit(3);

            mpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

                @Override
                public Fragment getItem(int position) {
                    switch (position % 4) {
                        case 0:
                            return ItemsFragment.newInstance();

                        case 1:
                            return DaySpecialFragments.newInstance();
                        case 2:
                            return OffersFragment.newInstance();
                        case 3:
                            return ReviewsFragment.newInstance();
                        default:
                            return null;
                    }
                }

                @Override
                public int getCount() {
                    return 4;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    switch (position % 4) {
                        case 0:
                            return "Dishes";
                        case 1:
                            return "Today's Specials";
                        case 2:
                            return "Offers";
                        case 3:
                            return "Reviews";

                    }
                    return "";
                }
            });
            tabLayout.setupWithViewPager(mpager);


        }

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return ApiHelper.getData(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();

            }
        }.execute(ApiHelper.getCollectionUrl("hotels") + "&q={\"_id\":" + id + "  }");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hotel_open, menu);
        return true;
    }

    FloatingActionButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_open);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Green Chutneys");
        setSupportActionBar(toolbar);
        if (getIntent().getExtras() != null && getIntent().getExtras().getString("id") != null && getIntent().getExtras().getString("id").trim().length() != 0)
            id = getIntent().getExtras().getString("id");


        init();
        cart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(HotelOpenActivity.this, CartActivity.class);
        startActivity(i);
    }
}
