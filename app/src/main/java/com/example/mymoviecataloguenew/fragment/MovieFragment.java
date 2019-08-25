package com.example.mymoviecataloguenew.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.activity.DetailActivity;
import com.example.mymoviecataloguenew.adap.MovieAdapter;
import com.example.mymoviecataloguenew.base.BaseFragment;
import com.example.mymoviecataloguenew.base.GridAutofitLayoutManager;
import com.example.mymoviecataloguenew.model.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment implements MovieAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "titleMovie";
    public static final String EXTRA_RATING = "ratingMovie";
    public static final String EXTRA_ID = "idMovie";

    private Context context;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    ProgressBar progressBar;
    private ArrayList<MovieItem> mExampleList;
    private RequestQueue mRequestQueue;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = rootView.findViewById(R.id.rv_category_movie);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
//        recyclerView.setVisibility(View.INVISIBLE);
//        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridAutofitLayoutManager(getActivity(), 300));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getContext());
        parseJSON();

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

//    private void movieLoad() {
//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
//        progressDialog.show();
//        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
//        Call<MovieItem> call = api.getMovie();
//        Log.d("gettingDataLoad", "Working");
//        call.enqueue(new Callback<MovieItem>() {
//            @Override
//            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
//                MovieItem movieItem = response.body();
//                adapter = new MovieAdapter(getContext(), movieItem);
//                recyclerView.setAdapter(adapter);
//                Log.d("gettingDataBody", response.body().toString());
//                Log.d("gettingDataResponse", "Working");
//                if (response.isSuccessful()){
//                progressBar.setVisibility(View.GONE);
//                recyclerView.setVisibility(View.VISIBLE);
//                }
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<MovieItem> call, Throwable t) {
//
//            }
//        });
//    }

    private void parseJSON() {
        String DB_API = "103f11c7f4c23b41d3505f7544913590";
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + DB_API;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);

                        int id = hit.getInt("id");
                        String imageUrl = hit.getString("poster_path");
                        String title = hit.getString("title");
                        double rating = hit.getDouble("vote_average");

                        mExampleList.add(new MovieItem(id, imageUrl, title, rating));
                    }

                    adapter = new MovieAdapter(getContext(), mExampleList);
                    recyclerView.setAdapter(adapter);
                    MovieAdapter.OnItemClickListener adapterIntent = new MovieAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent detailIntent = new Intent(getContext(), DetailActivity.class);
                            MovieItem clickedItem = mExampleList.get(position);

                            detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());
                            detailIntent.putExtra(EXTRA_TITLE, clickedItem.getmTitle());
                            detailIntent.putExtra(EXTRA_RATING, clickedItem.getmRating());
                            detailIntent.putExtra(EXTRA_ID, clickedItem.getId());

                            startActivity(detailIntent);
                        }
                    };

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getContext(), DetailActivity.class);
        MovieItem clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getmTitle());
        detailIntent.putExtra(EXTRA_RATING, clickedItem.getmRating());
        detailIntent.putExtra(EXTRA_ID, clickedItem.getId());

        startActivity(detailIntent);
    }
}
