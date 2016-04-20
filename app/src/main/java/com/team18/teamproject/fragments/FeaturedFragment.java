package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.extras.Urls;
import com.team18.teamproject.network.CustomStringRequest;
import com.team18.teamproject.network.JsonParser;
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


public class FeaturedFragment extends Fragment {

    private final static String URL = Urls.GET_FEATURED;

    private VolleySingleton volleysingleton;
    private RequestQueue requestQueue;
    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;

    private List<Recipe> recipes = new ArrayList<>();

    public FeaturedFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleysingleton = VolleySingleton.getInstance();
        requestQueue = volleysingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_featured, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.featured_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

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
                    // Display an error snackbar mess
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


}
