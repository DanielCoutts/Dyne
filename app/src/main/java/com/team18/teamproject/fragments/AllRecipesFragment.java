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
import java.util.List;

import static com.team18.teamproject.extras.Keys.Recipes.*;

/**
 * Fragment containing a scrollview that loads all recipes from the database.
 */
public class AllRecipesFragment extends Fragment {

    private final static String URL = "http://homepages.cs.ncl.ac.uk/2015-16/csc2022_team18/getRecipes.php";

    private VolleySingleton volleysingleton;
    private RequestQueue requestQueue;
    private RecipeRVAdapter adapter;
    private RecyclerView recyclerView;

    private List<Recipe> recipes = new ArrayList<>();

    public AllRecipesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleysingleton = VolleySingleton.getInstance();
        requestQueue = volleysingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_recipes, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recipe_rv);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);

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

                    JSONArray array = new JSONArray(response);
                    recipes = parseJSONArray(array);
                    adapter.setRecipeList(recipes);

                } catch (JSONException e) {
                    Application.responseError(recyclerView);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Application.connectionError(recyclerView);

                // TODO Remove below workaround
                try {

                    JSONArray array = new JSONArray("[{\"RecipeID\":\"1\",\"RName\":\"Tomato Soup\",\"ImageURL\":\"http:\\/\\/www.mymojorocks.com\\/wp-content\\/uploads\\/2016\\/03\\/sweetpotsoup.jpg\",\"Serves\":\"6\",\"Category\":\"Starter\",\"Difficulty\":\"Easy\",\"CookTime\":\"01:00:00\"},{\"RecipeID\":\"2\",\"RName\":\"Mini Pizza Quiches\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/2.jpg\",\"Serves\":\"6\",\"Category\":\"Starter\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:10:00\"},{\"RecipeID\":\"3\",\"RName\":\"Smoked Salmon Rotollos\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/3.jpg\",\"Serves\":\"25\",\"Category\":\"Starter\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:20:00\"},{\"RecipeID\":\"4\",\"RName\":\"Herb & Spice Paneer Fritters\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/4.jpg\",\"Serves\":\"12\",\"Category\":\"Starter\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:35:00\"},{\"RecipeID\":\"5\",\"RName\":\"Sausage Roll Twists With Tomato Dip\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/5.jpg\",\"Serves\":\"24\",\"Category\":\"Starter\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:30:00\"},{\"RecipeID\":\"6\",\"RName\":\"Parsley Omlette with Olive Ricotta\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/6.jpg\",\"Serves\":\"4\",\"Category\":\"Starter\",\"Difficulty\":\"Easy\",\"CookTime\":\"01:10:00\"},{\"RecipeID\":\"7\",\"RName\":\"Chicken Stir-Fry in 4 Easy Steps\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/7.jpg\",\"Serves\":\"4\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:50:00\"},{\"RecipeID\":\"8\",\"RName\":\"Really Easy Beefburgers\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/8.jpg\",\"Serves\":\"4\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"01:05:00\"},{\"RecipeID\":\"9\",\"RName\":\"Easy Noodles\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/9.jpg\",\"Serves\":\"4\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:20:00\"},{\"RecipeID\":\"10\",\"RName\":\"Easy Oven Frittata\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/10.jpg\",\"Serves\":\"4\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"01:00:00\"},{\"RecipeID\":\"11\",\"RName\":\"Greek-Style Roast Fish\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/11.jpg\",\"Serves\":\"2\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"01:00:00\"},{\"RecipeID\":\"12\",\"RName\":\"Butter Bean & Tomato Salad\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/12.jpg\",\"Serves\":\"6\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:20:00\"},{\"RecipeID\":\"13\",\"RName\":\"Yummy Scrummy Carrot Cake\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/13.jpg\",\"Serves\":\"15\",\"Category\":\"Dessert\",\"Difficulty\":\"Easy\",\"CookTime\":\"01:45:00\"},{\"RecipeID\":\"14\",\"RName\":\"Chocolate Brownie Cake\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/14.jpg\",\"Serves\":\"5\",\"Category\":\"Dessert\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:15:00\"},{\"RecipeID\":\"15\",\"RName\":\"Chocolate Fudge Cupcakes\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/15.jpg\",\"Serves\":\"12\",\"Category\":\"Dessert\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:55:00\"},{\"RecipeID\":\"16\",\"RName\":\"Simple Jammy Biscuits\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/16.jpg\",\"Serves\":\"12\",\"Category\":\"Dessert\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:22:00\"},{\"RecipeID\":\"17\",\"RName\":\"Flourless Chocolate & Pear Cake\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/17.jpg\",\"Serves\":\"8\",\"Category\":\"Dessert\",\"Difficulty\":\"Moderately Easy\",\"CookTime\":\"01:00:00\"},{\"RecipeID\":\"18\",\"RName\":\"Vegan Banana & Peanut Butter Cupcakes\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/18.jpg\",\"Serves\":\"16\",\"Category\":\"Dessert\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:45:00\"},{\"RecipeID\":\"19\",\"RName\":\"Porridge With Blueberry Compote\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/19.jpg\",\"Serves\":\"2\",\"Category\":\"Breakfast\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:10:00\"},{\"RecipeID\":\"20\",\"RName\":\"Fruit and Nut Granola\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/20.jpg\",\"Serves\":\"14\",\"Category\":\"Breakfast\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:35:00\"},{\"RecipeID\":\"21\",\"RName\":\"Scrambled Egg Muffin\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/21.jpg\",\"Serves\":\"1\",\"Category\":\"Breakfast\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:10:00\"},{\"RecipeID\":\"22\",\"RName\":\"Pasta With Tomato & Hidden Veg Sauce\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/22.jpg\",\"Serves\":\"4\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"01:05:00\"},{\"RecipeID\":\"23\",\"RName\":\"Carrot and Sweet potato Mash\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/23.jpg\",\"Serves\":\"4\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:30:00\"},{\"RecipeID\":\"24\",\"RName\":\"Stroganoff Steaks\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/24.jpg\",\"Serves\":\"2\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:10:00\"},{\"RecipeID\":\"25\",\"RName\":\"Chicken in a Wrap\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/25.jpg\",\"Serves\":\"2\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:30:00\"},{\"RecipeID\":\"26\",\"RName\":\"Country-Style Toad in the Hole\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/26.jpg\",\"Serves\":\"4\",\"Category\":\"Main\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:50:00\"},{\"RecipeID\":\"27\",\"RName\":\"Pi\\u00f1a Colada\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/27.jpg\",\"Serves\":\"1\",\"Category\":\"Drinks\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:05:00\"},{\"RecipeID\":\"28\",\"RName\":\"Flirtini\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/28.jpg\",\"Serves\":\"2\",\"Category\":\"Drinks\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:05:00\"},{\"RecipeID\":\"29\",\"RName\":\"Espresso Martini\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/29.jpg\",\"Serves\":\"4\",\"Category\":\"Drinks\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:05:00\"},{\"RecipeID\":\"30\",\"RName\":\"Gluten Free Pancakes\",\"ImageURL\":\"http:\\/\\/homepages.cs.ncl.ac.uk\\/2015-16\\/csc2022_team18\\/images\\/30.jpg\",\"Serves\":\"6\",\"Category\":\"Breakfast\",\"Difficulty\":\"Easy\",\"CookTime\":\"00:30:00\"}]");
                    recipes = parseJSONArray(array);
                    adapter.setRecipeList(recipes);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Application.getAppContext(), "WELL SHIT", Toast.LENGTH_LONG).show();
                }

            }
        });

        requestQueue.add(request);
    }

