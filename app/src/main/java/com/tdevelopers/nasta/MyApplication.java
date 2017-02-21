package com.tdevelopers.nasta;

import android.app.Application;

/**
 * Created by saitej dandge on 26-12-2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
    /*    new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return ApiHelper.getData(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                User[] data = gson.fromJson(s, User[].class);
                ApiHelper.currentUser = data[0];
                //  Log.v("currentuser", s + "");
                //Log.v("currentusercart", ApiHelper.currentUser.cart.toString());

            }
        }.execute(ApiHelper.getCollectionUrl("users") + "&q={\"userno\":\"" + ApiHelper.userno + "\"}");
    */
    }
}
