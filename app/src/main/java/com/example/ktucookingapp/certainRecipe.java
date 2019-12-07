package com.example.ktucookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.net.URL;

public class certainRecipe extends YouTubeBaseActivity {
    private TextView tfFoodname, tfdescription, tvDiff, tvIngredients, tvInstructions;
    private ImageView foodpic;
    private ImageButton btnYoutube, btnShare;
    private URL youtube;
    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener mOnInitialzedListener;
    Recipe certainRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_recipe);

        tfFoodname = (TextView) findViewById(R.id.tfFoodname);
        tfdescription = (TextView) findViewById(R.id.tfdescription);
        foodpic = (ImageView) findViewById(R.id.foodpic);
        tvDiff = (TextView) findViewById(R.id.tvDiff);
        tvIngredients = (TextView) findViewById(R.id.tvIngredients);
        tvInstructions = (TextView) findViewById(R.id.tvInstructions);
        btnShare = (ImageButton) findViewById(R.id.btnShareCertainRecipe);
        btnYoutube = (ImageButton) findViewById(R.id.btnYoutubeCertainRecipe);
        playerView = (YouTubePlayerView) findViewById(R.id.youtubeview123);

        mOnInitialzedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("rKOriaxRpI0&t");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "hmm, link isn´t working", Toast.LENGTH_SHORT);
            }
        };

        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerView.initialize(YoutubeConfig.getApiKey(), mOnInitialzedListener);
//                Intent intent = new Intent(getBaseContext(), playYoutube.class);
//                startActivity(intent);

            }
        });



        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            certainRecipe = (Recipe) bundle.getParcelable("recipe");
            tfFoodname.setText(certainRecipe.getTitle());
            tfdescription.setText(certainRecipe.getDescription());
            foodpic.setImageResource(certainRecipe.getImageId());
            tvDiff.setText("Difficulty: " + certainRecipe.getDifficulty());
            tfdescription.setText(certainRecipe.getDescription());
            tvInstructions.setText(certainRecipe.getInstructions());
            tvIngredients.setText(certainRecipe.getIngredients().toString());
        }

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = certainRecipe.toString();
//                String shareSub = "Your youtube link is still hidden, bitch!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
        });
    }
}
