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


public class GuidesFragment extends Fragment {


    public GuidesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_guides, container, false);

        loadImages(view);
        setupListeners(view);

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
        loadImage(view, R.id.guide1_imageview, R.drawable.essential_baking);

        loadImage(view, R.id.guide2_imageview, R.drawable.essential_boiling);
    }

    private void loadImage(View view, int imageViewId, int drawable) {
        ImageView imageView = (ImageView) view.findViewById(imageViewId);
        Picasso.with(view.getContext()).load(drawable).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);
    }

    /*
     * Setting up onClick listeners for the category cards.
     */
    private void setupListeners(View view) {
        CardView cv1 = (CardView) view.findViewById(R.id.guide1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "It worked", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

}
