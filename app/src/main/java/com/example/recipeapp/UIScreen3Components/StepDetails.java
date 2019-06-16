package com.example.recipeapp.UIScreen3Components;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.recipeapp.Model.Constants;
import com.example.recipeapp.R;

/*
 * Step details class displays information when ListView item is clicked
 * in StepsAndIngredientsActivity
 * */
public class StepDetails extends AppCompatActivity {

    private String shortDescription;
    private String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        getRecipeInformation();
    }

    private void getRecipeInformation() {

        if (getIntent().hasExtra(Constants.SEND_RECIPE_SHORT_DESCRIPTION)) {
            shortDescription = getIntent().getStringExtra(Constants.SEND_RECIPE_SHORT_DESCRIPTION);
        }

        if (getIntent().hasExtra(Constants.SEND_RECIPE_VIDEO_URL)) {
            videoUrl = getIntent().getStringExtra(Constants.SEND_RECIPE_VIDEO_URL);
        }

        showRecipeInformation();
    }

    private void showRecipeInformation() {

        StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SEND_RECIPE_SHORT_DESCRIPTION, shortDescription);
        bundle.putString(Constants.SEND_RECIPE_VIDEO_URL, videoUrl);
        stepDetailsFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.step_details_frame_layout, stepDetailsFragment)
                .commit();
    }
}
