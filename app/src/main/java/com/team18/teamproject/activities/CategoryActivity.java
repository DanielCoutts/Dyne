package com.team18.teamproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.team18.teamproject.R;
import com.team18.teamproject.extras.Urls;

/**
 * Loads in all recipes of the desired activity
 */
public class CategoryActivity extends AppCompatActivity {

    private String url = Urls.GET_CATEGORY;

    private String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        retrieveData();
    }

    private void retrieveData() {
        Intent intent = getIntent();
        String category = intent.getStringExtra("CATEGORY");
        Log.d("TEST", category);
    }
}
