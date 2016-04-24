package com.team18.teamproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.team18.teamproject.Application;
import com.team18.teamproject.R;

/**
 * Displays three settings toggles and basic 'about' information.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * The recipe activity toolbar. This contains a back button, a title and a favourite button.
     */
    Toolbar toolbar;

    Switch vegetarian;
    Switch vegan;
    Switch glutenFree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialise fields.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vegetarian = (Switch) findViewById(R.id.toggle_vegetarian);
        vegan = (Switch) findViewById(R.id.toggle_vegan);
        glutenFree = (Switch) findViewById(R.id.toggle_glutenFree);

        // Set up toolbar.
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle("Settings");

        // Set state of vegetarian toggle.
        if (Application.isVegetarian()) {
            vegetarian.setChecked(true);
        } else {
            vegetarian.setChecked(false);
        }

        // Set state of vegetarian toggle.
        if (Application.isVegan()) {
            vegan.setChecked(true);
        } else {
            vegan.setChecked(false);
        }

        // Set state of vegan toggle.
        if (Application.isGlutenFree()) {
            glutenFree.setChecked(true);
        } else {
            glutenFree.setChecked(false);
        }

        setupListeners();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            // Back button in action bar has the same behaviour as the system back button
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Set up listeners for toggles, so that they modify the global settings.
     */
    private void setupListeners() {
        if (vegetarian != null) {
            vegetarian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Application.isVegetarian()) {
                        Application.setVegetarian(false);
                    } else {
                        Application.setVegetarian(true);
                    }
                }
            });
        }

        if (vegan != null) {
            vegan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Application.isVegan()) {
                        Application.setVegan(false);
                    } else {
                        Application.setVegan(true);
                    }
                }
            });
        }

        if (glutenFree != null) {
            glutenFree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Application.isGlutenFree()) {
                        Application.setGlutenFree(false);
                    } else {
                        Application.setGlutenFree(true);
                    }
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save settings, favourites, and shopping list to shared preferences.
        Application.getsInstance().saveState();
    }
}
