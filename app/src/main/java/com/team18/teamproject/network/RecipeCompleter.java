package com.team18.teamproject.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.team18.teamproject.Application;
import com.team18.teamproject.extras.JsonParser;
import com.team18.teamproject.extras.Urls;
import com.team18.teamproject.pojo.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO remove this class

/**
 * Methods for completing detailed recipe information when RecipeActivity is launched.
 * <p/>
 * Created by Daniel.
 */
public class RecipeCompleter {

    private static RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

    public static void completeCurrentRecipe() {
        requestIngredientsAndSet();
        requestInstructionsAndSet();
        requestNutritionAndSet();
        requestRecommendedAndSet();
    }

    private static void requestIngredientsAndSet() {

        Map<String, String> params = new HashMap<>();
        params.put("RecipeID", Application.getCurrentRecipe().getId() + "");

        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, Urls.GET_INGREDIENTS, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    List<Ingredient> ingredients = JsonParser.parseJSONIngredientArray(array);
                    Application.getCurrentRecipe().setIngredients(ingredients);

                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue.add(request);
    }

    private static void requestInstructionsAndSet() {

        Map<String, String> params = new HashMap<>();
        params.put("RecipeID", Application.getCurrentRecipe().getId() + "");

        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, Urls.GET_INSTRUCTIONS, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    List<String> instructions = JsonParser.parseJSONInstructionArray(array);
                    Application.getCurrentRecipe().setInstructions(instructions);

                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue.add(request);
    }

    private static void requestNutritionAndSet() {

        Map<String, String> params = new HashMap<>();
        params.put("RecipeID", Application.getCurrentRecipe().getId() + "");

        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, Urls.GET_NUTRITION, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    Map<String, Float> nutritionInfo = JsonParser.parseJSONNutritionInfo(object);
                    Application.getCurrentRecipe().setNutritionalInfo(nutritionInfo);

                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue.add(request);
    }

    private static void requestRecommendedAndSet() {

        Map<String, String> params = new HashMap<>();
        params.put("RecipeID", Application.getCurrentRecipe().getId() + "");

        // Create Request
        CustomStringRequest request = new CustomStringRequest(Request.Method.POST, Urls.GET_RECOMMENDED_ESSENTIALS, params, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    List<String> essentials = JsonParser.parseJSONEssentialArray(array);
                    Application.getCurrentRecipe().setRecommendedEssentials(essentials);

                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        requestQueue.add(request);
    }

}