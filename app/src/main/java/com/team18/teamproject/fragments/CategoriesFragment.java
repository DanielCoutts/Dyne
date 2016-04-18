package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.team18.teamproject.R;

/**
 * Insert Comment Here
 */

public class CategoriesFragment extends Fragment {


    public CategoriesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_categories, container, false);

        if (savedInstanceState == null) {
            setupListeners(view);
        }

        loadImages(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*
     * Resizing and loading images into ImageViews using the Picasso library.
     */
    private void loadImages(View view) {
        ImageView breakfastImage = (ImageView) view.findViewById(R.id.category_breakfast_imageview);
        Picasso.with(getContext()).load(R.drawable.cat_breakfast).fit().centerCrop().into(breakfastImage);

        ImageView lunchImage = (ImageView) view.findViewById(R.id.category_lunch_imageview);
        Picasso.with(getContext()).load(R.drawable.cat_lunch).fit().centerCrop().into(lunchImage);

        ImageView dinnerImage = (ImageView) view.findViewById(R.id.category_dinner_imageview);
        Picasso.with(getContext()).load(R.drawable.cat_dinner).fit().centerCrop().into(dinnerImage);

        ImageView dessertsImage = (ImageView) view.findViewById(R.id.category_desserts_imageview);
        Picasso.with(getContext()).load(R.drawable.cat_dessert).fit().centerCrop().into(dessertsImage);

        ImageView drinksImage = (ImageView) view.findViewById(R.id.category_drinks_imageview);
        Picasso.with(getContext()).load(R.drawable.cat_drinks).fit().centerCrop().into(drinksImage);

        ImageView vegImage = (ImageView) view.findViewById(R.id.category_veg_imageview);
        Picasso.with(getContext()).load(R.drawable.cat_vegetarian).fit().centerCrop().into(vegImage);
    }

    /*
     * Setting up onClick listeners for the category cards.
     */
    private void setupListeners(View view) {
        CardView breakfast = (CardView) view.findViewById(R.id.category_breakfast);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "It worked", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

}
