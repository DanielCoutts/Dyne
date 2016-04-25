package com.team18.teamproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.team18.teamproject.Application;
import com.team18.teamproject.R;
import com.team18.teamproject.fragments.AllRecipesFragment;
import com.team18.teamproject.fragments.GlossaryFragment;
import com.team18.teamproject.fragments.GuidesFragment;
import com.team18.teamproject.fragments.HomeFragment;
import com.team18.teamproject.fragments.MapFragment;
import com.team18.teamproject.fragments.ShoppingListFragment;

/**
 * The main activity contains a navigation drawer with a fragment in the main body of the page.
 * Selecting items from the navigation drawer swaps out the main fragment with the desired section.
 *
 * Created by Daniel.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * The main app toolbar. This contains a hamburger menu, a title, and a search button.
     */
    public Toolbar toolbar;

    /**
     * The fragment manager for the main page fragment.
     */
    private FragmentManager fragmentManager;

    /**
     * The DrawerLayout element in the main activity's layout.
     */
    private DrawerLayout drawerLayout;

    /**
     * The NavigationView in the DrawerLayout's drawer.
     */
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up associated layout file.
        setContentView(R.layout.activity_main);

        // Initialise fields.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Set up the toolbar.
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Display and configure hamburger icon.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set up Navigation listeners.
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        // Sets the main content area to the home fragment.
        if (savedInstanceState == null) {
            setMainFragment(new HomeFragment());
        }

        // Resize and insert the navigation drawer header image into the its ImageView
        ImageView header = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.drawer_header_background);
        Picasso.with(this).load(R.drawable.header_image).fit().centerCrop().into(header);

    }

    /**
     * Change the main display area to a given page (fragment).
     *
     * @param fragment fragment to load into the main display area.
     */
    private void setMainFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.action_search:
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Define behaviour of navigation items.
        switch (id) {
            case R.id.nav_home:
                setMainFragment(new HomeFragment());
                setTitle("Dyne");
                break;

            case R.id.nav_recipes:
                setMainFragment(new AllRecipesFragment());
                setTitle("All Recipes");
                break;

            case R.id.nav_eating:
                setMainFragment(new MapFragment());
                setTitle("Eating Out");
                break;

            case R.id.nav_shopping_list:
                setMainFragment(new ShoppingListFragment());
                setTitle("Shopping List");
                break;

            case R.id.nav_essentials:
                setMainFragment(new GuidesFragment());
                setTitle("Essentials Guide");
                break;

            case R.id.nav_glossary:
                setMainFragment(new GlossaryFragment());
                setTitle("Glossary");
                break;

            case R.id.nav_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        // If back is pressed when the drawer is open, close it.
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save settings, favourites, and shopping list to shared preferences.
        Application.getsInstance().saveState();
    }
}
