package com.tdevelopers.nasta.Entities;

import android.util.Log;

/**
 * Created by saitej dandge on 24-12-2016.
 */

public class Hotel {
    public Object _id;
    public String name;
    public double rating;
    public String address;
    public double lat;
    public double longi;
    public String owner_name;
    public double pincode;
    public String email;
    public double phno;
    public String fbpage;
    public String website;
    public String dishes[];
    public String pic;
    public long start_time;
    public long end_time;
    public String id;

    public Hotel() {
        _id = name = address = owner_name = email = pic = website = fbpage = new String();


    }

    public Hotel(String name, String owner_name, double phno, String email, String address, double pincode, String website, String fbpage, double lat, double longi, String pic, long start_time, long end_time) {

        this.name = name;
        this.rating = 0;
        this.address = address;
        this.lat = lat;
        this.longi = longi;
        this.owner_name = owner_name;
        this.pincode = pincode;
        this.email = email;
        this.phno = phno;
        this.pic = pic;
        this.start_time = start_time;
        this.end_time = end_time;

        this.fbpage = fbpage;
        this.website = website;

        if (website.trim().length() == 0)
            this.website = null;
        if (fbpage.trim().length() == 0)
            this.fbpage = null;

        Log.v("construct", this._id.toString());


    }
}
