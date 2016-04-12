package com.team18.teamproject;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Daniel on 30/03/2016.
 */
public class RecipeRVAdapter extends RecyclerView.Adapter<RecipeRVAdapter.RecipeViewHolder> {

    List<Recipe> recipes;

    RecipeRVAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView time;
        TextView difficulty;
        ImageView image;


        public RecipeViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.recipe_cardview);
            title = (TextView) itemView.findViewById(R.id.card_title);
            time = (TextView) itemView.findViewById(R.id.card_time);
            difficulty = (TextView) itemView.findViewById(R.id.card_difficulty);
            image = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_recipe, viewGroup, false);
        RecipeViewHolder rvh = new RecipeViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder rvh, int i) {
        rvh.title.setText(recipes.get(i).getName());
        rvh.time.setText(recipes.get(i).getCookTime());
        rvh.difficulty.setText(recipes.get(i).getDifficulty());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
