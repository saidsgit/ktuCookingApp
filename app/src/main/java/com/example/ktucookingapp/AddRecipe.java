package com.example.ktucookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddRecipe extends AppCompatActivity {

    TextView title, description;
    ImageButton uploadImage;
    Button addRecipe;
    EditText editInstructions;
    Spinner spinner;
    ListView listViewIngredients;
    List<String> addedIngredients = new ArrayList<>();
    ArrayAdapter<String> adapterIngredients;
    Button addIngredient;
    EditText ingredientInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        LinearLayout relativeLayout = findViewById(R.id.gradientBackground);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        description = (TextView) findViewById(R.id.description);
        uploadImage = (ImageButton) findViewById(R.id.recipeImage);
        addRecipe = (Button) findViewById(R.id.btnAddRecipe);
        addRecipe.setOnClickListener(addRecipeClicked);

        //Makes the edittext for the instructions scrollable
        editInstructions = (EditText) findViewById(R.id.etInstructions);
        editInstructions.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.etInstructions) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });


        //Spinner for the difficulty
        spinner = (Spinner) findViewById(R.id.spinnerDifficulty);
        final List<String> difficulties = new ArrayList<>();
        difficulties.add(0,"Choose meals difficulty");
        difficulties.add("My dog can do it");
        difficulties.add("easy");
        difficulties.add("medium");
        difficulties.add("hard");
        difficulties.add("For boss cookers only");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Listview for the ingredients
        listViewIngredients = (ListView) findViewById(R.id.listViewIngredients);

        addedIngredients.add("best");
        addedIngredients.add("cooking");
        addedIngredients.add("app");
        addedIngredients.add("ever");
        adapterIngredients = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addedIngredients);
        //ArrayAdapter<String> adapterIngredients = new ArrayAdapter<String>(this, R.layout.listitem_ingredient, addedIngredients);
        listViewIngredients.setAdapter(adapterIngredients);
        //Solving the height issue when having a ListView in a Scrollview
        ListViewUtil.setListViewHeightBasedOnChildren(listViewIngredients);

        //Add given ingredient-input to array/listview
        ingredientInput = (EditText) findViewById(R.id.etNewIngredient);
        addIngredient = (Button) findViewById(R.id.btnAddIngredient);
        addIngredient.setOnClickListener(addIngredientClick);
    }


    private View.OnClickListener addIngredientClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ingredientInput != null) {
                addedIngredients.add(ingredientInput.getText().toString());
                adapterIngredients.notifyDataSetChanged();
                ListViewUtil.setListViewHeightBasedOnChildren(listViewIngredients);
            }
        }
    };

    private View.OnClickListener addRecipeClicked = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), CookingApp.class);
            startActivity(intent);
        }
    };

}
