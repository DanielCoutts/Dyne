package com.team18.teamproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.activities.RecipeActivity;
import com.team18.teamproject.objects.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom RecyclerView Adapter.
 */
public class RecipeRVAdapter extends RecyclerView.Adapter<RecipeRVAdapter.viewHolderRecipe> {

    /**
     * List of recipes to display in the RecyclerView.
     */
    private List<Recipe> recipes = new ArrayList<>();

    /**
     * The LayoutInflater object for the fragment.
     */
    private LayoutInflater layoutInflater;

    /**
     * The fragment context.
     */
    private Context context;

    /**
     * Constructor that initialises the context and layoutInflater.
     *
     * @param context context of the parent
     */
    public RecipeRVAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Initialises recipe list with specified list object
     *
     * @param recipes List of recipes to
     */
    public void setRecipeList(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    @Override
    public viewHolderRecipe onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.card_recipe, viewGroup, false);
        return new viewHolderRecipe(view);
    }

    @Override
    public void onBindViewHolder(viewHolderRecipe viewHolder, int i) {
        final Recipe currentRecipe = recipes.get(i);
        viewHolder.title.setText(currentRecipe.getName());
        viewHolder.time.setText(currentRecipe.getCookTime());
        viewHolder.difficulty.setText(currentRecipe.getDifficulty());

        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Application.setCurrentRecipe(currentRecipe);

                Intent intent = new Intent(Application.getAppContext(), RecipeActivity.class);
                context.startActivity(intent);
            }
        });

        Picasso.with(context).load(currentRecipe.getImageUrl()).fit().centerCrop().into(viewHolder.image);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Inner ViewHolder class.
     */
    public static class viewHolderRecipe extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        TextView time;
        TextView difficulty;
        ImageView image;

        /**
         * Constructor that initialises fields.
         *
         * @param itemView View that will display the data.
         */
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
