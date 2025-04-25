package com.example.itube;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
            for (String url : urlArray) {
                playlistUrls.add(url);
            }
        }

        // Initialize the adapter
        playlistAdapter = new PlaylistAdapter(playlistUrls);
        recyclerView.setAdapter(playlistAdapter);

        // Back button action
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to HomeActivity
            }
        });
    }
}
