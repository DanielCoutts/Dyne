package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.adapters.RecipeRVAdapter;
import com.team18.teamproject.adapters.ShoppingListRVAdapter;
import com.team18.teamproject.extras.JsonParser;
import com.team18.teamproject.extras.Urls;
import com.team18.teamproject.network.CustomStringRequest;
import com.team18.teamproject.network.VolleySingleton;
import com.team18.teamproject.pojo.Recipe;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Fragment containing a scrollview that loads the user's shopping list.
 */
public class ShoppingListFragment extends Fragment {

    private ShoppingListRVAdapter adapter;
    private RecyclerView recyclerView;

    /**
     * Empty public constructor
     */
    public ShoppingListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.shopping_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ShoppingListRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        adapter.setIngredientList(Application.getShoppingList());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the content when the activity resumes.
        adapter.setIngredientList(Application.getShoppingList());
    }
}
