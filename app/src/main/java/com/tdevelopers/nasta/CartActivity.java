package com.tdevelopers.nasta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.tdevelopers.nasta.Adapters.CartItemAdapter;
import com.tdevelopers.nasta.Entities.Dish;

public class CartActivity extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Cart");
        rv = (RecyclerView) findViewById(R.id.cartrv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setNestedScrollingEnabled(false);

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
                rv.setAdapter(new CartItemAdapter(data));
            }
        }.execute(ApiHelper.getCollectionUrl("dishes"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.checkout) {
            return true;
        } else if (id == R.id.totalcost) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
