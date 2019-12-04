package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class AddRecipe extends AppCompatActivity {

    TextView title, description;
    ImageButton uploadImage;
    Button addRecipe;
    EditText editInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        LinearLayout relativeLayout = findViewById(R.id.gradientBackground);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        description = (TextView) findViewById(R.id.description);
        uploadImage = (ImageButton) findViewById(R.id.recipeImage);
        addRecipe = (Button) findViewById(R.id.btnAddRecipe);
        addRecipe.setOnClickListener(addRecipeClicked);
        editInstructions = (EditText) findViewById(R.id.etInstructions);
        editInstructions.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.etInstructions) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
    }


    private View.OnClickListener addRecipeClicked = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), CookingApp.class);
            startActivity(intent);
        }
    };
}
