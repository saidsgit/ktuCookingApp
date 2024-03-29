package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.textservice.TextInfo;
import android.widget.ImageView;
import android.widget.TextView;

public class certainRecipe extends AppCompatActivity {
    private TextView tfFoodname, tfdescription;
    private ImageView foodpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_recipe);

        tfFoodname = (TextView) findViewById(R.id.tfFoodname);
        tfdescription = (TextView) findViewById(R.id.tfdescription);
        foodpic = (ImageView) findViewById(R.id.foodpic);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            tfFoodname.setText(bundle.getString("name"));
            tfdescription.setText(bundle.getString("description"));
            foodpic.setImageResource(bundle.getInt("imageID"));
        }

    }
}
