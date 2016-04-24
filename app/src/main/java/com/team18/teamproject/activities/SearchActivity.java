package com.team18.teamproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

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
 * Allows the user to search for a recipe.
 */
public class SearchActivity extends AppCompatActivity {

    /**
     * The search activity toolbar. This contains a back button and a title.
     */
    private Toolbar toolbar;

    /**
     * Script URL
     */
    private final static String URL = Urls.SEARCH;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;
    private EditText searchBar;

    private List<Recipe> recipes = new ArrayList<>();

    /**
     * The last executed search.
     */
    private static String searchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBar = (EditText) findViewById(R.id.search_bar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialise Volley fields.
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        // Set up RecyclerView.
        recyclerView = (RecyclerView) findViewById(R.id.search_rv);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(false);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up adapter with RecyclerView.
        adapter = new RecipeRVAdapter(this);
        recyclerView.setAdapter(adapter);

        sendJsonRequest(searchText);
        searchBar.setText(searchText);
    }

    /**
     * Sends a POST request for JSON data.
     * Populates the recycler view or shows an error as a snackbar message.
     */
    private void sendJsonRequest(String search) {
        Map<String, String> params = new HashMap<>();
        params.put("Vegetarian", Application.boolToString(Application.isVegetarian()));
        params.put("Vegan", Application.boolToString(Application.isVegan()));
        params.put("GlutenFree", Application.boolToString(Application.isGlutenFree()));
        params.put("Search", search);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            // Back button in action bar has the same behaviour as the system back button.
            case android.R.id.home:
                onBackPressed();
                return true;

            // Executes search when search pressed.
            case R.id.search:
                searchText = searchBar.getText().toString();
                sendJsonRequest(searchText);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
