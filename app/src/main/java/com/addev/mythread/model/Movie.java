package com.addev.mythread.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    private int id;
    private String type;
    private String title;
    private String releaseDate;
    private String description;
    private String poster;
    private String popularity;
    private String rating;
    private String length;

    public Movie() {

    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public Movie(JSONObject object) {
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd, mm, yyyy");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            int id = object.getInt("id");
            String description = object.getString("overview");
            String popularity = object.getString("popularity");
            String release = object.getString("release_date");
            String title = object.getString("title");
            String url_image = object.getString("poster_path");
            String vote = object.getString("vote_average");
            String length = object.getString("runtime");

            this.id = id;
            this.description = description;
            this.popularity = popularity;
            this.releaseDate = formatter.format(dateFormat.parse(release));
            this.title = title;
            this.poster = url_image;
            this.rating = vote;
            this.length = length;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected Movie(Parcel in) {
        id = in.readInt();
        type = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        description = in.readString();
        poster = in.readString();
        popularity = in.readString();
        rating = in.readString();
        length = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(description);
        dest.writeString(poster);
        dest.writeString(popularity);
        dest.writeString(rating);
        dest.writeString(length);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
