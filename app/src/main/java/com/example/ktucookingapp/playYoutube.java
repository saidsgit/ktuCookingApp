package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class playYoutube extends AppCompatActivity {

    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener mOnInitialzedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_youtube);

        playerView = (YouTubePlayerView) findViewById(R.id.youtubeview123);

        mOnInitialzedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("JZVWkjFGCBI&t");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "hmm, link isn´t working", Toast.LENGTH_SHORT);
            }
        };

                playerView.initialize(YoutubeConfig.getApiKey(), mOnInitialzedListener);
    }
}
