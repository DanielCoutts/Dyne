package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.adapters.RecipeRVAdapter;
import com.team18.teamproject.pojo.Recipe;

import java.util.Arrays;
import java.util.List;


public class FavouritesFragment extends Fragment {

    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;

    public FavouritesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.favourites_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecipeRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        List<Recipe> favourites = Arrays.asList( Application.getFavourites().values().toArray(new Recipe[Application.getFavourites().size()]) );
        adapter.setRecipeList(favourites);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set minimum height for the RecyclerView
        final View v = view;
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    recyclerView.setMinimumHeight(v.getHeight());
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Recipe> favourites = Arrays.asList( Application.getFavourites().values().toArray(new Recipe[Application.getFavourites().size()]) );
        adapter.setRecipeList(favourites);
    }

}
