package com.team18.teamproject.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.team18.teamproject.Application;

/**
 * Created by Daniel on 02/04/2016.
 *
 */
public class VolleySingleton {

    private static VolleySingleton sInstance = null;

    private RequestQueue mRequestQueue;

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(Application.getAppContext());
    }

    public static VolleySingleton getInstance() {

        if (sInstance == null) {
            sInstance = new VolleySingleton();
        }

        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
