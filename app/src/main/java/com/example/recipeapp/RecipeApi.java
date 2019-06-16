package com.example.recipeapp;

import com.example.recipeapp.Model.RecipeCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
/*
* https://medium.com/@jacinth9/android-retrofit-2-0-tutorial-89de3c714c63
* https://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
* https://www.youtube.com/watch?v=Lx1e_fdnNxA
* */
public interface RecipeApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeCard>> getRecipeCard();
}
