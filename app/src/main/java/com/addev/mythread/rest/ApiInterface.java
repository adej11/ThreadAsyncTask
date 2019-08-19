package com.addev.mythread.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/{id}?append_to_response=videos,credits,reviews")
    Call<String> getDetailMovie(@Path("id")int id,@Query("api_key") String apiKey,
                                @Query("language") String language);

}
