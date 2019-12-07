package com.example.ktucookingapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Entity
public class Recipe implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "imageID")
    private int imageId;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "instructions")
    private String instructions;
    @ColumnInfo(name = "ingredients")
    private List<String> ingredients;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "youtubeURL")
    private URL youtubeUrl;

    public Recipe(String title, int imageId, String description, List<String> ingredients, String difficulty, String instructions){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.ingredients = ingredients;
        this.difficulty = difficulty;
        this.instructions = instructions;
        try {
            youtubeUrl = new URL("http://youtube.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String result;
        result = "Schau dir folgendes Boss-Rezept an: " + "\n" + "\n" +
                this.title + "\n" +
                "_______________________" + "\n" + "\n" +
                this.description + "\n" +
                "Difficulty: " + this.difficulty + "\n" + "\n" +
                "All you need is: " + "\n" +
                this.ingredients.toString() + "\n" +"\n" +
                "And that´s how it works: " + "\n" +
                this.instructions + "\n" + "\n" +
                "You can also check this video here: " + "\n" +
                this.youtubeUrl.toString();

        return result;
    }

    protected Recipe(Parcel in) throws MalformedURLException {
        title = in.readString();
        imageId = in.readInt();
        description = in.readString();
        ingredients = in.createStringArrayList();
        difficulty = in.readString();
        instructions = in.readString();
        youtubeUrl = new URL(in.readString());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(imageId);
        parcel.writeString(description);
        parcel.writeStringList(ingredients);
        parcel.writeString(difficulty);
        parcel.writeString(instructions);
        parcel.writeString(youtubeUrl.toString());
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
