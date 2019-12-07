package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CookingApp extends AppCompatActivity {

    public static RecipeDatabase mDB;
    public static ListView listV;
    private static ListAdapter adapter;
    Button btnAdd;
    Button btnTrans;
    Button btnSort;
    public static List<Recipe> recipes;
    public static int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_app);

        mDB = AppActivity.getDatabase();
        recipes = mDB.recipeDao().getAllRecipes();
        if(recipes.size()<2) {
            mDB.recipeDao().nukeTable();
            this.addStartRecipesDB();
        }

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(btnAddClick);
        //btnTrans = (Button) findViewById(R.id.btnTrans);
        //btnTrans.setOnClickListener(btnTransClick);
        //btnTrans.setVisibility(View.INVISIBLE);
        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(btnSortClick);

        listV = (ListView) findViewById(R.id.listV);

        adapter = new ListAdapter(this, recipes);
        listV.setAdapter(adapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                //Clicked Recipe gets transfered
                Intent intent = new Intent(CookingApp.this, certainRecipe.class);
                intent.putExtra("recipe", recipes.get(i));

                startActivity(intent);
            }
        });

//        listV.setOnItemLongClickListener(itemLongClick);
    }

    public static void addRecipe(Recipe r) {
//        recipes.add(r);
        mDB.recipeDao().insertRecipe(r);
        recipes = mDB.recipeDao().getAllRecipes();
        adapter.notifyDataSetChanged();
    }

//    private AdapterView.OnItemLongClickListener itemLongClick = new AdapterView.OnItemLongClickListener() {
//        @Override
//        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//            recipes.remove(i);
//            adapter.notifyDataSetChanged();
//            return false;
//        }
//    };

    private View.OnClickListener btnAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //recipes.add(new Recipe("Steak", R.drawable.fleisch1, "Making best Steak ever"));
            //adapter.notifyDataSetChanged();

            Intent intent = new Intent(getBaseContext(), AddRecipe.class);
            startActivity(intent);
        }

    };

    //public void addRecipe(String title,)

    private View.OnClickListener btnSortClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Collections.sort(recipes, new Comparator<Recipe>() {
                @Override
                public int compare(Recipe r1, Recipe r2) {
                    return r1.getTitle().compareTo(r2.getTitle());
                }
            });
            adapter.notifyDataSetChanged();
        }
    };

    public static void updateListView() {
//        Intent intent = new Intent(CookingApp.this, CookingApp.class);
//        finish();
//        startActivity(intent);
        recipes = mDB.recipeDao().getAllRecipes();
        adapter.notifyDataSetChanged();

    }

    private void addStartRecipesDB(){
        List<String> ingredients = Arrays.asList("Steak", "Salz");
        mDB.recipeDao().insertRecipe(new Recipe("Steak", R.drawable.fleisch1, "Making best Steak ever", ingredients, "easy", "Einfach machen blyat"));
        List<String> ingredients2 = Arrays.asList("x", "y");
        mDB.recipeDao().insertRecipe(new Recipe("Pancakes", R.drawable.pfannekuchen, "Making best pancakes ever", ingredients2, "easy", "Einfach machen blyat"));
        List<String> ingredients3 = Arrays.asList("Spaghetti", "Cream", "x");
        Recipe spaghetti = new Recipe("Spaghetti Carbonara", R.drawable.spaghetti_carbonara, "Making best Spaghetti Cabonara ever", ingredients3, "easy", "Einfach machen blyat");
        try {
            spaghetti.setYoutubeUrl(new URL("https://www.youtube.com/watch?v=rKOriaxRpI0&t=1s"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mDB.recipeDao().insertRecipe(spaghetti);
        List<String> ingredients4 = Arrays.asList("Minced Beef", "Cheese", "x");
        mDB.recipeDao().insertRecipe(new Recipe("Lasagne", R.drawable.lasagne, "Making best Lasagne ever", ingredients4, "medium", "Einfach machen blyat"));
    }

    /*private View.OnClickListener btnTransClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), ListEdit.class);
            intent.putParcelableArrayListExtra("array", (ArrayList<? extends Parcelable>) recipes);
            startActivity(intent);
        }
    };*/



}
