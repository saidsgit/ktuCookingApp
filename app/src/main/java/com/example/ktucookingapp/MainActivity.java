package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnCookingApp, btnLabWork;
    private TextView tfStartMessage;
    public static List<Recipe> recipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCookingApp = (Button) findViewById(R.id.btnCookingApp);
        btnLabWork = (Button) findViewById(R.id.btnLabWork);
        tfStartMessage = (TextView) findViewById(R.id.tfStartMessage);

        btnCookingApp.setOnClickListener(btnCookingAppClick);
        btnLabWork.setOnClickListener(btnLabWorkClick);

        recipes = new ArrayList<>();
//max ist ein netter hs
        recipes.add(new Recipe("Steak", R.drawable.fleisch1, "Making best Steak ever", xyz, "easy"));
        recipes.add(new Recipe("pancakes", R.drawable.pfannekuchen, "Making best pancakes ever"));
        recipes.add(new Recipe("Spaghetti Carbonara", R.drawable.spaghetti_carbonara, "Making best Spaghetti Cabonara ever"));
        recipes.add(new Recipe("Lasagne", R.drawable.lasagne, "Making best Lasagne ever"));

    }

    private View.OnClickListener btnCookingAppClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Intro.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener btnLabWorkClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LabOverview.class);
                    startActivity(intent);
        }
    };
}
