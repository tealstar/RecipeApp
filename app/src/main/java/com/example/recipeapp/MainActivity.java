package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.recipeapp.Model.IngredientsList;
import com.example.recipeapp.Model.RecipeCard;
import com.example.recipeapp.Model.StepsList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<RecipeCard> recipeCards = null;
    ArrayList<IngredientsList> ingredientsLists = null;

    private String baseUrl = "https://d17h27t6h515a5.cloudfront.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeApi recipeApi = retrofit.create(RecipeApi.class);
        Call<List<RecipeCard>> getRecipeCard = recipeApi.getRecipeCard();

        getRecipeCard.enqueue(new Callback<List<RecipeCard>>() {
            @Override
            public void onResponse(Call<List<RecipeCard>> call, Response<List<RecipeCard>> response) {

                recipeCards = response.body();

                String recipeName = null;
                String ingredient = "";

                for (int i = 0; i < recipeCards.size(); i++) {

                    RecipeCard recipeCard = recipeCards.get(i);

                    recipeName = recipeCard.getName();


                    ingredientsLists = recipeCard.getIngredients();


                    for (int j = 0; j < ingredientsLists.size(); j++) {

                        IngredientsList ingredients = ingredientsLists.get(j);


                        ingredient += ingredients.getQuantity();
                        ingredient += ingredients.getMeasure();
                        ingredient += ingredients.getIngredient();

                    }
                    Log.e("listed ingredients", ingredient);

                    Log.e("test", Integer.toString(ingredientsLists.size()));
                }


            }

            @Override
            public void onFailure(Call<List<RecipeCard>> call, Throwable t) {

                Log.e("on failure", t.getMessage());
            }
        });
    }
}
