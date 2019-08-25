package com.example.mymoviecataloguenew.useless;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.mymoviecataloguenew.api.ApiClient;
import com.example.mymoviecataloguenew.api.ApiInterface;
import com.example.mymoviecataloguenew.model.MovieItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<MovieItem> movieList;

    public LiveData<MovieItem> getMovie() {
        if (movieList == null) {
            movieList = new MutableLiveData<MovieItem>();
            movieLoad();
        }
        return movieList;
    }


    private void movieLoad() {
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<MovieItem> call=api.getMovie();
        Log.d("gettingDataLoad", "Working");
        call.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                movieList.setValue(response.body());
                Log.d("gettingDataBody", response.body().toString());
                Log.d("gettingDataResponse", "Working");
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {

            }
        });

    }



}
