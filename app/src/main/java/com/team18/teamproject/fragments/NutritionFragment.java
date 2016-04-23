package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.extras.JsonParser;
import com.team18.teamproject.extras.Keys.*;
import com.team18.teamproject.extras.Urls;
import com.team18.teamproject.network.CustomStringRequest;
import com.team18.teamproject.network.VolleySingleton;
import com.team18.teamproject.pojo.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to handle the function and initialise the nutritional information fragment
 * for each recipe, including building the pie chart displayed on the page.
 */

public class NutritionFragment extends Fragment {

    View view;

    private Recipe recipe;

    private final static String URL = Urls.GET_NUTRITION;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    Map<String, Float> nutrition;

    public NutritionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipe = Application.getCurrentRecipe();
        nutrition = recipe.getNutritionalInfo();

        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nutrition, container, false);

        if (!(Application.getFavourites().keySet().contains(recipe.getId())
                && nutrition.size() == 5)) {
            sendJsonRequest();
        }
        else {
            fillData();
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void fillData() {
        nutrition = Application.getCurrentRecipe().getNutritionalInfo();

        float kcal = nutrition.get(Nutrition.KEY_KCAL);
        float carbs = nutrition.get(Nutrition.KEY_CARBS);
        float protein = nutrition.get(Nutrition.KEY_PROTEIN);
        float fat = nutrition.get(Nutrition.KEY_FAT);
        float sugar = nutrition.get(Nutrition.KEY_SUGAR);

        // Create a nutrition pie chart.
        createPiechart(view, carbs, protein, fat, sugar);

        // Put data in text views
        TextView carbsView = (TextView) view.findViewById(R.id.carbs);
        carbsView.setText(String.format(Locale.UK, "Carbohydrates: %.1fg", carbs));

        TextView proteinView = (TextView) view.findViewById(R.id.protein);
        proteinView.setText(String.format(Locale.UK, "Protein: %.1fg", protein));

        TextView fatView = (TextView) view.findViewById(R.id.fat);
        fatView.setText(String.format(Locale.UK, "Fat: %.1fg", fat));

        TextView sugarView = (TextView) view.findViewById(R.id.sugar);
        sugarView.setText(String.format(Locale.UK, "Sugar: %.1fg", sugar));

        TextView kcalView = (TextView) view.findViewById(R.id.calories);
        kcalView.setText(String.format(Locale.UK, "Calories per serving: %.0f", kcal));
    }

    /**
     * Initialises a pie chart using data provided from database.
     *
     * @param carbs   float value for carbohydrates.
     * @param protein float value for protein.
     * @param fat     flaot value for fat.
     * @param sugar   float value for sugar
     */
    private void createPiechart(View view, float carbs, float protein, float fat, float sugar) {
        PieChart pieChart = (PieChart) view.findViewById(R.id.chart);
        // creating data values
        ArrayList<Entry> entries = new ArrayList<>();
        //Carbs
        entries.add(new Entry(carbs, 0));
        //Protein
        entries.add(new Entry(protein, 1));
        //Fat
        entries.add(new Entry(fat, 2));
        //Sugar
        entries.add(new Entry(sugar, 3));

        PieDataSet dataset = new PieDataSet(entries, "Nutrition");

        if (dataset != null) {
            // set the color
            int[] colors = new int[]{ContextCompat.getColor(getContext(), R.color.colorPrimaryDark),
                    ContextCompat.getColor(getContext(), R.color.colorDivider),
                    ContextCompat.getColor(getContext(), R.color.colorPieChart),
                    ContextCompat.getColor(getContext(), R.color.colorTextSecondary)};
            dataset.setColors(colors);

            // creating labels -  These are required by PieData class but not desired by app hence blank
            ArrayList<String> labels = new ArrayList<>();
            labels.add(" ");
            labels.add(" ");
            labels.add(" ");
            labels.add(" ");

            // initialize Piedata
            PieData data = new PieData(labels, dataset);
            data.setValueTextSize(0);
            // set data into chart
            pieChart.setData(data);
            pieChart.setDrawSliceText(false);
            pieChart.setHoleRadius(0);
            pieChart.setTransparentCircleRadius(0);
            pieChart.setDescription(" ");
            pieChart.getLegend().setEnabled(false);
        }
    }

    private void sendJsonRequest() {

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
                    fillData();

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
