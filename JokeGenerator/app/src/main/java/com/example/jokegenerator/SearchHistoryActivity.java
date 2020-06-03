package com.example.jokegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchHistoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> jokesHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_list);

        listView = (ListView)findViewById(R.id.jokeList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        jokesHistory = getIntent().getStringArrayListExtra("jokesList");

        for (String joke : jokesHistory) {
            adapter.add(joke);
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String joke = adapter.getItem(i);

        Intent intent = new Intent(SearchHistoryActivity.this, JokeViewActivity.class);
        intent.putExtra("joke", joke);

        startActivity(intent);
    }
}