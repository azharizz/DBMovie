package com.example.mymoviecataloguenew.useless;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movie implements Parcelable {
    private String photo;
    private String name;
    private String description;
    private String genre;
    private String year;
    private String director;
    private String id;

    Movie(JSONObject object) {
        try {
            String id = object.getString("id");
            String name = object.getString("title");
            String photo = object.getString("poster_path");
            String description = object.getString("overview");
            String genre = object.getJSONArray("genres").getJSONObject(0).getString("name");
            String year = object.getString("release_date");
            String director = object.getString("tagline");
            this.photo = photo;
            this.name = name;
            this.description = description;
            this.genre = genre;
            this.year = year;
            this.director = director;
            this.id = id;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Movie(String photo, String name, String description, String genre, String year, String director, String id) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.year = year;
        this.director = director;
        this.id = id;
    }



    protected Movie() {
    }


    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    protected Movie(Parcel in) {
        photo = in.readString();
        name = in.readString();
        description = in.readString();
        genre = in.readString();
        year = in.readString();
        director = in.readString();
        id = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photo);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(genre);
        dest.writeString(year);
        dest.writeString(director);
    }
//
//    protected Movie(Parcel in) {
//        photo = in.readString();
//        name = in.readString();
//        description = in.readString();
//        genre = in.readString();
//        year = in.readString();
//        director = in.readString();
//    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
