package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.network.CustomStringRequest;
import com.team18.teamproject.objects.Recipe;
import com.team18.teamproject.adapters.RecipeRVAdapter;
import com.team18.teamproject.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.team18.teamproject.extras.Keys.Recipes.*;

public class AllRecipesFragment extends Fragment {

    private final static String URL = "http://homepages.cs.ncl.ac.uk/2015-16/csc2022_team18/getRecipes.php";

    private VolleySingleton volleysingleton;
    private RequestQueue requestQueue;
    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;

    private List<Recipe> recipes;

    private void initialiseData() {
        recipes = new ArrayList<>();
        recipes.add(new Recipe(1));
        recipes.add(new Recipe(2));
    }

    public AllRecipesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialiseData();
        volleysingleton = VolleySingleton.getInstance();
        requestQueue = volleysingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_recipes, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recipe_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecipeRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        sendJsonRequest();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void sendJsonRequest() {
        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, URL, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Application.getAppContext(), response, Toast.LENGTH_LONG).show();
                try {

                    // TODO Make this work with arrays when the server returns.

                    JSONObject object = new JSONObject(response);
                    recipes = parseJSONObject(object);
                    adapter.setRecipeList(recipes);

                } catch (JSONException e) {
                    Application.responseError(recyclerView);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Application.connectionError(recyclerView);
            }
        });

        requestQueue.add(request);
    }

    private ArrayList<Recipe> parseJSONObject(JSONObject object) {

        ArrayList<Recipe> recipes = new ArrayList<>();

        if (object != null && object.length() > 0) {

            try {
                if (object.has(KEY_RECIPES)) {

                    JSONArray array = object.getJSONArray(KEY_RECIPES);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject currentRecipe = array.getJSONObject(i);
                        String id = currentRecipe.getString(KEY_ID);
                        // TODO add other fields once server is alive

//                        recipes.add(new Recipe( ...,
//                                                ...,
//                                                ..., ));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return recipes;

    }
}
