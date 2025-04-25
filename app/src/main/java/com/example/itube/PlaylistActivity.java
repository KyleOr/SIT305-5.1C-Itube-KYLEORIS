package com.example.itube;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class PlaylistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlaylistAdapter playlistAdapter;
    ArrayList<String> playlistUrls = new ArrayList<>();
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        // Initialize RecyclerView and Button
        recyclerView = findViewById(R.id.recyclerView);
        buttonBack = findViewById(R.id.buttonBack);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load playlist from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("Playlist", MODE_PRIVATE);
        String urls = sharedPref.getString("urls", "");
        if (!urls.isEmpty()) {
            String[] urlArray = urls.split(";");
            Collections.addAll(playlistUrls, urlArray); // Bulk add
        }

        // Initialize the adapter
        playlistAdapter = new PlaylistAdapter(playlistUrls);
        recyclerView.setAdapter(playlistAdapter);

        // Back button action using lambda
        buttonBack.setOnClickListener(v -> finish());
    }
}