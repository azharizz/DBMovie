
package com.example.mymoviecataloguenew.model;

public class MovieItem {

    private int id;
    private String mImageUrl;
    private String mTitle;
    private double mRating;
    private boolean favorite;

    public MovieItem() {
    }

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmRating(double mRating) {
        this.mRating = mRating;
    }


}
