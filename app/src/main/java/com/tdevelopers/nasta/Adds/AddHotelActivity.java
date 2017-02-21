package com.tdevelopers.nasta.Adds;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.tdevelopers.nasta.ApiHelper;
import com.tdevelopers.nasta.Entities.Hotel;
import com.tdevelopers.nasta.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class AddHotelActivity extends AppCompatActivity implements TextWatcher {

    private static final int SELECT_PHOTO = 100;
    EditText name, ownername, ph, email, address, pincode, website, fbpage;
    ImageView adp;
    Bitmap bitmap = null;
    MaterialDialog dialog;
    double pincodeno=0, phno=0;

    public void init() {
        adp = (ImageView) findViewById(R.id.adp);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        ph = (EditText) findViewById(R.id.ph);
        email = (EditText) findViewById(R.id.email);
        ownername = (EditText) findViewById(R.id.ownername);
        website = (EditText) findViewById(R.id.website);
        fbpage = (EditText) findViewById(R.id.fbpage);
        pincode = (EditText) findViewById(R.id.pincode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_hotel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gallery:


                ApiHelper.verifyStoragePermissions(AddHotelActivity.this);
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Add Hotel");
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitleEnabled(false);
        init();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = AddHotelActivity.this.name.getText().toString().trim();
                final String ownername = AddHotelActivity.this.ownername.getText().toString().trim();
                final String email = AddHotelActivity.this.email.getText().toString().trim();
                final String address = AddHotelActivity.this.address.getText().toString().trim();
                final String website = AddHotelActivity.this.website.getText().toString().trim();
                final String fbpage = AddHotelActivity.this.fbpage.getText().toString().trim();
                String ph = AddHotelActivity.this.ph.getText().toString().trim();
                String pincode = AddHotelActivity.this.pincode.getText().toString().trim();

                try {
                    if (AddHotelActivity.this.ph.getText().toString().trim().length() != 0)
                        phno = Double.parseDouble(AddHotelActivity.this.ph.getText().toString().trim());

                    if (AddHotelActivity.this.pincode.getText().toString().trim().length() != 0)
                        pincodeno = Double.parseDouble(AddHotelActivity.this.pincode.getText().toString().trim());
                } catch (Exception e) {


                }

                AddHotelActivity.this.name.addTextChangedListener(AddHotelActivity.this);
                AddHotelActivity.this.ownername.addTextChangedListener(AddHotelActivity.this);
                AddHotelActivity.this.address.addTextChangedListener(AddHotelActivity.this);
                AddHotelActivity.this.pincode.addTextChangedListener(AddHotelActivity.this);
                AddHotelActivity.this.ph.addTextChangedListener(AddHotelActivity.this);
                AddHotelActivity.this.email.addTextChangedListener(AddHotelActivity.this);


                if (name.length() == 0)
                    AddHotelActivity.this.name.setError(getResources().getString(R.string.error));
                if (ownername.length() == 0)
                    AddHotelActivity.this.ownername.setError(getResources().getString(R.string.error));
                if (email.length() == 0)
                    AddHotelActivity.this.email.setError(getResources().getString(R.string.error));
                if (address.length() == 0)
                    AddHotelActivity.this.address.setError(getResources().getString(R.string.error));

                if (ph.length() != 10)
                    AddHotelActivity.this.ph.setError(getResources().getString(R.string.error));
                if (pincode.length() != 6)
                    AddHotelActivity.this.pincode.setError(getResources().getString(R.string.error));


                if ( phno != 0 && pincodeno != 0 && ph.length() == 10 && pincode.length() == 6 && name.length() != 0 && ownername.length() != 0 && email.length() != 0 && address.length() != 0) {
                    upload(name, ownername, phno, email, address, pincodeno, website, fbpage, "http://images.nymag.com/listings/restaurant/main-petrossian.jpg");

                }
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    public void upload(String name, String ownername, double phno, String email, String address, double pincode, String website, String fbpage, String pic) {


        Gson gson = new Gson();


        final String json = gson.toJson(new Hotel(name, ownername, phno, email, address, pincode, website, fbpage, 0, 0, pic, 0, 0));

        new AsyncTask<String, Void, JSONObject>() {
            @Override
            protected JSONObject doInBackground(String... strings) {

                try {


                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(json);
                    writer.flush();
                    String line;
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(conn.getInputStream()));
                    while ((line = reader.readLine()) != null) {
                        Log.v("httppost", line);
                    }
                    writer.close();
                    reader.close();
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                } catch (Exception e) {

                }

                return null;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
                Toast.makeText(AddHotelActivity.this, "Successfully obtained", Toast.LENGTH_SHORT).show();
            }
        }.execute(ApiHelper.getCollectionUrl("hotels"));

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        try {

            switch (requestCode) {

                case SELECT_PHOTO:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = imageReturnedIntent.getData();
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                            Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 2;
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            final byte[] byteArray = baos.toByteArray();
                            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                            //  fl.setVisibility(View.VISIBLE);
                            adp.setImageBitmap(bitmap);

                        } catch (Error e) {

                            Toast.makeText(AddHotelActivity.this, "File too big to process", Toast.LENGTH_SHORT).show();

                        }
                    }
            }
        } catch (Exception e) {
            Toast.makeText(AddHotelActivity.this, "Error occcured :( please try again !", Toast.LENGTH_SHORT).show();

        }
    }


    @Override
    public void afterTextChanged(Editable editable) {

        if (editable.toString().length() > 0) {
            if (editable == name.getEditableText()) {

                name.setError(null);
            }
            if (editable == ownername.getEditableText()) {

                ownername.setError(null);
            }
            if (editable == ph.getEditableText()) {

                ph.setError(null);
            }
            if (editable == email.getEditableText()) {

                email.setError(null);
            }
            if (editable == address.getEditableText()) {

                address.setError(null);
            }
            if (editable == pincode.getEditableText()) {

                pincode.setError(null);
            }

        }
    }
}
