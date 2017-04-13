package com.ingredient;

import android.app.Application;
import android.util.Log;

import com.ingredient.database.IngredientDAO;
import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

    }
}
