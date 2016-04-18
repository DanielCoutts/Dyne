package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.objects.Recipe;

/**
 * Insert Comment Here
 */

public class IngredientsFragment extends Fragment {

    private Recipe recipe;

    public IngredientsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipe = Application.getCurrentRecipe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
