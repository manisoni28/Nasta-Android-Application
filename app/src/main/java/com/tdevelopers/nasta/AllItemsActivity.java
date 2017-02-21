package com.tdevelopers.nasta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tdevelopers.nasta.Adapters.DishDetailedAdapter;
import com.tdevelopers.nasta.Entities.Dish;

import java.util.ArrayList;

public class AllItemsActivity extends AppCompatActivity {

    RecyclerView allitems;

    public void init() {
        allitems = (RecyclerView) findViewById(R.id.allitems);
        allitems.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Dish> data = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            data.add(new Dish("http://www.telegraphindia.com/1120615/images/15food1.jpg", "Puri"));
        allitems.setAdapter(new DishDetailedAdapter(data));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();


    }

}
