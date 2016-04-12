package com.team18.teamproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.team18.teamproject.network.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AllRecipesFragment extends Fragment {

    private final static String URL = "http://homepages.cs.ncl.ac.uk/2015-16/csc2022_team18/getRecipeFromID.php";

    RecyclerView recyclerView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_recipes, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recipe_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecipeRVAdapter adapter = new RecipeRVAdapter(recipes);
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Application.getAppContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject object = new JSONObject(response);
                    Toast.makeText(Application.getAppContext(), "WORKED\n " + object.toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Application.getAppContext(), "NOPE", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Application.getAppContext(), "ERROR " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("RecipeID", "1");
                return params;
            }
        };

        requestQueue.add(request);
    }
}
