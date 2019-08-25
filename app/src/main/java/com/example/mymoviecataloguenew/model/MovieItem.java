
package com.example.mymoviecataloguenew.model;

public class MovieItem {

    private int id;
    private String mImageUrl;
    private String mTitle;
    private double mRating;

    public MovieItem(int id, String mImageUrl, String mTitle, double mRating) {
        this.id = id;
        this.mImageUrl = mImageUrl;
        this.mTitle = mTitle;
        this.mRating = mRating;
    }

    public int getId() {
        return id;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public double getmRating() {
        return mRating;
    }
}
