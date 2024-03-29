package com.team18.teamproject.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom Volley StringRequest subclass that takes POST parameters in the constructor.
 *
 * Created by Daniel.
 */
public class CustomStringRequest extends StringRequest {

    private Map<String,String> bodyParams;

    public CustomStringRequest(int method, String url, Map<String,String> bodyParams, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        if (bodyParams != null) {
            this.bodyParams = bodyParams;
        }
        else {
            this.bodyParams = new HashMap<>();
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return bodyParams;
    }
}
