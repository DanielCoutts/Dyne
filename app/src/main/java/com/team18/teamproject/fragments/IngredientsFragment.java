package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
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
 * Class to handle the function and initialising of the ingredients fragment for each recipe.
 */

public class IngredientsFragment extends Fragment {

    private Recipe recipe;

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

        recipe = Application.getCurrentRecipe();

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.recipe_image);
        Picasso.with(getContext()).load(recipe.getImageUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(image);

        TextView serves = (TextView) view.findViewById(R.id.serves);
        serves.setText("Serves " + recipe.getServes());

        TextView time = (TextView) view.findViewById(R.id.cook_time);
        time.setText(recipe.getCookTime());

        TextView difficulty = (TextView) view.findViewById(R.id.difficulty);
        difficulty.setText(recipe.getDifficulty());

        recyclerView = (RecyclerView) view.findViewById(R.id.ingredient_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        adapter = new IngredientRVAdapter(getContext());
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
