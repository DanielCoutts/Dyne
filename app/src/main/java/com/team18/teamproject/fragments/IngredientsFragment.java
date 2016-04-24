package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.adapters.IngredientRVAdapter;
import com.team18.teamproject.extras.JsonParser;
import com.team18.teamproject.extras.Urls;
import com.team18.teamproject.network.CustomStringRequest;
import com.team18.teamproject.network.VolleySingleton;
import com.team18.teamproject.pojo.Ingredient;
import com.team18.teamproject.pojo.Recipe;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fragment That loads in information and a list of ingredients for the current recipe.
 */
public class IngredientsFragment extends Fragment {

    private Recipe recipe;

    /**
     * Script URL
     */
    private final static String URL = Urls.GET_INGREDIENTS;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private IngredientRVAdapter adapter;
    private RecyclerView recyclerView;

    public IngredientsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialise fields.
        recipe = Application.getCurrentRecipe();
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        // Initialise fields.
        ImageView image = (ImageView) view.findViewById(R.id.recipe_image);
        TextView serves = (TextView) view.findViewById(R.id.serves);
        TextView time = (TextView) view.findViewById(R.id.cook_time);
        TextView difficulty = (TextView) view.findViewById(R.id.difficulty);
        recyclerView = (RecyclerView) view.findViewById(R.id.ingredient_rv);

        // Load in image with Picasso.
        Picasso.with(getContext()).load(recipe.getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(image);

        // Populate serving info.
        serves.setText("Serves " + recipe.getServes());

        // Populate cooking time.
        time.setText(recipe.getCookTime());

        // Populate difficulty.
        difficulty.setText(recipe.getDifficulty());

        // Set up RecyclerView
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up adapter.
        adapter = new IngredientRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        // Retrieves ingredients from favourites if they are available.
        // Otherwise, request ingredients from database.
        if (Application.getFavourites().keySet().contains(recipe.getId())
                && recipe.getIngredients().size() > 0) {
            adapter.setIngredientList(recipe.getIngredients());
        } else {
            sendJsonRequest();
        }

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
        params.put("RecipeID", recipe.getId() + "");

        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, URL, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    List<Ingredient> ingredients = JsonParser.parseJSONIngredientArray(array);
                    Application.getCurrentRecipe().setIngredients(ingredients);

                    adapter.setIngredientList(ingredients);

                } catch (JSONException e) {
                    // Display an error snackbar message.
                    Application.responseError(recyclerView);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Display an error snackbar message.
                Application.connectionError(recyclerView);
            }
        });

        requestQueue.add(request);
    }
}
