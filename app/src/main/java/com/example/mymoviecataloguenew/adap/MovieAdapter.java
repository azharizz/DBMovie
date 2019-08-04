package com.example.mymoviecataloguenew.adap;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.activity.DetailActivity;
import com.example.mymoviecataloguenew.model.ResultMovie;
import com.example.mymoviecataloguenew.useless.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> movies;
    private FragmentManager fms;
    List<ResultMovie> resultMovies;

    public MovieAdapter(List<ResultMovie> resultMovies) {
        this.resultMovies = resultMovies;
    }



//    public MovieAdapter(Context context, List<ResultMovie> resultMovies) {
//        this.resultMovies = resultMovies;
//        this.context = context;
//    }

//
//    public MovieAdapter() {
//        this.movies = movies;
//        this.fms = fms;
//    }
//
//    public ArrayList<Movie> getMovies() {
//        return movies;
//    }
//
//    public void setMovies(ArrayList<Movie> movies) {
//        this.movies.clear();
//        this.movies.addAll(movies);
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
//        final Movie movie = getMovies().get(position);
////        holder.imgPhoto.setImageResource(movie.getPhoto());
////        Glide.with(context)
////                .load(movie.getPhoto())
////                .apply(new RequestOptions().override(600, 850))
////                .into(holder.imgPhoto);
//        holder.setPosterPath(movies.get(position).getPhoto());
//        holder.tvName.setText(movies.get(position).getName());
//
//        holder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(View view, int position) {
//                Movie movie = new Movie();
//                movie.setName(movies.get(position).getName());
//                movie.setDescription(movies.get(position).getDescription());
//                movie.setPhoto(movies.get(position).getPhoto());
//                movie.setGenre(movies.get(position).getGenre());
//                movie.setDirector(movies.get(position).getDirector());
//                movie.setYear(movies.get(position).getYear());
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
//                context.startActivity(intent);
//            }
//        }));


// set the data
        holder.tvName.setText(resultMovies.get(position).getOriginalTitle());
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + resultMovies.get(position).getPosterPath())
                .into(holder.imgPhoto);
        // implement setONCLickListtener on itemView
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // display a toast with user name
//                Toast.makeText(context, resultMovies.get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    public void setData(List<ResultMovie> resultMovies) {
        this.resultMovies = resultMovies;
    }


    @Override
    public int getItemCount() {
        return resultMovies.size();
    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }


    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvName;
        TextView tvDescription;
        TextView tvGenre;
        TextView tvYear;
        TextView tvDirector;
        Movie movie;
        View mView;


        MovieHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }

//        void bind(Movie movies) {
//            tvName.setText(movies.getName());
//            setPosterPath(movies.getPhoto());
//            tvDescription.setText(movies.getDescription());
//            tvGenre.setText(movies.getGenre());
//            tvYear.setText(movies.getYear());
//            tvDescription.setText(movies.getDescription());
//        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("key", movie);
            context.startActivity(intent);
        }

    }
}

