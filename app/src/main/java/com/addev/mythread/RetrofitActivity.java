package com.addev.mythread;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.addev.mythread.adapter.CastAdapter;
import com.addev.mythread.model.MovieResponse;
import com.addev.mythread.viewmodel.DetailViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

public class RetrofitActivity extends AppCompatActivity {

    ImageView imgView;
    TextView tvSynopsis, tvLength, tvDate;
    private DetailViewModel detailViewModel;
    RecyclerView recyclerView;
    String urlImage;
    private CastAdapter adapter;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_detail);
        tvSynopsis = findViewById(R.id.text_description);
        tvDate = findViewById(R.id.text_date);
        tvLength = findViewById(R.id.text_length);
        imgView = findViewById(R.id.image_poster);
        recyclerView = findViewById(R.id.rv_cast);
        progressBar = findViewById(R.id.progress_bar);

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        detailViewModel.getDetail().observe(this, getMovie);
        showLoading(true);
        detailViewModel.setDetailMovie(550);
        adapter = new CastAdapter(this);
    }

    private final Observer<MovieResponse> getMovie = new Observer<MovieResponse>() {
        @Override
        public void onChanged(MovieResponse movie) {
            if (movie != null) {
                showLoading(false);
                tvSynopsis.setText(movie.getMovie().getDescription());
                tvDate.setText(movie.getMovie().getReleaseDate());
                tvLength.setText(movie.getMovie().getLength());
                urlImage = movie.getMovie().getPoster();
                setImgView(urlImage);
                adapter.setListCasts(movie.getCast());
                setRecyclerCast();
                getSupportActionBar().setTitle(movie.getMovie().getTitle());
            }
        }
    };

    public void setImgView(String urlImage) {
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + urlImage)
                .transform(new CenterCrop())
                .into(imgView);
    }

    public void setRecyclerCast() {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

}
