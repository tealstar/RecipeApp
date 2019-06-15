package com.example.recipeapp;

import com.example.recipeapp.Model.RecipeCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RecipeApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeCard>> getRecipeCard();
}
