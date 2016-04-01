package com.team18.teamproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MethodFragment extends Fragment {

    RecyclerView recyclerView;

    private List<Recipe> recipes;

    private void initialiseData() {
        recipes = new ArrayList<>();
        recipes.add(new Recipe(1));
        recipes.add(new Recipe(2));
    }

    public MethodFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialiseData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recent_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecentRVAdapter adapter = new RecentRVAdapter(recipes);
        recyclerView.setAdapter(adapter);

//        ImageView image = (ImageView) getActivity().findViewById(R.id.card_image);
//        Picasso.with(getContext()).load("http://homepages.cs.ncl.ac.uk/2015-16/csc2022_team18/images/1.jpg").into(image);
    }
}