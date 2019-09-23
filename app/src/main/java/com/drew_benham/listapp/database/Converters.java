package com.drew_benham.listapp.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Converters {
    @TypeConverter
    public static HashMap<String, List<String>> hashFromString(String value) {
        Type listType = new TypeToken<HashMap<String, List<String>>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromHashmap(HashMap<String, List<String>> hashMap) {
        Gson gson = new Gson();
        return gson.toJson(hashMap);
    }

    @TypeConverter
    public static Date dateFromString(String value) {
        Type dateType = new TypeToken<Date>(){}.getType();
        return new Gson().fromJson(value, dateType);
    }

    @TypeConverter
    public static String fromDateType(Date date) {
        Gson gson = new Gson();
        return gson.toJson(date);
    }
}
