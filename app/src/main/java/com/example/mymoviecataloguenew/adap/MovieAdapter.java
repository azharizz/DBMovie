package com.example.mymoviecataloguenew.adap;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.model.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<MovieItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MovieAdapter(Context context, ArrayList<MovieItem> exampleList) {
        this.context = context;
        this.mExampleList = exampleList;
    }


    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new MovieHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        MovieItem currentItem = mExampleList.get(position);
// set the data
        holder.tvName.setText(currentItem.getmTitle());
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/" + currentItem.getmImageUrl())
                .into(holder.imgPhoto);

        holder.tvVote.setText("" + currentItem.getmRating());
        holder.id = currentItem.getId();
    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvVote;
        int id;


        MovieHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvVote = itemView.findViewById(R.id.tv_item_vote);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }


    }


}

