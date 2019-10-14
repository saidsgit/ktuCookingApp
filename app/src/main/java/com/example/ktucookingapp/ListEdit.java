package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListEdit extends AppCompatActivity {

    ListView transListV;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);

        transListV = (ListView) findViewById(R.id.transListV);

        Intent intent = getIntent();

        final ArrayList recipesCopy = intent.getIntegerArrayListExtra("array");


        adapter = new ListAdapter(this, recipesCopy);
        transListV.setAdapter(adapter);

    }
}
