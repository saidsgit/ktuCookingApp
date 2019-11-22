package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CookingApp extends AppCompatActivity {

    ListView listV;
    private ListAdapter adapter;
    Button btnAdd;
    Button btnTrans;
    Button btnSort;
    public List<recipe> recipes = MainActivity.recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_app);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(btnAddClick);
        btnTrans = (Button) findViewById(R.id.btnTrans);
        btnTrans.setOnClickListener(btnTransClick);
        btnTrans.setVisibility(View.INVISIBLE);
        btnSort = (Button) findViewById(R.id.btnSort);
        btnSort.setOnClickListener(btnSortClick);

        listV = (ListView) findViewById(R.id.listV);

        adapter = new ListAdapter(this, recipes);
        listV.setAdapter(adapter);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CookingApp.this, certainRecipe.class);
                // name, description and image get transfered
                intent.putExtra("name", recipes.get(i).getTitle());
                intent.putExtra("description", recipes.get(i).getDescription());
                intent.putExtra("imageID", recipes.get(i).getImageId());

                startActivity(intent);
            }
        });

        listV.setOnItemLongClickListener(itemLongClick);
    }

    private AdapterView.OnItemLongClickListener itemLongClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            recipes.remove(i);
            adapter.notifyDataSetChanged();
            return false;
        }
    };

    private View.OnClickListener btnAddClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recipes.add(new recipe("Steak", R.drawable.fleisch1, "Making best Steak ever"));
            //adapter.notifyDataSetChanged();

            Intent intent = new Intent(getBaseContext(), AddRecipe.class);
            startActivity(intent);
        }

    };

    //public void addRecipe(String title,)

    private View.OnClickListener btnSortClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Collections.sort(recipes, new Comparator<recipe>() {
                @Override
                public int compare(recipe r1, recipe r2) {
                    return r1.getTitle().compareTo(r2.getTitle());
                }
            });
            adapter.notifyDataSetChanged();
        }
    };

    private View.OnClickListener btnTransClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), ListEdit.class);
            intent.putParcelableArrayListExtra("array", (ArrayList<? extends Parcelable>) recipes);
            startActivity(intent);
        }
    };



}
