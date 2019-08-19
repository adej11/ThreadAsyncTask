package com.addev.mythread.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.addev.mythread.R;
import com.addev.mythread.model.Cast;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private final Activity activity;
    private List<Cast> mCasts = new ArrayList<>();

    public CastAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<Cast> getListMovies() {
        return mCasts;
    }

    public void setListCasts(List<Cast> listCasts) {
        if (listCasts == null) return;
        this.mCasts.clear();
        this.mCasts.addAll(listCasts);
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cast, parent, false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CastAdapter.CastViewHolder holder, int position) {
        holder.textViewStars.setText(getListMovies().get(position).getName());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" +getListMovies().get(position).getPhoto())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    class CastViewHolder extends RecyclerView.ViewHolder {

        final ImageView imgPoster;
        final TextView textViewStars;

        CastViewHolder(View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster);
            textViewStars = itemView.findViewById(R.id.textView);

        }
    }
}
