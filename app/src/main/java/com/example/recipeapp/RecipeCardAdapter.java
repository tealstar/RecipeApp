package com.example.recipeapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Model.RecipeCard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeCardAdapterViewHolder> {

    private static final String TAG = RecipeCardAdapter.class.getSimpleName();

    private List<RecipeCard> mRecipeCardList;
    private Context mContext;

    private OnRecipeCardClick mClickListener;

    public RecipeCardAdapter(Context context, List<RecipeCard> recipeCardList){
        mContext = context;
        mRecipeCardList = recipeCardList;

        Log.e(TAG, Integer.toString(mRecipeCardList.size()));
    }

    @NonNull
    @Override
    public RecipeCardAdapter.RecipeCardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.activity_main_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        RecipeCardAdapterViewHolder viewHolder = new RecipeCardAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardAdapter.RecipeCardAdapterViewHolder holder, int position) {

        RecipeCard currentRecipe = mRecipeCardList.get(position);

        String recipeName = currentRecipe.getName();

        holder.recipeCardTextView.setText(recipeName);
    }

    @Override
    public int getItemCount() {
        return mRecipeCardList.size();
    }

    public interface OnRecipeCardClick{
        void onRecipeCardClick(int position);
    }

    public void setOnClickListener(OnRecipeCardClick listener){
        mClickListener = listener;
    }

    public class RecipeCardAdapterViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        @BindView(R.id.recipe_card_text_view)
        TextView recipeCardTextView;

        public RecipeCardAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickListener.onRecipeCardClick(position);
        }
    }
}
