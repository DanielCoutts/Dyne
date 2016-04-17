package com.team18.teamproject.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.team18.teamproject.R;
import com.team18.teamproject.objects.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 30/03/2016.
 */
public class RecipeRVAdapter extends RecyclerView.Adapter<RecipeRVAdapter.viewHolderRecipe> {

    private List<Recipe> recipes = new ArrayList<>();

    private LayoutInflater layoutInflater;

    private Context context;

    public RecipeRVAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setRecipeList(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyItemRangeChanged(0, recipes.size());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public viewHolderRecipe onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.card_recipe, viewGroup, false);
        viewHolderRecipe viewHolder = new viewHolderRecipe(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewHolderRecipe viewHolder, int i) {
        Recipe currentRecipe = recipes.get(i);
        viewHolder.title.setText(currentRecipe.getName());
        viewHolder.time.setText(currentRecipe.getCookTime());
        viewHolder.difficulty.setText(currentRecipe.getDifficulty());
        Picasso.with(context).load(currentRecipe.getImageUrl()).fit().centerCrop().into(viewHolder.image);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class viewHolderRecipe extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView time;
        TextView difficulty;
        ImageView image;

        public viewHolderRecipe(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.recipe_cardview);
            title = (TextView) itemView.findViewById(R.id.card_title);
            time = (TextView) itemView.findViewById(R.id.card_time);
            difficulty = (TextView) itemView.findViewById(R.id.card_difficulty);
            image = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }
}
