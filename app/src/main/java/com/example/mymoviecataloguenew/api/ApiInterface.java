package com.example.mymoviecataloguenew.api;

import com.example.mymoviecataloguenew.model.MovieItem;
import com.example.mymoviecataloguenew.model.TvItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    public static String DB_API = "103f11c7f4c23b41d3505f7544913590";

    @GET("movie?api_key=" + DB_API + "&language=en-US&page=1")
    Call<List<MovieItem>> getMovie();

    @GET("tv?api_key=" + DB_API + "&language=en-US&page=1")
    Call<TvItem> getTv();
}
