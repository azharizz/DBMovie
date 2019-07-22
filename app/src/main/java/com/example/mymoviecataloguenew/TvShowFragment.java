package com.example.mymoviecataloguenew;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private String[] dataName;
    private String[] dataDescription;
    private String[] dataGenre;
    private String[] dataDirector;
    private String[] dataYear;
    private TypedArray dataPhoto;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;
    List<Result> results;

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
        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();
//        sendRequest();

        recyclerView = rootView.findViewById(R.id.rv_category_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        progressBar = container.findViewById(R.id.progressBar);


//        movieLoad();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel.setTV();
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getTV().observe(this, getMovie);
    }


    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setMovies(movies);
                showLoading(false);
            }
        }
    };
//
//    public void sendRequest() {
//        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, StringsAPI.POPULAR_MOVIES, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray obj = response.getJSONArray("results");
//                    for (int i = 0; i < obj.length(); i++) {
//
//                        JSONObject jsonObject = obj.getJSONObject(i);
//
//                        Movie movie = new Movie(
//                                jsonObject.getString("title"),
//                                jsonObject.getString("poster_path"),
//                                jsonObject.getString("overview"),
//                                jsonObject.getJSONArray("genres").getJSONObject(0).getString("name"),
//                                jsonObject.getString("release_date"),
//                                jsonObject.getString("tagline"),
//                                jsonObject.getString("id")
////                                jsonObject.getString("id"),
////                                jsonObject.getString("title"),
////                                jsonObject.getString("poster_path"),
////                                jsonObject.getString("backdrop_path"),
////                                jsonObject.getString("overview")
//                        );
//                        movies.add(movie);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                adapter.notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }

//    private void movieLoad() {
//        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
//        Call<MovieItem> call = api.getTv();
//        call.enqueue(new Callback<MovieItem>() {
//            @Override
//            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
//                MovieItem movieItem = response.body();
//                adapter = new MovieAdapter(results);
//                adapter.setData(movieItem.getResults());
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<MovieItem> call, Throwable t) {
//
//            }
//        });
//    }

}
