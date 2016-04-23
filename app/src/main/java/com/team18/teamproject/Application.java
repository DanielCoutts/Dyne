package com.team18.teamproject;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.team18.teamproject.pojo.Recipe;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Daniel on 03/04/2016.
 */
public class Application extends android.app.Application {

    private static Application sInstance;

    /**
     * Global vegetarian preference.
     */
    private static boolean vegetarian = false;
    /**
     * Global vegan preference.
     */
    private static boolean vegan = false;
    /**
     * Global gluten-free preference.
     */
    private static boolean glutenFree = false;

    /**
     * Globally accessible map of favourite recipes to be cached.
     */
    private static Map<Integer, Recipe> favourites = new TreeMap<>();
    // TODO getting, setting, and state saving.

    private static Recipe currentRecipe;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        String serialized = PreferenceManager.getDefaultSharedPreferences(getAppContext()).getString("STATE", null);

        if(serialized != null) {
            Gson gson = new Gson();
            MyWrapper wrapper = gson.fromJson(serialized, MyWrapper.class);
            vegetarian = wrapper.isVegetarian();
            vegan = wrapper.isVegan();
            glutenFree = wrapper.isGlutenFree();
            favourites = wrapper.getFavourites();
        }
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

    public static String boolToString(Boolean bool) {
        if (bool) {
            return "1";
        } else {
            return "0";
        }
    }

    public static Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    public static Recipe completeCurrentRecipe() {
        // TODO Maybe delete
//        RecipeCompleter.completeCurrentRecipe();
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

    public void saveState() {
        Gson gson = new Gson();
        MyWrapper wrapper = new MyWrapper(vegetarian, vegan, glutenFree, favourites);
        String serialized = gson.toJson(wrapper);
        PreferenceManager.getDefaultSharedPreferences(getAppContext()).edit().putString("STATE", serialized).commit();
    }

    public class MyWrapper {

        private boolean vegetarian = false;

        private boolean vegan = false;

        private boolean glutenFree = false;

        private Map<Integer, Recipe> favourites;

        public MyWrapper(boolean vegetarian, boolean vegan, boolean glutenFree, Map<Integer, Recipe> favourites) {
            this.vegetarian = vegetarian;
            this.vegan = vegan;
            this.glutenFree = glutenFree;
            this.favourites = favourites;
        }

        public Map<Integer, Recipe> getFavourites() {
            return favourites;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public boolean isVegan() {
            return vegan;
        }

        public boolean isGlutenFree() {
            return glutenFree;
        }
    }
}
