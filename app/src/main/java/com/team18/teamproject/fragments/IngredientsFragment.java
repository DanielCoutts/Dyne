package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.pojo.Recipe;

/**
 * Class to handle the function and initialising of the ingredients fragment for each recipe.
 */

public class IngredientsFragment extends Fragment {

    private Recipe recipe;

    public IngredientsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recipe = Application.getCurrentRecipe();
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
