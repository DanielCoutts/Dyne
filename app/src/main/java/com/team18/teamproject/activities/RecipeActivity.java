package com.team18.teamproject.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.team18.teamproject.fragments.IngredientsFragment;
import com.team18.teamproject.fragments.MethodFragment;
import com.team18.teamproject.fragments.NutritionFragment;
import com.team18.teamproject.R;

/**
 * Created by Daniel on 06/12/2015.
 */
public class RecipeActivity extends AppCompatActivity {

    public Toolbar toolbar;

    private TabLayout tabs;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Recipe_Name");

        pager = (ViewPager) findViewById(R.id.recipe_pager);
        pager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabs = (TabLayout) findViewById(R.id.recipe_tabs);
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

    /*
     * Custom pager adapter that defines which fragments should be loaded into tabs.
     */
    private class CustomAdapter extends FragmentPagerAdapter {

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
}
