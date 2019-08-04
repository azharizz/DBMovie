package com.example.mymoviecataloguenew.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymoviecataloguenew.useless.Movie;
import com.example.mymoviecataloguenew.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
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

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        detailName = findViewById(R.id.tv_detail_name);
        detailDescription = findViewById(R.id.tv_detail_description);
        detailPhoto = findViewById(R.id.img_detail);
        detailDirector = findViewById(R.id.tv_detail_director);
        detailGenre = findViewById(R.id.tv_detail_genre);

        back = findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailName.setText(movie.getName() + " (" + movie.getYear() + ")");
        detailDescription.setText(movie.getDescription());
//        detailPhoto.setImageResource(movie.getPhoto());
        detailDirector.setText(": " + movie.getDirector());
        detailGenre.setText(": " + movie.getGenre());


    }
}
