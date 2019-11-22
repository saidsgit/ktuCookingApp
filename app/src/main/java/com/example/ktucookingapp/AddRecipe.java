package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddRecipe extends AppCompatActivity {

    TextView title, description;
    ImageButton uploadImage;
    Button addRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        LinearLayout relativeLayout = findViewById(R.id.gradientBackground);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        title = (TextView) findViewById(R.id.tvTitle);
        description = (TextView) findViewById(R.id.description);
        uploadImage = (ImageButton) findViewById(R.id.recipeImage);
        addRecipe = (Button) findViewById(R.id.btnAddRecipe);

        addRecipe.setOnClickListener(addRecipeClicked);
    }


    private View.OnClickListener addRecipeClicked = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), CookingApp.class);
            startActivity(intent);
        }
    };
}
