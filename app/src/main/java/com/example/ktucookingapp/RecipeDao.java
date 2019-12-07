package com.example.ktucookingapp;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    Long insertRecipe(Recipe recipe);


    @Query("SELECT * FROM Recipe ORDER BY title ASC")
    List<Recipe> getAllRecipes();


    //@Query("SELECT * FROM Recipe WHERE id =:taskId")
    //LiveData<Recipe> getRecipe(int taskId);


    @Update
    void updateRecipe(Recipe recipe);


    @Delete
    void deleteRecipe (Recipe recipe);

    @Query("DELETE FROM Recipe")
    public void nukeTable();
}