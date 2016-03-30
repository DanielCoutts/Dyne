package com.team18.teamproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class CategoriesFragment extends Fragment {


    public CategoriesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadImages();

        setupListeners();
    }

    /*
     * Resizing and loading images into ImageViews using the Picasso library.
     */
    private void loadImages() {
        ImageView breakfastImage = (ImageView) getActivity().findViewById(R.id.category_breakfast_imageview);
        Picasso.with(getContext()).load(R.drawable.breakfast1).fit().centerCrop().into(breakfastImage);

        ImageView lunchImage = (ImageView) getActivity().findViewById(R.id.category_lunch_imageview);
        Picasso.with(getContext()).load(R.drawable.lunch1).fit().centerCrop().into(lunchImage);

        ImageView dinnerImage = (ImageView) getActivity().findViewById(R.id.category_dinner_imageview);
        Picasso.with(getContext()).load(R.drawable.dinner1).fit().centerCrop().into(dinnerImage);

        ImageView dessertsImage = (ImageView) getActivity().findViewById(R.id.category_desserts_imageview);
        Picasso.with(getContext()).load(R.drawable.dessert1).fit().centerCrop().into(dessertsImage);

        ImageView drinksImage = (ImageView) getActivity().findViewById(R.id.category_drinks_imageview);
        Picasso.with(getContext()).load(R.drawable.drinks1).fit().centerCrop().into(drinksImage);

        ImageView vegImage = (ImageView) getActivity().findViewById(R.id.category_veg_imageview);
        Picasso.with(getContext()).load(R.drawable.vegetarian1).fit().centerCrop().into(vegImage);
    }

    /*
     * Setting up onClick listeners for the category cards.
     */
    private void setupListeners() {
        CardView breakfast = (CardView) getActivity().findViewById(R.id.category_breakfast);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "It worked", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

}
