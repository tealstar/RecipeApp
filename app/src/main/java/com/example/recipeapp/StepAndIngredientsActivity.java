package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.recipeapp.Model.IngredientsList;
import com.example.recipeapp.Model.RecipeCard;
import com.example.recipeapp.Model.StepsList;

import java.util.ArrayList;

public class StepAndIngredientsActivity extends AppCompatActivity {

    private String recipeName;
    private String recipeServings;
    private ArrayList<IngredientsList> ingredientsLists;
    private ArrayList<StepsList> stepsLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_and_ingredients);

        ingredientsLists = new ArrayList<>();
        stepsLists = new ArrayList<>();

        getRecipeInformation();
    }

    private void getRecipeInformation(){
        if(getIntent().hasExtra(Constants.SEND_RECIPE_NAME)){
            recipeName = getIntent().getStringExtra(Constants.SEND_RECIPE_NAME);
        }
        if(getIntent().hasExtra(Constants.SEND_RECIPE_SERVINGS)){
            recipeServings = getIntent().getStringExtra(Constants.SEND_RECIPE_SERVINGS);
        }
        if(getIntent().hasExtra(Constants.SEND_RECIPE_INGREDIENTS_LIST)){
            ingredientsLists = getIntent().getParcelableArrayListExtra(Constants.SEND_RECIPE_INGREDIENTS_LIST);
        }

        if(getIntent().hasExtra(Constants.SEND_RECIPE_STEPS_LIST)){
            stepsLists = getIntent().getParcelableArrayListExtra(Constants.SEND_RECIPE_STEPS_LIST);
        }
        showRecipeInformation();
    }

    private void showRecipeInformation(){

        StepAndIngredientsFragment stepAndIngredientsFragment = new StepAndIngredientsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SEND_RECIPE_NAME, recipeName);
        bundle.putParcelableArrayList(Constants.SEND_RECIPE_INGREDIENTS_LIST, ingredientsLists);
        bundle.putParcelableArrayList(Constants.SEND_RECIPE_STEPS_LIST, stepsLists);
        bundle.putString(Constants.SEND_RECIPE_SERVINGS, recipeServings);
        stepAndIngredientsFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.recipe_steps_frame_layout, stepAndIngredientsFragment)
                .commit();
    }
}
