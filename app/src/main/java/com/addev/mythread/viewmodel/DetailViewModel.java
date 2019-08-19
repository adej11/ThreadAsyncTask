package com.addev.mythread.viewmodel;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.addev.mythread.BuildConfig;
import com.addev.mythread.model.Movie;
import com.addev.mythread.model.MovieResponse;
import com.addev.mythread.rest.ApiClient;
import com.addev.mythread.rest.ApiInterface;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {

    private final MutableLiveData<MovieResponse> listMovie = new MutableLiveData<>();

   public void setDetailMovie(int id) {
        ApiInterface mApiInterface;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        try {
            Call<String> authorized = mApiInterface.getDetailMovie(id, BuildConfig.ApiKey, "en-US");

            authorized.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                    if (response.isSuccessful()) {

                        try {
                            JSONObject responseObject = new JSONObject(response.body());
                            JSONObject movie = responseObject;
                            MovieResponse movies = new MovieResponse(movie);
                            listMovie.postValue(movies);
                        } catch (Exception e) {
                            Log.d("Exception", e.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {

                }
            });
        } catch (Exception e) {
            System.out.println("error" + e);
        }

    }

   public LiveData<MovieResponse> getDetail() {
        return listMovie;
    }
}
