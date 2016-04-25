package com.team18.teamproject.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.team18.teamproject.R;

/**
 * Fragment containing 3 tabs linked to a viewpager containing three fragments.
 *
 * Created by Daniel.
 */
public class HomeFragment extends Fragment {

    private TabLayout tabs;
    private ViewPager pager;

    /**
     * Empty public constructor.
     */
    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set up ViewPager.
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new CustomAdapter(getChildFragmentManager(), getActivity().getApplicationContext()));

        // Set up TabLayout.
        tabs = (TabLayout) view.findViewById(R.id.home_tabs);
        tabs.setupWithViewPager(pager);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Listener for tabs.
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
    public void onSaveInstanceState(Bundle outState) {
        // Empty override of onSaveInstanceState prevents views disappearing when using nested fragments.
    }

    /**
     * Custom FragmentPagerAdapter.
     */
    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {"Featured", "Favourites", "Categories"};

        public CustomAdapter(FragmentManager fragMan, Context applicationContext) {
            super(fragMan);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FeaturedFragment();
                case 1:
                    return new FavouritesFragment();
                case 2:
                    return new CategoriesFragment();
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
