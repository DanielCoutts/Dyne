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


public class HomeFragment extends Fragment {

    TabLayout tabs;
    ViewPager pager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new CustomAdapter(getChildFragmentManager(), getActivity().getApplicationContext()));

        tabs = (TabLayout) view.findViewById(R.id.home_tabs);
        tabs.setupWithViewPager(pager);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

    /*
     * Advised to override this method in order to prevent views disappearing in nested fragments.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {"Featured", "Favourites", "Categories"};

        public CustomAdapter(FragmentManager fragMan, Context applicationContext) {
            super(fragMan);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
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
