package com.addev.mythread.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse implements Parcelable {
    private List<Cast> cast;
    private Movie movie;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public static Creator<MovieResponse> getCREATOR() {
        return CREATOR;
    }

    protected MovieResponse(Parcel in) {
        movie = in.readParcelable(Movie.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(movie, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

    public MovieResponse(JSONObject object) {
        try {

            JSONObject objMovie = object;
            Movie movie = new Movie(objMovie);
            this.movie = movie;

            JSONObject credit = object.getJSONObject("credits");
            JSONArray listCast = credit.getJSONArray("cast");

            final ArrayList<Cast> listCasts = new ArrayList<>();
            for (int i = 0; i < listCast.length(); i++) {
                JSONObject castObject = listCast.getJSONObject(i);
                Cast cast = new Cast(castObject);
                listCasts.add(cast);
            }
            this.cast = listCasts;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
