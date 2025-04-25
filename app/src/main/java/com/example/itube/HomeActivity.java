package com.example.itube;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    EditText editTextYouTubeUrl;
    Button buttonPlay, buttonAddToPlaylist, buttonMyPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize UI components
        editTextYouTubeUrl = findViewById(R.id.editTextYouTubeUrl);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonAddToPlaylist = findViewById(R.id.buttonAddToPlaylist);
        buttonMyPlaylist = findViewById(R.id.buttonMyPlaylist);

        // Play button action
        buttonPlay.setOnClickListener(v -> {
            String youtubeUrl = editTextYouTubeUrl.getText().toString();
            if (!youtubeUrl.isEmpty()) {
                Intent intent = new Intent(HomeActivity.this, VideoPlayerActivity.class);
                intent.putExtra("youtube_url", youtubeUrl);
                startActivity(intent);
            }
        });

        // Add to Playlist button action
        buttonAddToPlaylist.setOnClickListener(v -> {
            String youtubeUrl = editTextYouTubeUrl.getText().toString();
            if (!youtubeUrl.isEmpty()) {
                SharedPreferences sharedPref = getSharedPreferences("Playlist", MODE_PRIVATE);
                String urls = sharedPref.getString("urls", "");
                urls = urls.isEmpty() ? youtubeUrl : urls + ";" + youtubeUrl;

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("urls", urls);
                editor.apply();
            }
        });

        // My Playlist button action
        buttonMyPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PlaylistActivity.class);
            startActivity(intent);
        });
    }
}