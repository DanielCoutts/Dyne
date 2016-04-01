package com.team18.teamproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Daniel on 06/12/2015.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // The main app toolbar. This contains a hamburger menu, a title, and a search button.
    public Toolbar toolbar;

    private FragmentManager fragmentManager;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Displays and configures hamburger icon
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        // Sets up Navigation listeners
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        // sets the main content area to the home fragment
        setMainFragment(new HomeFragment());

        // Resizes and inserts the navigation drawer header image into the its ImageView
        ImageView header = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.drawer_header_background);
        Picasso.with(this).load(R.drawable.avocado2).fit().centerCrop().into(header);
    }

    /*
     * Changes the main display area to a given page (fragment)
     */
    private void setMainFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, fragment)
                .commit();
    }

    @Override
    /*
     * Adds the search button
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    /*
     * Listener for the actionbar menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                Snackbar.make(drawerLayout, "'Search' clicked", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    /*
     * Listener for the navigation menu
     */
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                setMainFragment(new HomeFragment());
                break;
            case R.id.nav_recipes:

                break;
            case R.id.nav_shopping_list:

                break;
            case R.id.nav_timer:

                break;
            case R.id.nav_essentials:

                break;
            case R.id.nav_glossary:

                break;
            case R.id.nav_settings:
                    Intent intent = new Intent(this, RecipeActivity.class);
                    startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
