package com.example.mymoviecataloguenew.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.adap.MovieAdapter;
import com.example.mymoviecataloguenew.base.BaseFragment;
import com.example.mymoviecataloguenew.model.MovieItem;
import com.example.mymoviecataloguenew.model.MovieViewModel;
import com.example.mymoviecataloguenew.model.ResultMovie;
import com.example.mymoviecataloguenew.useless.MainViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment {
    private Context context;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<ResultMovie> movies = new ArrayList<>();

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
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        movieLoad();

        MovieViewModel model = ViewModelProviders.of(this).get(MovieViewModel.class);
        model.getMovie().observe(this, new Observer<MovieItem>() {
            @Override
            public void onChanged(@Nullable MovieItem movieItem) {
                adapter=new MovieAdapter(getContext(),movieItem.getResultMovies());
                recyclerView.setAdapter(adapter);
            }
        });
//
//        model.getMovie().observe(this, response -> {
//            List<ResultMovie> movieItems = response.getResultMovies();
//            movies.addAll(movieItems);
//            adapter.notifyDataSetChanged();
//        });


        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}
