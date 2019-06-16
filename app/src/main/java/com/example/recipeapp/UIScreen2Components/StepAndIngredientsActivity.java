package com.example.recipeapp.UIScreen2Components;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.recipeapp.Model.Constants;
import com.example.recipeapp.Model.IngredientsList;
import com.example.recipeapp.Model.StepsList;
import com.example.recipeapp.R;
import com.example.recipeapp.UIScreen3Components.StepDetails;
import com.example.recipeapp.UIScreen3Components.StepDetailsFragment;

import java.util.ArrayList;

/*
 * Activity that displays when ingredients and steps from StepAndIngredientFragment.class.
 * */
public class StepAndIngredientsActivity extends AppCompatActivity implements
        StepAndIngredientsFragment.OnListItemClickListener {

    FragmentManager fragmentManager;
    Bundle bundle;
    private String recipeName;
    private String recipeServings;
    private ArrayList<IngredientsList> ingredientsLists;
    private ArrayList<StepsList> stepsLists;
    private Boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_and_ingredients);

        ingredientsLists = new ArrayList<>();
        stepsLists = new ArrayList<>();

        fragmentManager = getSupportFragmentManager();
        bundle = new Bundle();

        //changes view depending on whether a phone or tablet is used.

        if (findViewById(R.id.tablet_view_step_detail_layout) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }

        getRecipeInformation();
    }

    private void getRecipeInformation() {
        if (getIntent().hasExtra(Constants.SEND_RECIPE_NAME)) {
            recipeName = getIntent().getStringExtra(Constants.SEND_RECIPE_NAME);
        }
        if (getIntent().hasExtra(Constants.SEND_RECIPE_SERVINGS)) {
            recipeServings = getIntent().getStringExtra(Constants.SEND_RECIPE_SERVINGS);
        }
        if (getIntent().hasExtra(Constants.SEND_RECIPE_INGREDIENTS_LIST)) {
            ingredientsLists = getIntent().getParcelableArrayListExtra(Constants.SEND_RECIPE_INGREDIENTS_LIST);
        }

        if (getIntent().hasExtra(Constants.SEND_RECIPE_STEPS_LIST)) {
            stepsLists = getIntent().getParcelableArrayListExtra(Constants.SEND_RECIPE_STEPS_LIST);
        }

        showRecipeInformation();
    }

    private void showRecipeInformation() {

        StepAndIngredientsFragment stepAndIngredientsFragment = new StepAndIngredientsFragment();
        bundle.putString(Constants.SEND_RECIPE_NAME, recipeName);
        bundle.putParcelableArrayList(Constants.SEND_RECIPE_INGREDIENTS_LIST, ingredientsLists);
        bundle.putParcelableArrayList(Constants.SEND_RECIPE_STEPS_LIST, stepsLists);
        bundle.putString(Constants.SEND_RECIPE_SERVINGS, recipeServings);
        stepAndIngredientsFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.recipe_steps_frame_layout, stepAndIngredientsFragment)
                .commit();

    }

    @Override
    public void onListItemClick(int position) {

        StepsList stepListObject = stepsLists.get(position);
        String shortDescription = stepListObject.getShortDescription();
        String videoUrl = stepListObject.getVideoURL();

        if (mTwoPane == false) {
            Intent intent = new Intent(StepAndIngredientsActivity.this, StepDetails.class);
            intent.putExtra(Constants.SEND_RECIPE_SHORT_DESCRIPTION, shortDescription);
            intent.putExtra(Constants.SEND_RECIPE_VIDEO_URL, videoUrl);
            startActivity(intent);
        } else {
            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            bundle.putString(Constants.SEND_RECIPE_SHORT_DESCRIPTION, shortDescription);
            bundle.putString(Constants.SEND_RECIPE_VIDEO_URL, videoUrl);
            stepDetailsFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.step_details_frame_layout, stepDetailsFragment)
                    .commit();
        }
    }

}
