package com.example.ktucookingapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class RecipeRepository {
    private String DB_NAME = "db_recipe";

    private RecipeDatabase recipeDatabase;

    public RecipeRepository(Context context) {
        recipeDatabase = Room.databaseBuilder(context, RecipeDatabase.class, DB_NAME).build();
    }

/*
    public void insertRecipe(final Recipe recipe) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.recipeDao().insertRecipe(recipe);
                return null;
            }
        }.execute();
    }

    public void updateRecipe(final Recipe recipe) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.recipeDao().updateRecipe(recipe);
                return null;
            }
        }.execute();
    }

    public void deleteRecipe(final int id) {
        final LiveData<Recipe> task = getTask(id);
        if(task != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    recipeDatabase.recipeDao().deleteRecipe(task.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public void deleteRecipe(final Recipe recipe) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                recipeDatabase.recipeDao().deleteRecipe(recipe);
                return null;
            }
        }.execute();
    }


    public LiveData<Recipe> getTask(int id) {
        return recipeDatabase.recipeDao().getRecipe(id);
    }

    public LiveData<List<Recipe>> getTasks() {
        return recipeDatabase.recipeDao().fetchAllTasks();
    }
*/
}
