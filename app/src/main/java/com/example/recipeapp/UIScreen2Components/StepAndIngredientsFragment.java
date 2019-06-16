package com.example.recipeapp.UIScreen2Components;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recipeapp.Model.Constants;
import com.example.recipeapp.Model.IngredientsList;
import com.example.recipeapp.Model.StepsList;
import com.example.recipeapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepAndIngredientsFragment extends Fragment {

    @BindView(R.id.ingredients_text_view)
    TextView ingredientsTextView;
    @BindView(R.id.steps_list_view) ListView stepsListView;
    ArrayList<StepsList> stepsLists;
    ArrayAdapter<String> mAdapter;
    OnListItemClickListener onListItemClickListener;

    public interface OnListItemClickListener{
        void onListItemClick(int position);
    }

    public StepAndIngredientsFragment() {
        // Required empty public constructor
        stepsLists = new ArrayList<>();
    }

    /*
    * https://www.youtube.com/watch?v=vdCejJobMp4
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_step_and_ingredients_fragment, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = getArguments();

        if(bundle != null){
            String recipeName = bundle.getString(Constants.SEND_RECIPE_NAME);
            ArrayList<IngredientsList> ingredientsLists = bundle.getParcelableArrayList(Constants.SEND_RECIPE_INGREDIENTS_LIST);
            String servingSize = bundle.getString(Constants.SEND_RECIPE_SERVINGS);

            String displayIngredients = "";

            displayIngredients += recipeName + ": \n\n";
            for(int i = 0; i < ingredientsLists.size(); i++){
                IngredientsList ingredients = ingredientsLists.get(i);

                displayIngredients += ingredients.getQuantity() + " ";
                displayIngredients += ingredients.getMeasure() + " ";
                displayIngredients += ingredients.getIngredient() + "\n";
            }

            displayIngredients += "\n"
                    + getString(R.string.servings)
                    + " "
                    + servingSize;

            ingredientsTextView.setText(displayIngredients);

            stepsLists = bundle.getParcelableArrayList(Constants.SEND_RECIPE_STEPS_LIST);
            ArrayList<String> descriptionList = new ArrayList<>();
            for(int i = 0; i < stepsLists.size(); i++){
                StepsList steps = stepsLists.get(i);

                String description = steps.getDescription();

                descriptionList.add(description + "...");
            }

            mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,
                    descriptionList);
            stepsListView.setAdapter(mAdapter);

            stepsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onListItemClickListener.onListItemClick(position);
                }
            });

        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            onListItemClickListener = (OnListItemClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }
}
