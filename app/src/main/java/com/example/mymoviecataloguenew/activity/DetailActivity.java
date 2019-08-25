package com.example.mymoviecataloguenew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymoviecataloguenew.R;
import com.squareup.picasso.Picasso;

import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_ID;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_RATING;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_TITLE;
import static com.example.mymoviecataloguenew.fragment.MovieFragment.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {
    TextView detailName;
    TextView detailDescription;
    TextView detailDirector;
    TextView detailGenre;
    ImageView detailPhoto;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        detailName = findViewById(R.id.tv_detail_name);
        detailDescription = findViewById(R.id.tv_detail_description);
        detailPhoto = findViewById(R.id.img_detail);
        detailDirector = findViewById(R.id.tv_detail_director);

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

        detailName.setText(title);
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w185/" + imageUrl)
                .into(detailPhoto);



    }
}
