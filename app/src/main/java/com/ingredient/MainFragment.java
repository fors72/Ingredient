package com.ingredient;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.ingredient.adapters.IngredientAdapter;
import com.ingredient.adapters.RecipeAdapter;
import com.ingredient.database.DBSchema;
import com.ingredient.database.IngredientCursorWrapper;
import com.ingredient.database.IngredientDAO;
import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {
    List<Recipe> recipes;
    IngredientDAO ingredientDAO;
    IngredientCursorWrapper cursorWrapper;
    IngredientAdapter ingredientAdapter;
    RecipeAdapter recipeAdapter;
    public static boolean favorite;

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.recyclerIngredient)
    RecyclerView recyclerIngredient;
    @BindView(R.id.recyclerRecipe)
    RecyclerView recyclerRecipe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        ingredientDAO = IngredientDAO.getInstance(getContext());
        if (savedInstanceState == null) {
            recipes = ingredientDAO.getRecipeList(false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_list_of_recipes,container,false);
        ButterKnife.bind(this,v);
        initUI();
        return v;
    }

    private void initUI() {
        if (((MainActivity)getActivity()).getSupportActionBar() != null) {
            ((MainActivity)getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ((MainActivity)getActivity()).getSupportActionBar().setCustomView(R.layout.text_action_bar);
        }
        searchView.setQueryHint(getResources().getString(R.string.find_by_ingredients));
        searchView.setIconifiedByDefault(false);
        int searchPlateId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlateView = searchView.findViewById(searchPlateId);
        if (searchPlateView != null) {
            searchPlateView.setBackgroundColor(Color.WHITE);
        }
        searchView.setSuggestionsAdapter(new SimpleCursorAdapter(
                getActivity(), android.R.layout.simple_list_item_1, null,
                new String[]{DBSchema.IngredientTable.Cols.NAME},
                new int[]{android.R.id.text1}));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1) {
                    cursorWrapper = ingredientDAO.getIngredientByKey(newText,((IngredientAdapter) recyclerIngredient.getAdapter()).getIngredients());
                    searchView.getSuggestionsAdapter().changeCursor(cursorWrapper);
                }
                return false;
            }
        });
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                searchView.setQuery("", false);
                if (recyclerIngredient.getVisibility() == View.GONE) {
                    recyclerIngredient.setVisibility(View.VISIBLE);
                }
                ((IngredientAdapter) recyclerIngredient.getAdapter()).addIngredient(cursorWrapper.getSimpleIngredient());
                updateRecipe();
                return true;
            }
        });

        if (ingredientAdapter == null) {
            ingredientAdapter = new IngredientAdapter(getActivity(), recyclerIngredient, recyclerRecipe);
        }else {
            if (recyclerIngredient.getVisibility() == View.GONE && ingredientAdapter.getIngredients().size() > 0) {
                recyclerIngredient.setVisibility(View.VISIBLE);
            }
        }
        recyclerIngredient.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerIngredient.setAdapter(ingredientAdapter);

        if (recipeAdapter == null){
            recipeAdapter = new  RecipeAdapter(getActivity(), recipes);
        }
        recyclerRecipe.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerRecipe.setAdapter(recipeAdapter);

    }

    private void updateRecipe(){
        List<Ingredient> ingredients = ((IngredientAdapter) recyclerIngredient.getAdapter()).getIngredients();
        List<Recipe> recipes;
        if (ingredients.size() != 0) {
            recyclerIngredient.getAdapter().notifyItemInserted(ingredients.size());
            recipes = ingredientDAO.getRecipeIdsByIngredients(ingredients,favorite);
        }else {
            recipes = ingredientDAO.getRecipeList(favorite);
        }
        recipeAdapter.setRecipes(recipes);
        recipeAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_recipe){
            startActivityForResult(new Intent(getActivity(),CreateRecipeActivity.class),1);
        }else if (item.getItemId() == R.id.action_show_favorite){
            if (item.isChecked()){
                favorite = false;
                item.setChecked(false);
            }else {
                favorite = true;
                item.setChecked(true);
            }
            updateRecipe();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                updateRecipe();
            }
        }
    }
}
