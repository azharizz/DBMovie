package com.example.mymoviecataloguenew.useless;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mymoviecataloguenew.adap.MovieAdapter;
import com.example.mymoviecataloguenew.api.ApiClient;
import com.example.mymoviecataloguenew.api.ApiInterface;
import com.example.mymoviecataloguenew.model.TvItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvViewModel extends ViewModel {
    private MutableLiveData<TvItem> tvList;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;

    public LiveData<TvItem> getMovie() {
        if (tvList == null) {
            tvList = new MutableLiveData<TvItem>();
            movieLoad();
        }
        return tvList;
    }


    private void movieLoad() {
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<TvItem> call=api.getTv();
        Log.d("gettingDataLoad", "Working");
        call.enqueue(new Callback<TvItem>() {
            @Override
            public void onResponse(Call<TvItem> call, Response<TvItem> response) {
                tvList.setValue(response.body());
                Log.d("gettingDataBody", response.body().toString());
                Log.d("gettingDataResponse", "Working");
            }

            @Override
            public void onFailure(Call<TvItem> call, Throwable t) {

            }
        });

    }

}
