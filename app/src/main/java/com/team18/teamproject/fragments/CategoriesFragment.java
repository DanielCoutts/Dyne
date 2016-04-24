package com.team18.teamproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.team18.teamproject.R;
import com.team18.teamproject.activities.CategoryActivity;

/**
 * Clickable list of category cards.
 */
public class CategoriesFragment extends Fragment {

    /**
     * Empty public constructor.
     */
    public CategoriesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_categories, container, false);

        setupListeners(view);

        loadImages(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * Resize and load images into ImageViews.
     *
     * @param view Inflated layout view.
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

    /**
     * Loads an image using the Picasso library.
     *
     * @param view        Inflated layout view.
     * @param imageViewId ID of ImageView.
     * @param drawable    ID of image.
     */
    private void loadImage(View view, int imageViewId, int drawable) {
        ImageView imageView = (ImageView) view.findViewById(imageViewId);
        Picasso.with(getContext()).load(drawable).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);
    }

    /**
     * Sets up onclick listeners for the category cards.
     *
     * @param view Inflated layout view.
     */
    private void setupListeners(View view) {
        CardView breakfast = (CardView) view.findViewById(R.id.category_breakfast);
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("CATEGORY", "Breakfast");
                startActivity(intent);
            }
        });

        CardView lunch = (CardView) view.findViewById(R.id.category_lunch);
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("CATEGORY", "Lunch");
                startActivity(intent);
            }
        });

        CardView starter = (CardView) view.findViewById(R.id.category_starter);
        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("CATEGORY", "Starter");
                startActivity(intent);
            }
        });

        CardView main = (CardView) view.findViewById(R.id.category_main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("CATEGORY", "Main");
                startActivity(intent);
            }
        });

        CardView dessert = (CardView) view.findViewById(R.id.category_desserts);
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("CATEGORY", "Dessert");
                startActivity(intent);
            }
        });

        CardView drink = (CardView) view.findViewById(R.id.category_drinks);
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("CATEGORY", "Drinks");
                startActivity(intent);
            }
        });

        CardView veg = (CardView) view.findViewById(R.id.category_veg);
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("CATEGORY", "Vegetarian");
                startActivity(intent);
            }
        });
    }
}
