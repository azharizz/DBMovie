package com.example.mymoviecataloguenew;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Movie> movies;
    private FragmentManager fms;
//    List<Result> results;

//    public MovieAdapter(List<Result> results) {
//        this.results = results;
//    }


    public MovieAdapter() {
        this.movies = movies;
        this.fms = fms;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movie movie = getMovies().get(position);
//        holder.imgPhoto.setImageResource(movie.getPhoto());
//        Glide.with(context)
//                .load(movie.getPhoto())
//                .apply(new RequestOptions().override(600, 850))
//                .into(holder.imgPhoto);
        holder.setPosterPath(movies.get(position).getPhoto());
        holder.tvName.setText(movies.get(position).getName());

        holder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Movie movie = new Movie();
                movie.setName(movies.get(position).getName());
                movie.setDescription(movies.get(position).getDescription());
                movie.setPhoto(movies.get(position).getPhoto());
                movie.setGenre(movies.get(position).getGenre());
                movie.setDirector(movies.get(position).getDirector());
                movie.setYear(movies.get(position).getYear());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                context.startActivity(intent);
            }
        }));

//        Picasso.with(holder.imgPhoto.getContext())
//                .load("https://image.tmdb.org/t/p/w185/" + results.get(position).getPosterPath())
//                .into(holder.imgPhoto);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvName;
        TextView tvDescription;
        TextView tvGenre;
        TextView tvYear;
        TextView tvDirector;
        Movie movie;
        View mView;


        ViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }

        void bind(Movie movies) {
            tvName.setText(movies.getName());
            setPosterPath(movies.getPhoto());
            tvDescription.setText(movies.getDescription());
            tvGenre.setText(movies.getGenre());
            tvYear.setText(movies.getYear());
            tvDescription.setText(movies.getDescription());
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("key", movie);
            context.startActivity(intent);
        }

        public void setPosterPath(String posterPath) {
            Picasso.with(imgPhoto.getContext()).load(StringsAPI.IMAGE_SOURCE + posterPath).placeholder(R.mipmap.ic_launcher)
                    .into(imgPhoto);

        }
    }
}

