package com.example.itube;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayerActivity extends YouTubeBaseActivity {

    private static final String TAG = "VideoPlayerActivity";
    private static final String YOUTUBE_API_KEY = "AIzaSyDfYqySHt8-t5OD3nOQcZ9tUUKeHkL5XpE"; // Add your API key here
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        // Initialize YouTubePlayerView
        youTubePlayerView = findViewById(R.id.youTubePlayerView);

        // Get the YouTube video ID from the intent
        String youtubeUrl = getIntent().getStringExtra("youtube_url");

        if (youtubeUrl != null) {
            // Extract video ID from YouTube URL
            String videoId = extractVideoIdFromUrl(youtubeUrl);
            if (videoId != null) {
                initializeYouTubePlayer(videoId);
            } else {
                Log.e(TAG, "Invalid YouTube URL");
                Toast.makeText(this, "Invalid YouTube URL", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Extract video ID from YouTube URL
    private String extractVideoIdFromUrl(String youtubeUrl) {
        String videoId = null;
        if (youtubeUrl.contains("youtube.com")) {
            String[] urlParts = youtubeUrl.split("v=");
            if (urlParts.length > 1) {
                videoId = urlParts[1];
            }
        }
        return videoId;
    }

    // Initialize the YouTube player
    private void initializeYouTubePlayer(final String videoId) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    // Load the video using the extracted video ID
                    player.loadVideo(videoId);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
                Toast.makeText(VideoPlayerActivity.this, "Failed to initialize YouTube player: " + errorReason.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
