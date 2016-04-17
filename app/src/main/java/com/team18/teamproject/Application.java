package com.team18.teamproject;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.team18.teamproject.objects.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 03/04/2016.
 */
public class Application extends android.app.Application {

    private static Application sInstance;

    /**
     * Globally accessible list of favourite recipes to be cached.
     */
    private static List<Recipe> favourites = new ArrayList<>();
    // TODO getting, setting, and state saving.

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Application getsInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public static void connectionError(View view) {
        Snackbar.make(view, "Connection Error", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public static void responseError(View view) {
        Snackbar.make(view, "Cannot Fetch Recipes", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
