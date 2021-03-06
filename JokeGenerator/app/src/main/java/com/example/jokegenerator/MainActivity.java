package com.example.jokegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView jokeTextBox;
    private Button jokeButton;
    private String joke;
    private ArrayList<String> jokesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeButton = findViewById(R.id.getJokeButton);
        jokesList = new ArrayList<String>();
    }

    public void retrieveJoke(View view) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call<Joke> call = api.getJoke();

        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                joke = response.body().getJoke();

                jokeTextBox = findViewById(R.id.jokeTextBox);
                jokeTextBox.setMovementMethod(new ScrollingMovementMethod());
                jokeTextBox.setText(joke);

                jokesList.add(joke);
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Internet connection required to generate joke", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToJokeHistory(View view) {
        Intent intent = new Intent(MainActivity.this, SearchHistoryActivity.class);
        intent.putStringArrayListExtra("jokesList", jokesList);

        startActivity(intent);
    }

    public List<String> getJokesList() {
        return jokesList;
    }
}
