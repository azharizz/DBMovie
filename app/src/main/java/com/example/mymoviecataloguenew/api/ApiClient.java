package com.example.mymoviecataloguenew.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/discover/";


    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

//    private static Retrofit retrofit = null;
//
//    public static ApiInterface getClient() {
//
//        // change your base URL
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        //Creating object for our interface
//        ApiInterface api = retrofit.create(ApiInterface.class);
//        return api; // return the APIInterface object
//    }
}
