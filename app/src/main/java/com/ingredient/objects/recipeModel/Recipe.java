package com.ingredient.objects.recipeModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("ingredient")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("step")
    @Expose
    private List<Step> steps = null;

    private boolean favorite;
    private String strIngredients;

    public Recipe(int id, String name, String description, String image, List<Ingredient> ingredients, List<Step> steps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
        strIngredients = toStringIngredients(true);
    }

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        strIngredients = toStringIngredients(true);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String toStringIngredients(boolean invalidate){
        if (strIngredients == null || invalidate) {
            strIngredients = "";
            for (int i = 0;i < ingredients.size();i++){
                Ingredient ingredient = ingredients.get(i);
                strIngredients += ingredient.getName() +
                        (ingredient.getQuantity().equals("") ? "": "(" + ingredient.getQuantity() + ")") +
                        (i == ingredients.size()-1 ? "" : ",") + " ";
            }

            return strIngredients;
        }
        return strIngredients;
    }

    public String toStringRecipe(){
        String s = name + "\n \n" + "Ингредиенты: \n" + toStringIngredients(false) + "\n \n " + "Способ приготовления: \n";
        for (int i = 0;i < steps.size();i++){
            Step step = steps.get(i);
            s += (i+1) + ". " + step.getDescription() + "\n\n";
        }
        s += "Отправлени из приложения - \"Ингредиент\" \n\n Скачать: \n *сылка Google Play*";

        return s;
    }
}
