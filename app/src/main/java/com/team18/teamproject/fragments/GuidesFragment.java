package com.team18.teamproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.activities.GuideActivity;


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

        loadImage(view, R.id.guide3_imageview, R.drawable.essential_fruitveg);

        loadImage(view, R.id.guide4_imageview, R.drawable.essential_meat);

        loadImage(view, R.id.guide5_imageview, R.drawable.essential_flour);

        loadImage(view, R.id.guide6_imageview, R.drawable.essential_greens_final);

        loadImage(view, R.id.guide7_imageview, R.drawable.essential_herbs);

        loadImage(view, R.id.guide8_imageview, R.drawable.essential_roasting);
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
                launchGuide(1);
            }
        });

        CardView cv2 = (CardView) view.findViewById(R.id.guide2);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(2);
            }
        });

        CardView cv3 = (CardView) view.findViewById(R.id.guide3);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(3);
            }
        });

        CardView cv4 = (CardView) view.findViewById(R.id.guide4);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(4);
            }
        });

        CardView cv5 = (CardView) view.findViewById(R.id.guide5);
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(5);
            }
        });

        CardView cv6 = (CardView) view.findViewById(R.id.guide6);
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(6);
            }
        });

        CardView cv7 = (CardView) view.findViewById(R.id.guide7);
        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(7);
            }
        });

        CardView cv8 = (CardView) view.findViewById(R.id.guide8);
        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(8);
            }
        });

        CardView cv9 = (CardView) view.findViewById(R.id.guide9);
        cv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(9);
            }
        });

        CardView cv10 = (CardView) view.findViewById(R.id.guide10);
        cv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGuide(10);
            }
        });
    }

    private void launchGuide(int i) {
        Application.setCurrentGuideId(i);
        Intent intent = new Intent(getContext(), GuideActivity.class);
        startActivity(intent);
    }

}
