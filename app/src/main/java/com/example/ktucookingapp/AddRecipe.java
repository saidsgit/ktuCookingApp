package com.example.ktucookingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddRecipe extends AppCompatActivity {

    TextView title;
    ImageButton uploadImage;
    Button addRecipe;
    EditText editInstructions, editYoutube;
    Spinner spinner;
    static ListView listViewIngredients;
    static final List<String> addedIngredients = new ArrayList<>();
    ArrayAdapter<String> adapterIngredients;
    Button addIngredient;
    EditText ingredientInput, description;
    int imageID =R.drawable.intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        //Gradient backround with animation
        LinearLayout relativeLayout = findViewById(R.id.gradientBackground);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        title = (EditText) findViewById(R.id.editTextTitle);
        description = (EditText) findViewById(R.id.editTextShortDescription);
        uploadImage = (ImageButton) findViewById(R.id.recipeImage);
        editYoutube = (EditText) findViewById(R.id.editTextURL);
        addRecipe = (Button) findViewById(R.id.btnAddRecipe);
        addRecipe.setOnClickListener(addRecipeClicked);
        imageID = -1;

        uploadImage.setOnClickListener(uploadImageClick);

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

        /*addedIngredients.add("best");
        addedIngredients.add("cooking");
        addedIngredients.add("app");
        addedIngredients.add("ever");*/
        //adapterIngredients = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addedIngredients);
        //adapterIngredients = new ArrayAdapter<String>(this, R.layout.listitem_ingredient, R.id.tvAddedIngredient, addedIngredients);
        adapterIngredients = new ListAdapterIngredient(this, addedIngredients);
        listViewIngredients.setAdapter(adapterIngredients);


        //Solving the height issue when having a ListView in a Scrollview
        ListViewUtil.setListViewHeightBasedOnChildren(listViewIngredients);

        //Add given ingredient-input to array/listview
        ingredientInput = (EditText) findViewById(R.id.etNewIngredient);
        addIngredient = (Button) findViewById(R.id.btnAddIngredient);
        addIngredient.setOnClickListener(addIngredientClick);
    }



    private static int RESULT_LOAD_IMAGE = 1;

    private View.OnClickListener uploadImageClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Permissions p = new Permissions();
            p.check(getApplicationContext()/*context*/, Manifest.permission.READ_EXTERNAL_STORAGE, null, new PermissionHandler() {
                @Override
                public void onGranted() {
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.ivUploadedImage);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

    private View.OnClickListener addIngredientClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String input = ingredientInput.getText().toString();

            if(input.length() > 0) {
                addedIngredients.add(input);
                ingredientInput.setText("");
                adapterIngredients.notifyDataSetChanged();
                ListViewUtil.setListViewHeightBasedOnChildren(listViewIngredients);
            }
        }
    };

    private View.OnClickListener addRecipeClicked = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(title.getText().toString().length() < 1) {
                Toast toast = Toast.makeText(getApplicationContext(), "No title, no recipe", Toast.LENGTH_LONG);
                toast.show();
                return;
            }

            if(description.getText().toString().length() < 1) {
                Toast toast2 = Toast.makeText(getApplicationContext(), "No description, no recipe", Toast.LENGTH_SHORT);
                toast2.show();
                return;
            }

            //Add recipe
            String newTitle = title.getText().toString();
            String newShortDescription = description.getText().toString();
            List<String> ingredients = addedIngredients;
            String newInstructions = editInstructions.getText().toString();
//            imageID = uploadImage.getSourceLayoutResId();
            String newUrl = editYoutube.getText().toString();

            //Check and ask user for empty fields
            /*if(addedIngredients.size() == 0 || newInstructions.length() < 1 || imageID == -1 ||
                    newUrl.length() < 1 || spinner.getSelectedItemId() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Warning!");
                builder.setMessage("You didn´t fill all! You want to progress?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                builder.show();
            }*/

            if(URLUtil.isValidUrl(newUrl)==false) {
                /*if(newUrl.length() < 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Warning!");
                    builder.setMessage("No youtube link! You want to progress?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });

                    builder.show();
                }
                else {*/
                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong URL, bro", Toast.LENGTH_SHORT);
                    toast.show();
                    //return;
                //}

            }
            String newDiff = spinner.getSelectedItem().toString();
            if(newDiff == "Choose meals difficulty") {newDiff = "unranked";}
            Recipe newRecipe = new Recipe(newTitle, R.drawable.said, newShortDescription, ingredients, newDiff, newInstructions);

            CookingApp.addRecipe(newRecipe);


            //Clear screen
            title.setText("");
            description.setText("");
            addedIngredients.clear();
            adapterIngredients.notifyDataSetChanged();
            ListViewUtil.setListViewHeightBasedOnChildren(listViewIngredients);
            editInstructions.setText("");
            editYoutube.setText("");
            spinner.setSelection(0);
            imageID = R.drawable.intro;

            Intent intent = new Intent(getBaseContext(), CookingApp.class);
            startActivity(intent);
        }
    };

}
