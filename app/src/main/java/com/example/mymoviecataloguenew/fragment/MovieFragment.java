package com.example.mymoviecataloguenew.fragment;


import android.content.Context;
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

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.adap.MovieAdapter;
import com.example.mymoviecataloguenew.api.ApiClient;
import com.example.mymoviecataloguenew.api.ApiInterface;
import com.example.mymoviecataloguenew.model.MovieItem;
import com.example.mymoviecataloguenew.model.ResultMovie;
import com.example.mymoviecataloguenew.useless.MainViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private Context context;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    List<ResultMovie> resultMovies;

    private MainViewModel mainViewModel;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);
//        adapter = new MovieAdapter();
//        adapter.notifyDataSetChanged();
//        sendRequest();

        recyclerView = rootView.findViewById(R.id.rv_category_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        recyclerView.setAdapter(adapter);
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
        Call<MovieItem> call= api.getMovie();
        call.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                MovieItem movieItem=response.body();
                adapter= new MovieAdapter(resultMovies);
//                adapter=new MovieAdapter(movieItem.getResultMovies());
                adapter.setData(movieItem.getResultMovies());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {

            }
        });
    }

}
