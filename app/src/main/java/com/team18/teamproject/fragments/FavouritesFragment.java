package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.adapters.RecipeRVAdapter;
import com.team18.teamproject.pojo.Recipe;

import java.util.Arrays;
import java.util.List;

/**
 * Loads in a list of favourites that are available offline and persistent.
 */
public class FavouritesFragment extends Fragment {

    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;

    /**
     * Empty public constructor.
     */
    public FavouritesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Set up RecyclerView.
        recyclerView = (RecyclerView) view.findViewById(R.id.favourites_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up adapter.
        adapter = new RecipeRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        // populate RV with list of favourites.
        List<Recipe> favourites = Arrays.asList(Application.getFavourites().values().toArray(new Recipe[Application.getFavourites().size()]));
        adapter.setRecipeList(favourites);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the favourites list.
        List<Recipe> favourites = Arrays.asList(Application.getFavourites().values().toArray(new Recipe[Application.getFavourites().size()]));
        adapter.setRecipeList(favourites);
    }

}
