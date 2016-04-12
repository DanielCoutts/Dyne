package com.team18.teamproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class GuidesFragment extends Fragment {


    public GuidesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guides, container, false);
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
        loadImage(R.id.guide1_imageview, R.drawable.cat_breakfast);

        loadImage(R.id.guide2_imageview, R.drawable.cat_lunch);
    }

    private void loadImage(int imageViewId, int drawable) {
        ImageView imageView = (ImageView) getActivity().findViewById(imageViewId);
        Picasso.with(getContext()).load(drawable).fit().centerCrop().into(imageView);
    }

    /*
     * Setting up onClick listeners for the category cards.
     */
    private void setupListeners() {
        CardView cv1 = (CardView) getActivity().findViewById(R.id.guide1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "It worked", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

}
