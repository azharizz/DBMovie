package com.example.mymoviecataloguenew.fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.adap.TvAdapter;
import com.example.mymoviecataloguenew.api.ApiClient;
import com.example.mymoviecataloguenew.api.ApiInterface;
import com.example.mymoviecataloguenew.model.ResultMovie;
import com.example.mymoviecataloguenew.model.ResultTv;
import com.example.mymoviecataloguenew.model.TvItem;
import com.example.mymoviecataloguenew.useless.MainViewModel;
import com.example.mymoviecataloguenew.useless.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TypedArray dataPhoto;
    private TvAdapter adapterr;
    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;
    List<ResultMovie> resultMovies;
    List<ResultTv> resultTvs;

    private MainViewModel mainViewModel;


    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = rootView.findViewById(R.id.rv_category_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        movieLoad();

        progressBar = container.findViewById(R.id.progressBar);
//        getUserListData();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void movieLoad() {

        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<TvItem> call = api.getTv();
        call.enqueue(new Callback<TvItem>() {
            @Override
            public void onResponse(Call<TvItem> call, Response<TvItem> response) {
                TvItem tvItem = response.body();
                adapterr = new TvAdapter(tvItem.getResults());
//                adapterr=new TvAdapter(tvItem.getResults());
                recyclerView.setAdapter(adapterr);
            }

            @Override
            public void onFailure(Call<TvItem> call, Throwable t) {

            }
        });
    }

}
