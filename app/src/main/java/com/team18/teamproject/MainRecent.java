package com.team18.teamproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainRecent extends AppCompatActivity {


        //onCreate implementation for Navigation Drawer
        private static String TAG = MainRecent.class.getSimpleName();

        ListView mDrawerList;
        RelativeLayout mDrawerPane;
        private ActionBarDrawerToggle mDrawerToggle;
        private DrawerLayout mDrawerLayout;

        ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_recent);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            mNavItems.add(new NavItem("Home", "Home", R.drawable.ic_home_black_24xhdpi));
            mNavItems.add(new NavItem("All Recipes", "Change your preferences", R.drawable.ic_restaurant_menu_black_24xhdpi));
            mNavItems.add(new NavItem("Favourites", "Get to know about us", R.drawable.ic_star_rate_black_18xhdpi));
            mNavItems.add(new NavItem("Shopping List", "Get to know about us", R.drawable.ic_shopping_cart_black_18xhdpi));
            mNavItems.add(new NavItem("Timer", "Get to know about us", R.drawable.ic_timer_black_18xhdpi));
            mNavItems.add(new NavItem("Essentials Guide", "Get to know about us", R.drawable.ic_move_to_inbox_black_18xhdpi));
            mNavItems.add(new NavItem("Glossary", "Get to know about us", R.drawable.ic_local_offer_black_18xhdpi));
            mNavItems.add(new NavItem("Settings", "Get to know about us", R.drawable.ic_settings_black_18xhdpi));

            // DrawerLayout
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

            // Populate the Navigtion Drawer with options
            mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
            mDrawerList = (ListView) findViewById(R.id.navList);
            DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
            mDrawerList.setAdapter(adapter);

            // Drawer Item click listeners
            mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectItemFromDrawer(position);
                }
            });
        }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setHeights();
    }

    private void setHeights() {
        ImageButton mostRecent = (ImageButton) findViewById(R.id.most_recent);
        int width = mostRecent.getWidth();

        ViewGroup.LayoutParams params = mostRecent.getLayoutParams();
        params.height = (width/3)*2;
        mostRecent.requestLayout();

        squareButton( (ImageButton) findViewById(R.id.button_1a) );
        squareButton( (ImageButton) findViewById(R.id.button_1b) );
        squareButton((ImageButton) findViewById(R.id.button_2a));
        squareButton((ImageButton) findViewById(R.id.button_2b));
    }

    private void squareButton(ImageButton tile) {
        int width = tile.getWidth();

        ViewGroup.LayoutParams params = tile.getLayoutParams();
        params.height = width;
        tile.requestLayout();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, recipeTemplate.class);
        startActivity(intent);
    }

    //Inner class for Navigation Drawer Items
    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }

    //Inner class for Navigation Drawer BaseAdapter
    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText( mNavItems.get(position).mTitle );
            subtitleView.setText( mNavItems.get(position).mSubtitle );
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

    /*
* Called when a particular item from the navigation drawer
* is selected.
* */
    private void selectItemFromDrawer(int position) {
        Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.toolbar, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }
}
