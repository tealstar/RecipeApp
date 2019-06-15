package com.example.recipeapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeCard {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("servings")
    @Expose
    private String servings;

    @SerializedName("ingredients")
    @Expose
    private ArrayList<IngredientsList> ingredients;

    @SerializedName("steps")
    @Expose
    private ArrayList<StepsList> steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }


    public ArrayList<IngredientsList> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<IngredientsList> ingredients) {
        this.ingredients = ingredients;
    }


    public ArrayList<StepsList> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepsList> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "RecipeCard{" +
                "name='" + name + '\'' +
                ", servings='" + servings + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
