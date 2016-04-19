package com.team18.teamproject;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.team18.teamproject.objects.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 03/04/2016.
 */
public class Application extends android.app.Application {

    private static Application sInstance;

    /** Global vegetarian preference. */
    private static boolean vegetarian;
    /** Global vegan preference. */
    private static boolean vegan;
    /** Global gluten-free preference. */
    private static boolean glutenFree;

    /**
     * Globally accessible map of favourite recipes to be cached.
     */
    private static Map<Integer,Recipe> favourites = new HashMap<>();
    // TODO getting, setting, and state saving.

    private static Recipe currentRecipe;

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

    public static Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    public static void setCurrentRecipe(Recipe currentRecipe) {
        Application.currentRecipe = currentRecipe;
    }

    public static Map<Integer, Recipe> getFavourites() {
        return favourites;
    }

    public static boolean isVegetarian() {
        return vegetarian;
    }

    public static void setVegetarian(boolean vegetarian) {
        Application.vegetarian = vegetarian;
    }

    public static boolean isVegan() {
        return vegan;
    }

    public static void setVegan(boolean vegan) {
        Application.vegan = vegan;
    }

    public static boolean isGlutenFree() {
        return glutenFree;
    }

    public static void setGlutenFree(boolean glutenFree) {
        Application.glutenFree = glutenFree;
    }
}
