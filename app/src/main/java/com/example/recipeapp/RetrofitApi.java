package com.example.recipeapp;

import com.example.recipeapp.Model.RecipeCard;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class RetrofitApi {


    public interface recipeService {
        String baseUrl = "https://d17h27t6h515a5.cloudfront.net/";

        @Headers("testing")
        @GET("topher/2017/May/59121517_baking/baking.json")
        Call<RecipeCard> getRecipeCard();
    }
}
