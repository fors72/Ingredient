package com.ingredient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ingredient.database.IngredientDAO;
import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaunchActivity extends AppCompatActivity {
    private static RecipeLoader recipeLoader;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.tStatus)
    TextView tStatus;
    @BindView(R.id.constLay)
    ConstraintLayout constLay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        progress.setMax(100);
        if (Settings.getFirstLaunch(getApplicationContext())) {
            constLay.setVisibility(View.VISIBLE);
            if (recipeLoader == null) {
                recipeLoader = new RecipeLoader(this);
                recipeLoader.execute();
            } else {
                recipeLoader.setLaunchActivity(this);
            }
        } else {
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            finish();
        }
    }

    private class RecipeLoader extends AsyncTask<Void, String, Void> {
        LaunchActivity launchActivity;
        String currentStatus;

        RecipeLoader(LaunchActivity launchActivity) {
            this.launchActivity = launchActivity;
        }

        void setLaunchActivity(LaunchActivity launchActivity) {
            this.launchActivity = launchActivity;
            launchActivity.tStatus.setText(currentStatus != null ? currentStatus : "");
        }

        @Override
        protected Void doInBackground(Void... params) {
            IngredientDAO ingredientDAO = IngredientDAO.getInstance(getApplicationContext());
            currentStatus = "Инициализация рецептов";
            publishProgress(currentStatus);
            RecipeBuilder recipeBuilder = new RecipeBuilder();
            List<Recipe> recipes = recipeBuilder.getRecipes();
            currentStatus = "Инициализация ингредиентов";
            publishProgress(currentStatus);
            List<Ingredient> ingredients = recipeBuilder.getIngredients();
            currentStatus = "Добавление рецептов в базу";
            publishProgress(currentStatus);
            for (Recipe recipe : recipes) {
                ingredientDAO.addRecipe(recipe);
            }
            currentStatus = "Добавление ингредиентов в базу";
            publishProgress(currentStatus);
            for (Ingredient ingredient : ingredients) {
                ingredientDAO.addIngredient(ingredient);
            }
            publishProgress("Готово");
            Settings.setFirstLaunch(LaunchActivity.this, false);
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            launchActivity.tStatus.setText(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            launchActivity.startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            launchActivity.finish();
        }
    }
}
