package com.example.mymoviecataloguenew.fragment;


import android.content.Context;
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
    private TypedArray dataPhoto;
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;
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
//        mainViewModel.setMovies();
//        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        mainViewModel.getMovies().observe(this, getMovies);

    }


//    private void showLoading(Boolean state) {
//        if (state) {
//            progressBar.setVisibility(View.VISIBLE);
//        } else {
//            progressBar.setVisibility(View.GONE);
//        }
//    }

//    private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
//        @Override
//        public void onChanged(ArrayList<Movie> movies) {
//            if (movies != null) {
//                adapter.setMovies(movies);
//                showLoading(false);
//            }
//        }
//    };

//    public void sendRequest() {
//        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, StringsAPI.POPULAR_MOVIES, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray obj = response.getJSONArray("resultMovies");
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
//
//    private void getUserListData() {
//        // display a progress dialog
//
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show(); // show progress dialog
//
//
//        (ApiClient.getClient().getMovie()).enqueue(new Callback<List<ResultMovie>>() {
//            @Override
//            public void onResponse(Call<List<ResultMovie>> call, Response<List<ResultMovie>> response) {
////                Log.d("responseGET", response.body().get(0).getTitle());
//                progressDialog.dismiss(); //dismiss progress dialog
//                resultMovies = response.body();
//                setDataInRecyclerView();
//            }
//
//
//            @Override
//            public void onFailure(Call<List<ResultMovie>> call, Throwable t) {
//                // if error occurs in network transaction then we can get the error in this method.
//                final String txt=t.getMessage();
////                getActivity().runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
////                t.printStackTrace();
////                    }
////                });
//                progressDialog.dismiss(); //dismiss progress dialog
//                t.getMessage();
////                t.toString().printStackTrace();
//
//            }
//        });
//
//
//    }
//
//    private void setDataInRecyclerView() {
//        // set a LinearLayoutManager with default vertical orientation
//
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        // call the constructor of UsersAdapter to send the reference and data to Adapter
//        MovieAdapter usersAdapter = new MovieAdapter(getActivity(), resultMovies);
////        recyclerView.setAdapter(adapter);
//        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
//    }

}
