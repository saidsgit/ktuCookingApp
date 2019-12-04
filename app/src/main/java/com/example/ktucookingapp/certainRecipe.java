package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.textservice.TextInfo;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;

public class certainRecipe extends AppCompatActivity {
    private TextView tfFoodname, tfdescription;
    private ImageView foodpic;
    private URL youtube;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certain_recipe);

        tfFoodname = (TextView) findViewById(R.id.tfFoodname);
        tfdescription = (TextView) findViewById(R.id.tfdescription);
        foodpic = (ImageView) findViewById(R.id.foodpic);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            Recipe recipe = (Recipe) bundle.getParcelable("recipe");
            tfFoodname.setText(recipe.getTitle());
            tfdescription.setText(recipe.getDescription());
            foodpic.setImageResource(recipe.getImageId());
            /*tfFoodname.setText(bundle.getString("name"));
            tfdescription.setText(bundle.getString("description"));
            foodpic.setImageResource(bundle.getInt("imageID"));*/
        }

    }
}
