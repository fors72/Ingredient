package com.ingredient.objects.recipeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Ingredient {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("quantity")
    @Expose
    private String quantity = "";

    public Ingredient(String name, String quantity,int id) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;

    }

    public Ingredient(String name,int id) {
        this.id = id;
        this.name = name;
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
