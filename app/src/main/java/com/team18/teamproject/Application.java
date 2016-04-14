package com.team18.teamproject;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Daniel on 03/04/2016.
 */
public class Application extends android.app.Application {

    private static Application sInstance;

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
}
