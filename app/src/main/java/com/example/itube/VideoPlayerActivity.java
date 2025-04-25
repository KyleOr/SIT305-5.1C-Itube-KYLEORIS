package com.example.itube;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String TAG = "VideoPlayerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youTubePlayerView);
        getLifecycle().addObserver(youTubePlayerView); // lifecycle-aware

        String youtubeUrl = getIntent().getStringExtra("youtube_url");

        if (youtubeUrl != null) {
            String videoId = extractVideoIdFromUrl(youtubeUrl);
            if (videoId != null) {
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            } else {
                Log.e(TAG, "Invalid YouTube URL");
            }
        }
    }

    private String extractVideoIdFromUrl(String url) {
        try {
            String[] parts = url.split("v=");
            if (parts.length > 1) {
                String idPart = parts[1];
                int ampIndex = idPart.indexOf('&');
                return (ampIndex != -1) ? idPart.substring(0, ampIndex) : idPart;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error extracting video ID", e);
        }
        return null;
    }
}
