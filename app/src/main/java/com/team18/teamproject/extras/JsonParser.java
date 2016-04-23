package com.team18.teamproject.extras;

import android.util.Log;

import com.team18.teamproject.pojo.Ingredient;
import com.team18.teamproject.pojo.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.team18.teamproject.extras.Keys.*;


/**
 * Class containing static functions for parsing different types of JSON data.
 *
 * Created by Daniel.
 */
public class JsonParser {

    /**
     * Converts JSON array of recipes into a List of Recipe objects.
     *
     * @param array JSON array of recipe JSON objects.
     * @return List of Recipe objects.
     */
    public static List<Recipe> parseJsonRecipeArray(JSONArray array) {

        List<Recipe> recipes = new ArrayList<>();

        if (array != null && array.length() > 0) {

            try {

                for (int i = 0; i < array.length(); i++) {
                    JSONObject currentRecipe = array.getJSONObject(i);
                    int id = currentRecipe.getInt(Recipes.KEY_ID);
                    String name = currentRecipe.getString(Recipes.KEY_NAME);
                    String image = currentRecipe.getString(Recipes.KEY_IMAGEURL);
                    int serves = currentRecipe.getInt(Recipes.KEY_SERVES);
                    String category = currentRecipe.getString(Recipes.KEY_CATEGORY);
                    String difficulty = currentRecipe.getString(Recipes.KEY_DIFFICULTY);
                    String cookTime = currentRecipe.getString(Recipes.KEY_COOKTIME);

                    recipes.add(new Recipe(id,
                            name,
                            serves,
                            category,
                            difficulty,
                            cookTime,
                            image));
                }

            } catch (JSONException e) {
                // Simply return the empty List.
            }

        }

        return recipes;

    }

    /**
     * Converts JSON array of ingredients into a List of Ingredient objects.
     *
     * @param array JSON array of ingredient JSON objects.
     * @return List of Ingredient objects.
     */
    public static List<Ingredient> parseJSONIngredientArray(JSONArray array) {

        List<Ingredient> ingredients = new ArrayList<>();

        if (array != null && array.length() > 0) {

            try {

                for (int i = 0; i < array.length(); i++) {
                    JSONObject currentIngredient = array.getJSONObject(i);
                    String name = currentIngredient.getString(Ingredients.KEY_NAME);
                    String units = currentIngredient.getString(Ingredients.KEY_UNITS);
                    String quantity = currentIngredient.getString(Ingredients.KEY_QUANTITY);

                    ingredients.add(new Ingredient(name, units, quantity));
                }

            } catch (JSONException e) {
                // Simply return the empty List.
            }

        }

        return ingredients;
    }

    /**
     * Converts JSON array of instructions into a List of instruction Strings.
     *
     * @param array JSON array of instruction JSON objects.
     * @return List of instructions as Strings.
     */
    public static List<String> parseJSONInstructionArray(JSONArray array) {

        List<String> instructions = new ArrayList<>();

        if (array != null && array.length() > 0) {

            try {

                for (int i = 0; i < array.length(); i++) {
                    instructions.add(array.getJSONObject(i).getString(Instructions.KEY_STEP));
                }

            } catch (JSONException e) {
                // Simply return the empty List.
            }

        }

        return instructions;
    }

    /**
     * Converts JSON nutrition object into a Map of nutritional information.
     *
     * @param object Nutrition JSON object.
     * @return Map of nutritional information.
     */
    public static Map<String, Double> parseJSONNutritionInfo(JSONObject object) {

        Map<String, Double> nutrition = new HashMap<>();

        if (object != null) {

            try {

                nutrition.put("KCal", object.getDouble(Nutrition.KEY_KCAL));
                nutrition.put("Fat", object.getDouble(Nutrition.KEY_FAT));
                nutrition.put("Sugar", object.getDouble(Nutrition.KEY_SUGAR));
                nutrition.put("Protein", object.getDouble(Nutrition.KEY_PROTEIN));
                nutrition.put("Carbohydrates", object.getDouble(Nutrition.KEY_CARBS));

            } catch (JSONException e) {
                // Simply return the empty Map.
            }

        }

        return nutrition;
    }

    /**
     * Converts JSON array of essential objects into a List of essential Strings.
     *
     * @param array JSON array of essential JSON objects.
     * @return List of essentials as Strings.
     */
    public static List<String> parseJSONEssentialArray(JSONArray array) {

        List<String> essentials = new ArrayList<>();

        if (array != null && array.length() > 0) {

            try {

                for (int i = 0; i < array.length(); i++) {
                    essentials.add(array.getJSONObject(i).getString(Essentials.KEY_ID));
                }

            } catch (JSONException e) {
                // Simply return the empty List.
            }

        }

        return essentials;
    }

}
