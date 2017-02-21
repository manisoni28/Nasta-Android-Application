package com.tdevelopers.nasta.Entities;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.tdevelopers.nasta.ApiHelper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by saitej dandge on 24-12-2016.
 */

public class User {
    public String pic;
    public String name;
    public String userno;
    public int status;
    public HashMap<String, Integer> cart = new HashMap<>();

    public User() {

    }

    public void addToCart(String id) {


        cart = new HashMap<>();
        cart.put("test", 12);
        Log.v("addcart", "addcart called");

        Log.v("checkurl", ApiHelper.getCollectionUrlSingle("users", "58726a9d98749cf46d6838a1"));

        new AsyncTask<String, Void, JSONObject>() {
            @Override
            protected JSONObject doInBackground(String... strings) {

                try {


                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("PUT");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                    JSONObject jsonObject = new JSONObject();
                    JSONObject object = new JSONObject();
                    object.put("cart", new Gson().toJson(cart));
                    jsonObject.put("$set", object);
                   writer.write(jsonObject.toString());
                    Log.v("addcart", jsonObject.toString());
                    writer.flush();
                    String line;
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(conn.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        Log.v("httppost", line);
                    }
                    writer.close();
                    reader.close();
                } catch (Exception e) {

                }

                return null;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
                //  Toast.makeText(AddHotelActivity.this, "Successfully obtained", Toast.LENGTH_SHORT).show();
            }
        }.execute(ApiHelper.getCollectionUrlSingle("users", "58726a9d98749cf46d6838a1"));


    }
}
