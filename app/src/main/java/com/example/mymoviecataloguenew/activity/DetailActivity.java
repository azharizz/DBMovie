package com.example.mymoviecataloguenew.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.data.FavoriteDbHelper;
import com.example.mymoviecataloguenew.model.MovieItem;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_ID;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_RATING;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_TITLE;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {
    TextView detailName;
    TextView detailDescription;
    TextView detailRelease;
    ImageView detailPhoto;
    TextView detailRating;
    ImageView detailBackdrop;
    ImageView back;
    ImageView detailFavorite;
    Context context;
    RequestQueue mRequestQueue;
    private FavoriteDbHelper favoriteDbHelper;
    private MovieItem favorite;
    private final AppCompatActivity activity = DetailActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        detailName = findViewById(R.id.tv_detail_name);
        detailDescription = findViewById(R.id.tv_detail_description);
        detailPhoto = findViewById(R.id.image_detail);
        detailRelease = findViewById(R.id.tv_detail_release);
        detailRating = findViewById(R.id.tv_item_vote);
        detailBackdrop = findViewById(R.id.img_backdrop);
        detailFavorite = findViewById(R.id.favorite);

        back = findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String title = intent.getStringExtra(EXTRA_TITLE);
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        int id = intent.getIntExtra(EXTRA_ID, 0);
        double rating = intent.getDoubleExtra(EXTRA_RATING, 0);
        Log.d("IDDDDDD", "" + id);

        mRequestQueue = Volley.newRequestQueue(this);

        detailName.setText(title);
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w185/" + imageUrl)
                .into(detailPhoto);

        getMovieDetail(id);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean favorite;
        detailFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("com.example.mymoviecataloguenew.activity.DetailActivity", MODE_PRIVATE).edit();

                editor.putBoolean("Favorite Added", true);
                editor.commit();
                saveFavorite();
                Snackbar.make(detailFavorite, "Added to Favorite",
                        Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void getMovieDetail(int id) {
        String url = "https://api.themoviedb.org/3/movie/" + id + "h?api_key=103f11c7f4c23b41d3505f7544913590&language=en-US";

        JsonObjectRequest movieDetailRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    detailDescription.setText(response.getString("overview"));
                    Picasso.with(context)
                            .load("https://image.tmdb.org/t/p/w500/" + response.getString("backdrop_path"))
                            .into(detailBackdrop);
                    detailRelease.setText(response.getString("release_date"));
                    detailRating.setText(response.getString("vote_average"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(movieDetailRequest);
    }

    public void saveFavorite() {
        favoriteDbHelper = new FavoriteDbHelper(activity);

        favorite = new MovieItem(
                getIntent().getIntExtra(EXTRA_ID, 0),
                getIntent().getStringExtra(EXTRA_URL),
                getIntent().getStringExtra(EXTRA_TITLE),
                getIntent().getDoubleExtra(EXTRA_RATING, 0));
        favorite.setFavorite(true);


//        Double rate = movie.getVoteAverage();


//        favorite.setId(movie_id);
//        favorite.setOriginalTitle(movieName);
//        favorite.setPosterPath(thumbnail);
//        favorite.setVoteAverage(rate);
//        favorite.setOverview(synopsis);

        favoriteDbHelper.addFavorite(favorite);
    }


}
