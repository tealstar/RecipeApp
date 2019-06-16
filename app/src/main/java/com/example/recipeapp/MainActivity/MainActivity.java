package com.example.recipeapp.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.recipeapp.Model.Constants;
import com.example.recipeapp.Model.IngredientsList;
import com.example.recipeapp.Model.RecipeCard;
import com.example.recipeapp.Model.StepsList;
import com.example.recipeapp.R;
import com.example.recipeapp.RecipeApi;
import com.example.recipeapp.UIScreen2Components.StepAndIngredientsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnClickListener,
        RecipeCardAdapter.OnRecipeCardClick {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recycler_view_recipe_cards)
    RecyclerView mainActivityRecyclerView;
    private RecipeCardAdapter mAdapter;

    private List<RecipeCard> recipeCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recipeCards = new ArrayList<>();

        getRecipeData();

    }

    private void getRecipeData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeApi recipeApi = retrofit.create(RecipeApi.class);
        Call<List<RecipeCard>> getRecipeCard = recipeApi.getRecipeCard();

        getRecipeCard.enqueue(new Callback<List<RecipeCard>>() {
            @Override
            public void onResponse(Call<List<RecipeCard>> call, Response<List<RecipeCard>> response) {

                recipeCards = response.body();

                displayData(recipeCards);
            }

            @Override
            public void onFailure(Call<List<RecipeCard>> call, Throwable t) {

                Log.e("on failure", t.getMessage());
            }
        });
    }

    private void displayData(List<RecipeCard> cards){

        mainActivityRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        if(findViewById(R.id.main_activity_phone_layout) != null){
            mainActivityRecyclerView.setLayoutManager(linearLayoutManager);
        }else {
            mainActivityRecyclerView.setLayoutManager(gridLayoutManager);
        }
        mAdapter = new RecipeCardAdapter(this, cards);
        mainActivityRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRecipeCardClick(int position) {

        RecipeCard recipeCard = recipeCards.get(position);

        String recipeName = recipeCard.getName();
        String servings = recipeCard.getServings();
        ArrayList<IngredientsList> ingredientsLists = recipeCard.getIngredients();
        ArrayList<StepsList> stepsLists = recipeCard.getSteps();

        Intent intent = new Intent(MainActivity.this, StepAndIngredientsActivity.class);
        intent.putExtra(Constants.SEND_RECIPE_NAME, recipeName);
        intent.putExtra(Constants.SEND_RECIPE_SERVINGS, servings);
        intent.putParcelableArrayListExtra(Constants.SEND_RECIPE_INGREDIENTS_LIST, ingredientsLists);
        intent.putParcelableArrayListExtra(Constants.SEND_RECIPE_STEPS_LIST, stepsLists);
        startActivity(intent);
    }
}
