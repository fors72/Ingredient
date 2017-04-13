package com.ingredient.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.ingredient.database.DBSchema.*;
import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;
import com.ingredient.objects.recipeModel.Step;

import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    public static IngredientDAO ingredientDAO;
    private SQLiteDatabase ingrDB;

    private IngredientDAO(Context context) {
        ingrDB = new DBHelper(context).getWritableDatabase();
    }

    public static IngredientDAO getInstance(Context context){
        if (ingredientDAO == null){
            ingredientDAO = new IngredientDAO(context);
        }
        return ingredientDAO;
    }
    public IngredientCursorWrapper getIngredientByKey(String key,List<Ingredient> ingredients){
        String query = "SELECT * FROM " + IngredientTable.NAME + " WHERE " + IngredientTable.Cols.NAME + " LIKE '%" + key + "%'";
        for (int i = 0;i < ingredients.size();i++){
            query += " AND " + IngredientTable.Cols.NAME + "<> '" + ingredients.get(i).getName() + "'";
        }
        return new IngredientCursorWrapper(ingrDB.rawQuery(query,null));
    }

    public List<Recipe> getRecipeIdsByIngredients(List<Ingredient> ingredients,boolean favorite){
        List<Recipe> recipes = new ArrayList<>();
//        String query = "SELECT * FROM " + RecipeTable.NAME +
//                " JOIN " + IngredientInRecipeTable.NAME +
//                " ON " + IngredientInRecipeTable.NAME + "." + IngredientInRecipeTable.Cols.RECIPE_ID + "=" + RecipeTable.NAME + "." + RecipeTable.Cols.RECIPE_ID +
//                " JOIN " + IngredientTable.NAME +
//                " ON " + IngredientTable.NAME + "." + IngredientTable.Cols.INGREDIENT_ID + "=" + IngredientInRecipeTable.NAME + "." + IngredientInRecipeTable.Cols.INGREDIENT_ID +
//                " WHERE";
//        for (int i = 0;i < ingredients.size();i++){
//            query += " " + IngredientTable.NAME + "." + IngredientTable.Cols.NAME + " LIKE '" + ingredients.get(i) + "%'";
//            if (i != ingredients.size()-1){
//                query += " AND";
//            }else {
//                query += " GROUP BY " + RecipeTable.NAME + "." + RecipeTable.Cols.RECIPE_ID;
//            }
//        }
        String query2 = "SELECT " + RecipeTable.NAME + ".* FROM " + RecipeTable.NAME +
                " JOIN " + IngredientInRecipeTable.NAME +
                " ON " + RecipeTable.NAME + "." + RecipeTable.Cols.RECIPE_ID + "=" + IngredientInRecipeTable.NAME + "." + IngredientInRecipeTable.Cols.RECIPE_ID +
                " AND " + IngredientInRecipeTable.NAME + "." + IngredientInRecipeTable.Cols.NAME + " in('";
        for (int i = 0;i < ingredients.size();i++){
            query2 += ingredients.get(i).getName();
            if (i != ingredients.size()-1){
                query2 += "', '";
            }
        }
        query2 += "') " + (favorite ? "AND recipe.favorite = 1":"") + " GROUP BY " + RecipeTable.NAME + "." + RecipeTable.Cols.RECIPE_ID + " HAVING count(DISTINCT " + IngredientInRecipeTable.NAME + "." + IngredientInRecipeTable.Cols.NAME + ")=" + ingredients.size();


        IngredientCursorWrapper cursorWrapper = new IngredientCursorWrapper(ingrDB.rawQuery(query2,null));
        try {
            if (cursorWrapper.getCount() != 0) {
                cursorWrapper.moveToFirst();
                while (!cursorWrapper.isAfterLast()){
                    Recipe recipe = cursorWrapper.getRecipe();
                    recipe.setIngredients(getIngredientListByRecipeId(recipe.getId()));
                    recipe.setSteps(getStepListByRecipeId(recipe.getId()));
                    recipes.add(recipe);
                    cursorWrapper.moveToNext();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeCursor(cursorWrapper);
        }
        return recipes;
    }

    public List<Recipe> getRecipeList(boolean favorite){
        List<Recipe> recipes = new ArrayList<>();
        IngredientCursorWrapper cursorWrapper;
        if (!favorite) {
            cursorWrapper = queryDB(RecipeTable.NAME,null,null);
        }else {
            cursorWrapper = queryDB(RecipeTable.NAME,RecipeTable.Cols.FAVORITE + "=1",null);
        }
        try {
            if (cursorWrapper.getCount() != 0) {
                cursorWrapper.moveToFirst();
                while (!cursorWrapper.isAfterLast()){
                    Recipe recipe = cursorWrapper.getRecipe();
                    recipe.setIngredients(getIngredientListByRecipeId(recipe.getId()));
                    recipe.setSteps(getStepListByRecipeId(recipe.getId()));
                    recipes.add(recipe);
                    cursorWrapper.moveToNext();
                }
            }
        }finally {
            closeCursor(cursorWrapper);
        }
        return recipes;
    }
    public void setRecipeFavorite(int id,boolean favorite){
        ContentValues values = new ContentValues();
        values.put(RecipeTable.Cols.FAVORITE,favorite ? 1:0);
        ingrDB.update(RecipeTable.NAME,values,RecipeTable.Cols.RECIPE_ID + "=" + id,null);
    }

    public List<Step> getStepListByRecipeId(int id){
        List<Step> steps = new ArrayList<>();
        IngredientCursorWrapper cursorWrapper = queryDB(StepsTable.NAME,StepsTable.Cols.RECIPE_ID + "=" + id,null);
        try {
            if (cursorWrapper.getCount() != 0) {
                cursorWrapper.moveToFirst();
                while (!cursorWrapper.isAfterLast()){
                    steps.add(cursorWrapper.getStep());
                    cursorWrapper.moveToNext();
                }
            }
        }finally {
            closeCursor(cursorWrapper);
        }
        return steps;
    }
    public List<Ingredient> getIngredientListByRecipeId(int id){
        List<Ingredient> steps = new ArrayList<>();
        IngredientCursorWrapper cursorWrapper = queryDB(IngredientInRecipeTable.NAME,IngredientInRecipeTable.Cols.RECIPE_ID + "=" + id,null);
        try {
            if (cursorWrapper.getCount() != 0) {
                cursorWrapper.moveToFirst();
                while (!cursorWrapper.isAfterLast()){
                    steps.add(cursorWrapper.getIngredient());
                    cursorWrapper.moveToNext();
                }
            }
        }finally {
            closeCursor(cursorWrapper);
        }
        return steps;
    }
    public void addStep(Step step,int recipeId){
        ContentValues contentValues = getContentValues(step,recipeId);
        ingrDB.insert(StepsTable.NAME,null,contentValues);
    }
    public void addIngredient(Ingredient ingredient,int recipeId){
        ContentValues contentValues = getContentValues(ingredient,recipeId);
        ingrDB.insert(IngredientInRecipeTable.NAME,null,contentValues);
    }
    public void addIngredient(Ingredient ingredient){
        ContentValues contentValues = getContentValues(ingredient);
        ingrDB.insert(IngredientTable.NAME,null,contentValues);
    }
    public void addRecipe(Recipe recipe){
        ingrDB.beginTransaction();
        try {
            ContentValues contentValues = getContentValues(recipe);
            ingrDB.insert(RecipeTable.NAME,null,contentValues);
            for (Ingredient ingredient:recipe.getIngredients()){
                addIngredient(ingredient,recipe.getId());
            }
            for (Step step:recipe.getSteps()){
                addStep(step,recipe.getId());
            }
            ingrDB.setTransactionSuccessful();
        }finally {
            ingrDB.endTransaction();
        }
    }


    private ContentValues getContentValues(Recipe recipe){
        ContentValues values = new ContentValues();
        values.put(RecipeTable.Cols.RECIPE_ID,recipe.getId());
        values.put(RecipeTable.Cols.NAME,recipe.getName());
        values.put(RecipeTable.Cols.DESCRIPTION,recipe.getDescription());
        values.put(RecipeTable.Cols.IMAGE,recipe.getImage());
        values.put(RecipeTable.Cols.FAVORITE,recipe.isFavorite()? 1 : 0);
        return values;
    }
    private ContentValues getContentValues(Ingredient ingredient){
        ContentValues values = new ContentValues();
        values.put(IngredientTable.Cols.NAME,ingredient.getName());
        values.put(IngredientTable.Cols.INGREDIENT_ID,ingredient.getId());
        return values;
    }
    private ContentValues getContentValues(Ingredient ingredient,int recipeId){
        ContentValues values = new ContentValues();
        values.put(IngredientInRecipeTable.Cols.NAME,ingredient.getName());
        values.put(IngredientInRecipeTable.Cols.QUANTITY,ingredient.getQuantity());
        values.put(IngredientInRecipeTable.Cols.RECIPE_ID,recipeId);
        values.put(IngredientInRecipeTable.Cols.INGREDIENT_ID,ingredient.getId());
        return values;
    }
    private ContentValues getContentValues(Step step,int recipeId){
        ContentValues values = new ContentValues();
        values.put(StepsTable.Cols.DESCRIPTION,step.getDescription());
        values.put(StepsTable.Cols.IMAGE,step.getImage());
        values.put(StepsTable.Cols.RECIPE_ID,recipeId);
        return values;
    }

    private IngredientCursorWrapper queryDB(String table, String whereClause, String[] whereArgs) {
        Cursor cursor = ingrDB.query(table,null,whereClause,whereArgs,null,null,null);
        return new IngredientCursorWrapper(cursor);
    }
    public void closeDB(){
        ingrDB.close();
        ingredientDAO = null;
    }

    public void closeCursor(IngredientCursorWrapper notiCursorWrapper){
        notiCursorWrapper.getWrappedCursor().close();
        notiCursorWrapper.close();
    }
}
