package com.example.jokegenerator;

import com.google.gson.annotations.SerializedName;

public class Joke {

    @SerializedName("joke")
    private String joke;

    public Joke() {

    }

    public Joke(String joke) {
        this.joke = joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    @Override
    public String toString() {
        return "Joke: " + joke;
    }
}
