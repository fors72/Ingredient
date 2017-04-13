package com.ingredient;


import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    public static void setFirstLaunch(Context context, boolean firstLaunch){
        SharedPreferences sPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putBoolean("firstLaunch",firstLaunch);
        editor.apply();
    }
    public static boolean getFirstLaunch(Context context){
        SharedPreferences sPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return sPref.getBoolean("firstLaunch",true);
    }
}
