package com.team18.teamproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.adapters.RecipeRVAdapter;
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
 * Loads in all recipes of the desired activity
 */
public class CategoryActivity extends AppCompatActivity {

    /**
     * The category activity toolbar. This contains a back button and a title.
     */
    private Toolbar toolbar;

    private final static String URL = Urls.GET_CATEGORY;

    private String category = "";

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;

    private List<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Set up toolbar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        retrieveData();

        setTitle();

        // Initialise Volley fields.
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        // Set up RecyclerView.
        recyclerView = (RecyclerView) findViewById(R.id.category_rv);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(false);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up adapter with RecyclerView.
        adapter = new RecipeRVAdapter(this);
        recyclerView.setAdapter(adapter);

        sendJsonRequest();

        // Set minimum height for the RecyclerView
        final View v = findViewById(R.id.category_layout);
        ViewTreeObserver viewTreeObserver = v.getViewTreeObserver();
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

    /**
     * Gets the category passed with the intent.
     */
    private void retrieveData() {
        Intent intent = getIntent();
        category = intent.getStringExtra("CATEGORY");
    }

    /**
     * Sends a POST request for JSON data.
     * Populates the recycler view or shows an error as a snackbar message.
     */
    private void sendJsonRequest() {
        if (category.equals("Vegetarian")) {
            Map<String, String> params = new HashMap<>();
            params.put("Vegetarian", "1");
            params.put("Vegan", Application.boolToString(Application.isVegan()));
            params.put("GlutenFree", Application.boolToString(Application.isGlutenFree()));

            // Create Request
            CustomStringRequest request = new CustomStringRequest(Request.Method.POST, Urls.GET_RECIPES, params, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // Parse JSON and set list.
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
                    // Display an error snackbar message.
                    Application.connectionError(recyclerView);
                }
            });

            requestQueue.add(request);
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("Vegetarian", Application.boolToString(Application.isVegetarian()));
            params.put("Vegan", Application.boolToString(Application.isVegan()));
            params.put("GlutenFree", Application.boolToString(Application.isGlutenFree()));
            params.put("Category", category);

            // Create Request
            CustomStringRequest request = new CustomStringRequest(Request.Method.POST, URL, params, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // Parse JSON and set list.
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
                    // Display an error snackbar message.
                    Application.connectionError(recyclerView);
                }
            });

            requestQueue.add(request);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            // Back button in action bar has the same behaviour as the system back button.
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTitle() {
        switch (category) {
            case "Breakfast":
                setTitle("Breakfast");
                break;

            case "Lunch":
                setTitle("Lunch");
                break;

            case "Starter":
                setTitle("Starters");
                break;

            case "Main":
                setTitle("Main Courses");
                break;

            case "Dessert":
                setTitle("Desserts");
                break;

            case "Drinks":
                setTitle("Drinks");
                break;

            case "Vegetarian":
                setTitle("Vegetarian Recipes");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the content when the activity resumes.
        sendJsonRequest();
    }
}
