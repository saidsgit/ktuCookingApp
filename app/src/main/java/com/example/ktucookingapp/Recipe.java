package com.example.ktucookingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.MalformedURLException;
import java.net.URL;

public class Recipe implements Parcelable {

    private String title;
    private int imageId;
    private String description;
    private String[] ingredients;


    private String difficulty;
    private URL youtubeUrl;

    public Recipe(String title, int imageId, String description, String[] ingredients, String difficulty){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.ingredients = ingredients;
        this.difficulty = difficulty;
        //this.youtubeUrl = new URL(difficulty);
    }

    protected Recipe(Parcel in) throws MalformedURLException {
        title = in.readString();
        imageId = in.readInt();
        description = in.readString();
        ingredients = in.createStringArray();
        difficulty = in.readString();
        if(youtubeUrl != null) {youtubeUrl = new URL(in.readString());}
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            try {
                return new Recipe(in);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getYoutubeUrl() { return youtubeUrl; }

    public void setYoutubeUrl(URL youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(imageId);
        parcel.writeString(description);
        parcel.writeStringArray(ingredients);
        parcel.writeString(difficulty);
        if(youtubeUrl != null) {parcel.writeString(youtubeUrl.toString());}
    }
}
