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

import com.squareup.picasso.Picasso;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.activities.RecipeActivity;
import com.team18.teamproject.pojo.Ingredient;
import com.team18.teamproject.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom RecyclerView Adapter.
 */
public class IngredientRVAdapter extends RecyclerView.Adapter<IngredientRVAdapter.viewHolderIngredient> {

    /**
     * List of ingredients to display in the RecyclerView.
     */
    private List<Ingredient> ingredients = new ArrayList<>();

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
     * @param context context of the parent.
     */
    public IngredientRVAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Initialises recipe list with specified list object.
     *
     * @param ingredients List of ingredients to set.
     */
    public void setIngredientList(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
        notifyItemRangeChanged(0, (ingredients.size() - 1));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    @Override
    public viewHolderIngredient onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.card_ingredient, viewGroup, false);
        return new viewHolderIngredient(view);
    }

    @Override
    public void onBindViewHolder(viewHolderIngredient viewHolder, int i) {
        final Ingredient currentIngredient = ingredients.get(i);

        String name = currentIngredient.getName();
        String quantity = currentIngredient.getQuantity();
        String units = currentIngredient.getUnits();

        viewHolder.textView.setText(formatIngredient(name, quantity, units));

        viewHolder.addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Add to shopping list
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Formats ingredient data into a single string.
     * Note: name cannot be null, if quantity is null, units must also be null.
     *
     * @param name     name of ingredient (Name cannot be null).
     * @param quantity quantity of ingredient.
     * @param units    units of ingredient (If quantity is null, units must also be null).
     * @return
     */
    private static String formatIngredient(String name, String quantity, String units) {

        if ((units.equals("null")) && (quantity.equals("null"))) {
            return name;
        } else if (units.equals("null")) {
            return quantity + " " + name;
        } else {
            return quantity + " " + units + " of " + name;
        }
    }

    /**
     * Inner ViewHolder class.
     */
    public static class viewHolderIngredient extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView addIcon;

        /**
         * Constructor that initialises fields.
         *
         * @param itemView View that will display the data.
         */
        public viewHolderIngredient(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.card_ingredient);
            addIcon = (ImageView) itemView.findViewById(R.id.card_add_ingredient);
        }
    }
}
