package com.example.mymoviecataloguenew.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;

import com.example.mymoviecataloguenew.adap.MovieAdapter;
import com.example.mymoviecataloguenew.api.ApiClient;
import com.example.mymoviecataloguenew.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<MovieItem> movieList = new MutableLiveData<>();
    private MovieAdapter adapter;
    private RecyclerView recyclerView;

    public LiveData<MovieItem> getMovie() {
        if (movieList == null) {
            movieList = new MutableLiveData<MovieItem>();
            movieLoad();
        }
        return movieList;
    }


    private void movieLoad() {
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<MovieItem> call = api.getMovie();
        call.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                movieList.setValue(response.body());

            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {

            }
        });
    }

//    private void movieLoad(){
//        ApiInterface api= ApiClient.getRetrofit().create(ApiInterface.class);
//        Call<List<MovieItem>> call=api.getMovie();
//        call.enqueue(new Callback<List<MovieItem>>() {
//            @Override
//            public void onResponse(Call<List<MovieItem>> call, Response<List<MovieItem>> response) {
//                movieList.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<MovieItem>> call, Throwable t) {
//
//            }
//        });
//
//    }


}
