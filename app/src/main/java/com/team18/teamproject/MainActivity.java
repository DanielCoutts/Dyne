package com.team18.teamproject;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Daniel on 06/12/2015.
 */
public class MainActivity extends AppCompatActivity{

    public Toolbar toolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DrawerFragment drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawerFragment);
        drawerFragment.setUp(R.id.drawerFragment, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);

        RecentFragment recentFragment = new RecentFragment();
        fragmentManager = getFragmentManager();
        setMainFragment(recentFragment);
    }

    private void setMainFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, fragment)
                .commit();
    }
}
