package com.example.ktucookingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

public class Recipe implements Parcelable {

    private String title;
    private int imageId;
    private String description;
    private URL youtubeUrl;
    private String[] ingredients;
    private String difficulty;

    public Recipe(String title, int imageId, String description, String[] ingredients, String difficulty){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.ingredients = ingredients;
        this.difficulty = difficulty;

    }

    protected Recipe(Parcel in) {
        title = in.readString();
        imageId = in.readInt();
        description = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(imageId);
        parcel.writeString(description);
        parcel.writeString(youtubeUrl.toString());
        parcel.writeStringArray(ingredients);
    }
}
