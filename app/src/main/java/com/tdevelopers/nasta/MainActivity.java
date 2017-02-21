package com.tdevelopers.nasta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tdevelopers.nasta.Adapters.HomeFoodItemsAdapter;
import com.tdevelopers.nasta.Entities.Dish;
import com.tdevelopers.nasta.Entities.Hotel;
import com.tdevelopers.nasta.Opens.HotelOpenActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView dishrv;
    FrameLayout f[];
    ImageView i[];
    TextView t[];
    Hotel[] data;

    public void init() {
        dishrv = (RecyclerView) findViewById(R.id.dishrv);
        dishrv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dishrv.setNestedScrollingEnabled(false);
        f = new FrameLayout[4];
        f[0] = (FrameLayout) findViewById(R.id.f1);
        f[0].setOnClickListener(this);
        f[1] = (FrameLayout) findViewById(R.id.f2);
        f[1].setOnClickListener(this);
        f[2] = (FrameLayout) findViewById(R.id.f3);
        f[2].setOnClickListener(this);
        f[3] = (FrameLayout) findViewById(R.id.f4);
        f[3].setOnClickListener(this);

        i = new ImageView[4];
        i[0] = (ImageView) findViewById(R.id.i1);
        i[1] = (ImageView) findViewById(R.id.i2);
        i[2] = (ImageView) findViewById(R.id.i3);
        i[3] = (ImageView) findViewById(R.id.i4);

        t = new TextView[4];
        t[0] = (TextView) findViewById(R.id.t1);
        t[1] = (TextView) findViewById(R.id.t2);
        t[2] = (TextView) findViewById(R.id.t3);
        t[3] = (TextView) findViewById(R.id.t4);


        FirebaseDatabase.getInstance().getReference("Hotels").orderByChild("rating").limitToFirst(4).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {
                    int ij = 0;
                    for (DataSnapshot d : dataSnapshot.getChildren()) {

                        data[ij++] = d.getValue(Hotel.class);
                    }

                    for (int k = 0; k < data.length; k++)
                        Glide.with(MainActivity.this).load(data[k].pic).into(i[k]);

                    for (int k = 0; k < data.length; k++)
                        t[k].setText(data[k].name);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Query q = FirebaseDatabase.getInstance().getReference("Dishes").orderByChild("rating").limitToFirst(10);
        dishrv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        dishrv.setAdapter(new HomeFoodItemsAdapter(q, Dish.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        try {
            int id = view.getId();
            Intent i = new Intent(MainActivity.this, HotelOpenActivity.class);
            switch (id) {

                case R.id.f1:
                    i.putExtra("id", data[0].id);

                    break;
                case R.id.f2:

                    i.putExtra("id", data[1].id);

                    break;
                case R.id.f3:


                    i.putExtra("id", data[2].id);
                    break;
                case R.id.f4:

                    i.putExtra("id", data[3].id);
                    break;
            }
            startActivity(i);
        } catch (Exception e) {

        }
    }
}
