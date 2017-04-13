package com.ingredient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ingredient.database.DBSchema.*;


public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Ingredient.db";
//    private static final String TAG = "IngredientDBHelper";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBBuilder.CREATE_RECIPE_TABLE);
        db.execSQL(DBBuilder.CREATE_INGREDIENT_TABLE);
        db.execSQL(DBBuilder.CREATE_INGREDIENT_IN_RECIPE_TABLE);
        db.execSQL(DBBuilder.CREATE_STEP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RecipeTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IngredientTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + IngredientInRecipeTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StepsTable.NAME);
        onCreate(db);

    }

    private static class DBBuilder {
        private static final String CREATE_RECIPE_TABLE = "CREATE TABLE " + RecipeTable.NAME + "(" +
                RecipeTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RecipeTable.Cols.RECIPE_ID + " INTEGER, " +
                RecipeTable.Cols.NAME + " TEXT, " +
                RecipeTable.Cols.DESCRIPTION + " TEXT, " +
                RecipeTable.Cols.IMAGE + " TEXT, " +
                RecipeTable.Cols.FAVORITE + " INTEGER)";
        private static final String CREATE_INGREDIENT_TABLE = "CREATE TABLE " + IngredientTable.NAME + "(" +
                IngredientTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IngredientTable.Cols.INGREDIENT_ID + " INTEGER, " +
                IngredientTable.Cols.NAME + " TEXT)";

        private static final String CREATE_INGREDIENT_IN_RECIPE_TABLE = "CREATE TABLE " + IngredientInRecipeTable.NAME + "(" +
                IngredientInRecipeTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IngredientInRecipeTable.Cols.RECIPE_ID + " INTEGER, " +
                IngredientInRecipeTable.Cols.INGREDIENT_ID + " INTEGER, " +
                IngredientInRecipeTable.Cols.NAME + " TEXT, " +
                IngredientInRecipeTable.Cols.QUANTITY + " INTEGER)";
        private static final String CREATE_STEP_TABLE = "CREATE TABLE " + StepsTable.NAME + "(" +
                StepsTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StepsTable.Cols.RECIPE_ID + " INTEGER, " +
                StepsTable.Cols.DESCRIPTION + " TEXT, " +
                StepsTable.Cols.IMAGE + " TEXT)";


    }
}
