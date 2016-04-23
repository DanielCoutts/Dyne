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
import com.team18.teamproject.extras.Urls;
import com.team18.teamproject.network.CustomStringRequest;
import com.team18.teamproject.extras.JsonParser;
import com.team18.teamproject.pojo.Recipe;
import com.team18.teamproject.adapters.RecipeRVAdapter;
import com.team18.teamproject.network.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Fragment containing a scrollview that loads all recipes from the database.
 */
public class AllRecipesFragment extends Fragment {

    private final static String URL = Urls.GET_RECIPES;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;

    private List<Recipe> recipes = new ArrayList<>();

    /**
     * Empty public constructor
     */
    public AllRecipesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_recipes, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recipe_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecipeRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        sendJsonRequest();

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

    private void sendJsonRequest() {
        Map<String, String> params = new HashMap<>();
        params.put("Vegetarian", Application.boolToString(Application.isVegetarian()));
        params.put("Vegan", Application.boolToString(Application.isVegan()));
        params.put("GlutenFree", Application.boolToString(Application.isGlutenFree()));

        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, URL, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    recipes = JsonParser.parseJsonRecipeArray(array);
                    adapter.setRecipeList(recipes);

                } catch (JSONException e) {
                    // Display an error snackbar message.
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

    @Override
    public void onResume() {
        super.onResume();
        sendJsonRequest();
    }
}
