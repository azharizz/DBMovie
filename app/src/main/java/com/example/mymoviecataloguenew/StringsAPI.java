package com.example.mymoviecataloguenew;

public class StringsAPI {
    private static String API_KEY = "103f11c7f4c23b41d3505f7544913590";
    public static String IMAGE_SOURCE = "http://image.tmdb.org/t/p/w185/";
    public static String POPULAR_MOVIES = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US&page=1";

    public static String DETAIL_MOVIES(int id) {
        return "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + API_KEY +"&language=en-US&page=1";
    }

    public static String DETAIL_TV (int id){
        return "https://api.themoviedb.org/3/discover/tv?api_key={API KEY}&language=en-US";
    }
}
