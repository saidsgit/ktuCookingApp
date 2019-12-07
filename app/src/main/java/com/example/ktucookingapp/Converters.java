package com.example.ktucookingapp;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String listToString(List<String> s) {
        String r = s.toString();
        return r;
    }

    @TypeConverter
    public static List<String> StringToList(String s) {
        s = s.replace("[","").replace("]","");
        List<String> result = Arrays.asList(s.split(","));
        return result;
    }

    @TypeConverter
    public static String urlToString(URL u) {
        String r = u.toString();
        return r;
    }

    @TypeConverter
    public static URL stringToUrl(String s) {
        URL r = null;
        try {
            r = new URL(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return r;
    }
}
