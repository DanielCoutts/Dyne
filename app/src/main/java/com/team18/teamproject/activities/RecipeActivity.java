package com.team18.teamproject.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.team18.teamproject.Application;
import com.team18.teamproject.fragments.IngredientsFragment;
import com.team18.teamproject.fragments.MethodFragment;
import com.team18.teamproject.fragments.NutritionFragment;
import com.team18.teamproject.R;
import com.team18.teamproject.pojo.Recipe;

/**
 * The recipe activity contains three tabs: ingredients, method, and nutrition.
 * Data is different based on the selected recipe, so it is loaded in when the activity is launched.
 */
public class RecipeActivity extends AppCompatActivity {

    /**
     * The recipe activity toolbar. This contains a back button, a title and a favourite button.
     */
    private Toolbar toolbar;

    /**
     * The actionbar menu (containing favourite button).
     */
    private Menu menu;

    /**
     * The recipe to be displayed.
     */
    private Recipe recipe;

    /**
     * The Ingredients, Method, and Nutrition tabs.
     */
    private TabLayout tabs;

    /**
     * The Viewpager containing the three fragments.
     */
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe);

        // Initialise fields.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabs = (TabLayout) findViewById(R.id.recipe_tabs);
        pager = (ViewPager) findViewById(R.id.recipe_pager);
        recipe = Application.getCurrentRecipe();

        // Set up toolbar.
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set title of page to the name of the recipe.
        setTitle(recipe.getName());

        // Set up pager and pager adapter.
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager(), getApplicationContext());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(3);

        // Set up tabs.
        if (tabs != null) {
            tabs.setupWithViewPager(pager);

            tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    pager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    pager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    pager.setCurrentItem(tab.getPosition());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        setHeartFill();
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

            case R.id.action_favourite:

                // Toggles favourite.
                if (Application.getFavourites().keySet().contains(recipe.getId())) {
                    Application.getFavourites().remove(recipe.getId());
                    menu.getItem(0).setIcon(R.drawable.ic_menu_favourite_outline);
                } else {
                    Application.getFavourites().put(recipe.getId(), recipe);
                    menu.getItem(0).setIcon(R.drawable.ic_menu_favourite_fill);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Sets the heart icon to be filled if the recipe is in the favourites.
     */
    private void setHeartFill() {
        if (Application.getFavourites().keySet().contains(recipe.getId())) {
            menu.getItem(0).setIcon(R.drawable.ic_menu_favourite_fill);
        }
    }

    /**
     * Custom pager adapter that defines which fragments should be loaded into tabs.
     */
    private class CustomAdapter extends FragmentStatePagerAdapter {

        private String fragments[] = {"Ingredients", "Method", "Nutrition"};

        public CustomAdapter(FragmentManager fragMan, Context applicationContext) {
            super(fragMan);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new IngredientsFragment();
                case 1:
                    return new MethodFragment();
                case 2:
                    return new NutritionFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save settings, favourites, and shopping list to shared preferences.
        Application.getsInstance().saveState();
    }
}
