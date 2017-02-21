package com.tdevelopers.nasta.Entities;

/**
 * Created by saitej dandge on 26-12-2016.
 */

public class Review {
    public String comment;
    public String pic;
    public String name;
    public double rating;
    public String source;

    public Review(String comment, String userpic, String username, double rating, String source) {
        this.comment = comment;
        this.name = username;
        this.pic = userpic;
        this.rating = rating;
        this.source = source;
    }
}
