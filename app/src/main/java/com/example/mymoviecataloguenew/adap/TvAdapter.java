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
import com.example.mymoviecataloguenew.model.TvItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_ID;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_RATING;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_TITLE;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_URL;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder> {
    private Context context;
    private ArrayList<TvItem> mExampleList;

    public TvAdapter(Context context, ArrayList<TvItem> exampleList) {
        this.context = context;
        this.mExampleList = exampleList;
    }

    @NonNull
    @Override
    public TvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, int position) {
        final TvItem currentItem = mExampleList.get(position);
// set the data
        holder.tvName.setText(currentItem.getmTitle());
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + currentItem.getmImageUrl())
                .into(holder.imgPhoto);

        holder.tvVote.setText("" + currentItem.getmRating());
        holder.id = currentItem.getId();

        holder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
//                MovieItem clickedItem = mExampleList.get(position);

                detailIntent.putExtra(EXTRA_RATING, currentItem.getmTitle());
                detailIntent.putExtra(EXTRA_ID, currentItem.getId());
                detailIntent.putExtra(EXTRA_TITLE, currentItem.getmTitle());
                detailIntent.putExtra(EXTRA_URL, currentItem.getmImageUrl());

                context.startActivity(detailIntent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    class TvHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvName,tvVote;
        int id;

        TvHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvVote = itemView.findViewById(R.id.tv_item_vote);
        }
    }
}
