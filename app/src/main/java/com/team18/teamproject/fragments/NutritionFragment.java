package com.team18.teamproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.team18.teamproject.R;


public class NutritionFragment extends Fragment {

    private View fragmentView;

    public NutritionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_nutrition, container, false);

        PieChart pieChart = (PieChart) fragmentView.findViewById(R.id.chart);
        // creating data values
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));

        PieDataSet dataset = new PieDataSet(entries, "# of calls");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); // set the color

        // creating labels
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Carbohydrates");
        labels.add("Protein");
        labels.add("Fat");

        PieData data = new PieData(labels, dataset); // initialize Piedata
        pieChart.setData(data); // set data into chart

        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleRadius(0);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
