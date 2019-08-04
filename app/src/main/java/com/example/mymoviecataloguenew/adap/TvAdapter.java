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
import com.example.mymoviecataloguenew.model.ResultMovie;
import com.example.mymoviecataloguenew.model.ResultTv;
import com.example.mymoviecataloguenew.useless.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder> {
    private Context context;
    List<ResultMovie> resultMovies;
    List<ResultTv> resultTvs;

    public TvAdapter(List<ResultTv> resultTvs) {
        this.resultTvs = resultTvs;
    }


    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, int position) {
        holder.tvName.setText(resultTvs.get(position).getOriginalName());
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + resultTvs.get(position).getPosterPath())
                .into(holder.imgPhoto);
    }

    public void setData(List<ResultTv> resultTvs) {
        this.resultTvs = resultTvs;
    }



    @Override
    public int getItemCount() {
        return resultTvs.size();
    }

    class TvHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvName;
        Movie movie;


        TvHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("key", movie);
            context.startActivity(intent);
        }
    }
}
