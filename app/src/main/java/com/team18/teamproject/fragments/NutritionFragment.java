package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.pojo.Recipe;

/**
 * Class to handle the function and initialise the nutritional information fragment
 * for each recipe, including building the pie chart displayed on the page.
 */

public class NutritionFragment extends Fragment {

    private View fragmentView;

    private Recipe recipe;

    public NutritionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipe = Application.getCurrentRecipe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_nutrition, container, false);

        //Create a nutrition pie chart. Ints (Datatype can be changed  must be sorted, Carbohydrates, Protein, Fat
        createPiechart(8,6,4);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Initialises a pie chart using data provided from database.
     *
     * @param carbs integer value for carbohydrates.
     * @param protein integer value for protein.
     * @param fat integer value for fat.
     */
    public void createPiechart(int carbs, int protein, int fat){
        PieChart pieChart = (PieChart) fragmentView.findViewById(R.id.chart);
        // creating data values
        ArrayList<Entry> entries = new ArrayList<>();
        //Carbs
        entries.add(new Entry(carbs, 0));
        //Protein
        entries.add(new Entry(protein, 1));
        //Fat
        entries.add(new Entry(fat, 2));

        PieDataSet dataset = new PieDataSet(entries, "Nutrition");
        // set the color
        int[] colors = new int[]{ContextCompat.getColor(getContext(), R.color.colorPrimaryDark),
                ContextCompat.getColor(getContext(), R.color.colorDivider),
                ContextCompat.getColor(getContext(), R.color.colorPieChart)};
        dataset.setColors(colors);

        // creating labels -  These are required by PieData class but not desired by app hence blank
        ArrayList<String> labels = new ArrayList<String>();
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
