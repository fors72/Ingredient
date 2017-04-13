package com.ingredient.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ingredient.PicassoTool;
import com.ingredient.R;
import com.ingredient.database.IngredientDAO;
import com.ingredient.objects.recipeModel.Recipe;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {
    private Context context;
    private List<Recipe> recipes = new ArrayList<>();
    private IngredientDAO ingredientDAO;



    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
        ingredientDAO = IngredientDAO.getInstance(context);
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeHolder(LayoutInflater.from(context).inflate(R.layout.recipe_card, parent, false));
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        holder.bindHolder(recipes.get(position));
    }


    @Override
    public int getItemCount() {
        return null == recipes ? 0 : recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tRecipeName) TextView tRecipeName;
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.tIngredient) TextView tIngredient;
        @BindView(R.id.expand_text_view) ExpandableTextView expandableTextView;
        @BindView(R.id.lMainRecipeInfo) LinearLayout lMainRecipeInfo;
        @BindView(R.id.tRecipeNameFull) TextView tRecipeNameFull;
        @BindView(R.id.tIngredient2) TextView tIngredient2;
        @BindView(R.id.steps_view_pager) ViewPager stepsViewPager;
        @BindView(R.id.iBackStep) ImageView iBackStep;
        @BindView(R.id.iNextStep) ImageView iNextStep;
        @BindView(R.id.lDetailedRecipeInfo) LinearLayout lDetailedRecipeInfo;
        @BindView(R.id.iLike) ImageView iLike;
        @BindView(R.id.tShare) TextView tShare;
        @BindView(R.id.tDevelop) TextView tDevelop;
        @BindView(R.id.tLike1) TextView tLike1;
        @BindView(R.id.tLike2) TextView tLike2;
        Recipe recipe;

        private RecipeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageView.setOnClickListener(this);
            tRecipeName.setOnClickListener(this);
            tRecipeNameFull.setOnClickListener(this);
            expandableTextView.setOnClickListener(this);
            iBackStep.setOnClickListener(this);
            iNextStep.setOnClickListener(this);
            iLike.setOnClickListener(this);
            tShare.setOnClickListener(this);
            tDevelop.setOnClickListener(this);
        }

        private void bindHolder(final Recipe recipe) {
            this.recipe = recipe;
            unbindHolder();
            tRecipeName.setText(recipe.getName());
            PicassoTool.load(context, recipe.getImage(), imageView);
            tIngredient.setText(recipe.toStringIngredients(false));
            expandableTextView.getLayoutParams().height= (int)(105 * context.getResources().getDisplayMetrics().density);
            expandableTextView.setText(recipe.getDescription());
            if (lMainRecipeInfo.getVisibility() == View.GONE) {
                lMainRecipeInfo.setVisibility(View.VISIBLE);
                lDetailedRecipeInfo.setVisibility(View.GONE);
            }
        }

        private void unbindHolder(){
            if (stepsViewPager.getAdapter() != null) {
                stepsViewPager.setAdapter(null);
                iNextStep.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == imageView.getId() || v.getId() == R.id.tRecipeName){
                lMainRecipeInfo.setVisibility(View.GONE);
                lMainRecipeInfo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_from_right));
                lDetailedRecipeInfo.setVisibility(View.VISIBLE);
                lDetailedRecipeInfo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right));
                if (stepsViewPager.getAdapter() == null) {
                    stepsViewPager.setAdapter(new StepPageAdapter(context, recipe.getSteps()));
                    tIngredient2.setText(recipe.toStringIngredients(false));
                    tRecipeNameFull.setText(recipe.getName());
                    invalidateLike(recipe.isFavorite());
                    iBackStep.setVisibility(View.GONE);
                    stepsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            if (position != 0) {
                                iBackStep.setVisibility(View.VISIBLE);
                            } else {
                                iBackStep.setVisibility(View.GONE);
                            }
                            if (position + 1 != stepsViewPager.getAdapter().getCount()) {
                                iNextStep.setVisibility(View.VISIBLE);
                            } else {
                                iNextStep.setVisibility(View.GONE);
                            }

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                        }
                    });
                }
            }else if (v.getId() == tRecipeNameFull.getId()){
                lMainRecipeInfo.setVisibility(View.VISIBLE);
                lMainRecipeInfo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
                lDetailedRecipeInfo.setVisibility(View.GONE);
                lDetailedRecipeInfo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_from_left));
            }else if (v.getId() == expandableTextView.getId()){
                expandableTextView.onClick(v);
            }else if (v.getId() == iNextStep.getId()){
                stepsViewPager.setCurrentItem(stepsViewPager.getCurrentItem() + 1);
            }else if (v.getId() == iBackStep.getId()){
                stepsViewPager.setCurrentItem(stepsViewPager.getCurrentItem() - 1);
            }else if (v.getId() == iLike.getId()){
                recipe.setFavorite(!recipe.isFavorite());
                ingredientDAO.setRecipeFavorite(recipe.getId(),recipe.isFavorite());
                invalidateLike(recipe.isFavorite());
            }else if (v.getId() == tShare.getId()){
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, recipe.toStringRecipe());
                i.putExtra(Intent.EXTRA_SUBJECT, "title");
                context.startActivity(i);
            }else if (v.getId() == tDevelop.getId()) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://uwdc.com.ua/"));
                context.startActivity(i);
            }
        }
        private void invalidateLike(boolean b) {
            if (b) {
                iLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_remove_favorite));
                tLike1.setText(context.getResources().getString(R.string.remove_from));
                tLike2.setText(context.getResources().getString(R.string.favoriteV1));
            } else {
                iLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_add_favorite));
                tLike1.setText(context.getResources().getString(R.string.add_to));
                tLike2.setText(context.getResources().getString(R.string.favorite));
            }
        }
    }
}
