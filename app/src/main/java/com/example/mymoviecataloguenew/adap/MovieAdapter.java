package com.example.mymoviecataloguenew.adap;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.activity.DetailActivity;
import com.example.mymoviecataloguenew.model.MovieItem;
import com.example.mymoviecataloguenew.model.ResultMovie;
import com.example.mymoviecataloguenew.useless.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    public static List<ResultMovie> resultMovies;
    public static List<MovieItem> movieItems;


    public MovieAdapter(Context context, List<ResultMovie> resultMovies) {
        this.context = context;
        this.resultMovies = resultMovies;
    }

//    public MovieAdapter(List<ResultMovie> resultMovies) {
//        this.resultMovies = resultMovies;
//    }



    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {

// set the data
        holder.tvName.setText(resultMovies.get(position).getTitle());
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + resultMovies.get(position).getPosterPath())
                .into(holder.imgPhoto);

        holder.tvVote.setText(""+resultMovies.get(position).getVoteAverage());
    }





    @Override
    public int getItemCount() {
        return resultMovies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvName,tvVote;
        Movie movie;


        MovieHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvVote=itemView.findViewById(R.id.tv_item_vote);
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("key", movie);
            context.startActivity(intent);
        }

    }
}

