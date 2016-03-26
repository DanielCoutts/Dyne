package com.team18.teamproject;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by Daniel on 06/12/2015.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Toolbar toolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        DrawerFragment drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawerFragment);
//        drawerFragment.setUp(R.id.drawerFragment, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        HomeFragment homeFragment = new HomeFragment();
        fragmentManager = getSupportFragmentManager();
        setMainFragment(homeFragment);
    }

    private void setMainFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
