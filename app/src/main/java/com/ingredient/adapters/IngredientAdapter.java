package com.ingredient.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.MainThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingredient.MainFragment;
import com.ingredient.R;
import com.ingredient.database.IngredientDAO;
import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder> {
    private IngredientDAO ingredientDAO;
    private Context context;
    private List<Ingredient> ingredients = new ArrayList<>();
    private RecyclerView recyclerRecipe;
    private RecyclerView recyclerIngredient;


    public IngredientAdapter(Context context, RecyclerView recyclerIngredient, RecyclerView recyclerRecipe) {
        this.context = context;
        this.recyclerRecipe = recyclerRecipe;
        this.recyclerIngredient = recyclerIngredient;
        ingredientDAO = IngredientDAO.getInstance(context);
    }

    @Override
    public IngredientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IngredientHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false));
    }

    @Override
    public void onBindViewHolder(IngredientHolder holder, int position) {
        holder.bindHolder(ingredients.get(position));
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(250);
        holder.itemView.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        recyclerIngredient.scrollToPosition(ingredients.size()-1);
    }

    class IngredientHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tIngredient) TextView tIngredient;
        @BindView(R.id.iHideIngredient) ImageView iHideIngredient;
        Ingredient ingredient;
        private IngredientHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tIngredient.setOnClickListener(this);
            iHideIngredient.setOnClickListener(this);
        }

        private void bindHolder(Ingredient ingredient) {
            this.ingredient = ingredient;
            tIngredient.setText(ingredient.getName()+ (ingredient.getQuantity().equals("") ? "":"(" + ingredient.getQuantity() + ")") );
        }

        @Override
        public void onClick(View v) {
            if (v == tIngredient){
                if (recyclerRecipe == null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(ingredient.getName());
                    final EditText input = new EditText(context);
                    input.setInputType(InputType.TYPE_CLASS_TEXT );
                    input.setText(ingredient.getQuantity());
                    builder.setView(input);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ingredient.setQuantity(input.getText().toString());
                            IngredientAdapter.this.notifyItemChanged(getAdapterPosition());
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }else if (v == iHideIngredient){
                List<Ingredient> ingredients = IngredientAdapter.this.getIngredients();
                ingredients.remove(getAdapterPosition());
                IngredientAdapter.this.notifyItemRemoved(getAdapterPosition());
                if (recyclerRecipe != null) {
                    List<Recipe> recipes;
                    if (IngredientAdapter.this.getItemCount() != 0) {
                        recipes = ingredientDAO.getRecipeIdsByIngredients(ingredients, MainFragment.favorite);
                    }else {
                        recipes = ingredientDAO.getRecipeList(MainFragment.favorite);
                    }
                    ((RecipeAdapter)recyclerRecipe.getAdapter()).setRecipes(recipes);
                    recyclerRecipe.getAdapter().notifyDataSetChanged();

                }
                if (getAdapterPosition() == 0) {
                    recyclerIngredient.setVisibility(View.GONE);
                }
            }
        }
    }
}
