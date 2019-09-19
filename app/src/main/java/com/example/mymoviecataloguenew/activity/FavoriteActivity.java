package com.example.mymoviecataloguenew.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.adap.MovieAdapter;
import com.example.mymoviecataloguenew.data.FavoriteContract;
import com.example.mymoviecataloguenew.data.FavoriteDbHelper;
import com.example.mymoviecataloguenew.model.MovieItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView rvFavorite;
    private LinkedList<MovieItem> list;
    private FavoriteContract favoriteContract;
    private FavoriteDbHelper favoriteDbHelper;
    private MovieAdapter movieAdapter;

    public FavoriteActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFavorite=(RecyclerView) findViewById(R.id.rv_favorite);

        favoriteDbHelper = new FavoriteDbHelper(this);
        favoriteDbHelper.open();

        list = new LinkedList<>();

        movieAdapter = new MovieAdapter(this);
        movieAdapter.setListFavorite(list);
        rvFavorite.setAdapter(movieAdapter);
        new LoadDB().execute();
    }

    private class LoadDB extends AsyncTask<Void, Void, ArrayList<MovieItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (list.size() > 0 ){
                list.clear();
            }

        }

        @Override
        protected void onPostExecute(ArrayList<MovieItem> favorites) {
            super.onPostExecute(favorites);
            list.addAll(favorites);
            movieAdapter.setListFavorite(list);
            movieAdapter.notifyDataSetChanged();

            if (list.size() == 0){
                Toast.makeText(getApplicationContext(), "tidak ada data", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected ArrayList<MovieItem> doInBackground(Void... voids) {
            return favoriteDbHelper.query();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (favoriteDbHelper != null){
            favoriteDbHelper.close();
        }
    }

}
