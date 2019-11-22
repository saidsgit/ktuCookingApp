package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                sleep(3500);
                Intent intent = new Intent(Intro.this, CookingApp.class);
                startActivity(intent);
                finish();
            }
                catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };
        thread.start();
    }
}
