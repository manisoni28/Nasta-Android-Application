package com.tdevelopers.nasta;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.tdevelopers.nasta.Entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by saitej dandge on 27-12-2016.
 */

public class ApiHelper {
    public static String apiKey = "Ta9bT-Cqqor2AJ5Y5JtNOlWKeJk82sQI";
    public static String baseUrl = "https://api.mlab.com/api/1/databases/nasta/collections/";

    public static String userno = "9177896188";
    public static User currentUser;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    public static String getCollectionUrl(String collection_name) {

        return baseUrl + collection_name + "?apiKey=" + apiKey;
    }

    public static String getCollectionUrlSingle(String collection_name,String _id) {

        return baseUrl + collection_name +"/"+_id+ "?apiKey=" + apiKey;
    }

    public static String getData(String stringurl) {

        String server_response = "";
        try {
            URL url = new URL(stringurl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                server_response = ApiHelper.readStream(urlConnection.getInputStream());

                //  Log.v("CatalogClient", server_response);


            }

            return server_response;
        } catch (Exception e) {

        }
        return server_response;

    }

    public static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
