package com.example.ktucookingapp;

import android.app.Application;

import androidx.room.Room;


public class AppActivity extends Application {

    static RecipeDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                RecipeDatabase.class,
                "my_app_db")
                .allowMainThreadQueries()
                .build();
    }

    public static RecipeDatabase getDatabase() {
        return db;
    }
}
