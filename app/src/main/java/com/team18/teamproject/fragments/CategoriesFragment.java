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
        loadImage(view, R.id.category_breakfast_imageview, R.drawable.cat_breakfast);

        loadImage(view, R.id.category_lunch_imageview, R.drawable.cat_lunch);

        loadImage(view, R.id.category_starter_imageview, R.drawable.cat_starter);

        loadImage(view, R.id.category_main_imageview, R.drawable.cat_main);

        loadImage(view, R.id.category_desserts_imageview, R.drawable.cat_dessert);

        loadImage(view, R.id.category_drinks_imageview, R.drawable.cat_drinks);

        loadImage(view, R.id.category_veg_imageview, R.drawable.cat_vegetarian);
    }

    private void loadImage(View view, int imageViewId, int drawable) {
        ImageView imageView = (ImageView) view.findViewById(imageViewId);
        Picasso.with(getContext()).load(drawable).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);
    }

    /**
     * Sets up onclick listeners for the category cards.
     *
     * @param view inflated fragment view.
     */
    private void setupListeners(View view) {
        CardView breakfast = (CardView) view.findViewById(R.id.category_breakfast);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView lunch = (CardView) view.findViewById(R.id.category_lunch);
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView starter = (CardView) view.findViewById(R.id.category_starter);
        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView main = (CardView) view.findViewById(R.id.category_main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView dessert = (CardView) view.findViewById(R.id.category_desserts);
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView drink = (CardView) view.findViewById(R.id.category_drinks);
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView veg = (CardView) view.findViewById(R.id.category_veg);
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
