package com.example.mymoviecataloguenew.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.adap.TvAdapter;
import com.example.mymoviecataloguenew.api.ApiClient;
import com.example.mymoviecataloguenew.api.ApiInterface;
import com.example.mymoviecataloguenew.model.TvItem;
import com.example.mymoviecataloguenew.useless.MainViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvAdapter adapterr;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

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

        progressBar = container.findViewById(R.id.progressBar);
//        getUserListData();

        movieLoad();


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void movieLoad() {
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<TvItem> call = api.getTv();
        Log.d("gettingDataLoad", "Working");
        call.enqueue(new Callback<TvItem>() {
            @Override
            public void onResponse(Call<TvItem> call, Response<TvItem> response) {
                TvItem tvItem = response.body();
                adapterr = new TvAdapter(getContext(),tvItem);
                recyclerView.setAdapter(adapterr);
                Log.d("gettingDataBody", response.body().toString());
                Log.d("gettingDataResponse", "Working");
            }

            @Override
            public void onFailure(Call<TvItem> call, Throwable t) {

            }


        });

    }


}
