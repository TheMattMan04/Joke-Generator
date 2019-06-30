package com.example.jokegenerator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Api {

    String URL = "https://icanhazdadjoke.com/";

    @Headers("accept: application/json")
    @GET(URL)
    Call<Joke> getJoke();
}
