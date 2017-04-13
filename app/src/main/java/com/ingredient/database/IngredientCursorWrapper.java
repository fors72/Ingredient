package com.ingredient.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ingredient.database.DBSchema.*;
import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;
import com.ingredient.objects.recipeModel.Step;


public class IngredientCursorWrapper extends CursorWrapper {

    IngredientCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Step getStep(){
        Step step = new Step();
        step.setImage(getString(getColumnIndex(StepsTable.Cols.IMAGE)));
        step.setDescription(getString(getColumnIndex(StepsTable.Cols.DESCRIPTION)));
        return step;
    }
    public Recipe getRecipe(){
        Recipe recipe = new Recipe();
        recipe.setId(getInt(getColumnIndex(RecipeTable.Cols.RECIPE_ID)));
        recipe.setName(getString(getColumnIndex(RecipeTable.Cols.NAME)));
        recipe.setDescription(getString(getColumnIndex(RecipeTable.Cols.DESCRIPTION)));
        recipe.setImage(getString(getColumnIndex(RecipeTable.Cols.IMAGE)));
        recipe.setFavorite(getInt(getColumnIndex(RecipeTable.Cols.FAVORITE)) == 1);
        return recipe;
    }
    public Ingredient getSimpleIngredient(){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(getString(getColumnIndex(IngredientTable.Cols.NAME)));
        ingredient.setId(getInt(getColumnIndex(IngredientTable.Cols.INGREDIENT_ID)));
        return ingredient;
    }
    public Ingredient getIngredient(){
        Ingredient ingredient = new Ingredient();
        ingredient.setName(getString(getColumnIndex(IngredientInRecipeTable.Cols.NAME)));
        ingredient.setQuantity(getString(getColumnIndex(IngredientInRecipeTable.Cols.QUANTITY)));
        ingredient.setId(getInt(getColumnIndex(IngredientTable.Cols.INGREDIENT_ID)));
        return ingredient;
    }
}