//    private ArrayList<Recipe> parseJSONObject(JSONObject object) {
//
//        ArrayList<Recipe> recipes = new ArrayList<>();
//
//        if (object != null && object.length() > 0) {
//
//            try {
//                if (object.has(KEY_RECIPES)) {
//
//                    JSONArray array = object.getJSONArray(KEY_RECIPES);
//
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject currentRecipe = array.getJSONObject(i);
//                        String id = currentRecipe.getString(KEY_ID);
//                        // TODO add other fields once server is alive
//
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return recipes;
//
//    }

    private ArrayList<Recipe> parseJSONArray(JSONArray array) {

        ArrayList<Recipe> recipes = new ArrayList<>();

        if (array != null && array.length() > 0) {

            try {

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject currentRecipe = array.getJSONObject(i);
                        int id = currentRecipe.getInt(KEY_ID);
                        String name = currentRecipe.getString(KEY_NAME);
                        String image = currentRecipe.getString(KEY_IMAGEURL);
                        int serves = currentRecipe.getInt(KEY_SERVES);
                        String category = currentRecipe.getString(KEY_CATEGORY);
                        String difficulty = currentRecipe.getString(KEY_DIFFICULTY);
                        String cookTime = currentRecipe.getString(KEY_COOKTIME);
                        // TODO add other fields once server is alive

                        recipes.add(new Recipe( id,
                                                name,
                                                serves,
                                                category,
                                                difficulty,
                                                cookTime,
                                                image));
                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return recipes;

    }
}
