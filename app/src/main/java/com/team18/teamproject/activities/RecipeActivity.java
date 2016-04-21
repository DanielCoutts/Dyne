package com.team18.teamproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.team18.teamproject.Application;
import com.team18.teamproject.fragments.IngredientsFragment;
import com.team18.teamproject.fragments.MethodFragment;
import com.team18.teamproject.fragments.NutritionFragment;
import com.team18.teamproject.R;
import com.team18.teamproject.network.RecipeCompleter;
import com.team18.teamproject.pojo.Recipe;

/**
 * The recipe activity contains three tabs: ingredients, method, and nutrition.
 * Data is different based on the selected recipe, so it is loaded in when the activity is launched.
 */
public class RecipeActivity extends AppCompatActivity {

    /**
     * The recipe activity toolbar. This contains a back button, a title, a favourite button, and a facebook share button.
     */
    private Toolbar toolbar;

    private Menu menu;

    private Recipe recipe;

    private TabLayout tabs;
    private ViewPager pager;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_recipe);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pager = (ViewPager) findViewById(R.id.recipe_pager);

        RecipeCompleter.completeCurrentRecipe(pager);
        recipe = Application.getCurrentRecipe();

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(recipe.getName());

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        pager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabs = (TabLayout) findViewById(R.id.recipe_tabs);
        if (tabs != null) {
            tabs.setupWithViewPager(pager);
        }

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

    /**
     * Test if another app is install on the device.
     * <p/>
     * Created by Alex 20/04/2016
     *
     * @param packageName Name of the package being tested for.
     */
    private boolean isAppInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Build intent for camera application and start camera activity.
     * <p/>
     * Created by Alex.
     */
    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    /**
     * Build a Facebook SharePhotoContent to share on Facebook.
     * <p/>
     * Create Alex
     *
     * @param bitmap Photo returned by camera activity
     */
    private void sharePhoto(Bitmap bitmap) {
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .setCaption("@string/share_message")
                .build();
        SharePhotoContent photoContent = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        shareDialog.show(photoContent);
    }

    private void shareError() {
        Context context = getApplicationContext();
        CharSequence text = "An error has occurred.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    /*
     * Adds the menu options
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        setHeartFill();
        return true;
    }

    @Override
    /*
     * Listener for the actionbar menu
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            // Back button in action bar has the same behaviour as the system back button
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_facebook:
                if (isAppInstalled("com.facebook.katana")) {
                    takePhoto();
                } else {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.facebook.katana")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.facebook.katana")));
                    }
                }
                return true;

            case R.id.action_favourite:
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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            if (ShareDialog.canShow(SharePhotoContent.class)) {
                sharePhoto(bitmap);
            }
        } else if (resultCode == RESULT_CANCELED) {

        } else {
            shareError();
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
