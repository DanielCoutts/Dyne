package com.team18.teamproject.network;

import com.team18.teamproject.objects.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.team18.teamproject.extras.Keys.Recipes.KEY_CATEGORY;
import static com.team18.teamproject.extras.Keys.Recipes.KEY_COOKTIME;
import static com.team18.teamproject.extras.Keys.Recipes.KEY_DIFFICULTY;
import static com.team18.teamproject.extras.Keys.Recipes.KEY_ID;
import static com.team18.teamproject.extras.Keys.Recipes.KEY_IMAGEURL;
import static com.team18.teamproject.extras.Keys.Recipes.KEY_NAME;
import static com.team18.teamproject.extras.Keys.Recipes.KEY_SERVES;

/**
 * Created by Daniel on 20/04/2016.
 */
public class JsonParser {

    public static ArrayList<Recipe> parseJsonRecipeArray(JSONArray array) {

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

                    recipes.add(new Recipe(id,
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
