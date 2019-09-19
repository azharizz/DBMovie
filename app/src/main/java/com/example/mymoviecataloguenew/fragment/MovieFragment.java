package com.example.mymoviecataloguenew.fragment;


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
public class MovieFragment extends BaseFragment{
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "titleMovie";
    public static final String EXTRA_RATING = "ratingMovie";
    public static final String EXTRA_ID = "idMovie";

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
        progressBar = rootView.findViewById(R.id.progressBar);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
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
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        mRequestQueue.add(request);
    }




}
