package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;

public class certainRecipe extends AppCompatActivity {
    private TextView tfFoodname, tfdescription, tvDiff, tvIngredients, tvInstructions;
    private ImageView foodpic;
    private ImageButton btnYoutube, btnShare;
    private URL youtube;


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
        btnShare = (ImageButton) findViewById(R.id.btnYoutubeCertainRecipe);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            Recipe recipe = (Recipe) bundle.getParcelable("recipe");
            tfFoodname.setText(recipe.getTitle());
            tfdescription.setText(recipe.getDescription());
            foodpic.setImageResource(recipe.getImageId());
            tvDiff.setText("Difficulty: " + recipe.getDifficulty());
            tfdescription.setText(recipe.getDescription());
            tvInstructions.setText(recipe.getInstructions());
            tvIngredients.setText(recipe.getIngredients().toString());
        }

    }
}
