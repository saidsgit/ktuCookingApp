package com.example.ktucookingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class recipe implements Parcelable {
    private String title;
    private int imageId;
    private String description;

    public recipe(String title, int imageId, String description){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
    }

    protected recipe(Parcel in) {
        title = in.readString();
        imageId = in.readInt();
        description = in.readString();
    }

    public static final Creator<recipe> CREATOR = new Creator<recipe>() {
        @Override
        public recipe createFromParcel(Parcel in) {
            return new recipe(in);
        }

        @Override
        public recipe[] newArray(int size) {
            return new recipe[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(imageId);
        parcel.writeString(description);
    }
}
